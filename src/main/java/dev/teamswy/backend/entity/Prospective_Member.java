package dev.teamswy.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "prospectivemember")
public class Prospective_Member {

    private String name;
    private String email;
    @Id
    private String phone;
    private String bidStatus;

    @ManyToOne
    @JoinTable(name = "chapterprospects",
    joinColumns = @JoinColumn(name = "phone"), 
            inverseJoinColumns = @JoinColumn(name = "idchapter"))
    private Chapter bidchapter;

    public Prospective_Member() {
    }

    public Prospective_Member(String name, String email, String phone, String bidStatus, Chapter bidchapter) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bidStatus = bidStatus;
        this.bidchapter = bidchapter;
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

    public String getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(String bidStatus) {
        this.bidStatus = bidStatus;
    }

    public Chapter getBidchapter() {
        return bidchapter;
    }

    public void setBidchapter(Chapter bidchapter) {
        this.bidchapter = bidchapter;
    }

}
