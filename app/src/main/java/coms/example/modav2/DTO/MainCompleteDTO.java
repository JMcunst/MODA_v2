package coms.example.modav2.DTO;

import java.util.Date;

public class MainCompleteDTO {
    private int completeId;
    private String completeContent;
    private String completeMemo;
    private Date completeDate;
    private int completeState;
    private int completeCategory;


    public MainCompleteDTO(int completeId, String completeContent, String completeMemo, Date completeDate, int completeState, int completeCategory) {
        this.completeId = completeId;
        this.completeContent = completeContent;
        this.completeMemo = completeMemo;
        this.completeDate = completeDate;
        this.completeState = completeState;
        this.completeCategory = completeCategory;
    }

    public int getCompleteId() {
        return completeId;
    }

    public void setCompleteId(int completeId) {
        this.completeId = completeId;
    }

    public String getCompleteContent() {
        return completeContent;
    }

    public void setCompleteContent(String completeContent) {
        this.completeContent = completeContent;
    }

    public String getCompleteMemo() {
        return completeMemo;
    }

    public void setCompleteMemo(String completeMemo) {
        this.completeMemo = completeMemo;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public int getCompleteState() {
        return completeState;
    }

    public void setCompleteState(int completeState) {
        this.completeState = completeState;
    }

    public int getCompleteCategory() {
        return completeCategory;
    }

    public void setCompleteCategory(int completeCategory) {
        this.completeCategory = completeCategory;
    }
}
