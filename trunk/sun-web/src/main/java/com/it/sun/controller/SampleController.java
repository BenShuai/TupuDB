package com.it.sun.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.it.common.base.config.BaseConfigProperties;
import com.it.sun.data.DataEdit;
import com.it.common.utils.ErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value="/Sample",description="公共信息查询类")
@RestController
@RequestMapping(value="/Sample")
public class SampleController {
    //http://localhost:8085/swagger-ui.html

    @Autowired
    private BaseConfigProperties baseConfigProperties;

    @ApiOperation(value="1:添加数据对象", notes="添加数据对象",position = 1)
    @RequestMapping(value="/addObj", method= {RequestMethod.POST})
    public String addObj(@ApiParam(name = "obj", value = "对象", required = true) @RequestParam(required = true) String obj,
                         @ApiParam(name = "key", value = "对象的key", required = false) @RequestParam(required = false) String key,
                         @ApiParam(name = "subkey", value = "对象的副key", required = false) @RequestParam(required = false) String subkey,
                         @ApiParam(name = "val", value = "对象的值", required = false) @RequestParam(required = false) String val) {
        JSONObject resultJo = new JSONObject();
        resultJo.put("code", ErrorCode.SUCCESS.getStatus());
        resultJo.put("msg", "添加成功");
        if (!DataEdit.addData(baseConfigProperties.data, obj.trim(), key!=null?key.trim():null,subkey!=null?subkey.trim():null, val!=null?val.trim():null)) {
            resultJo.put("code", ErrorCode.ERROR.getStatus());
            resultJo.put("msg", "添加失败");
        }
        return resultJo.toJSONString();
    }

    @ApiOperation(value="2:删除数据对象", notes="删除数据对象",position = 2)
    @RequestMapping(value="/delObj", method= {RequestMethod.POST})
    public String delObj(@ApiParam(name = "obj", value = "对象", required = true) @RequestParam(required = true) String obj) {
        JSONObject resultJo = new JSONObject();
        resultJo.put("code", ErrorCode.SUCCESS.getStatus());
        resultJo.put("msg", "删除成功");
        if(!DataEdit.delData(baseConfigProperties.data, obj.trim())){
            resultJo.put("code", ErrorCode.ERROR.getStatus());
            resultJo.put("msg", "删除失败");
        }
        return resultJo.toJSONString();
    }

    @ApiOperation(value="3:查询数据对象", notes="查询数据对象",position = 3)
    @RequestMapping(value="/queObj", method= {RequestMethod.POST})
    public String queObj(@ApiParam(name = "obj", value = "对象", required = true) @RequestParam(required = true) String obj,
                         @ApiParam(name = "searchKey", value = "搜索关键字 — 模糊匹配", required = false) @RequestParam(required = false) String searchKey) {
        JSONObject resultJo = new JSONObject();
        resultJo.put("code", ErrorCode.SUCCESS.getStatus());
        resultJo.put("msg", "查询成功");
        JSONArray ja=DataEdit.queData(baseConfigProperties.data, obj.trim(),searchKey!=null?searchKey.trim():null);
        if(ja==null){
            resultJo.put("code", ErrorCode.ERROR.getStatus());
            resultJo.put("msg", "数据不存在");
        }else {
            resultJo.put("v",ja);
        }
        return resultJo.toJSONString();
    }


////////////////////////////////////////////////////////////////////// 上面处理对象 — 下面处理关系 ///////////////////////////////////////////////////////////////////////////////////


    @ApiOperation(value="a:添加数据关系", notes="添加数据关系",position = 4)
    @RequestMapping(value="/addDataRelation", method= {RequestMethod.POST})
    public String addDataRelation(@ApiParam(name = "obja", value = "对象A", required = true) @RequestParam(required = true) String obja,
                          @ApiParam(name = "relation", value = "对象A和B的关系 → A 的 XXX 是 B", required = true) @RequestParam(required = true) String relation,
                          @ApiParam(name = "mutual", value = "关系是否双向的 — 1：是   0：不是  如果是双向，那么 \"A 的 relation 是 B\" 并且 \"B 的 relation 是 A\"", required = true) @RequestParam(required = true)  int mutual,
                          @ApiParam(name = "objb", value = "对象B", required = true) @RequestParam(required = true) String objb) {
        JSONObject resultJo = new JSONObject();
        resultJo.put("code", ErrorCode.SUCCESS.getStatus());
        resultJo.put("msg", "添加成功");
        if(!DataEdit.addDataRelation(baseConfigProperties.data,obja.trim(),relation.trim(),mutual,objb.trim())){
            resultJo.put("code", ErrorCode.ERROR.getStatus());
            resultJo.put("msg", "添加失败");
        }
        return resultJo.toJSONString();
    }

    @ApiOperation(value="b:删除数据关系", notes="删除数据关系",position = 5)
    @RequestMapping(value="/delDataRelation", method= {RequestMethod.POST})
    public String delDataRelation(@ApiParam(name = "obja", value = "对象A", required = true) @RequestParam(required = true) String obja,
                          @ApiParam(name = "relation", value = "对象A和B的关系 → A 的 XXX 是 B", required = true) @RequestParam(required = true) String relation,
                          @ApiParam(name = "objb", value = "对象B", required = true) @RequestParam(required = true) String objb) {
        JSONObject resultJo = new JSONObject();
        resultJo.put("code", ErrorCode.SUCCESS.getStatus());
        resultJo.put("msg", "删除成功");
        if(obja.trim().equals(objb.trim())){
            resultJo.put("code", ErrorCode.ERROR.getStatus());
            resultJo.put("msg", "对象A 和 对象B 不能是同一个对象");
        }else if(!DataEdit.delDataRelation(baseConfigProperties.data,obja.trim(),relation.trim(),objb.trim())){
            resultJo.put("code", ErrorCode.ERROR.getStatus());
            resultJo.put("msg", "删除失败");
        }
        return resultJo.toJSONString();
    }

    @ApiOperation(value="c:查询数据关系", notes="查询数据关系",position = 6)
    @RequestMapping(value="/queDataRelation", method= {RequestMethod.POST})
    public String queDataRelation(@ApiParam(name = "obja", value = "对象A", required = true) @RequestParam(required = true) String obja,
                          @ApiParam(name = "relation", value = "对象A和B的关系 → A 的 XXX 是 B", required = false) @RequestParam(required = false) String relation,
                          @ApiParam(name = "objb", value = "对象B", required = false) @RequestParam(required = false) String objb) {
        JSONObject resultJo = new JSONObject();
        resultJo.put("code", ErrorCode.SUCCESS.getStatus());
        resultJo.put("msg", "查询成功");
        JSONArray ja=DataEdit.queDataRelation(baseConfigProperties.data,obja.trim(),relation!=null?relation.trim():null,objb!=null?objb.trim():null);
        if(ja==null){
            resultJo.put("code", ErrorCode.ERROR.getStatus());
            resultJo.put("msg", "查询失败");
        }else {
            resultJo.put("v",ja);
        }
        return resultJo.toJSONString();
    }
}
