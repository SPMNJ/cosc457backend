package dev.teamswy.backend.dto;

public class RoleDTO {

    private long rollNo;
    private int rankID;
    private String title;
    private boolean exeuctiveBoard;

    public RoleDTO() {
    }

    public RoleDTO(long rollNo, int rankID, String title, boolean exeuctiveBoard) {
        this.rollNo = rollNo;
        this.rankID = rankID;
        this.title = title;
        this.exeuctiveBoard = exeuctiveBoard;
    }

    public long getRollNo() {
        return rollNo;
    }

    public void setRollNo(long rollNo) {
        this.rollNo = rollNo;
    }

    public int getRankID() {
        return rankID;
    }

    public void setRankID(int rankID) {
        this.rankID = rankID;
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
    

}
