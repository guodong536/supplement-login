package com.pingan.pare.bi.datasupplementanalysis.temp.domain.dto;

import com.pingan.pare.bi.datasupplementanalysis.temp.domain.po.UserDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class UserDemoDto {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建对象
     *
     */
    public void addUser(UserDemo t){
        mongoTemplate.save(t);
        System.out.println("t="+t);
    }

    /**
     * 根据用户名查询对象
     * @return
     */
    public UserDemo findTestByName(String name) {
        Query query=new Query(Criteria.where("name").is("guodong"));
        UserDemo mgt =  mongoTemplate.findOne(query , UserDemo.class);
        System.out.println("name="+mgt.getName());
        return mgt;
    }

    /**
     * 修改用户
     */
    /**
     * 更新对象
     */
    public void updateTest(UserDemo test) {
        Query query=new Query(Criteria.where("id").is(1));
        Update update= new Update().set("age", test.getAge()).set("name", test.getName());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,UserDemo.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserDemo.class);
        System.out.println("test="+test);
    }



    /**
     * 删除用户
     */
    public void deleteTestById(Integer id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,UserDemo.class);
        System.out.println("id="+id);
    }
}
