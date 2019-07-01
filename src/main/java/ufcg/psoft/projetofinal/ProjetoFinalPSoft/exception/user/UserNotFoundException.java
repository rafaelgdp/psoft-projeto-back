package ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.user;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
