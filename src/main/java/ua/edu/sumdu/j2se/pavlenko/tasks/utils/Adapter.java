package ua.edu.sumdu.j2se.pavlenko.tasks.utils;

import java.time.LocalDateTime;

public class Adapter {
    public static LocalDateTime timeAdapter(String string) {
        // 1
        //return LocalDateTime.parse(string, DateTimeFormatter.ofPattern("dd.MM.yyyy'T'HH:mm:ss"));
        // 2
        return LocalDateTime.parse(string);
    }
}
