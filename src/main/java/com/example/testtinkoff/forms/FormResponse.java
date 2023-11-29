package com.example.testtinkoff.forms;

import java.util.List;

public class FormResponse {
    private List<String> words;

    public FormResponse() {
    }

    public String getWords() {
        return words.toString();
    }

    public List<String> getListWords(){
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public FormResponse(List<String> words) {
        this.words = words;
    }
}
