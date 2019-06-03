package com.pingan.pare.bi.datasupplementanalysis.demo;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class GeneratorCodeTest {

    @Test
    public void generateCode() {
        //基础包定义
        String packageName = "com.pingan.pare.bi.datasupplementanalysis.temp";
        //需要生成代码的表，空则生成所有
        String[] tableNames = {
                "bi_supplement_user"
        };
        //接口以I开始，如user-->UserService,设置为true,user-->IUserService
        boolean serviceNameStartWithI = false;
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String userName = "postgres";
        String password = "root";
        String driverName = "org.postgresql.Driver";
        generateByTables(url, userName, password, driverName, serviceNameStartWithI, packageName, tableNames);
    }

    /**
     * 自动生成表结构
     */
    private void generateByTables(String url, String userName, String password, String driverName, boolean serviceNameStartWithI, String packageName, String[] tableNames) {
        //链接配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.POSTGRE_SQL)
                .setUrl(url)
                .setUsername(userName)
                .setPassword(password)
                .setDriverName(driverName);

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)
                .setEntityLombokModel(false)
                .setCapitalMode(true)//表名、字段名、是否使用下划线命名（默认 false）
                .setNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix("bi_")
                .setEntityLombokModel(true)
                //.setSuperEntityClass("cn.java.code.entity.BaseVo")
                //.setSuperControllerClass("cn.java.code.controller.BaseController")
                //.setVersionFieldName("object_version")
                .setInclude(tableNames);//需要生成的的表名，多个表名传数组

        //全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false)
                .setAuthor("GUODONG536")
                .setOutputDir("d:\\CenCode")
                //.setOutputDir("F:\\springbootwork\\data-supplement-analysis\\src\\main\\java")
                .setIdType(IdType.INPUT)
                .setFileOverride(false);

        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");//设置service名
        }

        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig().setParent(packageName)
                                .setController("controller")
                                .setMapper("dao")
                                .setEntity("domain.po")
                ).execute();
    }
}
