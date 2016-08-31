package io.musika.notifier.domain.model.shared;

/**
 * A value object, as explained in the Domain Driven Design book.
 */
public interface ValueObject<T> {

    /**
     * Value objects compare by the values of their attributes, they don't have an identity.
     *
     * @param other The other value object
     * @return <code>true</code> if the given value object's and this value object's attributes are the same
     */
    boolean sameValueAs(T other);

}
