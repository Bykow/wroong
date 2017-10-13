package ch.heigvd.amt.wroong.web;

import ch.heigvd.amt.wroong.service.DataManager;

import javax.ejb.EJB;
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
@WebServlet(name = "AddSuccessServlet", urlPatterns = {"/addsuccess"})
public class AddSuccessServlet extends HttpServlet {

    @EJB
    private DataManager dataManager;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        dataManager.addTweet(request.getParameter("content"));

        request.getRequestDispatcher("/WEB-INF/pages/addsuccess.jsp").forward(request, response);
    }
}
