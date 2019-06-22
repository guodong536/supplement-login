package com.pingan.pare.bi.login.util;

import lombok.extern.slf4j.Slf4j;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.drools.core.spi.KnowledgeHelper;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.io.ResourceType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import java.io.UnsupportedEncodingException;

@Slf4j
public class DroolsUtil {

    /**
     * 日志
     */
    public static void setLog(final KnowledgeHelper knowledgeHelper){
        log.info("rule:{}"+knowledgeHelper.getRule().getName());
    }

    public static void setLog(String str){
        log.info("rule:{}"+str);
    }

    /**
     * 通过固定路径文件读取数据
     * knowledgeBuilder对规则文件进行编译
     */
    public static InternalKnowledgeBase readKonwLedgerBase(final String filePath){
        KnowledgeBuilder knowledgeBuilder= KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBuilder.add(ResourceFactory.newClassPathResource(filePath), ResourceType.DRL);
        valideErrors(knowledgeBuilder);

        return getInternalKnowledgeBase(knowledgeBuilder);
    }

    /**
     * 通过内容读取数据
     */
    public static InternalKnowledgeBase readKonwLedgerByContent(String ruleContent) throws UnsupportedEncodingException {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newByteArrayResource(ruleContent.getBytes("UTF-8")),ResourceType.DRL);
        valideErrors(builder);

        return getInternalKnowledgeBase(builder);
    }

    private static InternalKnowledgeBase getInternalKnowledgeBase(KnowledgeBuilder knowledgeBuilder) {
        /**
         * 生成基本类
         */
        KieBaseConfiguration configuration= KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        configuration.setProperty("drools.removeIdentities","true");

        InternalKnowledgeBase internalKnowledgeBase = KnowledgeBaseFactory.newKnowledgeBase(configuration);
        internalKnowledgeBase.addPackages(knowledgeBuilder.getKnowledgePackages());

        return internalKnowledgeBase;
    }

    private static void valideErrors(KnowledgeBuilder knowledgeBuilder) {
        /**
         * 校验错误
         */
        KnowledgeBuilderErrors errors =knowledgeBuilder.getErrors();
        if(errors !=null && errors.size()>0){
            for (KnowledgeBuilderError error:errors) {
                log.warn(error.toString());
            }
            throw new IllegalArgumentException("could not parse konwledge");
        }
    }
}
