package com.bart.weatherapplication.repository.api;

import com.bart.weatherapplication.example.WeatherFullResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class CityApi {

    private final WeatherConnector weatherConnector;
    private final ObjectMapper objectMapper;

    public CityApi() {
        weatherConnector = new WeatherConnector();
        objectMapper = new ObjectMapper();
    }

    private String createUrlForWeather(String city) {

        return "https://samples.openweathermap.org/data/2.5/weather?q=" + city + "&appid=b6907d289e10d714a6e88b30761fae22";
        //return "https://samples.openweathermap.org/data/2.5/weather?q=" + city + "&appid=b6907d289e10d714a6e88b30761fae22";
        //return "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=aff3ccdce49c632c4e74f438c5e8faa4";
        //return "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=bb67359d75f8081afed0b2a58595b3b1";
    }

    public WeatherFullResponse getData(String city) {
        try {
            String data = weatherConnector.downloadData(createUrlForWeather(city));
            return objectMapper.readValue(data, WeatherFullResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}