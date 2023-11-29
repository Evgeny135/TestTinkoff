package com.example.testtinkoff.services;

import com.example.testtinkoff.forms.FormRequest;
import com.example.testtinkoff.forms.FormResponse;
import com.example.testtinkoff.entity.Requests;
import com.example.testtinkoff.repository.RequestsRepository;
import com.example.testtinkoff.repository.TranslationsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private JSONWorker jsonWorker;

    @Autowired
    private RequestsRepository requestsRepository;

    @Autowired
    private TranslationsRepository translationsRepository;

    public String getResponse(FormRequest formRequest, String ipAddress, LocalDateTime timeRequest){
        Requests requests = new Requests();
        String body = jsonWorker.generateJSON(formRequest.getText(), formRequest.getTarget());

        requests.setInput(formRequest.getText());
        requests.setIPAddress(ipAddress);
        requests.setParameters(formRequest.getTarget());
        requests.setRequestTime(timeRequest);

        String response = jsonWorker.sendRequest(body);
        FormResponse formResponse = jsonWorker.createFormResponse(response);

        requests.setOutput(formResponse.getWords());

        int index = requestsRepository.saveInfo(requests);
        translationsRepository.save(formResponse,requests,index);

        return response;
    }
}
