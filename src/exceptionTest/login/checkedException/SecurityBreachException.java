package exceptionTest.login.checkedException;

/*
 * http://www.javaprogrammingforums.com/content/55-create-your-own-exceptions.html#comments_start
 */
/**
 * Checked Exception
 * 
 * @author 陈超
 * @date Nov 7, 2012 11:26:16 AM
 */
/*
 * As the SecurityBreachException class extends Exception, it means this will be
 * a checked exception, and will therefore need to be handled by the programmer.
 * Using its super class constructor (Exception(String message)), I set the
 * message for my exception to "Attempted Security Breach - User attempted to
 * breach security" which is the message that will now be registered with the
 * thrown exception.
 * 
 * Although I do it here, I wouldn't exactly recommend you always extend the
 * broad classes, such as Exception for checked and RuntimeException for
 * unchecked, but extend relevant exceptions, such as IOException for anything
 * media related, or in this case I probably should have extended
 * IllegalAccessException - as in any case; specifics are best.
 */
public class SecurityBreachException extends Exception {

	public SecurityBreachException() {
		super("Attempted Security Breach - User attemped to breach security");
	}
}
