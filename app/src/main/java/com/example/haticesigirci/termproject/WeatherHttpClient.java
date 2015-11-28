package com.example.haticesigirci.termproject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by haticesigirci on 29/11/15.
 */
public class WeatherHttpClient {

    //private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?lat=";
    private static String IMG_URL = "http://openweathermap.org/img/w/";
    private static String BASE_URL = "http://api.worldweatheronline.com/free/v2/weather.ashx?q=";

	/*7d681077497eba25a6f0b131fc839 key */
	/*public static final String FREE_API_ENDPOINT = "http://api.worldweatheronline.com/free/v1/weather.ashx";*/

    //private static String BASE_URL = "http://api.worldweatheronline.com/free/v2/weather.ashx?q=London&format=json&includelocation=yes&key=15c2af78a54117970a614ef22d5d8";

	/*http://api.worldweatheronline.com/free/v1/weather.ashx/key=7d681077497eba25a6f0b131fc839&q=48.834,2.394&format=json
	 */
    /** api.openweathermap.org/data/2.5/weather?lat=35&lon=139 **/
    public String getWeatherData(String lat,String lon) { /*(String location*/
        HttpURLConnection con = null ;
        InputStream is = null;

        //Log.d("latitude",lat);


        try {
            con = (HttpURLConnection) ( new URL(BASE_URL + lat + "," + lon +"&format=json&includelocation=yes&key=15c2af78a54117970a614ef22d5d8")).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }

    public byte[] getImage(String code) {
        HttpURLConnection con = null ;
        InputStream is = null;
        try {
            con = (HttpURLConnection) ( new URL(IMG_URL + code)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            is = con.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while ( is.read(buffer) != -1)
                baos.write(buffer);

            return baos.toByteArray();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }

}
