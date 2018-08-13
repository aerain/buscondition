package com.hackathon.bus.VO;

import java.util.ArrayList;

public class FavoritBusVo {

    private String busNum="";
    private String busInfo="제주대방면";


    public String getBusInfo() {
        return busInfo;
    }

    public String getBusNum() {
        return busNum;
    }

    public void setBusInfo(String businfo) {
        this.busInfo=businfo;
    }

    public void setBusNum(String busnum) {
        this.busNum=busnum;
    }
}
