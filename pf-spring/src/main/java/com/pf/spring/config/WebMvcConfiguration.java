package com.pf.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * Author: Ru He
 * Date: Created in 2019/1/8.
 * Description: spring相关配置类
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{

    /**
     * 配置静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/",
                        "classpath:/resources/",
                        "classpath:/static/",
                        "classpath:/public/");
    }

    /**
     * 配置匹配路由请求规则
     * setUseSuffixPatternMatch : 设置是否是后缀模式匹配，如“/user”是否匹配/user.*，默认为true
     * setUseTrailingSlashMatch : 设置是否自动后缀路径模式匹配，如“/user”是否匹配“/user/”，默认为true
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
    }

    /**
     * 内容协商配置
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        // 是否通过请求Url的扩展名来决定media type
       /* configurer.favorPathExtension(true)
                // 不检查Accept请求头
                .ignoreAcceptHeader(true)
                .parameterName("mediaType")
                // 设置默认的media yype
                .defaultContentType(MediaType.TEXT_HTML)
                // 请求以.html结尾的会被当成MediaType.TEXT_HTML
                .mediaType("html", MediaType.TEXT_HTML)
                // 请求以.json结尾的会被当成MediaType.APPLICATION_JSON
                .mediaType("json", MediaType.APPLICATION_JSON);*/

    }

    /**
     * 配置异步支持
     */
    /*@Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

    }*/

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    }

    /**
     * 配置类型转换器和格式化器
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
    }

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }

    /**
     * 添加跨域支持
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("*")
                .maxAge(3600);
    }

    /**
     * 自定义视图控制器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }

    /**
     * 配置视图解析器
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //registry.jsp("/", ".html");
        //registry.enableContentNegotiation(new MappingJackson2JsonView());
    }

    /**
     * 添加自定义方法参数处理器
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    }

    /**
     * 配置返回值解析
     */
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
    }

    /**
     * 配置消息转换器
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        /*
        //1.需要定义一个convert转换消息的对象;
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //2.添加fastJson的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteDateUseDateFormat);
        //3处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        //4.在convert中添加配置信息.
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        //5.将convert添加到converters当中.
        converters.add(fastJsonHttpMessageConverter);
        */
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    }

    /**
     * 配置异常处理
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
//        ExceptionHandlerExceptionResolver handler = new ExceptionHandlerExceptionResolver();
//        resolvers.add(handler);
//        resolvers.add(new ResponseStatusExceptionResolver());
//        resolvers.add(new DefaultHandlerExceptionResolver());
//        resolvers.add(new SimpleMappingExceptionResolver());
        /*resolvers.add(new HandlerExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        });*/
        //System.out.println(resolvers.size());
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
    }
}
