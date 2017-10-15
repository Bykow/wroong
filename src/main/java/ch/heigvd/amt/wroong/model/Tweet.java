package ch.heigvd.amt.wroong.model;

import java.sql.Timestamp;

/**
 * Project : wroong
 * Author(s) : Antoine Friant, Lawrence Stalder
 * Date : 29.09.17
 */
public class Tweet {
    private final int id;       // id du tweet (attribué par la base donnée)
    private String content;     // contenu du tweet
    private Timestamp date;     // date du création du tweet

    /**
     * Constructeur
     * @param id
     * @param content
     * @param date
     */
    public Tweet(int id, String content, Timestamp date) {
        this.id = id;
        this.content = content;
        this.date = date;
    }

    /**
     * Accesseur du contenu du tweet
     * @return String content
     */
    public String getContent() {
        return content;
    }

    /**
     * Modifie le contenu du tweet
     * @param content nouveau contenu
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Accesseur de la date de création du tweet
     * @return Timestamp date
     */
    public Timestamp getDate() {
        return date;
    }

    /**
     * Récupère l'id du tweet
     * @return
     */
    public int getId() {
        return id;
    }
}
