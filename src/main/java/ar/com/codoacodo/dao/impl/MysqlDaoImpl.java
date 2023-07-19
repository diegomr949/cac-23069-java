package ar.com.codoacodo.dao.impl;

import java.sql.Connection;//es una interface de JDBC que está implementado en el conector=driver=depedencia=artefacto=libreria de mysql
import java.sql.Date;
//que agregamos al pom.xml
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import ar.com.codoacodo.dao.AdministradorDeConexiones;
import ar.com.codoacodo.dao.DAO;
import ar.com.codoacodo23069.Producto;

/* implemento el contrato = interface DAO
 * 
 */
public class MysqlDaoImpl implements DAO{
    
    public void create(Producto producto) throws Exception{
        
        //vamos a ver la clase que viene JDBC
        Connection connection = AdministradorDeConexiones.getConnection();//f5
        
        //ahora si armo el sql para hacer un INSERT                                      1  2  3  4  5
        String sql = "INSERT INTO productos (titulo, fecha, autor,codigo,imagen, precio) VALUES (?, ?, ?, ?, ? , ?)";
        PreparedStatement pst = connection.prepareStatement(sql);

        //ahora seteo los valores 
        pst.setString(1, producto.getTitulo());
        pst.setDate(2, dateFrom(producto.getFecha()));        
        pst.setString(3, producto.getAutor());
        pst.setString(4, producto.getCodigo());
        pst.setString(5, producto.getImagen());
        pst.setDouble(6, producto.getPrecio());

        pst.executeUpdate();
        //ResultSet
    }
    

    @Override
    public Producto getById(Long id) throws Exception {//1
        String sql = "select * from productos where id =?";

        //Obtener la Conection
        Connection con = AdministradorDeConexiones.getConnection();

        //PreparedStatement con mi sql
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setLong(1,id);

        Producto producto = null;

        ResultSet res = pst.executeQuery();

        if(res.next()) {
            
            String titulo = res.getString(2);
            double precio = res.getDouble(3);
            String img = res.getString(4);
            Date fecha = res.getDate(5);
            String codigo = res.getString(6);
            String autor = res.getString(7);

            producto = new Producto (id, titulo, precio, img, fecha.toLocalDate(), codigo, autor);
         }
        return producto;
    }


    @Override
    public void delete(Long id) throws Exception{
        Connection connection = AdministradorDeConexiones.getConnection();//f5
 
        String sql = "delete from productos where id = ?";
        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setLong(1, id);

        pst.executeUpdate();//insert/update/delete
    }

    @Override
    public ArrayList<Producto> findAll() throws Exception{
        //vamos a ver la clase que viene JDBC
        Connection connection = AdministradorDeConexiones.getConnection();//f5
        
        //ahora si armo el sql para hacer un INSERT                                      1  2  3  4  5
        String sql = "select * from productos";
        PreparedStatement pst = connection.prepareStatement(sql);
        
        ResultSet res =  pst.executeQuery();
        
        ArrayList<Producto> listado  = new ArrayList<>();
        //extraer los datos del res!
        while(res.next()) {
            //aca uds hace la magia
            Long id =res.getLong(1);
            String titulo = res.getString(2);
            double precio = res.getDouble(3);
            String img = res.getString(4);
            Date fecha = res.getDate(5);
            String codigo = res.getString(6);
            String autor = res.getString(7);
            
            listado.add(new Producto(id, titulo, precio, img, fecha.toLocalDate(), codigo, autor));
        }

        return listado;
    }

    private Date dateFrom(LocalDate ldt) {
        java.util.Date date = Date.from(ldt.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return new java.sql.Date(date.getTime());
    }

    @Override
    public void update(Producto producto) throws Exception{
        Connection connection = AdministradorDeConexiones.getConnection();//f5
        
        //ahora si armo el sql update                     
        String sql = "update productos set titulo=?, precio=?, imagen=?, autor=? where id=?";
        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setString(1, producto.getTitulo());
        pst.setDouble(2, producto.getPrecio());
        pst.setString(3, producto.getImagen());
        pst.setString(4,producto.getAutor());
        pst.setLong(5, producto.getId());

        pst.executeUpdate();
   
    }

    

       


   

   
    
    
}
