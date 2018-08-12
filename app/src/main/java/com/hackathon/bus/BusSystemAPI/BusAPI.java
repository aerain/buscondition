package com.hackathon.bus.BusSystemAPI;

public class BusAPI {

    private String token;

    public BusAPI() {
        token = "60io4%2B%2BLsPCgnJcw%2B1%2FufoEFwxNyyxiYIL1j9eqjqmr6OclPIxkGFXsUt%2FkY2dvAtaWXh3KzyEns%2BLVujevEww%3D%3D";
    }

    public void getBusPos() {
        BusPos busPos = new BusPos(token);
        busPos.run();
    }
}
