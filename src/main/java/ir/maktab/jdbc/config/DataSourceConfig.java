package ir.maktab.jdbc.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.DataSource;


public class DataSourceConfig {
    private final static MysqlDataSource mysqlDataSource = new MysqlDataSource();
    private static DataSourceConfig instance;

    private DataSourceConfig() {}

    public static DataSourceConfig getInstance() {
        if (instance == null)
            instance = new DataSourceConfig();
        return instance;
    }

    public DataSource createDataSource() {
        mysqlDataSource.setURL(DbConfig.URL);
        mysqlDataSource.setUser(DbConfig.USERNAME);
        mysqlDataSource.setPassword(DbConfig.PASSWORD);
        return mysqlDataSource;
    }
}
