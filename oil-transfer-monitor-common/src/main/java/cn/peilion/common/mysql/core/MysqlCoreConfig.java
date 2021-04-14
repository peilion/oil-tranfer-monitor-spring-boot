package cn.peilion.common.mysql.core;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author fpl11
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "mysql.core")
@PropertySource("classpath:mysql-core-jdbc.properties")
@MapperScan(basePackages = "cn.peilion.model.mappers", sqlSessionFactoryRef = "mysqlCoreSessionFactory")
public class MysqlCoreConfig {

    String jdbcUrl;
    String jdbcUserName;
    String jdbcPassword;
    String jdbcDriver;
    //mapper文件在classpath下存放的根路径
    String rootMapper;
    //别名包
    String aliasesPackage;

    /**
     * 设置一个数据库的连接池
     */
    @Bean("mysqlCoreDataSource")
    public DataSource mysqlCoreDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(this.getJdbcUserName());
        dataSource.setPassword(this.getJdbcPassword());
        dataSource.setJdbcUrl(this.getJdbcUrl());
        dataSource.setDriverClassName(this.getJdbcDriver());
        //最大连接数
        dataSource.setMaximumPoolSize(50);
        //最小连接数
        dataSource.setMinimumIdle(5);
        return dataSource;
    }

    /**
     * 整合mybatis  SqlSessionFactoryBean
     */
    @Bean("mysqlCoreSessionFactory")
    public SqlSessionFactoryBean mysqlCoreSessionFactory(@Qualifier("mysqlCoreDataSource") DataSource dataSource) throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        //数据源
        factoryBean.setDataSource(dataSource);
        //别名
        factoryBean.setTypeAliasesPackage(this.getAliasesPackage());
        //mapper文件存储的位置
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(this.getMapperFilePath());
        factoryBean.setMapperLocations(resources);
        //开启驼峰标识  user_name  --  》 userName
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(configuration);
        return factoryBean;
    }

    /**
     * 拼接mapper.xml文件的存储位置
     */
    public String getMapperFilePath() {
        return new StringBuffer("classpath*:").append(this.getRootMapper()).append("/**/*.xml").toString();
    }
}
