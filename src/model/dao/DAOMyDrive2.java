package model.dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DAOMyDrive2 {

    static final DAOMyDrive2 DAO_MYDRIVE2 = new DAOMyDrive2();
    private final DataSource dataSource;

    // TODO statement を preparedStatementに直す

    private DAOMyDrive2() {
        final PoolProperties pp = new PoolProperties();
        pp.setUrl("jdbc:mysql://mydrive2-mysql/MyDrive2");
        pp.setDriverClassName("com.mysql.cj.jdbc.Driver");
        pp.setUsername("mydrive2");
        pp.setPassword("password");

        pp.setJmxEnabled(true);
        pp.setTestWhileIdle(false);
        pp.setTestOnBorrow(true);
        pp.setValidationQuery("SELECT 1");
        pp.setTestOnReturn(false);
        pp.setValidationInterval(30000);
        pp.setTimeBetweenEvictionRunsMillis(30000);
        pp.setMaxActive(100);
        pp.setInitialSize(10);
        pp.setMaxWait(10000);
        pp.setRemoveAbandonedTimeout(60);
        pp.setMinEvictableIdleTimeMillis(30000);
        pp.setMinIdle(10);
        pp.setLogAbandoned(true);
        pp.setRemoveAbandoned(true);
        pp.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
                + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

        final DataSource dataSource = new DataSource();
        dataSource.setPoolProperties(pp);

        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
