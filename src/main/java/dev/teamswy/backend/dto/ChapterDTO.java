package dev.teamswy.backend.dto;

import java.time.LocalDate;

public class ChapterDTO {
    private String chapterName;
    private LocalDate charterDate;
    private String chapterStatus;
    private Integer chapterId;
    
    public ChapterDTO() {
    }

    public ChapterDTO(String chapterName) {
        this.chapterName = chapterName;
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

    public Integer getChapterId() {
        return chapterId;
    }    

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }
}
