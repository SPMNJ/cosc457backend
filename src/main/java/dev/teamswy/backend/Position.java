package dev.teamswy.backend;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Position {

    @Id
    private int rank;
    private String title;
    private boolean exeuctiveBoard;

    @OneToOne
    @JoinColumns({
        @JoinColumn(name = "chapterID"),
        @JoinColumn(name = "rollNo")})
    private Member member;
    private LocalDate startDate;

    public Position() {
    }

    public Position(int rank, String title, boolean exeuctiveBoard, Chapter chapter, Member member, LocalDate startDate) {
        this.rank = rank;
        this.title = title;
        this.exeuctiveBoard = exeuctiveBoard;
        this.member = member;
        this.startDate = startDate;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isExeuctiveBoard() {
        return exeuctiveBoard;
    }

    public void setExeuctiveBoard(boolean exeuctiveBoard) {
        this.exeuctiveBoard = exeuctiveBoard;
    }   
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }   

    @Override
    public String toString() {
        return "Position{" + "rank=" + rank + ", title=" + title + ", exeuctiveBoard=" + exeuctiveBoard + ", member=" + member + ", startDate=" + startDate + '}';
    }

}
