package com.hackathon.bus.BusSystemAPI;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class SearchBusAPI {

    private String strStart;
    private String strEnd;
    private String startX;
    private String startY;
    private String endX;
    private String endY;


    //탑승지명, 하차지명
    public SearchBusAPI(String strStart, String strEnd) {
        this.strStart = strStart;
        this.strEnd = strEnd;
    }

    private static String[] getTagValue(Element eElement) {
        // tag = gpsX, gpsY

        NodeList nlListX = eElement.getElementsByTagName("gpsX").item(0).getChildNodes();
        NodeList nlListY = eElement.getElementsByTagName("gpsY").item(0).getChildNodes();
        Node nValueX = (Node) nlListX.item(0);
        Node nValueY = (Node) nlListY.item(0);
        if (nValueX == null)
            return null;

        String[] gpsCoord = {nValueX.getNodeValue(), nValueY.getNodeValue()};
        return gpsCoord;
    }

    private String[] xmlParser(String str) {

        String[] result = null;
        long before = System.currentTimeMillis();
        try {

            // parsing할 url 지정(API 키 포함해서)
            String url = "http://ws.bus.go.kr/api/rest/pathinfo/getLocationInfo?ServiceKey=K0Fwq0uxVP8aJKAmam5CtRdKH%2FWZmICcq9yRMQJq11IfQrif2GOZd106cle42eMW2npQ%2BFKaxVfQ731XWaWsUQ%3D%3D&stSrch=" + str;
            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
            Document doc = dBuilder.parse(url);

            // root tag
            doc.getDocumentElement().normalize();
            String root = doc.getDocumentElement().getNodeName();

            // 파싱할 tag
            NodeList nList = doc.getElementsByTagName(root);
            Node nNode = nList.item(0);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                result = getTagValue(eElement);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }    // try~catch end

        long after = System.currentTimeMillis();

        long processingTime = after - before;

        Log.i("개빡치네", processingTime + " ms");
        return result;
    }

    public String[] getGPS(String str) {
        String[] gpsCoord = xmlParser(str);
        return gpsCoord;
    }
//    public String getEnd() {
//        endX = xmlParser(strEnd, "gpsX");
//        return endX;
//    }
//
//    public String getStartX() {
//        startX = xmlParser(strStart, "gpsX");
//        return startX;
//    }
//
//    public String getEndY() {
//        endY = xmlParser(strEnd, "gpsY");
//        return endY;
//    }
//
//    public String getStartY() {
//        startY = xmlParser(strStart, "gpsY");
//        return startY;
//    }

}
