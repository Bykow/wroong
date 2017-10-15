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
@WebServlet(name = "ClearServlet", urlPatterns = {"/clear"})
public class ClearServlet extends HttpServlet {

    @EJB
    private TweetManager tweetManager;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // vide la table des tweets et redirige sur la page de la liste des tweets
        tweetManager.deleteAll();
        response.sendRedirect("./list");
    }
}
