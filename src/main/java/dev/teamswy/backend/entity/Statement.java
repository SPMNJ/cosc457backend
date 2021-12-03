package dev.teamswy.backend.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "statements")
public class Statement {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long ref_id;
    private String statementType;
    private LocalDate date;
    private String description;
    private int amount;
    @ManyToOne
    @JoinTable(name = "chapterstatements",
           joinColumns = @JoinColumn(name = "chapterId"), 
            inverseJoinColumns = @JoinColumn(name = "refid"))
    private Chapter chapter;

    public Statement() {
    }

    public Statement(String statementType, LocalDate date, String description, int amount, Chapter chapter) {
        this.statementType = statementType;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.chapter = chapter;
    }

    public Long getRef_id() {
        return ref_id;
    }

    public void setRef_id(Long ref_id) {
        this.ref_id = ref_id;
    }

    public String getStatementType() {
        return statementType;
    }

    public void setStatementType(String statementType) {
        this.statementType = statementType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    @Override
    public String toString() {
        return "Statement{" + "ref_id=" + ref_id + ", statement=" + statementType + ", date=" + date + ", description=" + description + ", amount=" + amount + ", chapter=" + chapter + '}';
    }
    
}