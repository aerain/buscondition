package com.hackathon.bus.VO;

import com.hackathon.bus.BusSystemAPI.Congestion;

public class BusVO {

    private String BusNumber;
    private String BusRouteId;
    private String ArriveTime1;
    private String ArriveTime2;
    private String VehicleId1;
    private String VehicleId2;
    private String SectionOrder1;
    private String SectionOrder2;
    private String StationOrder;
    private String Busterm;




    private String Congestion;

    public BusVO(String rtNm, String busRouteId, String traTime1, String traTime2, String vehId1, String vehId2, String sectOrd1, String sectOrd2, String staOrd, String term){

        BusNumber = rtNm;
        BusRouteId = busRouteId;
        ArriveTime1 = traTime1;
        ArriveTime2 = traTime2;
        VehicleId1 = vehId1;
        VehicleId2 = vehId2;
        SectionOrder1 = sectOrd1;
        SectionOrder2 = sectOrd2;
        StationOrder = staOrd;
        Busterm = term;

    }

    public String getBusNumber() {
        return BusNumber;
    }

    public String getBusRouteId() {
        return BusRouteId;
    }

    public String getArriveTime1() {
        return ArriveTime1;
    }

    public String getArriveTime2() {
        return ArriveTime2;
    }

    public String getVehicleId1() {
        return VehicleId1;
    }

    public String getVehicleId2() {
        return VehicleId2;
    }

    public String getSectionOrder1() {
        return SectionOrder1;
    }

    public String getSectionOrder2() {
        return SectionOrder2;
    }

    public String getStationOrder() {
        return StationOrder;
    }

    public String getBusterm() {
        return Busterm;
    }

    public String getCongestion() {
        return Congestion;
    }

    public void setCongestion(String congestion) {
        Congestion = congestion;
    }
}
