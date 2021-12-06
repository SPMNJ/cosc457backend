package dev.teamswy.backend.dto;

import java.time.LocalDate;

public class MemberDTO {
    private Integer rollNo;
    private String name;
    private String email;
    private String phone;
    private String status;
    private LocalDate inductionDate;
    private LocalDate initiationDate;

    public MemberDTO() {
    }

    public MemberDTO(Integer rollNo, String name, String email, String phone, String status, LocalDate inductionDate, LocalDate initiationDate) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.inductionDate = inductionDate;
        this.initiationDate = initiationDate;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getInductionDate() {
        return inductionDate;
    }

    public void setInductionDate(LocalDate inductionDate) {
        this.inductionDate = inductionDate;
    }

    public LocalDate getInitationDate() {
        return initiationDate;
    }

    public void setInitiationDate(LocalDate initiationDate) {
        this.initiationDate = initiationDate;
    }
}
