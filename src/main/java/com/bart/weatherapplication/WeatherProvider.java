package com.bart.weatherapplication;

import com.bart.weatherapplication.model.Result;
import com.bart.weatherapplication.model.Weather;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class WeatherProvider {

    //    @EventListener(ApplicationReadyEvent.class)
    public List<Result> getWeather(String keywords, String location) {

        String URL = "https://www.reed.co.uk/api/1.0/search?keywords=" + keywords +
                "&distancefromlocation=5&locationname=" + location;

        return getResultsRestTemplate(URL);
    }

    public List<Result> getAllWeather() {
        String URL = "https://www.reed.co.uk/api/1.0/search?keywords=java&location=london&distancefromlocation=5";

        return getResultsRestTemplate(URL);
    }

    private List<Result> getResultsRestTemplate(String URL) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic ZGY0YjE0ZjktNmQ2Yy00MzBiLWI3MGUtOGEwZjdiY2Q5YmI0Og==");
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<Weather> exchange = restTemplate.exchange(URL, HttpMethod.GET, httpEntity, Weather.class);

        return exchange.getBody().getResults();
    }

}