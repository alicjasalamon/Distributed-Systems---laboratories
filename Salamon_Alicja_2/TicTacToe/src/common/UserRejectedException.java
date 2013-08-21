package common;

public class UserRejectedException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserRejectedException(String message){
		super(message);
	}
}
