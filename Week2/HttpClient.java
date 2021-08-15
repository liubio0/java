package com.liuzm.nio01;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class HttpClient {
    private static String run(String url) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            return response.body().string();
        }
        else{
            throw new IOException("Unexcepted code :" + response);
        }
    }

    public static void main(String[] args) throws Exception{
        String url = "http://127.0.0.1:8808/test";
        System.out.println(run(url));
        url = "http://127.0.0.1:8801/test";
        System.out.println(run(url));
    }
}
