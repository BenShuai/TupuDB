package com.it.sun.controller;

import com.it.common.base.config.RedisConfigProperties;
import com.it.sun.task.AnsyncTask;
import com.it.common.uitl.ErrorCode;
import com.it.sun.vo.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@Api(value="/Sample",description="公共信息查询类")
@RestController
@RequestMapping(value="/Sample")
public class SampleController {
    //http://localhost:8085/swagger-ui.html

    @Autowired
    private AnsyncTask ansyncTask;

    @Autowired
    private RedisConfigProperties redisConfigProperties;


    @ApiOperation(value="返回字符串", notes="返回字符串")
    @RequestMapping(value="/hello", method= RequestMethod.GET)
    public String hello(HttpServletRequest request, HttpServletResponse response) {
        return "{\"name\":\"sunshuai\",\"value\":\""+redisConfigProperties.host+"\"}";
    }

    @ApiOperation(value="调用异步执行方法", notes="调用异步执行方法")
    @RequestMapping(value="/AnsyncPlay", method=RequestMethod.POST)
    public ApiResult AnsyncPlay() {
        ApiResult apiResult = new ApiResult(ErrorCode.ERROR.getStatus(),ErrorCode.ERROR.getMsg(),"执行失败");
        try {
            ansyncTask.Task1();
            ansyncTask.Task2();
            ansyncTask.Task3();

            return new ApiResult(ErrorCode.SUCCESS.getStatus(),ErrorCode.SUCCESS.getMsg(),"success");
        }catch (Exception e){
            apiResult.setData(e.getMessage());
            return apiResult;
        }
    }


}
