package com.hackathon.bus.BusSystemAPI;

import android.util.Log;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class BusPos implements Runnable {

    private String url = "http://ws.bus.go.kr/api/rest/buspos/getBusPosByVehId" ;

    DocumentBuilderFactory dbFactory;
    DocumentBuilder dBulider;
    Document doc;

    // K0Fwq0uxVP8aJKAmam5CtRdKH%2FWZmICcq9yRMQJq11IfQrif2GOZd106cle42eMW2npQ%2BFKaxVfQ731XWaWsUQ%3D%3D&vehId=111033115;
    public BusPos(String token) {
        url += "?ServiceKey=" + token + "&vehId=111033115";

        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            dBulider = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) { }
    }
    @Override
    public void run() {

        Log.i("시스템","스레드 테스트");
        try {
            doc = dBulider.parse(url);
            doc.getDocumentElement().normalize();
            Log.i("파싱", doc.getDocumentElement().getNodeName());
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
