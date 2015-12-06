package com.example.haticesigirci.termproject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haticesigirci.termproject.model.Weather;

import org.json.JSONException;

/**
 * Created by haticesigirci on 29/11/15.
 */
public class WeatherMainActivity extends  Activity{

        private TextView cityText;
        private TextView condDescr;
        private TextView temp;
        private TextView press;
        private TextView windSpeed;
        private TextView windDeg;

        private TextView hum;
        private ImageView imgView;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.weather_activity);
            String city = "Istanbul,Turkey";

		/* Latitude and Longitude*/
            String lat =  "40.9734";
            String lon = "29.1526";


            cityText = (TextView) findViewById(R.id.cityText);
            condDescr = (TextView) findViewById(R.id.condDescr);
            temp = (TextView) findViewById(R.id.temp);
            hum = (TextView) findViewById(R.id.hum);
            press = (TextView) findViewById(R.id.press);
            windSpeed = (TextView) findViewById(R.id.windSpeed);
            windDeg = (TextView) findViewById(R.id.windDeg);
            imgView = (ImageView) findViewById(R.id.condIcon);

            JSONWeatherTask task = new JSONWeatherTask();
            task.execute(new String[]{lat,lon});
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }


        private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

            @Override
            protected Weather doInBackground(String... params) {
                Weather weather = new Weather();
                String data = ( (new WeatherHttpClient()).getWeatherData(params[0],params[1]));

                try {
                    weather = JSONWeatherParser.getWeather(data);

                    // Let's retrieve the icon
                    weather.iconData = ( (new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return weather;

            }




            @Override
            protected void onPostExecute(Weather weather) {
                super.onPostExecute(weather);

                if (weather.iconData != null && weather.iconData.length > 0) {
                    Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
                    //	imgView.setImageBitmap(img);
                }

                Log.d("On post", "inside ");



                //	Log.d("JSON DATA", weather.location.getCity());
                cityText.setText(weather.location.getCity() + "," + weather.location.getCountry());
                condDescr.setText(weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescr() + ")");
                temp.setText("" + (weather.temperature.getTemp()) + (char) 0x00B0);
                hum.setText("" + weather.currentCondition.getHumidity() + "%");
                press.setText("" + weather.currentCondition.getPressure() + " hPa");
                windSpeed.setText("" + weather.wind.getSpeed() + " mps");
                windDeg.setText("" + weather.wind.getDeg() + (char) 0x00B0);

            }

        }
    }


