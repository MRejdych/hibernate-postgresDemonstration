package app.dao;

import app.entities.Customer;
import app.stats.StatisticsKeeper;
import app.utils.CustomerBuilder;
import app.utils.DatabaseUtils;
import org.hibernate.stat.Statistics;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import static app.stats.StatisticType.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CustomerDAOTests {
    private DatabaseUtils dbutils = mock(DatabaseUtils.class);
    private EntityManager em = mock(EntityManager.class);
    private Statistics statistics = mock(Statistics.class);

    @Before
    public void before(){
        StatisticsKeeper.clearStatistics();
        when(dbutils.getEntityManager()).thenReturn(em);
        when(statistics.getStartTime()).thenReturn(1L);
        when(statistics.getQueryExecutionCount()).thenReturn(1L);
        when(statistics.getQueries()).thenReturn(new String[0]);
        when(dbutils.getStatistics()).thenReturn(statistics);
    }

    @Test
    public void testCreate(){
        CustomersDAO customersDAO = new CustomersDAO(dbutils);
        Customer customer = new CustomerBuilder().withCompanyName("TEST").build();
        customersDAO.create(customer);

        verify(em, times(1)).persist(customer);
        verify(dbutils, times(1)).openConnection();
        verify(dbutils, times(1)).closeConnection();
        verify(dbutils, times(1)).getEntityManager();

        assertTrue(StatisticsKeeper.getStatisticsMap().get(CREATE).size() == 0);
    }

    @Test
    public void testCreateGeneratorMode(){
        CustomersDAO customersDAO = new CustomersDAO(dbutils, true);
        Customer customer = new CustomerBuilder().withCompanyName("TEST").build();
        customersDAO.create(customer);

        verify(em, times(1)).persist(customer);
        verify(dbutils, times(1)).openConnection();
        verify(dbutils, times(1)).closeConnection();
        verify(dbutils, times(1)).getEntityManager();
        verify(dbutils, times(1)).getStatistics();

        assertTrue(StatisticsKeeper.getStatisticsMap().get(CREATE).size() == 1);
    }
}
