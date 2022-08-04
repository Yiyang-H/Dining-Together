package com.summerHack.diningTogether.utils;

import okhttp3.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface MapUtils {
    public Response getDistance(String origin, String destination) throws IOException;
    public Long analyzeDistance(Response response) throws ParseException;

}
