
package tec.emergentes.controlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tec.emergentes.ConexionBD;
import tec.emergentes.modelo.Libro;

/**
 * @author Dreick Lap1
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{    
            String op;
            op=(request.getParameter("op")!=null)?request.getParameter("op"):"list";
            ArrayList<Libro> lista = new ArrayList<Libro>();
            ConexionBD canal = new ConexionBD();
            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            if (op.equals("list")) {
                // para litar los datos 
                String sql ="select * from libros";
                //consulta e selecion y almacen en uns coleccion
                ps= conn.prepareCall(sql);
                rs=ps.executeQuery();
                while(rs.next()){
                    Libro lib = new Libro();
                    lib.setId(rs.getInt("id"));
                    lib.setIsbn(rs.getString("isbn"));
                    lib.setTitulo(rs.getString("titulo"));
                    lib.setCategoria(rs.getString("categoria"));
                    lista.add(lib);
                }
                request.setAttribute("lista", lista);
                //Enviar al index.jsp para mostrar la informacion.
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            if(op.equals("nuevo")){
                Libro li = new Libro ();
                System.out.println(li.toString());
                //el objeto se pone como atributo de request
                request.setAttribute("lib",li);
                // redirecionar a editar.jsp
                request.getRequestDispatcher("editarlibros.jsp").forward(request, response);
               }
            if(op.equals("eliminar")){
                //obtener el id
                int id=Integer.parseInt(request.getParameter("id"));
                // realizar la eliminacion en la base de datos
                String sql= "delete from libro where id=?";
                ps = conn.prepareCall(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                //redireccionamos a main controller
                response.sendRedirect("MainController");
            }
        } catch (SQLException ex){
            System.out.println("Error al Conectar"+ex.getMessage());
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        int id = Integer.parseInt(request.getParameter("id"));
            System.out.println("Valor de Id "+id);
            String isbn = request.getParameter("isbn");
            String titulo = request.getParameter("titulo");
            String categoria = request.getParameter("categoria");
            Libro lib= new Libro();
            lib.setId(id);
            lib.setIsbn(isbn);
            lib.setTitulo(titulo);
            lib.setCategoria(categoria);
            ConexionBD canal = new ConexionBD();
            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            if(id==0){
                String sql = "insert into libros(isbn,titulo,categoria)values (?,?,?)";
                ps=conn.prepareCall(sql);
                ps.setString(1,lib.getIsbn());
                ps.setString(2,lib.getTitulo());
                ps.setString(3,lib.getCategoria());
                ps.executeUpdate();
                }
            else
                { // editar registros
                    
                }
            response.sendRedirect("MainController");               
            } catch (SQLException ex){
                System.out.println("Error en SQL"+ex.getMessage());
            }
    }
}
