package ch.heigvd.amt.wroong.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project : wroong
 * Author(s) : Antoine Friant
 * Date : 04.10.17
 */
@WebServlet(name = "ConfigSuccessServlet", urlPatterns = {"/configsuccess"})
public class ConfigSuccessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int nbOfGeneratedTweets = Integer.parseInt(request.getParameter("nbOfGeneratedTweets"));
        request.setAttribute("nbOfGeneratedTweets", nbOfGeneratedTweets);

        request.getRequestDispatcher("/WEB-INF/pages/configsuccess.jsp").forward(request, response);
    }
}
