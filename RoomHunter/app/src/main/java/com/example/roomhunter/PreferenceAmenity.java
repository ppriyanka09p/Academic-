package com.example.roomhunter;

public class PreferenceAmenity {
    public int getPrefImageView() {
        return prefImageView;
    }

    public String getPrefTextView() {
        return prefTextView;
    }

    public PreferenceAmenity(int imageView, String textView){
        this.prefImageView = imageView;
        this.prefTextView = textView;

    }

    public void setPrefImageView(int featuresImageView) {
        this.prefImageView = featuresImageView;
    }

    public void setPrefTextView(String featuresTextView) {
        this.prefTextView = featuresTextView;
    }

    private int prefImageView;
    private String prefTextView;
}
