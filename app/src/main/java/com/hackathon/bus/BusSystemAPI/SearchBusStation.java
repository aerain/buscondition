package com.hackathon.bus.BusSystemAPI;


import android.util.Log;
import com.hackathon.bus.VO.StationVO;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

public class SearchBusStation {

    private LinkedList<StationVO> Vos;
    private int count;

    public SearchBusStation(){
        Vos = new LinkedList<>();
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
                            Vos.add(node);
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
        System.out.println(Vos.size() + "확실하냐?");

        for(int i = 0; i < Vos.size(); i++) {
            System.out.println(Vos.get(i).getsNum() + "입니다." + i);
            System.out.println(Vos.get(i).getsName() + "입니다." + i);
        }

        return buffer.toString();
    }

    public String xmlParser_direction(){

        StringBuffer buffer = new StringBuffer();

        try {

            XmlPullParserFactory factory_direction = XmlPullParserFactory.newInstance();
            XmlPullParser xpp_direction = factory_direction.newPullParser();

            for (int i = 0; i < Vos.size(); i++)  {
                StationVO node = Vos.get(i);
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




}
