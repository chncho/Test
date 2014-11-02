package exceptionTest.login.unCheckedException;

/**
 * unChecked Exception
 * 
 * @author 陈超
 * @date Nov 7, 2012 11:48:21 AM
 */
/*
 * Notice how I've replaced 'extends Exception' with 'extends
 * IllegalArgumentException'; there are two reasons for this, mainly because it
 * is a subclass of RuntimeException, meaning unchecked and secondly, because it
 * is more specific that simply extending RuntimeException. Everything else in
 * the class is left untouched, meaning it is a very simple transition.
 */
public class SecurityBreachException extends IllegalArgumentException {

	public SecurityBreachException() {
		super("Attempted Security Breach - User attepted to breach security");
	}

}
