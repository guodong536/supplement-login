package com.pingan.pare.bi.datasupplementanalysis.temp.config;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.lang.Nullable;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

/**
 * mongodb 文件实例
 */
public class GridFilesTemplate extends GridFsTemplate {

    /**
     * mongodb 工厂
     */
    private final MongoDbFactory dbFactory;

    @Nullable
    private final String bucket;

    public GridFilesTemplate(MongoDbFactory dbFactory, MongoConverter converter) {
        this(dbFactory,converter,(String)null);
    }

    public GridFilesTemplate(MongoDbFactory dbFactory, MongoConverter converter,@Nullable String bucket) {
        super(dbFactory, converter, bucket);
        this.dbFactory = dbFactory;
        this.bucket = bucket;
    }

    /**
     * 获取mongodb数据库文件存储桶
     */
    private GridFSBucket getGridFs(){
        MongoDatabase db =this.dbFactory.getDb();
        return this.bucket==null? GridFSBuckets.create(db) :GridFSBuckets.create(db,this.bucket);
    }

    /**
     * 根据文件信息获取file文件 与文件流
     */
    public GridFsResource getResource(GridFSFile fsFile){
        return new GridFsResource(fsFile,this.getGridFs().openDownloadStream(fsFile.getObjectId()));
    }
}
