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
@WebServlet(name = "AddSuccessServlet", urlPatterns = {"/addsuccess"})
public class AddSuccessServlet extends HttpServlet {

    @EJB
    private TweetManager tweetManager;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ajoute le tweet et redirige vers la page addsuccess.jsp
        tweetManager.addTweet(request.getParameter("content"));
        request.getRequestDispatcher("/WEB-INF/pages/addsuccess.jsp").forward(request, response);
    }
}
