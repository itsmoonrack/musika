package io.musika.notifier.infrastructure.search.hibernate;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Transactional(transactionManager = "transactionManager")
@ContextConfiguration({"/context-infrastructure-persistence.xml", "/context-domain.xml"})
public class SearchServiceHibernateTest {



}
