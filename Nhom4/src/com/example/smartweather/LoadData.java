package com.example.smartweather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import Model.Weather;

import org.json.JSONObject;
import android.R.integer;
import android.os.AsyncTask;
import android.util.Log;

public class LoadData {
	ArrayList<Weather> arrWeather = new ArrayList<Weather>();

	public String downloadUrl(String strUrl) throws IOException {
		String data = "";
		InputStream iStream = null;
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(strUrl);
			urlConnection = (HttpURLConnection) url.openConnection();
			// Connecting to url
			urlConnection.connect();
			// Reading data from url
			iStream = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					iStream));
			StringBuffer sb = new StringBuffer();

			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			data = sb.toString();

			br.close();

		} catch (Exception e) {
			Log.d("Error downloading url", e.toString());
		} finally {
			try {
				if (iStream != null) {
					iStream.close();
				}
				if (urlConnection != null) {
					urlConnection.disconnect();
				}
			} catch (Exception ignored) {
			}
		}
		return data;
	}

	public class DataWeather extends AsyncTask<String, integer, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void onPostExecute(String s) {

			JsonWeather(s);
		}

	}

	public void JsonWeather(String input) {

		Log.d("test", input);

		if (input.isEmpty()) {
			return;
		}

		try {

			// String Tenthanhpho;
			// String Nhietdo;
			// String Nhietdocaonhat;
			// String Nhietdothapnhat;
			// String Doam;
			// String Tocdogio;
			// String Date;
			// String Giomattroimoc;
			// String Giomattroilan;
			JSONObject obj = new JSONObject(input);

			Weather weather = new Weather();
			if (obj.getString("message").equals("Error"))
				return;

			JSONObject loc = obj.getJSONObject("city");
			Log.d("test", loc.getString("name"));
			weather.setTenthanhpho(loc.getString("name"));

			for (int i = 0; i < obj.length(); i++) {
				
				JSONObject list = obj.getJSONArray("list").getJSONObject(i);
				JSONObject tmp = list.getJSONObject("temp");
				weather.setNhietdo(tmp.getDouble("day") + " *C");
				Log.d("test",
						loc.getString("nhiet do: " + tmp.getDouble("day")));

				weather.setNhietdocaonhat(tmp.getDouble("max") + " *C");
				Log.d("test",
						loc.getString("nhiet do max: " + tmp.getDouble("max")));

				weather.setNhietdothapnhat(tmp.getDouble("min") + " *C");
				Log.d("test",
						loc.getString("nhiet do min: " + tmp.getDouble("min")));

				weather.setDoam(list.getDouble("humidity") + " %");
				Log.d("test",
						loc.getString("Doam: " + tmp.getDouble("humidity")));

				weather.setTocdogio(list.getDouble("speed") + "");
				Log.d("test",
						loc.getString("Tocdogio: " + tmp.getDouble("speed")));

				weather.setApSuat(list.getDouble("pressure") + "");
				Log.d("test",
						loc.getString("ApSuat: " + tmp.getDouble("pressure")));
				
				arrWeather.add(weather);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
