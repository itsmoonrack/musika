package io.musika.notifier.domain.model.release;

/**
 * If a {@link ReleaseEvent} cannot be created from a given set of parameters.
 *
 * It is a checked exception because it's not a programming error, but rather a
 * special business case that the application is built to handle. It can occur
 * during normal program execution.
 *
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public class UnableToCreateReleaseEventException extends Exception {

	public UnableToCreateReleaseEventException(Exception e) {
		super(e);
	}

	public UnableToCreateReleaseEventException() {
		super();
	}

}
