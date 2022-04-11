package com.carerobot.cockpit.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.common.multi.MultipleDataSource;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
//@MapperScan("com.todomanager.comstodoj.mapper")
public class MybatisPlusConfig {

    // 旧版
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        //paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        //paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }

    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "spring.datasource.db1" )
    public DataSource db1() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "db2")
    @ConfigurationProperties(prefix = "spring.datasource.db2" )
    public DataSource db2() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "db3")
    @ConfigurationProperties(prefix = "spring.datasource.db3" )
    public DataSource db3() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "db4")
    @ConfigurationProperties(prefix = "spring.datasource.db4" )
    public DataSource db4() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "db5")
    @ConfigurationProperties(prefix = "spring.datasource.db5" )
    public DataSource db5() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "db6")
    @ConfigurationProperties(prefix = "spring.datasource.db6" )
    public DataSource db6() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "db7")
    @ConfigurationProperties(prefix = "spring.datasource.db7" )
    public DataSource db7() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "db8")
    @ConfigurationProperties(prefix = "spring.datasource.db8" )
    public DataSource db8() {
        return DruidDataSourceBuilder.create().build();
    }
    /**
     * 动态数据源配置
     * @return
     */
    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier("db1") DataSource db1, @Qualifier("db2") DataSource db2, @Qualifier("db3") DataSource db3
            , @Qualifier("db4") DataSource db4, @Qualifier("db5") DataSource db5,@Qualifier("db6") DataSource db6
            ,@Qualifier("db7") DataSource db7,@Qualifier("db8") DataSource db8) {
        MultipleDataSource multipleDataSource = new MultipleDataSource();
        Map< Object, Object > targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.DB1.getValue(), db1);
        targetDataSources.put(DataSourceEnum.DB2.getValue(), db2);
        targetDataSources.put(DataSourceEnum.DB3.getValue(), db3);
        targetDataSources.put(DataSourceEnum.DB4.getValue(), db4);
        targetDataSources.put(DataSourceEnum.DB5.getValue(), db5);
        targetDataSources.put(DataSourceEnum.DB6.getValue(), db6);
        targetDataSources.put(DataSourceEnum.DB7.getValue(), db7);
        targetDataSources.put(DataSourceEnum.DB8.getValue(), db8);
        //添加数据源
        multipleDataSource.setTargetDataSources(targetDataSources);
        //设置默认数据源
        multipleDataSource.setDefaultTargetDataSource(db1);
        return multipleDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(multipleDataSource(db1(),db2(),db3(),db4(),db5(),db6(),db7(),db8()));
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/carerobot/cockpit/mapper/**/*.xml"));
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setDbConfig(new GlobalConfig.DbConfig().setFieldStrategy(FieldStrategy.IGNORED));
        sqlSessionFactory.setGlobalConfig(globalConfig);

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        //控制台打印sql
        configuration.setLogImpl(StdOutImpl.class);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{ //PerformanceInterceptor(),OptimisticLockerInterceptor()
                paginationInterceptor() //添加分页功能
        });
        return sqlSessionFactory.getObject();
    }
}