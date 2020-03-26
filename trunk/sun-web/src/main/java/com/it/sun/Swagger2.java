package com.it.sun;

import com.google.common.base.Predicates;
import com.it.common.utils.ErrorCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Swagger插件的配置类，开发环境保留，正式生产的时候，可以 去掉配置，并且将war包中webapp里面的/sw/文件夹删除
 * @author sunshuai
 * 2016-10-20
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket api() {

        //添加全局响应状态码
        List<ResponseMessage> responseMessageList = new ArrayList<>();

        Arrays.stream(ErrorCode.values()).forEach(ErrorCode -> {
            responseMessageList.add(
                    new ResponseMessageBuilder().code(ErrorCode.getStatus()).message(ErrorCode.getMsg()).responseModel(
                            new ModelRef(ErrorCode.getMsg())).build()
            );
        });

        return new Docket(DocumentationType.SWAGGER_2)

                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)

                .apiInfo(apiInfo())
                .select()  // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.any()) // 对所有api进行监控
                .paths(Predicates.not(PathSelectors.regex("/error.*")))// 错误路径不监控
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("孙帅-知识图谱-API文档")
                .description("南方朗郎")
                .termsOfServiceUrl("https://openapi.w.com:8085/")
                .contact(new Contact("lenleicool#163.com", "https://www.w.com/", "lenleicool#163.com"))
                .version("1.1.18")
                .build();
    }

}