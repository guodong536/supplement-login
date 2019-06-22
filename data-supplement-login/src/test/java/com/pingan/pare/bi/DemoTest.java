package com.pingan.pare.bi;

import com.pingan.pare.bi.login.domain.vo.Order;
import com.pingan.pare.bi.login.domain.vo.User;
import com.pingan.pare.bi.login.util.DroolsUtil;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

public class DemoTest {

    @Test
    public void show(){
        KieSession session = DroolsUtil.readKonwLedgerBase("drool/demo1.drl").newKieSession();

        Order o=new Order();
        o.setName("郭东");
        //放置对象
        session.insert(o);
        //触发规则
        int i = session.fireAllRules();

        //释放资源
        session.dispose();
        System.out.println("执行了"+i+"条规则！");
        System.out.println(o);
        System.out.println();
    }

    @Test
    public void show1(){
        KieSession session = DroolsUtil.readKonwLedgerBase("drool/demo2.drl").newKieSession();

        Order o=new Order();
        o.setName("栋");

        User user=new User();
        user.setAge(22);
        session.insert(user);
        int i = session.fireAllRules();

        session.dispose();
        System.out.println("执行了"+i+"条规则！");
        System.out.println(user);
    }

    @Test
    public void show2(){
        KieSession session = DroolsUtil.readKonwLedgerBase("drool/demo3.drl").newKieSession();
        User user=new User();
        user.setAge(22);
        user.setName("东");
        session.insert(user);
        int i = session.fireAllRules();

        QueryResults count = session.getQueryResults("query count");
        System.out.println("count:{}"+count.size());

        if(count !=null&& count.size()>0){
            for (QueryResultsRow row: count) {
                System.out.println(row.get("$user"));
            }
        }
        session.dispose();
        System.out.println("执行了"+i+"条规则！");
        System.out.println(user);
    }

    /**
     * 定时器
     */
    @Test
    public void show3(){
        KieSession session = DroolsUtil.readKonwLedgerBase("drool/demo4.drl").newKieSession();

        session.insert(null);
        int i = session.fireAllRules();
        session.dispose();
    }

    /**
     * 校验rule
     */
    @Test
    public void show4(){

    }

    /**
     * golbal应用
     */
    @Test
    public void show5(){

    }

    /**
     * 申明对象declare
     */
    @Test
    public void show6(){

    }
}
