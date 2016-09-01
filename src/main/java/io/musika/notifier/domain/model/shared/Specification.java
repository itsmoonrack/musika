package io.musika.notifier.domain.model.shared;

public interface Specification<T> {

	/**
	 *  Check if {@code o} is satisfied by the specification.
	 *
	 *  @param o Object to test
	 *  @return {@code true} if {@code o} satisfies the specification
	 */
	boolean isSatisfiedBy(final T o);

	/**
	 * Create a new specification that is the AND operation of {@code this} specification and another specification.
	 * @param specification Specification to AND
	 * @return A new specification
	 */
	default Specification<T> and(final Specification<T> specification) {
		return new AndSpecification<>(this, specification);
	}

	/**
	 * Create a new specification that is the OR operation of {@code this} specification and another specification.
	 * @param specification Specification to OR.
	 * @return A new specification.
	 */
	default Specification<T> or(Specification<T> specification) {
		return new OrSpecification<>(this, specification);
	}

	/**
	 * Create a new specification that is the NOT operation of {@code this} specification.
	 * @param specification Specification to NOT.
	 * @return A new specification.
	 */
	default Specification<T> not(Specification<T> specification) {
		return new NotSpecification<>(specification);
	}

}
