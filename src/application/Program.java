package application;

import jdbc.DB;
import jdbc.DbException;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) throws IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DB.getConnection();
            //prepara uma insercão
            ps = conn.prepareStatement("INSERT INTO seller (Name, Email, Birthdate, BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);//placeholder para inserir valores e retorna o ID
            ps.setString(1, "João da Silva");
            ps.setString(2, "js@gmail.com");
            ps.setDate(3, new java.sql.Date(sdf.parse("01/02/1975").getTime()));
            ps.setDouble(4, 3000.00);
            ps.setInt(5, 2);

            int rowsAffected = ps.executeUpdate();

            if(rowsAffected > 0){
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! ID = "+id);
                }
            }else {
                System.out.println("No rows affected");
            }

        }catch (SQLException | ParseException e){
            e.printStackTrace();
        } finally {
            DB.closeStatement(ps);
            DB.closeConnection();
        }

        }

}