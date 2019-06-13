package ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.user;

@SuppressWarnings("serial")
public class UserWithEmailAlreadyRegistered extends RuntimeException {
	public UserWithEmailAlreadyRegistered(String email) {
        super("There is a user with this login " + email + "registered in our database! ");
    }
}
