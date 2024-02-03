package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void getEventFromForDisplayTest(){
        String content = "Project";
        String from = "2022-12-31 12:31";
        String to = "2023-01-02 03:04";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime parsedFromDateTime = LocalDateTime.parse(from, formatter);
        LocalDateTime parsedToDateTime = LocalDateTime.parse(to, formatter);
        Event newTask = new Event(content, parsedFromDateTime, parsedToDateTime);
        assertEquals("DECEMBER 31 2022 12:31PM", newTask.getEventFromForDisplay());
    }

    @Test
    public void getEventToForDisplayTest(){
        String content = "Project";
        String from = "2022-12-31 12:31";
        String to = "2023-01-02 03:04";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime parsedFromDateTime = LocalDateTime.parse(from, formatter);
        LocalDateTime parsedToDateTime = LocalDateTime.parse(to, formatter);
        Event newTask = new Event(content, parsedFromDateTime, parsedToDateTime);
        assertEquals("JANUARY 02 2023 03:04AM", newTask.getEventToForDisplay());
    }
}
