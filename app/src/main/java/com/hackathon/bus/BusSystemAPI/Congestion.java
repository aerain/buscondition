package com.hackathon.bus.BusSystemAPI;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Congestion {

    HttpURLConnection conn;
    BufferedReader br;
    String json;

    public ArrayList<Integer> parser(String paramArsId){
        ArrayList<Integer> congestionList = new ArrayList<>();
        try{

            URL url = new URL("http://117.17.102.139:3000/getBusTime?arsid=" + paramArsId);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            json = br.readLine();
            json = json.replaceAll("&#34;","\"");
            //json file to String
            System.out.println(json);

            JSONObject jsonObject = new JSONObject(json);

            int size = jsonObject.getInt("size");
            String busRoute = jsonObject.getString("busRoute");

            JSONArray jsonArray = new JSONArray(busRoute);

            for (int i = 0; i<jsonArray.length(); i++){

                jsonObject = jsonArray.getJSONObject(i);

                String busRouteId = jsonObject.getString("busRouteId");

                String busNum = jsonObject.getString("busNum");
                System.out.println("노선ID : " + busRouteId);
                System.out.println("버스번호 : " + busNum);
                String busTimearr = jsonObject.getString("busTimearr");

                JSONArray jsonArray1 = new JSONArray(busTimearr);

                if(jsonArray1.length() != 0) {
                    for (int j = 0; j<jsonArray1.length(); j++) {

                        jsonObject = jsonArray1.getJSONObject(j);

                        String station = jsonObject.getString("station");
                        String arsId = jsonObject.getString("arsId");
                        System.out.println("정류장 : " + station);
                        System.out.println("고유번호 : " + arsId);
                        String busTime = jsonObject.getString("busTime");

                        JSONArray jsonArray2 = new JSONArray(busTime);

                        //jsonObject = jsonArray2.getJSONObject(0);
                        int busTimeUp = jsonArray2.getInt(0);
                        //jsonObject = jsonArray2.getJSONObject(1);
                        int busTimeDown = jsonArray2.getInt(1);
                        System.out.println("승차인원 : " + busTimeUp);
                        System.out.println("하차인원 : " + busTimeDown);
                        congestionList.add(busTimeUp - busTimeDown);
                        System.out.println(congestionList.get(j));

                    }
                } else {
                    congestionList.add(0);
                    System.out.println(congestionList.get(congestionList.size() - 1));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return congestionList;
    }

}
