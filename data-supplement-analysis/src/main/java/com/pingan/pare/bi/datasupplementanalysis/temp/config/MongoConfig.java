package com.pingan.pare.bi.datasupplementanalysis.temp.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
public class MongoConfig extends AbstractMongoConfiguration {

    /**
     * mongodb url
     */
    @Value("${spring.data.mongodb.uri}")
    private String mongodbUri;

    /**
     * 存储桶名称，默认为空：fs
     */
    private String bucket;

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(new MongoClientURI(mongodbUri));
    }

    @Override
    protected String getDatabaseName() {
        return "MongoDB";
    }

    /**
     * 实例化工厂
     */
    @Bean
    public SimpleMongoDbFactory simpleMongoDbFactory(){
        return new SimpleMongoDbFactory(new MongoClientURI(mongodbUri));
    }

    /**
     * create mongotemplate instance
     */
    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(simpleMongoDbFactory());
    }

    /**
     * create GridFilesTemplate
     */
    @Bean
    public GridFilesTemplate gridFilesTemplate() throws  Exception{
        return new GridFilesTemplate(simpleMongoDbFactory(),mappingMongoConverter(),bucket);
    }
}
