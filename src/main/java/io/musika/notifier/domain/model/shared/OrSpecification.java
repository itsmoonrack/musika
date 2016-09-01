package io.musika.notifier.domain.model.shared;

/**
 * OR specification, used to create a new specification that is the OR of two other specifications.
 */
class OrSpecification<T> implements Specification<T> {

	private final Specification<T> spec1;
	private final Specification<T> spec2;

	/**
	 * Creates a new OR specification based on two other spec.
	 */
	public OrSpecification(final Specification<T> spec1, final Specification<T> spec2) {
		this.spec1 = spec1;
		this.spec2 = spec2;
	}

	@Override
	public boolean isSatisfiedBy(final T o) {
		return spec1.isSatisfiedBy(o) || spec2.isSatisfiedBy(o);
	}

}
