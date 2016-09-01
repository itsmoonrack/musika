package io.musika.notifier.domain.model.shared;

/**
 * NOT decorator, used to create a new specification that is the inverse (NOT) of the given spec.
 */
class NotSpecification<T> implements Specification<T> {

	private Specification<T> spec;

	/**
	 * Create a new NOT specification based on another spec.
	 *
	 * @param spec Specification instance to not.
	 */
	public NotSpecification(final Specification<T> spec) {
		this.spec = spec;
	}

	public boolean isSatisfiedBy(final T o) {
		return !spec.isSatisfiedBy(o);
	}

}
