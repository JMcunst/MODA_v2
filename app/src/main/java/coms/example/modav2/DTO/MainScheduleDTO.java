package coms.example.modav2.DTO;

import java.util.Date;

public class MainScheduleDTO {
    private int scheduleId;
    private String sheduleContent;
    private String sheduleMemo;
    private Date scheduleDate;
    private int scheduleState;
    private int schedulePin;
    private int scheduleCategory;

    public MainScheduleDTO(int scheduleId, String sheduleContent, String sheduleMemo, Date scheduleDate, int scheduleState,int schedulePin, int scheduleCategory) {
        this.scheduleId = scheduleId;
        this.sheduleContent = sheduleContent;
        this.sheduleMemo = sheduleMemo;
        this.scheduleDate = scheduleDate;
        this.scheduleState = scheduleState;
        this.schedulePin = schedulePin;
        this.scheduleCategory = scheduleCategory;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getSheduleContent() {
        return sheduleContent;
    }

    public void setSheduleContent(String sheduleContent) {
        this.sheduleContent = sheduleContent;
    }

    public String getSheduleMemo() {
        return sheduleMemo;
    }

    public void setSheduleMemo(String sheduleMemo) {
        this.sheduleMemo = sheduleMemo;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public int getScheduleState() {
        return scheduleState;
    }

    public void setScheduleState(int scheduleState) {
        this.scheduleState = scheduleState;
    }

    public int getSchedulePin() {
        return schedulePin;
    }

    public void setSchedulePin(int schedulePin) {
        this.schedulePin = schedulePin;
    }

    public int getScheduleCategory() {
        return scheduleCategory;
    }

    public void setScheduleCategory(int scheduleCategory) {
        this.scheduleCategory = scheduleCategory;
    }
}
