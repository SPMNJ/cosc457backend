package dev.teamswy.backend;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ChapterMember implements Serializable {

    @Column(name = "chapterId", nullable = false)
    int chapterId;
    @Column(name = "rollNo", nullable = false)
    int rollNo;

    public ChapterMember() {
    }

    public ChapterMember(int chapterId, int rollNo) {
        this.chapterId = chapterId;
        this.rollNo = rollNo;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    @Override
    public String toString() {
        return "ChapterMember [chapterId=" + chapterId + ", rollNo=" + rollNo + "]";
    }

}
