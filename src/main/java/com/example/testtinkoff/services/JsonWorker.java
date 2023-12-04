package com.example.testtinkoff.services;


import com.example.testtinkoff.forms.FormResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class JsonWorker {

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
