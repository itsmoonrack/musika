package io.musika.notifier.domain.model.shared;

/**
 * AND specification, used to create a new specification that is the AND of two other specifications.
 */
class AndSpecification<T> implements Specification<T> {

	private final Specification<T> spec1;
	private final Specification<T> spec2;

	/**
	 * Creates a new AND specification based on two other spec.
	 */
	public AndSpecification(final Specification<T> spec1, final Specification<T> spec2) {
		this.spec1 = spec1;
		this.spec2 = spec2;
	}

	@Override
	public boolean isSatisfiedBy(final T o) {
		return spec1.isSatisfiedBy(o) && spec2.isSatisfiedBy(o);
	}

}
