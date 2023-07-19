package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.sql.SQLException;

import ar.com.codoacodo.dao.DAO;
import ar.com.codoacodo.dao.impl.MysqlDaoImpl;
import ar.com.codoacodo23069.Producto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/BuscadorController")
public class BuscadorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = (req.getParameter("id"));
        
        
        DAO dao = new MysqlDaoImpl();

        try {
            Producto producto = dao.getById(Long.parseLong(id));

            req.setAttribute("producto", producto);

            getServletContext().getRequestDispatcher("/editar.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
