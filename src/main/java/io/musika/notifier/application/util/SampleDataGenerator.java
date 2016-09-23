package io.musika.notifier.application.util;

import static io.musika.notifier.application.util.DateTestUtil.toDate;
import static io.musika.notifier.domain.model.store.SampleReleases.MOUNTAIN_NOODLES;
import static io.musika.notifier.domain.model.store.SampleReleases.MYSTIC_EP;
import static io.musika.notifier.domain.model.store.SampleReleases.OBSIDIAN_SKY;
import static io.musika.notifier.domain.model.store.SampleStores.AMAZON;
import static io.musika.notifier.domain.model.store.SampleStores.BEATPORT;
import static io.musika.notifier.domain.model.store.SampleStores.ITUNES;
import static io.musika.notifier.domain.model.store.SampleTracks.MOONRACK_OBSIDIAN_SKY_ORIGINAL_MIX;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.activation.DataSource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import io.musika.notifier.domain.model.notifier.Catalog;
import io.musika.notifier.domain.model.notifier.Record;
import io.musika.notifier.domain.model.notifier.Subscription;
import io.musika.notifier.domain.model.notifier.TrackSpecification;
import io.musika.notifier.domain.model.release.ReleaseEvent;
import io.musika.notifier.domain.model.release.ReleaseEventFactory;
import io.musika.notifier.domain.model.release.ReleaseEventRepository;
import io.musika.notifier.domain.model.release.ReleaseHistory;
import io.musika.notifier.domain.model.release.UnableToCreateReleaseEventException;
import io.musika.notifier.domain.model.shared.kernel.ReleaseRepository;
import io.musika.notifier.domain.model.shared.kernel.TrackId;
import io.musika.notifier.domain.model.shared.kernel.TrackRepository;
import io.musika.notifier.domain.model.store.SampleArtists;
import io.musika.notifier.domain.model.store.SampleRecordLabels;
import io.musika.notifier.domain.model.store.SampleStores;
import io.musika.notifier.domain.model.store.SampleTracks;
import io.musika.notifier.domain.model.store.StoreRepository;

/**
 * Provides sample date.
 */
public class SampleDataGenerator implements ServletContextListener {

	private static final Timestamp base;
	static {
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2008-01-01");
			base = new Timestamp(date.getTime() - 1000L * 60 * 60 * 24 * 100);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	private static final Logger logger = LoggerFactory.getLogger(SampleDataGenerator.class);

	private static void loadStoreData(JdbcTemplate jdbcTemplate) {
		String storeSql =
				"insert into Store (id, uricode, name) values (?, ?, ?)";

		Object[][] storeArgs = {
				{1, "http://www.beatport.com", "Beatport"},
				{2, "http://www.amazonmp3.com", "Amazon MP3"},
				{3, "http://www.itunes.com", "iTunes"},
				{4, "http://www.junodownload.com", "Juno Download"},
				{5, "http://www.traxsource.com", "Traxsource"},
		};

		executeUpdate(jdbcTemplate, storeSql, storeArgs);
	}

	private static void loadRecordLabelData(JdbcTemplate jdbcTemplate) {
		String recordLabelSql =
				"insert into RecordLabel (id, name) values (?, ?)";

		Object[][] recordLabelArgs = {
				{1, "OSCIL8 Recordings"},
				{2, "Intec Digital"},
				{3, "Mood Records"}
		};

		executeUpdate(jdbcTemplate, recordLabelSql, recordLabelArgs);
	}

	private static void loadArtistData(JdbcTemplate jdbcTemplate) {
		String artistSql =
				"insert into Artist (id, name) values (?, ?)";

		Object[][] artistArgs = {
				{1, "Moonrack"},
				{2, "Setiawan"},
				{3, "Gibb's Master"},
				{4, "EKLPX"},
				{5, "Mersel"},
				{6, "DJ Pangolin"}
		};

		executeUpdate(jdbcTemplate, artistSql, artistArgs);
	}

	private static void loadTrackData(JdbcTemplate jdbcTemplate) {
		String releaseSql =
				"insert into Release ("; // TODO Release / Track relationship
		String trackSql =
				"insert into Track (id, track_id, title, year) values (?, ?, ?, ?)";

		Object[][] trackArgs = {
				{1, "Moonrack - Obsidian Sky (Original Mix)", 			"Obsidian Sky", 2016},
				{2, "Moonrack - Obsidian Sky (Setiawan Remix)", 		"Obsidian Sky", 2016},
				{3, "Moonrack - Obsidian Sky (Gibb's Master Remix)", 	"Obsidian Sky", 2016},
				{4, "Moonrack - Obsidian Sky (EKLPX Remix)", 			"Obsidian Sky", 2016},
				{5, "Moonrack - Obsidian Sky (Mersel Remix)",	 		"Obsidian Sky", 2016},
				{6, "Moonrack - Obsidian Sky (DJ Pangolin Remix)", 		"Obsidian Sky", 2016}
		};

		executeUpdate(jdbcTemplate, trackSql, trackArgs);
	}

	private static void loadSubscriptionData(JdbcTemplate jdbcTemplate) {
		String subscriptionSql =
				"insert into Subscription (id, track_id, is_available, spec_track_id, current_release_id, notification_status, calculated_at, availability, last_store_id, last_event_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Object[][] subscriptionArgs = {
				{1, "Moonrack - Obsidian Sky (Original Mix)", true, 1, }
		};
	}

