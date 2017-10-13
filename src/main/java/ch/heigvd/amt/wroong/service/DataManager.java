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
 * Author(s) : Antoine Friant
 * Date : 06.10.17
 */
@Startup
@Singleton
public class DataManager {
    @Resource(lookup = "java:/wroong")
    private DataSource dataSource;

    private List<Tweet> trumpTweets;

    @PostConstruct
    void init() {
        trumpTweets = new ArrayList<>();

        for (int f = 1; f <= 2; f++) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/tweets" + f + ".json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            JsonArray jarray = new JsonParser().parse(reader).getAsJsonArray();
            try {
                for (JsonElement elem : jarray) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
                    Date parsedDate = dateFormat.parse(elem.getAsJsonObject().get("timestamp").getAsString());

                    Tweet t = new Tweet(1, elem.getAsJsonObject().get("text").getAsString(),
                            new Timestamp(parsedDate.getTime()));

                    trumpTweets.add(t);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                reader.close();
            } catch (IOException e) {
                Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public void deleteTweet(int id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM tweets WHERE id = ?;");
            pstmt.setInt(1, id);
            pstmt.execute();
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void editTweet(int id, String content) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE tweets SET content = ? WHERE id = ?;");
            pstmt.setString(1, content);
            pstmt.setInt(2, id);
            pstmt.execute();
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

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
                if (i == 1000) {
                    pstmt.executeBatch();
                }
            }
            pstmt.executeBatch();
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void addTweet(String content) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO tweets (content) VALUES(?);");
            pstmt.setString(1, content);
            pstmt.execute();
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

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
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return tweets;
    }
}
