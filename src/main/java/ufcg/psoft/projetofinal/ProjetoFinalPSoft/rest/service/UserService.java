package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.service;

import org.springframework.stereotype.Service;

import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.user.UserNotFoundException;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.user.UserWithEmailAlreadyRegistered;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.dao.UserDAO;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.User;

import java.util.List;

@Service
public class UserService {

    private final UserDAO<User, String> userDAO;

    UserService(UserDAO<User, String> userDAO) {
        this.userDAO = userDAO;
    }

    public User create(User user) {
    	User foundUser = userDAO.findByEmail(user.getEmail());
    	if (foundUser != null) {
    		System.out.println("User " + user.getEmail() + " already registered!!!");
    		throw new UserWithEmailAlreadyRegistered(user.getEmail());
    	}
    	System.out.println("I'm saving user " + user.getEmail() + " on the db.");
        return userDAO.save(user);
    }

    public User update(User userToUpdate) {

        User user = userDAO.findByEmail(userToUpdate.getEmail());

        if (user == null)
            throw new  UserNotFoundException("Could not update. The user does not exist.");

        return userDAO.save(userToUpdate);
    }

    public User findByEmail(String login) {
        return userDAO.findByEmail(login);
    }
    
    // Just for testing purposes
    public List<User> findAll() {
        return userDAO.findAll();
    }

}
