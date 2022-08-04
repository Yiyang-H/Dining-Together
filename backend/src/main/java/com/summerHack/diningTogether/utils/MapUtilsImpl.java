package com.summerHack.diningTogether.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.repository.FoodRepository;
import io.jsonwebtoken.lang.Assert;
import io.swagger.v3.core.util.Json;
import lombok.AllArgsConstructor;
import lombok.Data;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;

@Component
@AllArgsConstructor
@Data
public class MapUtilsImpl implements MapUtils {

    private Environment env;

    @Override
    public Response getResponse(String origin, String destination) throws IOException {


        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");

        String apiKey = env.getProperty("APIKEY");
        String url = String.format("https://maps.googleapis.com/maps/api/" +
                        "distancematrix/json?origins=%s&destinations=%s&units=imperial&key=%s",
                origin, destination, apiKey);
=
        System.out.println(System.getenv());
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();

        return response;

    }

    @Override
    public Long analyzeDistance(Response response) throws IOException {

        //parsing json data and updating data

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseBody responseBody = response.body();
        int distance = (Integer)new JSONObject(responseBody.string())
                .getJSONArray("rows")
                .getJSONObject(0)
                .getJSONArray("elements")
                .getJSONObject(0)
                .getJSONObject("distance")
                .get("value");
        System.out.println(distance);
        return (long)distance;


    }

    @Override
    public Long getDistance(String origin, String destination) throws IOException {
        Response response = getResponse(origin, destination);
        return analyzeDistance(response);
    }



}
