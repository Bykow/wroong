package ch.heigvd.amt.wroong.model;

import java.util.Date;

/**
 * Project : wroong
 * Author(s) : Antoine Friant
 * Date : 29.09.17
 */
public class Tweet {
    private String content;
    private Date date;

    public Tweet(String content, Date date) {
        this.content = content;
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
