package ch.heigvd.amt.wroong.service;

import ch.heigvd.amt.wroong.model.Tweet;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Project : wroong
 * Author(s) : Antoine Friant, Lawrence Stalder
 * Date : 06.10.17
 */
@Startup
@Singleton
public class TweetManager {
    // base de données
    @Resource(lookup = "java:/wroong")
    private DataSource dataSource;

    // intégralité des tweets de Trump, sert à peupler la table
    private List<Tweet> trumpTweets;

    /**
     * fonction appelée au lancement du serveur, sert à construire la liste de trumpTweets
     */
    @PostConstruct
    void init() {
        trumpTweets = new ArrayList<>();

        // lit les fichiers tweets1.json et tweets2.json
        for (int f = 1; f <= 2; f++) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/tweets" + f + ".json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            JsonArray jarray = new JsonParser().parse(reader).getAsJsonArray();

            try {
                // parcourt tous les tweets du fichier json
                for (JsonElement elem : jarray) {
                    // récupère la date du tweet
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
                    Date parsedDate = dateFormat.parse(elem.getAsJsonObject().get("timestamp").getAsString());

                    // créé le tweet
                    Tweet t = new Tweet(1, elem.getAsJsonObject().get("text").getAsString(),
                            new Timestamp(parsedDate.getTime()));

                    // ajoute le tweet à la liste
                    trumpTweets.add(t);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                reader.close();
            } catch (IOException e) {
                Logger.getLogger(TweetManager.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    /**
     * Supprime un tweet désigné par son id
     * @param id
     */
    public void deleteTweet(int id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM tweets WHERE id = ?;");
            pstmt.setInt(1, id);
            pstmt.execute();
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(TweetManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Modifie le contenu d'un tweet
     * @param id id du tweet
     * @param content nouveau contenu
     */
    public void editTweet(int id, String content) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE tweets SET content = ? WHERE id = ?;");
            pstmt.setString(1, content);
            pstmt.setInt(2, id);
            pstmt.execute();
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(TweetManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Remplit la table en choisissant n tweets aléatoires écrits par Trump
     * @param n nombre de tweets à ajouter
     */
    public void populateTweets(int n) {
        try {
            Random r = new Random();
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO tweets (content, created_on) VALUES(?, ?);");
            for (int i = 0; i < n; i++) {
                int index = r.nextInt(trumpTweets.size());
                pstmt.setString(1, trumpTweets.get(index).getContent());
                pstmt.setTimestamp(2, trumpTweets.get(index).getDate());
                pstmt.addBatch();

                // exécute le batch toutes les 1000 insertions
                if (i == 1000) {
                    pstmt.executeBatch();
                }
            }
            pstmt.executeBatch();

            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(TweetManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Ajoute un tweet, sa date de création est la date actuelle (définit par mysql)
     * @param content contenu du tweet
     */
    public void addTweet(String content) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO tweets (content) VALUES(?);");
            pstmt.setString(1, content);
            pstmt.execute();
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(TweetManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * @return tous les tweets de la table
     */
    public List<Tweet> getAllTweets() {
        List<Tweet> tweets = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM tweets;");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String content = rs.getString("content");
                Timestamp created_on = rs.getTimestamp("created_on");
                tweets.add(new Tweet(id, content, created_on));
            }
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(TweetManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return tweets;
    }

    /**
     * @param id
     * @return le tweet correspondant à l'id "id"
     */
    public Tweet getTweet(int id) {
        Tweet tw = null;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM tweets WHERE id = ?;");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            tw = new Tweet(rs.getInt("id"), rs.getString("content"), rs.getTimestamp("created_on"));
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(TweetManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return tw;
    }

    /**
     * Vide la table des tweets
     */
    public void deleteAll() {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM tweets;");
            pstmt.execute();
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(TweetManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
