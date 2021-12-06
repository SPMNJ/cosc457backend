package dev.teamswy.backend.dto;

import java.time.LocalDate;

public class StatementDTO {
    private int amount;
    private String desc;
    private LocalDate date;
    private String type;
    
    public StatementDTO() {
    }

    public StatementDTO(int amount, String desc, LocalDate date, String type) {
        this.amount = amount;
        this.desc = desc;
        this.date = date;
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
