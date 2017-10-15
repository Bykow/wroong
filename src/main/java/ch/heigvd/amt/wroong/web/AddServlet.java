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
@WebServlet(name = "AddServlet", urlPatterns = {"/add"})
public class AddServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // redirige sur la page add.jsp
        request.getRequestDispatcher("/WEB-INF/pages/add.jsp").forward(request, response);
    }
}
