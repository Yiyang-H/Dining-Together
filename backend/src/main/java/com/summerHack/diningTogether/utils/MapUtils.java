package com.summerHack.diningTogether.utils;

import okhttp3.*;


import java.io.IOException;

public interface MapUtils {
    public Response getResponse(String origin, String destination) throws IOException;
    public Long analyzeDistance(Response response) throws IOException;
    public Long getDistance(String origin, String destination) throws IOException;

}
