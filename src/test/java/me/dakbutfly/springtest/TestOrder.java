package me.dakbutfly.springtest;

import me.dakbutfly.domain.Item;
import me.dakbutfly.domain.Order;
import me.dakbutfly.repository.ItemRepository;
import me.dakbutfly.service.ItemService;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by dakbutfly on 2017-01-04.
 */
public class TestOrder {

    ItemService itemService;
    ItemRepository repository;

    private static long start;
    private static EntityManagerFactory emf;

    @BeforeClass
    public static void start() {
        start = System.currentTimeMillis();
        System.out.println("시작");
        emf = entityManagerFactory(dataSource(), hibernateProperties());
    }

    @AfterClass
    public static void end() {
        System.out.println("걸린 시간 : "+(System.currentTimeMillis() - start));
    }

    public static DataSource dataSource() {
        final BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:jpashop");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    public static Properties hibernateProperties(){
        final Properties properties = new Properties();

        properties.put( "hibernate.dialect", "org.hibernate.dialect.H2Dialect" );
        properties.put( "hibernate.hbm2ddl.auto", "update" );
        properties.put( "hibernate.show_sql", "true" );
        properties.put( "hibernate.format_sql", "true" );
        properties.put( "hibernate.use_sql_comments", "true" );

        return properties;
    }

    public static EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties ){
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource( dataSource );
        em.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
        em.setJpaProperties( hibernateProperties );
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.setPackagesToScan("me.dakbutfly.domain");
        em.setPersistenceUnitName( "mytestdomain" );
        em.afterPropertiesSet();

        return em.getObject();
    }

    @Before
    public void setup() {
        EntityManager em = emf.createEntityManager();
        JpaRepositoryFactory factory = new JpaRepositoryFactory(em);
        repository = factory.getRepository(ItemRepository.class);
    }

    @Test
    public void 상품등록_테스트() throws Exception {
        Item item = new Item();
        item.setName("상품01");
        item.setPrice(1000);

        itemService.saveItem(item);

        Long id = item.getId();
        assertNotNull(id);
        assertThat(id, is(1L));
    }
}
