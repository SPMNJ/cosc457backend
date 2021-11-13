package dev.teamswy.backend;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FraternityHQ {
    @Id
    private String letters;
    private String name;
    private LocalDate date;
    private String address;
    private String members;

    public FraternityHQ() {
    }

    public FraternityHQ(String letters, String name, LocalDate date, String address, String members) {
        this.letters = letters;
        this.name = name;
        this.date = date;
        this.address = address;
        this.members = members;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Fraternity_HQ{" +
                "letters='" + letters + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", address='" + address + '\'' +
                ", members='" + members + '\'' +
                '}';
    }

}
