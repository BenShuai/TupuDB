package com.it.sun.controller;

import com.alibaba.fastjson.JSONObject;
import com.it.common.base.config.RedisConfigProperties;
import com.it.sun.cache.BaseCaChe;
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


    @ApiOperation(value="添加数据关系", notes="添加数据关系")
    @RequestMapping(value="/addData", method= {RequestMethod.GET,RequestMethod.POST})
    public String addData(@ApiParam(name = "obja", value = "对象A", required = true) @RequestParam(required = true) String obja,
                          @ApiParam(name = "relationship", value = "对象A和B的关系 → A 的 XXX 是 B", required = true) @RequestParam(required = true) String relationship,
                          @ApiParam(name = "objb", value = "对象B", required = true) @RequestParam(required = true) String objb,
                          HttpServletRequest request, HttpServletResponse response) {
        JSONObject jo=new JSONObject();
        jo.put("obja",obja);
        jo.put("relationship",relationship);
        jo.put("objb",objb);
        BaseCaChe.addChildJSONObject(jo);
        return "{\"code\":\""+ErrorCode.SUCCESS+"\",\"msg\":\"添加成功\"}";
    }

    @ApiOperation(value="删除数据关系", notes="删除数据关系")
    @RequestMapping(value="/delData", method= {RequestMethod.GET,RequestMethod.POST})
    public String delData(@ApiParam(name = "obja", value = "对象A", required = true) @RequestParam(required = true) String obja,
                          @ApiParam(name = "relationship", value = "对象A和B的关系 → A 的 XXX 是 B", required = true) @RequestParam(required = true) String relationship,
                          @ApiParam(name = "objb", value = "对象B", required = true) @RequestParam(required = true) String objb,
                          HttpServletRequest request, HttpServletResponse response) {
        JSONObject jo=new JSONObject();
        jo.put("obja",obja);
        jo.put("relationship",relationship);
        jo.put("objb",objb);
        BaseCaChe.delChildJSONObject(jo);
        return "{\"code\":\""+ErrorCode.SUCCESS+"\",\"msg\":\"删除成功\"}";
    }
}
