package dev.teamswy.backend.dto;

import java.time.LocalDate;

public class RoleDTO {

    private int rollNo;
    private int rankId;
    private String title;
    private LocalDate startDate;
    private boolean eboard;

    public RoleDTO() {
    }

    public RoleDTO(int rollNo, int rankId, String title, LocalDate startDate, boolean eboard) {
        this.rollNo = rollNo;
        this.rankId = rankId;
        this.title = title;
        this.startDate = startDate;
        this.eboard = eboard;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isEboard() {
        return eboard;
    }

    public void setEboard(boolean eboard) {
        this.eboard = eboard;
    }
}
