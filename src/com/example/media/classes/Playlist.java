package com.example.media.classes;

import java.util.ArrayList;
import java.util.List;


public class Playlist<T extends Media> {
    private List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public List<T> getItems() {
        return items;
    }


    public int getTotalDuration() {
        return items.stream().mapToInt(Media::getDuration).sum();
    }


    public void printAll() {
        items.forEach(System.out::println);
    }
}
