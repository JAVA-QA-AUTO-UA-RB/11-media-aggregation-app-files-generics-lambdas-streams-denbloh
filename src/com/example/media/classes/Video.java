package com.example.media.classes;


public class Video extends Media {
    private String channel;
    private String category;
    private int views;

    public Video(String title, String channel, String category, int duration, int views) {
        super(title, duration);
        this.channel = channel;
        this.category = category;
        this.views = views;
    }

    public String getChannel() {
        return channel;
    }

    public String getCategory() {
        return category;
    }

    public int getViews() {
        return views;
    }

    @Override
    public String toString() {
        return String.format("%s - %s [%s] (%d views, %s)", title, channel, category, views, getDurationInMinutes());
    }
}
