package coms.example.modav2.DTO;

import java.util.Date;

public class MainScheduleDTO {
    private int scheduleId;
    private String sheduleContent;
    private String sheduleMemo;
    private Date scheduleDate;
    private int scheduleState;

    public MainScheduleDTO(int scheduleId, String sheduleContent, String sheduleMemo, Date scheduleDate, int scheduleState) {
        this.scheduleId = scheduleId;
        this.sheduleContent = sheduleContent;
        this.sheduleMemo = sheduleMemo;
        this.scheduleDate = scheduleDate;
        this.scheduleState = scheduleState;
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
}
