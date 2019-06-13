package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.service;

import org.springframework.stereotype.Service;

import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.user.UserNotFoundException;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.user.UserWithLoginAlreadyRegistered;
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
    	User foundUser = userDAO.findByLogin(user.getLogin());
    	if (foundUser != null) {
    		System.out.println("User " + user.getLogin() + " already registered!!!");
    		throw new UserWithLoginAlreadyRegistered(user.getLogin());
    	}
    	System.out.println("I'm saving user " + user.getLogin() + " on the db.");
        return userDAO.save(user);
    }

    public User update(User userToUpdate) throws UserNotFoundException {

        User user = userDAO.findByLogin(userToUpdate.getLogin());

        if (user == null)
            throw new  UserNotFoundException("Could not update. The user does not exist.");

        return userDAO.save(userToUpdate);
    }

    public User findByLogin(String login) {
        return userDAO.findByLogin(login);
    }
    
    public List<User> findAll() {
        return userDAO.findAll();
    }

}
