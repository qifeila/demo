package goct.query.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * Mybatis  第二个ds4数据源配置
 * 多数据源配置依赖数据源配置
 */
@Configuration
@MapperScan(basePackages ="goct.query.demo.mapper.oracleGis", sqlSessionTemplateRef  = "ds4SqlSessionTemplate")
public class DataSourceGIS {

    //ds4数据源
    @Bean("ds4SqlSessionFactory")
    public SqlSessionFactory ds4SqlSessionFactory(@Qualifier("ds4DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources("classpath*:mybatis/mapper/oracleGis/*.xml"));
        return sqlSessionFactory.getObject();
    }

    //事务支持
    @Bean(name = "ds4TransactionManager")
    public DataSourceTransactionManager ds4TransactionManager(@Qualifier("ds4DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "ds4SqlSessionTemplate")
    public SqlSessionTemplate ds4SqlSessionTemplate(@Qualifier("ds4SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}