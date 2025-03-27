package org.example;

import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите (Латинскими буквами, регистр не имеет роль) (Страну/Город), формат должен быть корректным");

        Scanner scanner = new Scanner(System.in);

        String countryAndCity = scanner.next();

        String key = "831e45c8b66f48d1a52121059252703";

        String urlString = "http://api.weatherapi.com/v1/current.json?key=" + key + "&q=" + countryAndCity;


        try {
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty(key, "Accept/json");
            connection.setRequestMethod("GET");

            if(connection.getResponseCode() != 200) {
                System.out.println("Ошибка! Попробуйте по позже");
            }

            InputStream inputStream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            StringBuilder result = new StringBuilder();

            while ( (line = reader.readLine()) != null) {
                result.append(line);
            }

            JSONObject jsonObject = new JSONObject(result.toString());
            String city = jsonObject.getJSONObject("location").getString("name");
            String region = jsonObject.getJSONObject("location").getString("region");
            String country = jsonObject.getJSONObject("location").getString("country");
            String time = jsonObject.getJSONObject("location").getString("localtime");


            JSONObject jsonObject1 = new JSONObject(result.toString());
            double tempC = jsonObject1.getJSONObject("current").getDouble("temp_c");
            double tempF = jsonObject1.getJSONObject("current").getDouble("temp_f");

            System.out.println("ГОРОД: " + city);
            System.out.println("РЕГИОН: " + region);
            System.out.println("СТРАНА: " + country);
            System.out.println("ПОСЛЕДНЕЕ ВРЕМЯ ОБНОВЛЕНИИ ДАННЫХ: " + time);
            System.out.println("ГРАДУС ПО ЦЕЛЬСИИ: " + tempC);
            System.out.println("ГРАДУС ПО ФАРЕНГЕЙТУ: " + tempF);


        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (JSONException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
