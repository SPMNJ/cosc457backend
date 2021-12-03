package dev.teamswy.backend.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;

@Entity
public class Role {

    @Id
    private int rank;
    private String title;
    private boolean exeuctiveBoard;

    @OneToOne
    @JoinColumns({
        @JoinColumn(name = "chapterId"),
        @JoinColumn(name = "rollNo")})
    private Member member;
    private LocalDate startDate;

    public Role() {
    }

    public Role(int rank, String title, boolean exeuctiveBoard, Member member, LocalDate startDate) {
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

}
