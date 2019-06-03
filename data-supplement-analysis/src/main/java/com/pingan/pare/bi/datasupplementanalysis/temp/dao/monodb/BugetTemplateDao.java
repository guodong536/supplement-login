package com.pingan.pare.bi.datasupplementanalysis.temp.dao.monodb;

import com.pingan.pare.bi.datasupplementanalysis.temp.domain.po.BugetTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BugetTemplateDao extends MongoDbDao<BugetTemplate>{

    @Override
    protected Class<BugetTemplate> getEntityClass() {
        return BugetTemplate.class;
    }
}
