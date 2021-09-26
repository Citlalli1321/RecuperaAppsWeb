package mx.edu.utez.server;

import mx.edu.utez.database.ConnectionMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Handler {
    Connection con;
    PreparedStatement pstm;
    ResultSet rs;

    public int suma(int x, int y){
        return x + y;
    }

    public boolean createUser(String name, String lastname, String email, String password){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            pstm = con.prepareCall("INSERT INTO `user`(name, lastname, email, password, date_registered, status)VALUES(?,?,?,?,NOW(),1);");
            pstm.setString(1, name);
            pstm.setString(2, lastname);
            pstm.setString(3, email);
            pstm.setString(4, password);

            flag = pstm.executeUpdate() == 1;
        }catch(SQLException e){
            System.out.println("Error" + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, pstm);
        }

        return flag;
    }

    public boolean updateUser(int id, String name, String lastname, String email, String password){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            pstm = con.prepareCall("UPDATE `user` SET name = ?, lastname = ?, email = ?, password = ? WHERE id = ?;");
            pstm.setString(1, name);
            pstm.setString(2, lastname);
            pstm.setString(3, email);
            pstm.setString(4, password);
            pstm.setInt(5, id);

            flag = pstm.executeUpdate() == 1;
        }catch(SQLException e){
            System.out.println("Error" + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, pstm);
        }

        return flag;
    }

    public boolean deleteUser(int id){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            pstm = con.prepareCall("DELETE FROM `user` WHERE id = ?;");
            pstm.setInt(1, id);

            flag = pstm.executeUpdate() == 1;
        }catch(SQLException e){
            System.out.println("Error" + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, pstm);
        }

        return flag;
    }
}
