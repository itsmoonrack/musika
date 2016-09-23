package io.musika.notifier.infrastructure.search.hibernate;

import java.lang.annotation.ElementType;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Factory;
import org.hibernate.search.cfg.SearchMapping;

import io.musika.notifier.domain.model.shared.kernel.Track;

/**
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
public class SearchMappingFactoryHibernate {

	@Factory
	public SearchMapping getSearchMapping() {
		SearchMapping mapping = new SearchMapping();

		mapping
				.analyzerDef("ngram", StandardTokenizerFactory.class)
					.filter(LowerCaseFilterFactory.class)
					.filter(NGramFilterFactory.class)
					.param("minGramSize", "3")
					.param("maxGramSize", "3")
				.entity(Track.class).indexed()
					.property("trackId", ElementType.FIELD).documentId().name("id");

		return mapping;
	}

}
