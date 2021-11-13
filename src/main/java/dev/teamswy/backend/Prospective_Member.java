package dev.teamswy.backend;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class Prospective_Member {

    private String name;
    private String email;
    @Id
    private String phone;
    private String bidStatus;

    @ManyToOne
    @JoinTable(name = "Chapter_Prospects",
           joinColumns = @JoinColumn(name = "chapterId"), 
            inverseJoinColumns = @JoinColumn(name = "phone"))
    private Chapter bidchapter;

    public Prospective_Member() {
    }

    public Prospective_Member(String name, String email, String phone, String bidStatus) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bidStatus = bidStatus;
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

    @Override
    public String toString() {
        return "Prospective_Member{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", bidStatus='" + bidStatus + '\'' +
                '}';
    }

}