	@Override
	public void contextInitialized(final ServletContextEvent event) {
		final WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
		final DataSource dataSource = context.getBean(DataSource.class);
		final PlatformTransactionManager transactionManager = context.getBean(PlatformTransactionManager.class);
		final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

		final SessionFactory sessionFactory = context.getBean(SessionFactory.class);
		final ReleaseEventFactory releaseEventFactory = new ReleaseEventFactory(
				context.getBean(TrackRepository.class),
				context.getBean(StoreRepository.class),
				context.getBean(ReleaseRepository.class)
		);
		loadHibernateData(transactionTemplate, sessionFactory, releaseEventFactory, context.getBean(ReleaseEventRepository.class));
	}

	public static void loadHibernateData(final TransactionTemplate transactionTemplate, final SessionFactory sessionFactory,
			final ReleaseEventFactory releaseEventFactory, final ReleaseEventRepository releaseEventRepository) {
		logger.info("Loading Hibernate data ...");
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				final Session session = sessionFactory.getCurrentSession();

				SampleStores.getAll().forEach(session::save);

				session.save(OBSIDIAN_SKY);
				session.save(MYSTIC_EP);
				session.save(MOUNTAIN_NOODLES);

				TrackSpecification trackSpecification1 = new TrackSpecification(MOONRACK_OBSIDIAN_SKY_ORIGINAL_MIX);
				TrackId trackId1 = new TrackId("Moonrack - Obsidian Sky (Original Mix)");
				Subscription obsidianSky = new Subscription(trackId1, trackSpecification1);

				Catalog catalog = new Catalog(Arrays.asList(
						new Record(AMAZON, OBSIDIAN_SKY),
						new Record(BEATPORT, OBSIDIAN_SKY),
						new Record(ITUNES, OBSIDIAN_SKY)
				));
				obsidianSky.attachToCatalog(catalog);

				session.save(obsidianSky);

				try {
					ReleaseEvent event1 = releaseEventFactory.createReleaseEvent(
							toDate("2016-06-15"), trackId1, OBSIDIAN_SKY.releaseNumber(), AMAZON.uriCode(), ReleaseEvent.Type.RELEASED
					);
					session.save(event1);

					ReleaseEvent event2 = releaseEventFactory.createReleaseEvent(
							toDate("2016-06-15"), trackId1, OBSIDIAN_SKY.releaseNumber(), BEATPORT.uriCode(), ReleaseEvent.Type.RELEASED
					);
					session.save(event2);

					ReleaseEvent event3 = releaseEventFactory.createReleaseEvent(
							toDate("2016-06-15"), trackId1, OBSIDIAN_SKY.releaseNumber(), ITUNES.uriCode(), ReleaseEvent.Type.RELEASED
					);
					session.save(event3);


				} catch (UnableToCreateReleaseEventException e) {
					throw new RuntimeException(e);
				}

				ReleaseHistory releaseHistory1 = releaseEventRepository.lookupReleaseHistoryOfTrack(trackId1);
				obsidianSky.deriveNotificationStatus(releaseHistory1);

				session.update(obsidianSky);
			}
		});

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

	public static void loadSampleDate(final JdbcTemplate jdbcTemplate, final TransactionTemplate transactionTemplate) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				loadStoreData(jdbcTemplate);
				loadRecordLabelData(jdbcTemplate);
				loadArtistData(jdbcTemplate);
				loadTrackData(jdbcTemplate);
				loadSubscriptionData(jdbcTemplate);
			}
		});
	}

	private static void executeUpdate(JdbcTemplate jdbcTemplate, String sql, Object[][] args) {
		for (Object[] arg : args) {
			jdbcTemplate.update(sql, arg);
		}
	}

	private static Timestamp ts(int hours) {
		return new Timestamp(base.getTime() + 1000L * 60 * 60 * hours);
	}

	public static Date offset(int hours) {
		return new Date(ts(hours).getTime());
	}

}