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
@WebServlet(name = "DeleteServlet", urlPatterns = {"/delete"})
public class DeleteServlet extends HttpServlet {

    @EJB
    private TweetManager tweetManager;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // supprime le tweet
        tweetManager.deleteTweet(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("./list");
    }
}
