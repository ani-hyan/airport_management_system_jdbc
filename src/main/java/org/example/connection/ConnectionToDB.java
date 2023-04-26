package org.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionToDB {
    private static final String url = "jdbc:postgresql://localhost:5432/airline_system";
    private static final String user = "postgres";
    private static final String pass = "dumbo";
    private static final Connection con;

    //todo change static exception
    static {
        try {
            con = DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionToDB() {
    }
    public static Connection getConnection(){
        return con;
    }

    public static void closeConnection(){
        try{
            con.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}