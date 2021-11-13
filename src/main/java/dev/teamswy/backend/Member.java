package dev.teamswy.backend;

import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Member {
    @EmbeddedId
    private ChapterMember chapterMember;

    private String name;
    private String email;
    private String phone;
    private String address;
    private String status;
    private LocalDate inductionDate;
    private LocalDate intiationDate;

    public Member() {
    }

    public Member(ChapterMember chapterMember, String name, String email, String phone, String address, String status, LocalDate inductionDate, LocalDate intiationDate) {
        this.chapterMember = chapterMember;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        return "Member{" +
                "chapterMember=" + chapterMember +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", inductionDate=" + inductionDate +
                ", intiationDate=" + intiationDate +
                '}';
    }
}
