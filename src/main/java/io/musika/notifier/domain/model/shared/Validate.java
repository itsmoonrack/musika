package io.musika.notifier.domain.model.shared;

/**
 * @author sylvain
 */
public class Validate {

	/**
	 * <p>Validate an argument, throwing <code>IllegalArgumentException</code>
	 * if the argument is <code>null</code>.</p>
	 *
	 * <pre>
	 * Validate.notNull(myObject, "The object must not be null");
	 * </pre>
	 *
	 * @param object  the object to check is not <code>null</code>
	 * @param message  the exception message you would like to see
	 *  if the object is <code>null</code>
	 * @throws IllegalArgumentException if the object is <code>null</code>
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

}
