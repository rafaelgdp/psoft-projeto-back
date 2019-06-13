package ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.user;

@SuppressWarnings("serial")
public class UserWithLoginAlreadyRegistered extends RuntimeException {
	public UserWithLoginAlreadyRegistered(String login) {
        super("There is a user with this login " + login + "registered in our database! ");
    }
}
