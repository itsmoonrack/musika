package io.musika.notifier.infrastructure.search.hibernate;

import java.util.List;

import org.apache.lucene.search.Query;
import org.hibernate.SessionFactory;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.musika.notifier.domain.model.shared.kernel.Artist;
import io.musika.notifier.domain.model.shared.kernel.Track;
import io.musika.notifier.domain.model.shared.kernel.TrackId;
import io.musika.notifier.domain.services.SearchService;

/**
 * @author Sylvain Lecoy <sylvain.lecoy@gmail.com>
 */
@Service
@SuppressWarnings("unchecked")
public class SearchServiceHibernate implements SearchService {

	private final FullTextEntityManager entityManager;

	public SearchServiceHibernate(final SessionFactory sessionFactory) {
		this.entityManager = Search.getFullTextEntityManager(sessionFactory.getCurrentSession());
	}

	@Override
	@Transactional(readOnly = true)
	public List<TrackId> getTrackIdSuggestions(final String trackName) {
		final QueryBuilder queryBuilder = entityManager
				.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(Track.class)
				.get();
		final Query query = queryBuilder
				.keyword()
				.onFields("trackId")
				.matching(trackName)
				.createQuery();
		return (List<TrackId>) entityManager
				.createFullTextQuery(query, Track.class)
				.setProjection("trackId")
				.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Artist> getArtistSuggestions(final String artistName) {
		return null;
	}

}
