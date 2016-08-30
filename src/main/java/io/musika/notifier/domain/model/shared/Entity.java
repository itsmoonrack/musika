package io.musika.notifier.domain.model.shared;

/**
 * An entity, as explained in the Domain Driven Design book.
 */
public interface Entity<T, Key> {

    /**
     * Entities compare by identity, not by attributes.
     *
     * @param other The other entity
     * @return <code>true</code> if the identities are the same, regardless of other attributes
     */
    boolean sameIdentityAs(T other);

    /**
     * Returns the identity of this entity.
     *
     * @return The identity of this entity
     */
    Key identity();

}
