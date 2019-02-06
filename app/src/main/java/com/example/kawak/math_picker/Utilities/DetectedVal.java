package com.example.kawak.math_picker.Utilities;

public class DetectedVal {
    public static String value = "";
    private static ChangeListener listener;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        if (listener != null) listener.onChange();
    }

    public ChangeListener getListener() {
        return listener;
    }

    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }

    public interface ChangeListener {
        void onChange();
    }
}
