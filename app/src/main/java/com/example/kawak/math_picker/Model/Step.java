package com.example.kawak.math_picker.Model;

public class Step {
    private String brfore;
    private String change;
    private String after;

    public Step() { }

    public Step(String brfore, String change, String after) {
        this.brfore = brfore;
        this.change = change;
        this.after = after;
    }

    public String getBrfore() {
        return brfore;
    }

    public void setBrfore(String brfore) {
        this.brfore = brfore;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }
}
