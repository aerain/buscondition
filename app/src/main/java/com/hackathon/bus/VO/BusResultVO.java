package com.hackathon.bus.VO;

/**
 * Created by kim on 2018-08-12.
 */

public class BusResultVO {
    private String bus_num;
    private String bus_docha;
    private String bus_go_end;
    public String getBus_num() {
        return bus_num;
    }
    public String getBus_docha() {
        return bus_docha;
    }
    public String getBus_go_end(){
        return bus_go_end;
    }

    public BusResultVO(String bus_num, String bus_docha, String bus_go_end) {
        this.bus_num = bus_num;
        this.bus_docha = bus_docha;
        this.bus_go_end = bus_go_end;
    }
}
