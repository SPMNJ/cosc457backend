package dev.teamswy.backend.entity;

import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import dev.teamswy.backend.ChapterMember;

@Entity
public class Member {
    @EmbeddedId
    private ChapterMember chapterMember;

    @ManyToOne
    @MapsId("chapterId")
    @JoinColumn(name = "chapterId")
    private Chapter chapter;
    private String name;
    private String email;
    private String phone;
    private String status;
    private LocalDate inductionDate;
    private LocalDate intiationDate;

    public Member() {
    }

    public Member(Chapter chapter, int rollNo, String name, String email, String phone, String status,
            LocalDate inductionDate, LocalDate intiationDate) {
        this.chapter = chapter;
        this.chapterMember = new ChapterMember(chapter.getChapterId(), rollNo);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.inductionDate = inductionDate;
        this.intiationDate = intiationDate;
    }

    public ChapterMember getChapterMember() {
        return chapterMember;
    }

    public void setChapterMember(ChapterMember chapterMember) {
        this.chapterMember = chapterMember;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getInductionDate() {
        return inductionDate;
    }

    public void setInductionDate(LocalDate inductionDate) {
        this.inductionDate = inductionDate;
    }

    public LocalDate getIntiationDate() {
        return intiationDate;
    }

    public void setIntiationDate(LocalDate intiationDate) {
        this.intiationDate = intiationDate;
    }

    @Override
    public String toString() {
        return "Member [chapterMember=" + chapterMember + ", chapter=" + chapter + ", name=" + name
                + ", email=" + email + ", phone=" + phone + ", status=" + status
                + ", inductionDate=" + inductionDate + ", intiationDate=" + intiationDate + "]";
    }
}
