package dev.teamswy.backend;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Chapter {
    
    @Id
    private int chapterId;

    private String chapterName;
    private LocalDate charterDate;
    private String chapterStatus;
    @ManyToOne
    @JoinColumn(name = "letters")
    private FraternityHQ chapterHQ;
    private int chapterMembers;

    public Chapter(int chapterId, String chapterName, LocalDate charterDate, String chapterStatus, FraternityHQ chapterHQ, int chapterMembers) {
        this.chapterId = chapterId;
        this.chapterName = chapterName;
        this.charterDate = charterDate;
        this.chapterStatus = chapterStatus;
        this.chapterHQ = chapterHQ;
        this.chapterMembers = chapterMembers;
    }

    public Chapter() {
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public LocalDate getCharterDate() {
        return charterDate;
    }

    public void setCharterDate(LocalDate charterDate) {
        this.charterDate = charterDate;
    }

    public String getChapterStatus() {
        return chapterStatus;
    }

    public void setChapterStatus(String chapterStatus) {
        this.chapterStatus = chapterStatus;
    }

    public FraternityHQ getChapterHQ() {
        return chapterHQ;
    }

    public void setChapterHQ(FraternityHQ chapterHQ) {
        this.chapterHQ = chapterHQ;
    }

    public int getChapterMembers() {
        return chapterMembers;
    }

    public void setChapterMembers(int chapterMembers) {
        this.chapterMembers = chapterMembers;
    }

    @Override
    public String toString() {
        return "Chapter [chapterId=" + chapterId + ", chapterName=" + chapterName + ", charterDate=" + charterDate
                + ", chapterStatus=" + chapterStatus + ", chapterHQ=" + chapterHQ + ", chapterMembers=" + chapterMembers
                + "]";
    }

}
