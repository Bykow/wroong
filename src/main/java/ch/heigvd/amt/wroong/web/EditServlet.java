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
@WebServlet(name = "EditServlet", urlPatterns = {"/edit"})
public class EditServlet extends HttpServlet {

    @EJB
    private TweetManager tweetManager;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // affiche le formulaire de modification du tweet
        request.setAttribute("tweet", tweetManager.getTweet(Integer.parseInt(request.getParameter("id"))));
        request.getRequestDispatcher("/WEB-INF/pages/edit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // édite le tweet avec son nouveau contenu, renvoie vers la liste des tweets
        tweetManager.editTweet(Integer.parseInt(request.getParameter("id")), request.getParameter("content"));
        response.sendRedirect("./list");
    }

}
