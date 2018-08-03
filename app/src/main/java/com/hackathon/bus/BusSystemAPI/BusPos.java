package com.hackathon.bus.BusSystemAPI;

public class BusPos implements Runnable {
    public BusPos(String token) {

    }
    @Override
    public void run() {
        System.out.println("스레드 테스트");
    }
}
