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
@WebServlet(name = "ConfigSuccessServlet", urlPatterns = {"/configsuccess"})
public class ConfigSuccessServlet extends HttpServlet {
    @EJB
    private TweetManager tweetManager;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // remplit la table avec le nombre spécifié de tweets de Trump
        tweetManager.populateTweets(Integer.parseInt(request.getParameter("nbOfGeneratedTweets")));
        request.setAttribute("nbOfGeneratedTweets", request.getParameter("nbOfGeneratedTweets"));

        request.getRequestDispatcher("/WEB-INF/pages/configsuccess.jsp").forward(request, response);
    }
}
