package com.hackathon.bus.BusSystemAPI;

public class BusAPI {
    private String token;

    public BusAPI() {
        token = "K0Fwq0uxVP8aJKAmam5CtRdKH%2FWZmICcq9yRMQJq11IfQrif2GOZd106cle42eMW2npQ%2BFKaxVfQ731XWaWsUQ%3D%3D";
    }

    public void getBusPos() {
        BusPos busPos = new BusPos(token);
        busPos.run();
    }
}
