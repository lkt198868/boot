package com.gqq.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@Data
public class DataSourceConfig {

    private Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
    @Value("${dataSource.driverClassName}")
    private String driverClassName;
    @Value("${dataSource.dbUrl}")
    private String dbUrl;
    @Value("${dataSource.username}")
    private String username;
    @Value("${dataSource.password}")
    private String password;
// 可选 启动时创建的连接数
    @Value("${dataSource.initialSize}")
    private Integer initialSize;
// 可选 最大连接数，0无限制
    @Value("${dataSource.maxActive}")
    private Integer maxActive ;
//  可选 最大空闲连接数 0无限制
@Value("${dataSource.maxIdle}")
    private Integer maxIdle;
//  可选 最小空闲连接数 0无限制
@Value("${dataSource.minIdle}")
    private Integer minIdle;
// 可选 同时能从语句池中分配的预处理语句最大值，0无限制 -->
@Value("${dataSource.maxOpenPreparedStatement}")
    private Integer  maxOpenPreparedStatement;
// 可选  连接连接池最大等待时间，-1无限等待
@Value("${dataSource.maxWait}")
    private Integer  maxWait;
// 可选 连接数据库验证语句
@Value("${dataSource.validationQuery}")
    private String validationQuery ;
// 可选 空闲连接池回收器运行周期  毫秒
@Value("${dataSource.timeBetweenEvictionRunsMillis}")
    private Long timeBetweenEvictionRunsMillis;
// 可选 连接在池中保持空闲而不被回收的最大时间
@Value("${dataSource.minEvictableIdleTimeMillis}")
    private Long minEvictableIdleTimeMillis;
// 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
@Value("${dataSource.testOnReturn}")
    private boolean testOnReturn ;
// 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，
// 如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
@Value("${dataSource.testWhileIdle}")
    private boolean testWhileIdle ;
    // 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
    @Value("${dataSource.testOnBorrow}")
    private boolean testOnBorrow;
//可选 布尔值，是否对预处理语句进行池管理
@Value("${dataSource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${dataSource.maxPoolPreparedStatementPerConnectionSize}")
    private Integer maxPoolPreparedStatementPerConnectionSize;
// 通过别名的方式配置扩展插件，常用的插件有：
// 监控统计用的filter:stat日志用的filter:log4j防御sql注入的filter:wall
@Value("${dataSource.filters}")
    private String filters;

@Value("${dataSource.connectionProperties}")
    private String connectionProperties;


    @Bean
    public DataSource dataSource (){
        DruidDataSource datasource =new DruidDataSource();
        datasource.setUrl(dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        // configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setValidationQuery(validationQuery);
        try {
            datasource.setFilters(filters);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("druid configuration initialization filter", e);
            }
        }
        datasource.setConnectionProperties(connectionProperties);

        return datasource;
    }



}
