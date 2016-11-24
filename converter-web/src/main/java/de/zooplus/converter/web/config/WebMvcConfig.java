package de.zooplus.converter.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.annotation.AnnotationJmxAttributeSource;
import org.springframework.jmx.export.assembler.MetadataMBeanInfoAssembler;
import org.springframework.jmx.export.naming.MetadataNamingStrategy;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;
import org.springframework.jmx.support.MBeanServerFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.management.MBeanServer;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by dragan
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"de.zooplus.converter.web.controller", "de.zooplus.converter.web.validation"})
@Import(PropertyConfig.class)
@EnableMBeanExport(server="myMBeanServer")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Value("${STATIC_RESOURCE_CACHE_PERIOD_IN_SECONDS}")
    private int cachePeriod;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jackson2HttpMessageConverter());
    }

    @Bean
    public InternalResourceViewResolver htmlViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setExposeContextBeansAsAttributes(true);
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        return bean;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/**","/resources/**").
                addResourceLocations("/WEB-INF/","/resources/").
                setCachePeriod(cachePeriod);

    }

    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter(){
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasenames("classpath:ValidationAndConversionMessages", "classpath:messages");
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }

    @Bean
    public MetadataNamingStrategy getNamingStrategy() {
        MetadataNamingStrategy strategy = new MetadataNamingStrategy();
        strategy.setAttributeSource(new AnnotationJmxAttributeSource());
        return strategy;
    }

    @Bean
    public MetadataMBeanInfoAssembler getMbeanInfoAssembler() {
        return new MetadataMBeanInfoAssembler(new AnnotationJmxAttributeSource());
    }

    @Bean
    @Lazy(false)
    public MBeanExporter getExporter() {
        MBeanExporter exporter = new MBeanExporter();
        exporter.setAutodetect(true);
        exporter.setNamingStrategy(getNamingStrategy());
        exporter.setAssembler(getMbeanInfoAssembler());
        exporter.setServer(mBeanServer());
        return exporter;
    }

    @Bean(name = "myMBeanServer")
    public MBeanServer mBeanServer() {
        MBeanServerFactoryBean mBeanServerFactoryBean = new MBeanServerFactoryBean();
        mBeanServerFactoryBean.setLocateExistingServerIfPossible(true);
        mBeanServerFactoryBean.afterPropertiesSet();
        MBeanServer server = mBeanServerFactoryBean.getObject();


        return server;
    }

    @Bean
    public ConnectorServerFactoryBean serverConnector(){
        return new ConnectorServerFactoryBean();
    }

    @Bean
    public MBeanServerConnectionFactoryBean clientConnector(){
        MBeanServerConnectionFactoryBean factoryBean = new MBeanServerConnectionFactoryBean();
//        factoryBean.setServiceUrl(ConnectorServerFactoryBean.DEFAULT_SERVICE_URL);
        return factoryBean;
    }

}
