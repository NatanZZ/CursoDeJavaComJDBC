package application;

import jdbc.DB;

import java.io.IOException;
import java.sql.Connection;

public class Program {
    public static void main(String[] args) throws IOException {

        Connection conn = DB.getConnection();
        DB.closeConnection();
        }

}