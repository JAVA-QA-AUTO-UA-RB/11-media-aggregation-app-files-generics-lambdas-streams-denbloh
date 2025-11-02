package com.example.media.classes;


public abstract class Media {
    protected String title;
    protected int duration; // in seconds

    public Media(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }


    public String getDurationInMinutes() {
        int minutes = duration / 60;
        int seconds = duration % 60;
        return minutes + " min " + seconds + " sec";
    }

    @Override
    public String toString() {
        return title + " (" + duration + " sec)";
    }
}
