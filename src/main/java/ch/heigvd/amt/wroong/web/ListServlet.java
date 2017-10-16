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
        int p = request.getParameter("p") == null ? 1 : Integer.parseInt(request.getParameter("p"));
        if (p < 1) {
            p = 1;
        }

        String orderBy = request.getParameter("orderby") == null ? "created_on" : request.getParameter("orderby");
        if (!orderBy.equals("created_on") && !orderBy.equals("id") && !orderBy.equals("content")) {
            orderBy = "created_on";
        }

        String filter = request.getParameter("filter") == null ? "" : request.getParameter("filter");

        boolean descending = request.getParameter("descending") == null
                || Boolean.parseBoolean(request.getParameter("descending"));

        // récupère la liste des tweets et l'affiche dans la page list.jsp
        request.setAttribute("tweets", tweetManager.getTweets(p, filter, orderBy, descending));
        // envoie l'info sur les filtres à la page
        request.setAttribute("p", p);
        request.setAttribute("filter", filter);
        request.setAttribute("descending", descending);
        request.setAttribute("orderby", orderBy);
        request.setAttribute("count", tweetManager.countTweets(filter));
        request.getRequestDispatcher("/WEB-INF/pages/list.jsp").forward(request, response);
    }
}
