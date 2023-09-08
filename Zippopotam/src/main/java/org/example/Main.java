package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException{
        URL url = null;
        HttpURLConnection connectionObject = null;
        int responseCode = 0;
        String urlString = "https://api.zippopotam.us/us/33162";

        try{
            url = new URL(urlString);
        }catch (Exception e){
            System.out.println("invalid url : Please check it !!!");
        }

        // Building connection to url
        try{
            connectionObject = (HttpURLConnection) url.openConnection();
            responseCode = connectionObject.getResponseCode();
        }catch (Exception e){
            System.out.println("Not able to connect. Please try again !!!!");
        }

        //Extracting data from the connectioObject which has all raw data in bytes
        if(responseCode == 200){
            BufferedReader input = new BufferedReader(new InputStreamReader(connectionObject.getInputStream()));
            StringBuilder apiData = new StringBuilder();
            String currentReadLine = null;
            while ((currentReadLine = input.readLine())!= null) {
                apiData.append(currentReadLine);
            }
            try{
                input.close();
            }catch (IOException e){
                throw new RuntimeException(e);
            }

//            System.out.println(apiData.toString());
            JSONObject jsonAPIResponse = new JSONObject(apiData.toString());
            System.out.println(jsonAPIResponse);
            System.out.println("Getting JSON data :");
            System.out.println(jsonAPIResponse.get("places"));
        }else{
            System.out.println("API call could not be made!!!");
        }
    }
}