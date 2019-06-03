package com.pingan.pare.bi.datasupplementanalysis.temp.dao.monodb;

import com.pingan.pare.bi.datasupplementanalysis.temp.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public abstract class MongoDbDao<T> {

    /**
     * 反射获取泛型类型
     */
    protected abstract Class<T> getEntityClass();

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存
     */
    public void save(T t){
        this.mongoTemplate.save(t);
    }

    /**
     * 查询
     */
    public T queryById(String id){
        Query query =new Query(Criteria.where("id").is(id));
        return this.mongoTemplate.findOne(query,this.getEntityClass());
    }

    /**
     * 分页查询
     */
    public List<T> getPage(T object,int start,int size){
        Query query =getQueryByObject(object);
        query.skip(start);
        query.limit(size);
        return this.mongoTemplate.find(query,this.getEntityClass());
    }

    /**
     * 查询条数
     */
    public Long getCount(T object){
        Query query = getQueryByObject(object);
        return this.mongoTemplate.count(query,this.getEntityClass());
    }

    /**
     * 删除对象
     */
    public int delete(T t){
        return (int)this.mongoTemplate.remove(t).getDeletedCount();
    }

    /**
     * 根据id删除对象
     */
    public void delete(Integer id){
        Criteria criteria=Criteria.where("_id").is(id);
        if(null !=criteria){
            Query query = new Query(criteria);
            T obj =this.mongoTemplate.findOne(query,this.getEntityClass());
            if(obj !=null){
                this.delete(obj);
            }
        }
    }

    /**
     * 修改匹配到的第一条记录
     */
    public void updateFirst(T srcObj,T targetObj){
        Query query =getQueryByObject(srcObj);
        Update update =getUpdateByObject(targetObj);
        this.mongoTemplate.updateFirst(query,update,this.getEntityClass());
    }

    /**
     * 修改匹配到的所有记录
     */
    public void updateMulti(T srcObj,T targetObj){
        Query query =getQueryByObject(srcObj);
        Update update =getUpdateByObject(targetObj);
        this.mongoTemplate.updateMulti(query,update,this.getEntityClass());
    }

    /**
     * 将查询到的对象转换为query
     * 包含name字段的进行模糊查询
     * 其余字段进行相等查询
     */
    private Query getQueryByObject(T object){
        Query query =new Query();
        String[] fileds=ReflectionUtil.getFiledName(object);
        Criteria criteria=new Criteria();
        for (int i = 0; i < fileds.length; i++) {
            String filedName = fileds[i];
            Object filedValue =ReflectionUtil.getObjByName(filedName,object);
            if(filedValue !=null){
                //包含name字段的进行模糊查询
                if(filedName.toLowerCase().contains("name")){
                    criteria.and(filedName).regex(".*?"+filedValue+".*");
                }else{
                    criteria.and(filedName).is(filedValue);
                }
            }
        }
        query.addCriteria(criteria);
        return query;
    }

    /**
     * 将查询到的对象转换为query
     * 包含name字段的进行模糊查询
     * 其余字段进行相等查询
     */
    private Update getUpdateByObject(T object){
        Update update =new Update();
        String[] fileds= ReflectionUtil.getFiledName(object);
        for (int i = 0; i < fileds.length; i++) {
            String filedName= fileds[i];
            Object filedValue =ReflectionUtil.getObjByName(filedName,object);
            if(filedValue !=null){
                update.set(filedName,filedValue);
            }
        }
        return update;
    }
}
