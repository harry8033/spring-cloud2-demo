package com.pf.code.util;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Ru He
 * Date: Created in 2019/2/14.
 * Description:
 */
public class FreeMarkerUtil {

    private static final Logger log = LoggerFactory.getLogger(FreeMarkerUtil.class);

    /**
     * 功能描述: 通过模板生成内容
     * @auther Ru He
     * @param templateStr 模板字符串内容 model 填充实体
     * @return 
     * @date 2019/2/14 下午3:27
     */
    public static String generate(String templateStr, Object model){
        Version version = new Version("2.3.23");
        Configuration cfg = new Configuration(version);
        StringTemplateLoader loader = new StringTemplateLoader();
        loader.putTemplate("default", templateStr);
        cfg.setTemplateLoader(loader);
        try {
            StringWriter writer = new StringWriter();
            Template template = cfg.getTemplate("default");
            template.process(model, writer);
            return writer.toString();
        } catch (Exception e) {
            log.error("generate content from template error.", e);
        }
        return null;
    }

    public static void main(String[] args) {
        Map m  = new HashMap();
        m.put("body", "123123");
        System.out.println(FreeMarkerUtil.generate("<a>${body}</a>", m));
    }

}
