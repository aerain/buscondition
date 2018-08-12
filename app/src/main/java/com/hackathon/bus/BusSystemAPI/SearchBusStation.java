package com.hackathon.bus.BusSystemAPI;


import com.hackathon.bus.VO.BusVO;
import com.hackathon.bus.VO.StationVO;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;

public class SearchBusStation {

    private LinkedList<StationVO> Station;
    private int count;
    private LinkedList<BusVO> Bus;


    public SearchBusStation(){
        Station = new LinkedList<>();
        Bus = new LinkedList<>();
        count=0;
    }

    public String xmlParser_station(String str) {

        long before = System.currentTimeMillis();

        StringBuffer buffer = new StringBuffer();
        str = str.replaceAll(" ","");

        try {

            XmlPullParserFactory factory_station = XmlPullParserFactory.newInstance();
            XmlPullParser xpp_station = factory_station.newPullParser();

            URL url_staton = new URL("http://ws.bus.go.kr/api/rest/stationinfo/getStationByName?serviceKey=60io4%2B%2BLsPCgnJcw%2B1%2FufoEFwxNyyxiYIL1j9eqjqmr6OclPIxkGFXsUt%2FkY2dvAtaWXh3KzyEns%2BLVujevEww%3D%3D&stSrch=" + str);
            InputStream station = url_staton.openStream();

            xpp_station.setInput(new InputStreamReader(station, "UTF-8"));

            int eventType = xpp_station.getEventType();
            String busName = "", busNum = "", busStationId = "";
            String tag_station;

            while (eventType != XmlPullParser.END_DOCUMENT){


                switch (eventType){

                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("start xml parser\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag_station = xpp_station.getName();

                        if (tag_station.equals("arsId")) {
                            buffer.append("고유번호 : ");
                            xpp_station.next();
                            buffer.append(xpp_station.getText());
                            busNum = xpp_station.getText();
                            buffer.append("\n");
                        }

                        else if (tag_station.equals("stId")){
                            buffer.append("역ID : ");
                            xpp_station.next();
                            buffer.append(xpp_station.getText());
                            busStationId = xpp_station.getText();
                            buffer.append("\n");
                        }

                        else if (tag_station.equals("stNm")){
                            buffer.append("정류장이름 : ");
                            xpp_station.next();
                            buffer.append(xpp_station.getText());
                            busName = xpp_station.getText();

                            buffer.append("\n");

                        }

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag_station = xpp_station.getName();
                        if(tag_station.equals("itemList")) {
                            StationVO node = new StationVO(busName, busNum, busStationId);
                            System.out.println(node.getsNum());
                            Station.add(node);
                        }
                        break;
                    }

                    eventType = xpp_station.next();
                }

            } catch(Exception e){

            e.printStackTrace();

        }

        buffer.append("end xml parser\n");
        long after = System.currentTimeMillis();

        System.out.println((after - before) + " ms");
//        System.out.println(buffer.toString());
        System.out.println(Station.size() + "확실하냐?");

        for(int i = 0; i < Station.size(); i++) {
            System.out.println(Station.get(i).getsNum() + "입니다." + i);
            System.out.println(Station.get(i).getsName() + "입니다." + i);
        }

        return buffer.toString();
    }

    public String xmlParser_direction(){

        StringBuffer buffer = new StringBuffer();

        try {

            XmlPullParserFactory factory_direction = XmlPullParserFactory.newInstance();
            XmlPullParser xpp_direction = factory_direction.newPullParser();

            for (int i = 0; i < Station.size(); i++)  {
                StationVO node = Station.get(i);
                String arsidStr = node.getsNum();

                URL url_staton = new URL("http://ws.bus.go.kr/api/rest/stationinfo/getStationByUid?serviceKey=60io4%2B%2BLsPCgnJcw%2B1%2FufoEFwxNyyxiYIL1j9eqjqmr6OclPIxkGFXsUt%2FkY2dvAtaWXh3KzyEns%2BLVujevEww%3D%3D&arsId=" + arsidStr);
                InputStream direction = url_staton.openStream();

                xpp_direction.setInput(new InputStreamReader(direction, "UTF-8"));

                String tag_direction;

                int eventType = xpp_direction.getEventType();

                parseBreak:

                while (eventType != XmlPullParser.END_DOCUMENT){

                    switch(eventType) {

                        case XmlPullParser.START_DOCUMENT :
                            buffer.append("parse START");
                            break;

                        case XmlPullParser.START_TAG :
                            tag_direction = xpp_direction.getName();

//                            if (tag_direction.equals("busRouteId")) {
//                                buffer.append("버스번호ID : ");
//                                xpp_direction.next();
//                                buffer.append(xpp_direction.getText());
//                                //busNum = xpp_station.getText();
//                                buffer.append("\n");
//                            }
//
//                            else if (tag_direction.equals("vehid")) {
//                                buffer.append("차량ID : ");
//                                xpp_direction.next();
//                                buffer.append(xpp_direction.getText());
//                                //busNum = xpp_station.getText();
//                                buffer.append("\n");
//                            }

                            if(tag_direction.equals("nxtStn")) {
                                xpp_direction.next();
                                node.setsDirection(xpp_direction.getText());
                                if(xpp_direction.getText().equals(" ")){
                                    node.setsDirection("종점");
                                }
                            }
                            break;

                        case XmlPullParser.END_TAG :
                            tag_direction = xpp_direction.getName();
                            if(tag_direction.equals("itemList")) {
                                break parseBreak;
                            }
                            break ;

                    }

                    eventType = xpp_direction.next();
                }
                System.out.println(node.getsDirection());


            }


        } catch (Exception e) {
            e.printStackTrace();
        }



        return buffer.toString();

    }

    public String xmlParser_Bus(){

        StringBuffer buffer = new StringBuffer();

        try {

            XmlPullParserFactory factory_bus = XmlPullParserFactory.newInstance();
            XmlPullParser xpp_bus = factory_bus.newPullParser();

            URL url_bus = new URL("http://ws.bus.go.kr/api/rest/stationinfo/getStationByUid?serviceKey=60io4%2B%2BLsPCgnJcw%2B1%2FufoEFwxNyyxiYIL1j9eqjqmr6OclPIxkGFXsUt%2FkY2dvAtaWXh3KzyEns%2BLVujevEww%3D%3D&arsId=21111");
            InputStream station = url_bus.openStream();

            xpp_bus.setInput(new InputStreamReader(station, "UTF-8"));

            int eventType = xpp_bus.getEventType();
            String rtNm = "", busRouteId = "", traTime1 = "", traTime2 = "", vehId1 = "", vehId2 = "", sectOrd1 = "", sectOrd2 = "", staOrd = "", term = "";
            String tag_bus;

            while (eventType != XmlPullParser.END_DOCUMENT){


                switch (eventType){

                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("start xml parser\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag_bus = xpp_bus.getName();

                        if (tag_bus.equals("rtNm")) {
                            buffer.append("버스번호 : ");
                            xpp_bus.next();
                            buffer.append(xpp_bus.getText());
                            rtNm = xpp_bus.getText();
                            buffer.append("\n");
                        }

                        else if (tag_bus.equals("busRouteId")){
                            buffer.append("노선번호 : ");
                            xpp_bus.next();
                            buffer.append(xpp_bus.getText());
                            busRouteId = xpp_bus.getText();
                            buffer.append("\n");
                        }

                        else if (tag_bus.equals("arrmsg1")){
                            buffer.append("첫번째 도착메세지 : ");
                            xpp_bus.next();
                            buffer.append(xpp_bus.getText());
                            //traTime1 = xpp_bus.getText();
                            buffer.append("\n");
                        }

                        else if (tag_bus.equals("arrmsg2")){
                            buffer.append("두번째 도착메세지 : ");
                            xpp_bus.next();
                            buffer.append(xpp_bus.getText());
                            //traTime1 = xpp_bus.getText();
                            buffer.append("\n");
                        }

                        else if (tag_bus.equals("traTime1")){
                            buffer.append("첫번째 도착시간 : ");
                            xpp_bus.next();
                            buffer.append(xpp_bus.getText());
                            traTime1 = xpp_bus.getText();
                            buffer.append("\n");
                        }

                        else if (tag_bus.equals("traTime1")){
                            buffer.append("첫번째 도착시간 : ");
                            xpp_bus.next();
                            buffer.append(xpp_bus.getText());
                            traTime1 = xpp_bus.getText();
                            buffer.append("\n");
                        }

                        else if (tag_bus.equals("traTime2")){
                            buffer.append("두번째 도착시간 : ");
                            xpp_bus.next();
                            buffer.append(xpp_bus.getText());
                            traTime2 = xpp_bus.getText();
                            buffer.append("\n");
                        }

                        else if (tag_bus.equals("vehId1")){
                            buffer.append("첫번쨰 차량ID : ");
                            xpp_bus.next();
                            buffer.append(xpp_bus.getText());
                            vehId1 = xpp_bus.getText();
                            buffer.append("\n");
                        }

                        else if (tag_bus.equals("vehId2")){
                            buffer.append("두번쨰 차량ID : ");
                            xpp_bus.next();
                            buffer.append(xpp_bus.getText());
                            vehId2 = xpp_bus.getText();
                            buffer.append("\n");
                        }

                        else if (tag_bus.equals("sectOrd1")){
                            buffer.append("첫번쨰차량 구간번호 : ");
                            xpp_bus.next();
                            buffer.append(xpp_bus.getText());
                            sectOrd1 = xpp_bus.getText();
                            buffer.append("\n");
                        }

                        else if (tag_bus.equals("sectOrd2")){
                            buffer.append("두번쨰차량 구간번호 : ");
                            xpp_bus.next();
                            buffer.append(xpp_bus.getText());
                            sectOrd2 = xpp_bus.getText();
                            buffer.append("\n");
                        }

                        else if (tag_bus.equals("staOrd")){
                            buffer.append("정류소 순번 : ");
                            xpp_bus.next();
                            buffer.append(xpp_bus.getText());
                            staOrd = xpp_bus.getText();
                            buffer.append("\n");
                        }

                        else if (tag_bus.equals("term")){
                            buffer.append("배차간격 : ");
                            xpp_bus.next();
                            buffer.append(xpp_bus.getText());
                            term = xpp_bus.getText();
                            buffer.append("\n");
                        }

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag_bus = xpp_bus.getName();
                        if(tag_bus.equals("itemList")) {
                            BusVO node = new BusVO(rtNm, busRouteId, traTime1, traTime2, vehId1, vehId2, sectOrd1, sectOrd2, staOrd, term);
                            System.out.println(node.getBusNumber());
                            Bus.add(node);
                        }
                        break;
                }

                eventType = xpp_bus.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(buffer.toString());


        return buffer.toString();

    }

    public String xmlParser_Congestion(){

        StringBuffer buffer = new StringBuffer();

        try{

            XmlPullParserFactory factory_congestion = XmlPullParserFactory.newInstance();
            XmlPullParser xpp_congestion = factory_congestion.newPullParser();

            for (int i = 0; i < Bus.size(); i++)  {
                BusVO node = Bus.get(i);
                String VehicleId = node.getVehicleId1();

                URL url_congestion = new URL("http://ws.bus.go.kr/api/rest/buspos/getBusPosByVehId?serviceKey=60io4%2B%2BLsPCgnJcw%2B1%2FufoEFwxNyyxiYIL1j9eqjqmr6OclPIxkGFXsUt%2FkY2dvAtaWXh3KzyEns%2BLVujevEww%3D%3D&vehId=" + VehicleId);
                InputStream congestion = url_congestion.openStream();

                xpp_congestion.setInput(new InputStreamReader(congestion, "UTF-8"));

                String tag_congestion;

                int eventType = xpp_congestion.getEventType();


                while (eventType != XmlPullParser.END_DOCUMENT){

                    switch(eventType) {

                        case XmlPullParser.START_DOCUMENT :
                            buffer.append("parse START");
                            break;

                        case XmlPullParser.START_TAG :
                            tag_congestion = xpp_congestion.getName();

                            if(tag_congestion.equals("congetion")) {
                                xpp_congestion.next();
                                node.setCongestion(xpp_congestion.getText());
                            }

                            break;

                        case XmlPullParser.END_TAG :
                            break ;

                    }

                    eventType = xpp_congestion.next();
                }
                System.out.println("버스번호 : " + node.getBusNumber());
                System.out.println("차량ID : " + node.getVehicleId1());
                System.out.println("버스혼잡도 : " + node.getCongestion());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return buffer.toString();


    }




}
