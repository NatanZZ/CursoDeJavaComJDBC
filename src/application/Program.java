package application;

import jdbc.DB;
import jdbc.DbException;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
    public static void main(String[] args) throws IOException {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();//conecta ao banco de dados

            st = conn.createStatement(); //instancia objeto do tipo statemente, para realizar querys

            rs =  st.executeQuery("select * from department");// realiza a query

            while (rs.next()){//enquanto houver itens
                System.out.println(rs.getInt("Id") + "|" + rs.getString("Name"));//mostra o id e o name
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }

        }

}