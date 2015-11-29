package com.example.haticesigirci.termproject.model;

import android.util.Log;

/**
 * Created by haticesigirci on 29/11/15.
 */
public class Weather {

    public Location location;
    public CurrentCondition currentCondition = new CurrentCondition();
    public Temperature temperature = new Temperature();
    public Wind wind = new Wind();
    public Rain rain = new Rain();
    public Snow snow = new Snow()	;
    public Clouds clouds = new Clouds();

    public byte[] iconData;

    public  class CurrentCondition {
        private String weatherId;
        private String condition;
        private String descr;
        private String icon;


        private String pressure;
        private String humidity;

        public String getWeatherId() {
            return weatherId;
        }
        public void setWeatherId(String weatherId) {

            this.weatherId = weatherId;
        }
        public String getCondition() {
            return condition;
        }
        public void setCondition(String condition) {
            Log.d("weatherInside", condition);
            this.condition = condition;
        }
        public String getDescr() {
            return descr;
        }
        public void setDescr(String descr) {
            Log.d("weatherInside",descr);
            this.descr = descr;
        }
        public String getIcon() {
            return icon;
        }
        public void setIcon(String icon) {
            this.icon = icon;
        }
        public String getPressure() {
            return pressure;
        }
        public void setPressure(String pressure) {
            this.pressure = pressure;
        }
        public String getHumidity() {
            return humidity;
        }
        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }


    }

    public  class Temperature {
        private String temp;
        private String minTemp;
        private String maxTemp;

        public String getTemp() {
            return temp;
        }
        public void setTemp(String temp) {
            this.temp = temp;
        }
        public String getMinTemp() {
            return minTemp;
        }
        public void setMinTemp(String minTemp) {
            this.minTemp = minTemp;
        }
        public String getMaxTemp() {
            return maxTemp;
        }
        public void setMaxTemp(String maxTemp) {
            this.maxTemp = maxTemp;
        }

    }

    public  class Wind {
        private String speed;
        private String deg;
        public String getSpeed() {
            return speed;
        }
        public void setSpeed(String speed) {
            this.speed = speed;
        }
        public String getDeg() {
            return deg;
        }
        public void setDeg(String deg) {
            this.deg = deg;
        }


    }

    public  class Rain {
        private String time;
        private float ammount;
        public String getTime() {
            return time;
        }
        public void setTime(String time) {
            this.time = time;
        }
        public float getAmmount() {
            return ammount;
        }
        public void setAmmount(float ammount) {
            this.ammount = ammount;
        }



    }

    public  class Snow {
        private String time;
        private float ammount;

        public String getTime() {
            return time;
        }
        public void setTime(String time) {
            this.time = time;
        }
        public float getAmmount() {
            return ammount;
        }
        public void setAmmount(float ammount) {
            this.ammount = ammount;
        }


    }

    public  class Clouds {
        private String perc;

        public String getPerc() {
            return perc;
        }

        public void setPerc(String perc) {
            this.perc = perc;
        }


    }

}
