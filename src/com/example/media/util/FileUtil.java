package com.example.media.util;

import com.example.media.classes.Track;
import com.example.media.classes.Video;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class for reading input files.
 */
public class FileUtil {

    public static List<Track> readTracks(String filename) throws IOException {
        List<Track> tracks = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get(filename))) {
            List<String> dataLines = lines
                    .filter(line -> line != null && !line.trim().isEmpty())
                    .skip(1) // skip header
                    .collect(Collectors.toList());

            for (String line : dataLines) {
                // Expected: Title;Artist;Genre;Duration;Rating
                String[] parts = line.split(";");
                if (parts.length < 5) continue; // skip malformed
                try {
                    String title = parts[0].trim();
                    String artist = parts[1].trim();
                    String genre = parts[2].trim();
                    int duration = Integer.parseInt(parts[3].trim());
                    int rating = Integer.parseInt(parts[4].trim());
                    tracks.add(new Track(title, artist, genre, duration, rating));
                } catch (NumberFormatException ex) {

                }
            }
        }

        return tracks;
    }

    public static List<Video> readVideos(String filename) throws IOException {
        List<Video> videos = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get(filename))) {
            List<String> dataLines = lines
                    .filter(line -> line != null && !line.trim().isEmpty())
                    .skip(1) // skip header
                    .collect(Collectors.toList());

            for (String line : dataLines) {
                // Expected: Title;Channel;Category;Duration;Views
                String[] parts = line.split(";");
                if (parts.length < 5) continue; // skip malformed
                try {
                    String title = parts[0].trim();
                    String channel = parts[1].trim();
                    String category = parts[2].trim();
                    int duration = Integer.parseInt(parts[3].trim());
                    int views = Integer.parseInt(parts[4].trim());
                    videos.add(new Video(title, channel, category, duration, views));
                } catch (NumberFormatException ex) {

                }
            }
        }

        return videos;
    }
}
