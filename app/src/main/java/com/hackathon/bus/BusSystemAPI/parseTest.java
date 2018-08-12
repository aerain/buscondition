package com.hackathon.bus.BusSystemAPI;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class parseTest {
    public static void main(String[] args) {
        try {
            URL input = new URL("http://117.17.102.139:3000");
            BufferedReader in = new BufferedReader(new InputStreamReader(input.openStream()));

            String inputline;

            while ((inputline = in.readLine()) != null) {
                System.out.println(inputline);
            }
            in.close();


        } catch(Exception e) {

        }

    }
}
