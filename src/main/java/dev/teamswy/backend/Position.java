package dev.teamswy.backend;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Position {
    
    @Id
    private int rank;
    private String title;
    private boolean exeuctiveBoard;

    public Position() {
    }

    public Position(int rank, String title, boolean exeuctiveBoard) {
        this.rank = rank;
        this.title = title;
        this.exeuctiveBoard = exeuctiveBoard;
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

    @Override
    public String toString() {
        return "Position{" + "rank=" + rank + ", title=" + title + ", exeuctiveBoard=" + exeuctiveBoard + '}';
    }

}
