package dev.teamswy.backend;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.teamswy.backend.dto.ChapterDTO;
import dev.teamswy.backend.dto.MemberDTO;
import dev.teamswy.backend.dto.ProspectiveDTO;
import dev.teamswy.backend.dto.RoleDTO;
import dev.teamswy.backend.dto.StatementDTO;
import dev.teamswy.backend.entity.Chapter;
import dev.teamswy.backend.entity.FraternityHQ;
import dev.teamswy.backend.entity.Member;
import dev.teamswy.backend.entity.Prospective_Member;
import dev.teamswy.backend.entity.Role;
import dev.teamswy.backend.entity.Statement;
import dev.teamswy.backend.repository.IChapterRepository;
import dev.teamswy.backend.repository.IFraternityHQRepository;
import dev.teamswy.backend.repository.IMemberRepository;
import dev.teamswy.backend.repository.IProspectRepository;
import dev.teamswy.backend.repository.IRoleRepository;
import dev.teamswy.backend.repository.IStatementRepository;
import net.bytebuddy.asm.Advice.Local;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class MainControlller {
    @Autowired
    private IChapterRepository chapterRepository;

    @Autowired
    private IFraternityHQRepository fraternityHQRepository;

    @Autowired
    private IMemberRepository memberRepository;

    @Autowired
    private IStatementRepository statementRepository;

    @Autowired
    private IProspectRepository prospectRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @GetMapping(value = "/")
    @ResponseBody
    public ResponseEntity<Void> sendViaResponseEntity() {
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping(value = "/chapter")
    public ResponseEntity<Iterable<Chapter>> getAllChapters() {
        Iterable<Chapter> chapters = chapterRepository.findAll();
        return new ResponseEntity<>(chapters, HttpStatus.OK);
    }

    @PostMapping("/chapter/create")
    public ResponseEntity<Chapter> createChapter(@RequestBody ChapterDTO chapter) {
        // Check for existing chapter name
        Optional<Chapter> existingChapter = chapterRepository.findByChapterName(chapter.getChapterName());
        if (existingChapter.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Chapter newChapter = new Chapter();
        newChapter.setChapterName(chapter.getChapterName());
        Optional<FraternityHQ> fHq = fraternityHQRepository.findById("ΦΚΨ");
        if (fHq.isPresent()) {
            FraternityHQ fraternityHQ = fHq.get();
            newChapter.setChapterHQ(fraternityHQ);
            fraternityHQ.setChapters(fraternityHQ.getChapters() + 1);
            newChapter.setChapterId(fraternityHQ.getChapters());
            newChapter.setCharterDate(LocalDate.now());
            newChapter.setChapterStatus("Colony");
            newChapter.setChapterMembers(0);
            chapterRepository.save(newChapter);
            fraternityHQRepository.save(fraternityHQ);
            return new ResponseEntity<>(newChapter, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/chapter/update")
    public ResponseEntity<Chapter> updateChapter(@RequestBody ChapterDTO chapter) {
        Optional<Chapter> chapterToUpdate = chapterRepository.findById(chapter.getChapterId());
        Optional<Chapter> existingChapter = chapterRepository.findByChapterName(chapter.getChapterName());
        if (existingChapter.isPresent() && existingChapter.get().getChapterId() != chapter.getChapterId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if (chapterToUpdate.isPresent()) {
            Chapter updatedChapter = chapterToUpdate.get();
            updatedChapter.setChapterName(chapter.getChapterName());
            updatedChapter.setCharterDate(chapter.getCharterDate());
            updatedChapter.setChapterStatus(chapter.getChapterStatus());
            if (updatedChapter.getChapterStatus().equals("Suspended")) {
                prospectRepository.deleteAll(prospectRepository.findByChapter(updatedChapter));
                roleRepository.deleteAll(roleRepository.findByChapter(updatedChapter));
                statementRepository.deleteAll(statementRepository.findByChapter(updatedChapter));
                for (Member member : memberRepository.findByChapter(updatedChapter)) {
                    if (!member.getStatus().equals("Alumnus")) {
                        member.setStatus("Suspended");
                        memberRepository.save(member);
                    }
                }
            }
            chapterRepository.save(updatedChapter);
            return new ResponseEntity<>(chapterToUpdate.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/chapter/{chapterId}")
    public ResponseEntity<Chapter> getChapterById(@PathVariable(value = "chapterId") int chapterId) {
        Optional<Chapter> chapter = chapterRepository.findById(chapterId);
        if (chapter.isPresent()) {
            return new ResponseEntity<>(chapter.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/chapter/{chapterId}/members")
    public ResponseEntity<Iterable<Member>> getChapterMembers(@PathVariable(value = "chapterId") int chapterId) {
        Optional<Chapter> chapter = chapterRepository.findById(chapterId);
        if (chapter.isPresent()) {
            Iterable<Member> members = memberRepository.findByChapter(chapter.get());
            return new ResponseEntity<>(members, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/chapter/{chapterId}/members/{rollNo}")
    public ResponseEntity<Member> getChapterMember(@PathVariable(value = "chapterId") int chapterId,
            @PathVariable(value = "rollNo") int rollNo) {
        Optional<Chapter> chapter = chapterRepository.findById(chapterId);
        if (chapter.isPresent()) {
            Optional<Member> member = memberRepository
                    .findById(new ChapterMember(chapter.get().getChapterId(), rollNo));
            if (member.isPresent()) {
                return new ResponseEntity<>(member.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/chapter/{chapterId}/members/update")
    public ResponseEntity<Member> updateChapterMember(@PathVariable(value = "chapterId") int chapterId,
            @RequestBody MemberDTO member) {
        Optional<Chapter> chapter = chapterRepository.findById(chapterId);
        if (chapter.isPresent()) {
            Optional<Member> memberToUpdate = memberRepository
                    .findById(new ChapterMember(chapter.get().getChapterId(), member.getRollNo()));
            if (memberToUpdate.isPresent()) {
                Member updatedMember = memberToUpdate.get();
                updatedMember.setName(member.getName());
                updatedMember.setEmail(member.getEmail());
                updatedMember.setPhone(member.getPhone());
                updatedMember.setStatus(member.getStatus());
                updatedMember.setInductionDate(member.getInductionDate());
                updatedMember.setInitiationDate(member.getInitationDate());
                if (updatedMember.getStatus().equals("Active") && updatedMember.getInitiationDate() == null) {
                    updatedMember.setInitiationDate(LocalDate.now());
                }
                memberRepository.save(updatedMember);
                return new ResponseEntity<>(updatedMember, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/chapter/{chapterId}/prospects")
    public ResponseEntity<Iterable<Prospective_Member>> getChapterProspects(
            @PathVariable(value = "chapterId") int chapterId) {
        Optional<Chapter> chapter = chapterRepository.findById(chapterId);
        if (chapter.isPresent()) {
            Iterable<Prospective_Member> prospects = prospectRepository.findByChapter(chapter.get());
            return new ResponseEntity<>(prospects, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/chapter/{chapterId}/prospects/{prospectId}")
    public ResponseEntity<Prospective_Member> getChapterProspectById(
            @PathVariable(value = "chapterId") int chapterId,
            @PathVariable(value = "prospectId") String prospectId) {
        Optional<Chapter> chapter = chapterRepository.findById(chapterId);
        if (chapter.isPresent()) {
            Optional<Prospective_Member> prospect = prospectRepository.findById(prospectId);
            if (prospect.isPresent()) {
                return new ResponseEntity<>(prospect.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/chapter/{chapterId}/prospects/add")
    public ResponseEntity<Prospective_Member> addProspect(@PathVariable(value = "chapterId") int chapterId,
            @RequestBody ProspectiveDTO prospect) {
        Optional<Chapter> chapter = chapterRepository.findById(chapterId);
        if (chapter.isPresent() && !chapter.get().getChapterStatus().equals("Suspended")) {
            if (prospect.getPhone() != null && prospect.getPhone().length() > 0) {
                Optional<Prospective_Member> prospectExists = prospectRepository.findById(prospect.getPhone());
                if (prospectExists.isPresent()) {
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            }
            Prospective_Member prospectMember = new Prospective_Member();
            prospectMember.setName(prospect.getName());
            prospectMember.setEmail(prospect.getEmail());
            prospectMember.setPhone(prospect.getPhone());
            prospectMember.setBidStatus(prospect.getBidStatus());
            prospectMember.setBidchapter(chapter.get());
            prospectRepository.save(prospectMember);
            return new ResponseEntity<>(prospectMember, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/chapter/{chapterId}/prospects/update")
    public ResponseEntity<Prospective_Member> updateProspect(@PathVariable(value = "chapterId") int chapterId,
            @RequestBody ProspectiveDTO prospect) {
        Optional<Chapter> chapter = chapterRepository.findById(chapterId);
        if (chapter.isPresent()) {
            Optional<Prospective_Member> prospectMember = prospectRepository.findById(prospect.getPhone());
            if (prospectMember.isPresent()) {
                Prospective_Member updatedProspect = prospectMember.get();
                updatedProspect.setName(prospect.getName());
                updatedProspect.setEmail(prospect.getEmail());
                updatedProspect.setPhone(prospect.getPhone());
                updatedProspect.setBidStatus(prospect.getBidStatus());
                if (prospect.getBidStatus().equals("Remove")) {
                    prospectRepository.delete(updatedProspect);
                } else {
                    prospectRepository.save(updatedProspect);
                }
                return new ResponseEntity<>(updatedProspect, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/chapter/{chapterId}/extendbid")
    public ResponseEntity<Member> extendBid(@PathVariable(value = "chapterId") int chapterId,
            @RequestBody ProspectiveDTO extendBid) {
        Optional<Chapter> chapter = chapterRepository.findById(chapterId);
        Optional<Prospective_Member> prospect = prospectRepository.findById(extendBid.getPhone());
        if (chapter.isPresent() && prospect.isPresent()) {
            Prospective_Member prospectMember = prospect.get();
            prospectRepository.delete(prospectMember);
            Member member = new Member();
            Chapter currentChapter = chapter.get();
            member.setName(prospectMember.getName());
            member.setEmail(prospectMember.getEmail());
            member.setPhone(prospectMember.getPhone());
            member.setChapter(currentChapter);
            member.setStatus("New Member");
            member.setInductionDate(LocalDate.now());
            member.setChapterMember(
                    new ChapterMember(currentChapter.getChapterId(), currentChapter.getChapterMembers() + 1));
            currentChapter.setChapterMembers(currentChapter.getChapterMembers() + 1);
            memberRepository.save(member);
            chapterRepository.save(currentChapter);
            return new ResponseEntity<>(member, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/chapter/{chapterId}/roles")
    public ResponseEntity<Iterable<Role>> getChapterRoles(@PathVariable(value = "chapterId") int chapterId) {
        Optional<Chapter> chapter = chapterRepository.findById(chapterId);
        if (chapter.isPresent()) {
            Iterable<Role> roles = roleRepository.findByChapter(chapter.get());
            return new ResponseEntity<>(roles, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/chapter/{chapterId}/roles/{roleId}")
    public ResponseEntity<Role> getChapterRoleById(@PathVariable(value = "chapterId") int chapterId,
            @PathVariable(value = "roleId") int roleId) {
        Optional<Chapter> chapter = chapterRepository.findById(chapterId);
        if (chapter.isPresent()) {
            Optional<Role> role = roleRepository.findById(roleId);
            if (role.isPresent()) {
                return new ResponseEntity<>(role.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/chapter/{chapterId}/roles/add")
    public ResponseEntity<Role> addRole(@PathVariable(value = "chapterId") int chapterId, @RequestBody RoleDTO role) {
        Optional<Chapter> chapter = chapterRepository.findById(chapterId);
        if (chapter.isPresent()) {
            Optional<Member> member = memberRepository
                    .findById(new ChapterMember(chapter.get().getChapterId(), role.getRollNo()));
            if (member.isPresent()) {
                Role newRole = new Role();
                newRole.setMember(member.get());
                newRole.setTitle(role.getTitle());
                newRole.setEboard(role.isEboard());
                if (role.getStartDate() == null) {
                    newRole.setStartDate(LocalDate.now());
                } else {
                    newRole.setStartDate(role.getStartDate());
                }
                roleRepository.save(newRole);
                return new ResponseEntity<>(newRole, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/chapter/{chapterId}/roles/update")
    public ResponseEntity<Role> updateRole(@PathVariable(value = "chapterId") int chapterId,
            @RequestBody RoleDTO role) {
        Optional<Chapter> chapter = chapterRepository.findById(chapterId);
        if (chapter.isPresent()) {
            Optional<Role> roleExists = roleRepository.findById(role.getRankId());
            if (roleExists.isPresent()) {
                Role updatedRole = roleExists.get();
                if (role.getRollNo() != 0) {
                    Optional<Member> member = memberRepository
                            .findById(new ChapterMember(chapter.get().getChapterId(), role.getRollNo()));
                    if (member.isPresent()) {
                        updatedRole.setMember(member.get());
                    }
                }
                updatedRole.setTitle(role.getTitle());
                updatedRole.setEboard(role.isEboard());
                updatedRole.setStartDate(role.getStartDate());
                roleRepository.save(updatedRole);
                return new ResponseEntity<>(updatedRole, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/chapter/{chapterId}/roles/delete")
    public ResponseEntity<Role> deleteRole(@PathVariable(value = "chapterId") int chapterId,
            @RequestBody RoleDTO role) {
        Optional<Chapter> chapter = chapterRepository.findById(chapterId);
        if (chapter.isPresent()) {
            Optional<Role> roleExists = roleRepository.findById(role.getRankId());
            if (roleExists.isPresent()) {
                Role deletedRole = roleExists.get();
                roleRepository.delete(deletedRole);
                return new ResponseEntity<>(deletedRole, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/chapter/{chapterId}/statements")
    public ResponseEntity<Iterable<Statement>> getChapterStatements(@PathVariable(value = "chapterId") int chapterId) {
        Optional<Chapter> chapter = chapterRepository.findById(chapterId);
        if (chapter.isPresent()) {
            Iterable<Statement> statements = statementRepository.findByChapter(chapter.get());
            return new ResponseEntity<>(statements, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/chapter/{chapterId}/statements/add")
    public ResponseEntity<Statement> addStatement(@PathVariable(value = "chapterId") int chapterId,
            @RequestBody StatementDTO statement) {
        Optional<Chapter> chapter = chapterRepository.findById(chapterId);
        if (chapter.isPresent()) {
            Statement newStatement = new Statement();
            newStatement.setChapter(chapter.get());
            newStatement.setAmount(statement.getAmount());
            if (statement.getDate() == null) {
                newStatement.setDate(LocalDate.now());
            } else {
                newStatement.setDate(statement.getDate());
            }
            newStatement.setDescription(statement.getDesc());
            newStatement.setStatementType(statement.getType());
            statementRepository.save(newStatement);
            return new ResponseEntity<>(newStatement, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
