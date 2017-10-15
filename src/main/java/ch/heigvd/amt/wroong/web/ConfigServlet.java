package ch.heigvd.amt.wroong.web;

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
@WebServlet(name = "ConfigServlet", urlPatterns = {"/config"})
public class ConfigServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // redirige vers la page config.jsp
        request.getRequestDispatcher("/WEB-INF/pages/config.jsp").forward(request, response);
    }
}
