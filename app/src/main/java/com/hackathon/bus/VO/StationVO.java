package com.hackathon.bus.VO;

public class StationVO {
    private String sName;
    private String sNum;
    private String sStationid;
    private String sDirection;

    public StationVO(String sName, String sNum, String sStationid) {
        this.sName = sName;
        this.sNum = sNum;
        this.sStationid = sStationid;
    }

    public String getsDirection() {
        return sDirection;
    }

    public String getsName() {
        return sName;
    }

    public String getsNum() {
        return sNum;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public void setsDirection(String sDirection) {
        this.sDirection = sDirection;
    }

    public void setsNum(String sNum) {
        this.sNum = sNum;
    }

    public String getsStationid() {
        return sStationid;
    }

    public void setsStationid(String sStationid) {
        this.sStationid = sStationid;
    }
}
