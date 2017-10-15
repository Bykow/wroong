package ch.heigvd.amt.wroong.web;

import ch.heigvd.amt.wroong.service.TweetManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project : wroong
 * Author(s) : Antoine Friant, Lawrence Stalder
 * Date : 04.10.17
 */
@WebServlet(name = "ListServlet", urlPatterns = {"/list"})
public class ListServlet extends HttpServlet {

    @EJB
    private TweetManager tweetManager;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // récupère la liste des tweets et l'affiche dans la page list.jsp
        request.setAttribute("tweets", tweetManager.getAllTweets());
        request.getRequestDispatcher("/WEB-INF/pages/list.jsp").forward(request, response);
    }
}
