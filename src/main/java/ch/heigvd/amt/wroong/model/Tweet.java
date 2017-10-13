package ch.heigvd.amt.wroong.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Project : wroong
 * Author(s) : Antoine Friant
 * Date : 29.09.17
 */
public class Tweet {
    private int id;
    private String content;
    private Timestamp date;

    public Tweet(int id, String content, Timestamp date) {
        this.id = id;
        this.content = content;
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }
}
