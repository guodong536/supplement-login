package com.pingan.pare.bi.datasupplementanalysis.temp.dao.monodb;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.pingan.pare.bi.datasupplementanalysis.temp.config.GridFilesTemplate;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.Map;

@Repository
public class GridFilesDao {

    @Autowired
    private GridFilesTemplate gridFilesTemplate;

    public String saveTemplate(InputStream context, String fileName, Map<String,Object> metadata){
        ObjectId objId = gridFilesTemplate.store(context, fileName, metadata);
        return objId ==null?null:objId.toString();

    }

    public GridFSFile findGridFsById(String id){
        return gridFilesTemplate.findOne(new Query(Criteria.where("_id").is(id)));
    }

    public GridFsResource findGridFsInputSteam(GridFSFile gridFSFile){
        if(gridFSFile ==null){
            return null;
        }
        return gridFilesTemplate.getResource(gridFSFile);
    }
}
