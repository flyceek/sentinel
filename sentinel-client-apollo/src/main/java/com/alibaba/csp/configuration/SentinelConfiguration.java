package com.alibaba.csp.configuration;

import com.alibaba.csp.entity.RestfulResponse;
import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.alibaba.sentinel.datasource.converter.JsonConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SentinelConfiguration {


    @Primary
    @Bean("sentinelConverterObjectMapper")
//    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper sentinelConverterObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        // 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
        // Include.Include.ALWAYS 默认
        // Include.NON_DEFAULT 属性为默认值不序列化
        // Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化，则返回的json是没有这个字段的。这样对移动端会更省流量
        // Include.NON_NULL 属性为NULL 不序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许出现特殊字符和转义符
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        // 允许出现单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 字段保留，将null值转为""
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                    throws IOException {
                jsonGenerator.writeString("");
            }
        });
        return objectMapper;
    }

    @Primary
    @Bean("sentinel-custom-flow-converter")
    public JsonConverter jsonFlowConverter(ObjectMapper sentinelConverterObjectMapper) {
        return new JsonConverter(sentinelConverterObjectMapper, FlowRule.class);
    }

    @Primary
    @Bean("sentinel-custom-degrade-converter")
    public JsonConverter sentinelCustomDegradeConverter(ObjectMapper sentinelConverterObjectMapper) {
        return new JsonConverter(sentinelConverterObjectMapper, DegradeRule.class);
    }

    @Bean("sentinel-custom-system-converter")
    public JsonConverter jsonSystemConverter(ObjectMapper sentinelConverterObjectMapper) {
        return new JsonConverter(sentinelConverterObjectMapper, SystemRule.class);
    }

    @Primary
    @Bean("sentinel-custom-authority-converter")
    public JsonConverter jsonAuthorityConverter(ObjectMapper sentinelConverterObjectMapper) {
        return new JsonConverter(sentinelConverterObjectMapper, AuthorityRule.class);
    }

    @Primary
    @Bean("sentinel-custom-param-flow-converter")
    public JsonConverter jsonParamFlowConverter(ObjectMapper sentinelConverterObjectMapper) {
        return new JsonConverter(sentinelConverterObjectMapper, ParamFlowRule.class);
    }

    @Bean
    public UrlBlockHandler customUrlBlockHandler() {
        return new CustomUrlBlockHandler();
    }

    public class CustomUrlBlockHandler implements UrlBlockHandler {

        private final Logger logger = LoggerFactory.getLogger(CustomUrlBlockHandler.class);

        @Override
        public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
            logger.warn("Sentinel UrlBlockHandler handing a Exception: " + e.getRule().toString());
            RestfulResponse restfulResponse = RestfulResponse.fail("190901", "server busy, please try again later.");
            httpServletResponse.addHeader("Content-type", "application/json;charset=UTF-8");
            PrintWriter out = httpServletResponse.getWriter();
            out.print(JSON.toJSONString(restfulResponse));
            out.flush();
            out.close();
        }
    }

}
