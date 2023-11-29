package com.example.testtinkoff.services;

import com.example.testtinkoff.forms.FormResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class JSONWorker {

    @Qualifier("getAPI")
    @Autowired
    private String API;

    @Qualifier("getFolderId")
    @Autowired
    private String folderId;

    public String generateJSON(String text, String format){
        String[] words = text.split(" ");
        String[] languages = format.split("-");

        JSONObject body = new JSONObject();
        JSONArray arrayWords = new JSONArray();

        body.put("folderId", folderId);
        body.put("sourceLanguageCode",languages[0]);
        body.put("targetLanguageCode",languages[1]);

        for (String word:
             words) {
            arrayWords.add(word);
        }

        body.put("texts",arrayWords);

        return body.toJSONString();
    }

    public String sendRequest(String body){
        try {
            URL url = new URL("https://translate.api.cloud.yandex.net/translate/v2/translate");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");

            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization","Bearer "+API);

            con.setRequestProperty("Accept", "application/json");

            con.setDoOutput(true);

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = body.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            StringBuilder response = new StringBuilder();
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            return response.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public FormResponse createFormResponse(String response){
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject object = (JSONObject) jsonParser.parse(response.toString());

            JSONArray jsonArray = (JSONArray) object.get("translations");

            List<String> words = new ArrayList<>();

            Iterator it = jsonArray.iterator();

            while (it.hasNext()) {
                JSONObject jsonObject = (JSONObject) it.next();

                words.add((String) jsonObject.get("text"));
            }

            return new FormResponse(words);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
