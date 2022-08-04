package com.summerHack.diningTogether.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import okhttp3.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@AllArgsConstructor
@Data
public class MapUtilsImpl implements MapUtils {


    @Override
    public Response getResponse(String origin, String destination) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        String url = String.format("https://maps.googleapis.com/maps/api/" +
                        "distancematrix/json?origins=%s&destinations=%s&units=imperial&key=%s",
                origin, destination, System.getenv().get("APIKEY"));
        Request request = new Request.Builder()
                .url(url)
                .method("GET", body)
                .build();
        Response response = client.newCall(request).execute();
        return response;

    }

    @Override
    public Long analyzeDistance(Response response) throws ParseException {
        long distance = -1L;
        long time = -1L;
        //parsing json data and updating data

            JSONParser jp = new JSONParser();
            JSONObject jo = (JSONObject) jp.parse(String.valueOf(response));
            JSONArray ja = (JSONArray) jo.get("rows");
            jo = (JSONObject) ja.get(0);
            ja = (JSONArray) jo.get("elements");
            jo = (JSONObject) ja.get(0);
            JSONObject je = (JSONObject) jo.get("distance");
            JSONObject jf = (JSONObject) jo.get("duration");
            distance = (long) je.get("value");
            time = (long) jf.get("value");
            return distance;

    }

    @Override
    public Long getDistance(String origin, String destination) throws IOException, ParseException {
        Response response = getResponse(origin, destination);
        return analyzeDistance(response);
    }

}
