package com.example.roomhunter;

public class Features {
    public int getFeaturesImageView() {
        return featuresImageView;
    }

    public String getFeaturesTextView() {
        return featuresTextView;
    }

    public Features(int imageView, String textView){
        this.featuresImageView = imageView;
        this.featuresTextView = textView;

    }

    public void setFeaturesImageView(int featuresImageView) {
        this.featuresImageView = featuresImageView;
    }

    public void setFeaturesTextView(String featuresTextView) {
        this.featuresTextView = featuresTextView;
    }

    private int featuresImageView;
    private String featuresTextView;
}

