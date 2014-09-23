package com.inekoalaty.forceboard;

/**
 *
 * @author jamesthomas
 */
public class NotDisplayableException extends Exception {

	/**
	 * Creates a new instance of
	 * <code>NotDisplayableException</code> without detail message.
	 */
	public NotDisplayableException() {
	}

	/**
	 * Constructs an instance of
	 * <code>NotDisplayableException</code> with the specified detail message.
	 *
	 * @param msg the detail message.
	 */
	public NotDisplayableException(String msg) {
		super(msg);
	}
}