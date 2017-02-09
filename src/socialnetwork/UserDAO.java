package socialnetwork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author utente
 */
public class UserDAO {
    
    void delete (User user) throws SQLException, Exception{
    Connection con = null;    
    Statement stmt = null;
    ResultSet rs = null;    
    
        String db_user = "root";
        String db_user_password = "root";

        con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/socialnetwork", 
                db_user, db_user_password);

        try {
            stmt = con.createStatement();
            User usus;
//            if (user.id != null){
                stmt.executeUpdate(String.format("DELETE FROM user\n" +
                                                 "WHERE " + ((user.id != null) ? ("id = " + user.id) : ("name = '" + user.name + "' and password = '" + user.password + "'"))));
//            } else {
//                UserDAO userDAO = new UserDAO();
//                ArrayList<User> users = userDAO.findAllByNamePassword(user);
//                for(User u: users){
//                    stmt.executeUpdate( String.format( "\"DELETE FROM Registration \" \n" +
//                                        "\"WHERE name = '%s', password = '%s'", u.name, u.password));
//                }
//            }
        }
        catch(SQLException e) {
            System.out.println("Errore in delete: " + e);
            throw e;
        }
        finally {
            if (rs != null) { try {rs.close();} catch(SQLException e){}}
            if (stmt != null) { try {stmt.close();} catch(SQLException e){}}
            if(con != null) {try {con.close();} catch (SQLException e){}}
        }  
    }    
    
    int save (User user) throws SQLException, Exception{
    Connection con = null;    
    Statement stmt = null;
    ResultSet rs = null;    
    
        String db_user = "root";
        String db_user_password = "root";

        con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/socialnetwork", 
                db_user, db_user_password);

        try {
            stmt = con.createStatement();
            String sql = String.format( "INSERT INTO `user` ( " + ((user.id == null) ? "" : " id, ") + "`name`, `password`)\n" +
                                        "VALUES ( " + ((user.id == null ) ? "" : user.id + ", ") + "'%s', '%s') " +
                                        "on duplicate key " + 
                                        "update name='%s', password='%s'", user.name, user.password, user.name, user.password);

            int rows = stmt.executeUpdate(sql);
            
            return rows;
        }
        catch(SQLException e) {
            System.out.println("Errore in save: " + e);
            throw e;
        }
        
        finally {
            if (rs != null) { try {rs.close();} catch(SQLException e){}}
            if (stmt != null) { try {stmt.close();} catch(SQLException e){}}
            if(con != null) {try {con.close();} catch (SQLException e){}}
        }  
    }    
    
    User findById(Integer id) throws SQLException, Exception{
    Connection con = null;    
    Statement stmt = null;
    ResultSet rs = null;    
    
        String db_user = "root";
        String db_user_password = "root";

        con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/socialnetwork", 
                db_user, db_user_password);

        try {
            stmt = con.createStatement();

            String sql = String.format("select name, password from user where id=" + id);

            rs = stmt.executeQuery(sql);

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            if(rs.next()){
                String name = rs.getString("name");
                String pwd = rs.getString("password");

                User user = new User(id, name, pwd);                
                return user;
            } else {
                throw new Exception("Attenzione utente non trovato");
            }
        }
        catch(SQLException e) {
            System.out.println("Errore in findById: " + e);
            throw e;
        }
        finally {
            if (rs != null) { try {rs.close();} catch(SQLException e){}}
            if (stmt != null) { try {stmt.close();} catch(SQLException e){}}
            if(con != null) {try {con.close();} catch (SQLException e){}}
        }       
        
    }    
    
    ArrayList<User> findAll() throws SQLException, Exception{
        Connection con = null;    
        Statement stmt = null;
        ResultSet rs = null;  
        ArrayList<User> users = new ArrayList<User>(); 
    
        String db_user = "root";
        String db_user_password = "root";

        con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/socialnetwork", 
                db_user, db_user_password);

        try {
            stmt = con.createStatement();

            String sql = String.format("select id, name, password from user");

            rs = stmt.executeQuery(sql);

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            
            int i = 0;
            while(rs.next()){
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String pwd = rs.getString("password");

                users.add(i, new User(id, name, pwd));
                i++;
            } 
            
            if(users.size()>0){                               
                return users;
            } else {
                throw new Exception("Attenzione: nessun utente non trovato");
            }
        }
        catch(SQLException e) {
            System.out.println("Errore in findAll: " + e);
            throw e;
        }
        finally {
            if (rs != null) { try {rs.close();} catch(SQLException e){}}
            if (stmt != null) { try {stmt.close();} catch(SQLException e){}}
            if(con != null) {try {con.close();} catch (SQLException e){}}
        }     
    }
    
    ArrayList<User> findAllByNamePassword(User user) throws SQLException, Exception{
        Connection con = null;    
        Statement stmt = null;
        ResultSet rs = null;  
        ArrayList<User> users = new ArrayList<User>(); 
    
        String db_user = "root";
        String db_user_password = "root";

        con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/socialnetwork", 
                db_user, db_user_password);

        try {
            stmt = con.createStatement();

            String sql =  String.format("select id, name, password from user where " +
                                        ((user.id == null) ? "" : " id = "+ user.id +", ") +
                                        "name = '%s', password = '%s'", user.name, user.password);

            rs = stmt.executeQuery(sql);

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            
            int i = 0;
            while(rs.next()){
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String pwd = rs.getString("password");

                users.add(i, new User(id, name, pwd));
                i++;
            } 
            
            if(users.size()>0){                               
                return users;
            } else {
                throw new Exception("Attenzione: nessun utente non trovato");
            }
        }
        catch(SQLException e) {
            System.out.println("Errore in findAll: " + e);
            throw e;
        }
        finally {
            if (rs != null) { try {rs.close();} catch(SQLException e){}}
            if (stmt != null) { try {stmt.close();} catch(SQLException e){}}
            if(con != null) {try {con.close();} catch (SQLException e){}}
        }       
        
    
    }
}
