package dev.teamswy.backend.entity;

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
    private Integer chapters;

    public FraternityHQ() {
    }

    public FraternityHQ(String letters, String name, LocalDate date, String address, Integer chapters) {
        this.letters = letters;
        this.name = name;
        this.date = date;
        this.address = address;
        this.chapters = chapters;
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

    public Integer getChapters() {
        return chapters;
    }

    public void setChapters(Integer i) {
        this.chapters = i;
    }

    @Override
    public String toString() {
        return "Fraternity_HQ{" +
                "letters='" + letters + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", address='" + address + '\'' +
                ", members='" + chapters + '\'' +
                '}';
    }

}
