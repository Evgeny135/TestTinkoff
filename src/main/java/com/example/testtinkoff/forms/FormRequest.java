package com.example.testtinkoff.forms;

public class FormRequest {
    private String text;
    private String target;

    public FormRequest(String text, String target) {
        this.text = text;
        this.target = target;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
