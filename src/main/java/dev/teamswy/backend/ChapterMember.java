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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + chapterId;
        result = prime * result + rollNo;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChapterMember other = (ChapterMember) obj;
        if (chapterId != other.chapterId)
            return false;
        if (rollNo != other.rollNo)
            return false;
        return true;
    }
}
