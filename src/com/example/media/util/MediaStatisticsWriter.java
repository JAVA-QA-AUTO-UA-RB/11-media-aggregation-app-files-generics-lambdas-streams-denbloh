package com.example.media.util;

import com.example.media.classes.Playlist;
import com.example.media.classes.Track;
import com.example.media.classes.Video;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for writing media statistics to output files.
 */
public class MediaStatisticsWriter {

    public static void writeTrackStats(Playlist<Track> playlist, String filename) throws IOException {
        List<Track> items = playlist.getItems();
//        int avgDuration = (int) Math.round(
//                items.stream().mapToInt(Track::getDuration).average().orElse(0.0)
//        );
        int count = items.size();
        int avgDurationSec = (int) Math.round(
                items.stream().mapToInt(Track::getDuration).average().orElse(0.0)
        );
        int avgMin = avgDurationSec / 60;
        int avgSec = avgDurationSec % 60;


        List<Track> top3 = items.stream()
                .sorted(Comparator.comparingInt(Track::getRating).reversed()
                        .thenComparing(Comparator.comparingInt(Track::getDuration).reversed()))
                .limit(3)
                .collect(Collectors.toList());


        List<Track> popTracks = items.stream()
                .filter(t -> t.getGenre() != null && t.getGenre().equalsIgnoreCase("Pop"))
                .collect(Collectors.toList());


        StringBuilder sb = new StringBuilder();
        sb.append("Tracks count: ").append(count).append(System.lineSeparator());
        sb.append(System.lineSeparator());
//        sb.append("Average duration: ").append(avgDuration).append(" seconds").append(System.lineSeparator());
        sb.append("Average duration: ").append(avgMin)
                .append(" min ").append(avgSec).append(" sec")
                .append(System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append("Top 3 tracks by rating:").append(System.lineSeparator());
        for (int i = 0; i < top3.size(); i++) {
            Track t = top3.get(i);
            sb.append(i + 1).append(". ").append(t.getTitle())
                    .append(" (rating ").append(t.getRating()).append(")").append(System.lineSeparator());
        }
        sb.append(System.lineSeparator());
        sb.append("Pop tracks:").append(System.lineSeparator());
        for (Track t : popTracks) {
            sb.append("- ").append(t.getTitle()).append(System.lineSeparator());
        }


        try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Paths.get(filename)))) {
            pw.print(sb.toString());
        }
    }


    public static void writeVideoStats(Playlist<Video> playlist, String filename) throws IOException {
        List<Video> items = playlist.getItems();

        int count = items.size();
//        int avgDuration = (int) Math.round(
//                items.stream().mapToInt(Video::getDuration).average().orElse(0.0)
//        );
        int avgDurationSec = (int) Math.round(
                items.stream().mapToInt(Video::getDuration).average().orElse(0.0)
        );
        int avgMin = avgDurationSec / 60;
        int avgSec = avgDurationSec % 60;


        List<Video> top3 = items.stream()
                .sorted(Comparator.comparingInt(Video::getViews).reversed())
                .limit(3)
                .collect(Collectors.toList());


        List<Video> eduVideos = items.stream()
                .filter(v -> v.getCategory() != null && v.getCategory().equalsIgnoreCase("Education"))
                .collect(Collectors.toList());


        StringBuilder sb = new StringBuilder();
        sb.append("Videos count: ").append(count).append(System.lineSeparator());
//        sb.append("Average duration: ").append(avgDuration).append(" seconds").append(System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append("Average duration: ").append(avgMin)
                .append(" min ").append(avgSec).append(" sec")
                .append(System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append("Top 3 videos by views:").append(System.lineSeparator());
        for (int i = 0; i < top3.size(); i++) {
            Video v = top3.get(i);
            sb.append(i + 1).append(". ").append(v.getTitle())
                    .append(" (").append(v.getViews()).append(" views)").append(System.lineSeparator());
        }
        sb.append(System.lineSeparator());
        sb.append("Education videos:").append(System.lineSeparator());
        for (Video v : eduVideos) {
            sb.append("- ").append(v.getTitle()).append(System.lineSeparator());
        }


        try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Paths.get(filename)))) {
            pw.print(sb.toString());
        }
    }
}
