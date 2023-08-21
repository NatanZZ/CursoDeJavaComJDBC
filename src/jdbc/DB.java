package jdbc;

import jdbc.DbException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    /*
    A EXCECÃO É PERSONALIZADA PORQUE ALGUMAS SÃO OBRIGATÓRIAS DE SEREM TRATADAS,
    ENTÃO O PROGRAMA NÃO PRECISA UTILIZAR SEMPRE TRY E CATCH,
    APENAS SE NECESSÁRIO
     */

    private static Connection conn = null;
    // MÉTODO PARA INICIAR A CONEXÃO
    public static Connection getConnection() throws IOException {
        if(conn == null){// verifica se conn está vazio, se sim, inicia a conexão
            try {
                //carrega as propriedades
                Properties props = loadProperties();
                String url = props.getProperty("dburl");//recebe a url do banco
                conn = DriverManager.getConnection(url, props);//inicia conexão(instanciar objeto do tipo Connection) e a salva
            }catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    //MÉTODO PARA PARAR A CONEXÃO
    public static void closeConnection(){
        if(conn != null){
            try{
                conn.close();

            }catch (SQLException e){
                throw new DbException(e.getMessage());
            }

        }

    }

    //MÉTODO PARA CARREGAR AS PROPRIEDADES
    private static Properties loadProperties() throws IOException {
        try(FileInputStream fs = new FileInputStream("db.properties")){//pega a propriedade
            Properties props = new Properties();//instancia uma nova propriedade
            props.load(fs);//carrega a propriedade

            return props;

        }catch (IOException e){
            throw new DbException(e.getMessage());
        }
    }

    public static void closeStatement(Statement st){
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}
