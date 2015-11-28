package com.example.haticesigirci.termproject;

import android.util.Log;

import com.example.haticesigirci.termproject.model.Location;
import com.example.haticesigirci.termproject.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by haticesigirci on 29/11/15.
 */
public class JSONWeatherParser {

    /**
     * This is a tutorial source code
     * provided "as is" and without warranties.
     *
     * For any question please visit the web site
     * http://www.survivingwithandroid.com
     *
     * or write an email to
     * survivingwithandroid@gmail.com
     *
     */


        public static Weather getWeather(String data) throws JSONException {
            Weather weather = new Weather();

            // We create out JSONObject from the data
            JSONObject jObj2 = new JSONObject(data);
            JSONObject jObj = jObj2.getJSONObject("data");


//		Log.d("JSONOBJECT",jObj.toString());
            // We start extracting the info
            Location loc = new Location();

            //Log.d("jsonWeather","parser");

            JSONArray jArrArea = jObj.getJSONArray("nearest_area");

            Log.d("JARRAY", jArrArea.toString());

            JSONObject JSONArea= jArrArea.getJSONObject(0);

            Log.d("JSONOBJECT", JSONArea.toString());

            //	JSONObject coordObj = getObject("nearest_area", jObj);		/*OK*/
            loc.setLatitude(getString("latitude", JSONArea));			/*OK*/
            Log.d("latitude","json");
            loc.setLongitude(getString("longitude", JSONArea));			/*OK*/

            Log.d("firstlog","first");
            JSONArray jArrName = JSONArea.getJSONArray("areaName");
            JSONObject JSOName= jArrName.getJSONObject(0);

//		JSONObject newObj = (JSONObject) JSOName.get("value"); /*OK*/
            loc.setCity(getString("value", JSOName));					/*OK*/

            Log.d("LogCity", getString("value",JSOName));

            JSONArray jArrCountry = JSONArea.getJSONArray("country");
            JSONObject JSONCountry= jArrCountry.getJSONObject(0);

            loc.setCountry(getString("value", JSONCountry));				/*OK*/

            Log.d("JSONCOUNTRY",JSONCountry.toString());

            JSONArray jArrMainWeather = jObj.getJSONArray("weather");
            JSONObject JSONMainWeather= jArrMainWeather.getJSONObject(0);

            Log.d("JSONMainWeather",JSONMainWeather.toString());

            JSONArray jArrAstronomy = JSONMainWeather.getJSONArray("astronomy");
            JSONObject JSONAstronomy =  jArrAstronomy.getJSONObject(0);
            loc.setSunrise(getString("sunrise", JSONAstronomy));
            loc.setSunset(getString("sunset", JSONAstronomy));
            weather.location = loc;


            ///////////////////////////////////////////////////////////////////////////////////////////////
            // We get weather info (This is an array)
            JSONArray jArrCurrentCondition = jObj.getJSONArray("current_condition");

//		JSONArray jArrWeather = jObj.getJSONArray("weather");

            // We use only the first value
            JSONObject JSONCurrentCondition = jArrCurrentCondition.getJSONObject(0);

            Log.d("JSONCurrentCondition", JSONCurrentCondition.toString());

            weather.currentCondition.setWeatherId(getString("weatherCode", JSONCurrentCondition));		/*OK*/

            Log.d("JSONWeatherCode",getString("weatherCode", JSONCurrentCondition));

            JSONArray jArrDesc = JSONCurrentCondition.getJSONArray("weatherDesc");
            JSONObject JSONDesc = jArrDesc.getJSONObject(0);


            weather.currentCondition.setDescr(getString("value", JSONDesc));						/*OK*/
            Log.d("JSONDesc",getString("value", JSONDesc));


            JSONArray jArrMain = JSONArea.getJSONArray("region");
            JSONObject JSONMain = jArrMain.getJSONObject(0);

            weather.currentCondition.setCondition(getString("value", JSONMain));				/*OK*/
            Log.d("JSONWeatherMain",getString("value", JSONMain));

//		JSONArray jArrIcon = JSONCurrentCondition.getJSONArray("weatherIconUrl");
//		JSONObject JSONIcon = jArrIcon.getJSONObject(0);
//
//		weather.currentCondition.setIcon(getString("icon", JSONIcon));							/*OK*/
//
//		JSONObject JSONWeather = jArrWeather.getJSONObject(0);


            //	JSONObject mainObj = getObject("current_condition", jObj);
            weather.currentCondition.setHumidity(getString("humidity", JSONCurrentCondition));		/*OK*/
            weather.currentCondition.setPressure(getString("pressure", JSONCurrentCondition));		/*OK*/

            Log.d("Pressure",getString("pressure", JSONCurrentCondition));

//		weather.temperature.setMaxTemp(getFloat("temp_max", mainObj));//**current_condition in icinde degil
//		weather.temperature.setMinTemp(getFloat("temp_min", mainObj));//**current_condition in icinde degil
            weather.temperature.setTemp(getString("temp_C", JSONCurrentCondition));				/*OK*/
            weather.temperature.setMaxTemp(getString("maxtempC", JSONMainWeather));					/*OK*/
            weather.temperature.setMinTemp(getString("mintempC", JSONMainWeather));					/*OK*/

            Log.d("WeatherTemperature","Temperature");
            // Wind
//		JSONObject wObj = getObject("wind", jObj);

            weather.wind.setSpeed(getString("windspeedKmph", JSONCurrentCondition));				/*OK*/
            weather.wind.setDeg(getString("winddirDegree", JSONCurrentCondition));				/*OK*/
            Log.d("WeatherSomething","Something");
            // Clouds
            //	JSONObject cObj = getObject("clouds", jObj);
            weather.clouds.setPerc(getString("cloudcover", JSONCurrentCondition));					/*OK*/

            // We download the icon to show

            Log.d("JSONWeatherFinal","final");
            return weather;
        }


        private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
            JSONObject subObj = jObj.getJSONObject(tagName);
            return subObj;
        }

        private static String getString(String tagName, JSONObject jObj) throws JSONException {
            return jObj.getString(tagName);
        }

        private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
            return (float) jObj.getDouble(tagName);
        }

        private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
            return jObj.getInt(tagName);
        }

    }



