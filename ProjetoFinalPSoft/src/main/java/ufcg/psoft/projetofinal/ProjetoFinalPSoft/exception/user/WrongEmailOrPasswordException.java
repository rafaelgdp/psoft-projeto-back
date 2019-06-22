package ufcg.psoft.projetofinal.ProjetoFinalPSoft.exception.user;

@SuppressWarnings("serial")
public class WrongEmailOrPasswordException extends RuntimeException {
	public WrongEmailOrPasswordException (String msg) {
		super(msg);
	}
}
