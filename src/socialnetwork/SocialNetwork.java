package socialnetwork;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author utente
 */
public class SocialNetwork {


    public static void main(String[] args) throws SQLException, Exception {
        
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(1);
        
        user.showProfile();
        
        ArrayList<User> users = userDAO.findAll();
        for (int i = 0; i < users.size(); i++){
            users.get(i).showProfile();
        }
        
        userDAO.delete(new User("Chicco", "Cacco"));
    }
    
}
