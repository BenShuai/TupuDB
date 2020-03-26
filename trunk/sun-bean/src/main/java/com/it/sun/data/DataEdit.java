package com.it.sun.data;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.it.common.utils.BaseUtil;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * 数据编辑类
 */
public class DataEdit {
    /**
     * 写入数据
     * @param basePath 配置的数据存储根目录
     * @param objName  数据的标题
     * @param key      数据key
     * @param subkey   数据 副key
     * @param val      数据内容
     * @return
     */
    public static Boolean addData(String basePath,String objName,String key,String subkey,String val){
        Boolean flag=false;
        if(BaseUtil.CreDir(basePath)) {
            //开始去掉对象数据中的不可以当作目录名的字符
            objName = objName.replaceAll("/", "")
                    .replaceAll("\\\\", "")
                    .replaceAll(":", "")
                    .replaceAll("\\*", "")
                    .replaceAll("\\?", "")
                    .replaceAll("\"", "")
                    .replaceAll("<", "")
                    .replaceAll(">", "")
                    .replaceAll("|", "")
                    .replaceAll(" ","");
            StringBuilder path = new StringBuilder(basePath);
            for (int i = 1; i <= objName.length(); i++) {
                path.append(objName.substring(0, i)).append("/");
                if (!BaseUtil.CreDir(path.toString())) {
                    path = null;
                    break;
                }
            }
            if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(val) && path != null) {
                JSONObject jo = new JSONObject();
                jo.put("key", key);
                jo.put("subkey",subkey);
                jo.put("val", val);

                String filePath = path.append("data.tp").toString();//数据存储的文件地址
                File file = new File(filePath);
                JSONArray ja = new JSONArray();
                if (file.exists()) {
                    String baseData = BaseUtil.ReadTxt(filePath);
                    if (!StringUtils.isEmpty(baseData)) {
                        ja = JSONArray.parseArray(baseData);
                    }
                }
                for (int i=0;i<ja.size();i++){
                    if(ja.getJSONObject(i).equals(jo)){//数据重复，就不保存没直接返回成功
                        return true;
                    }
                }
                ja.add(jo);
                BaseUtil.WriteTxt(filePath, ja.toJSONString());
                flag=true;
            }
        }
        return flag;
    }

    /**
     * 数据删除
     * 就是在数据的名字后面标记一个 "-del-时间"
     * @param basePath 数据根目录
     * @param objName  数据对象名
     * @return
     */
    public static Boolean delData(String basePath,String objName){
        Boolean flag=false;
        if(BaseUtil.CreDir(basePath)) {
            //开始去掉对象数据中的不可以当作目录名的字符
            objName = objName.replaceAll("/", "")
                    .replaceAll("\\\\", "")
                    .replaceAll(":", "")
                    .replaceAll("\\*", "")
                    .replaceAll("\\?", "")
                    .replaceAll("\"", "")
                    .replaceAll("<", "")
                    .replaceAll(">", "")
                    .replaceAll("|", "")
                    .replaceAll(" ","");
            StringBuilder path = new StringBuilder(basePath);
            for (int i = 1; i <= objName.length(); i++) {
                path.append(objName.substring(0, i)).append("/");
            }
            File f=new File(path.toString());
            if(f.exists()){
                try {
                    String dirPath = path.toString().substring(0, path.length() - 1) + "-del" + "-" + BaseUtil.GetDataStr("yyyyMMddHHmmss");
                    new File(path.toString()).renameTo(new File(dirPath));
                    flag = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 数据搜索查询
     * @param basePath  数据根目录
     * @param dataStr   数据名
     * @param searchKey 搜索关键字
     * @return
     */
    public static JSONArray queData(String basePath,String dataStr,String searchKey) {
        if (BaseUtil.CreDir(basePath)) {
            //开始去掉对象数据中的不可以当作目录名的字符
            dataStr = dataStr.replaceAll("/", "")
                    .replaceAll("\\\\", "")
                    .replaceAll(":", "")
                    .replaceAll("\\*", "")
                    .replaceAll("\\?", "")
                    .replaceAll("\"", "")
                    .replaceAll("<", "")
                    .replaceAll(">", "")
                    .replaceAll("|", "")
                    .replaceAll(" ", "");
            StringBuilder path = new StringBuilder(basePath);
            for (int i = 1; i <= dataStr.length(); i++) {
                path.append(dataStr.substring(0, i)).append("/");
            }
            File f=new File(path.toString());
            if(f.exists()){
                JSONArray ja=new JSONArray();
                String filePath = path.append("data.tp").toString();//数据存储的文件地址
                try {
                    String baseData = BaseUtil.ReadTxt(filePath);
                    if (!StringUtils.isEmpty(baseData)) {
                        ja = JSONArray.parseArray(baseData);
                        if(!StringUtils.isEmpty(searchKey)){//寻找包含这个关键字的
                            for (int i=0;i<ja.size();i++){
                                JSONObject jo=ja.getJSONObject(i);
                                if(jo.getString("key").indexOf(searchKey)!=-1 || (jo.get("subkey")!=null && jo.getString("subkey").indexOf(searchKey)!=-1) || jo.getString("val").indexOf(searchKey)!=-1){
                                    //这个是符合条件的
                                }else {
                                    ja.remove(i);
                                    i--;
                                }
                            }
                        }
                    }
                }catch (Exception e){e.printStackTrace();}
                if(ja.size()>0) {
                    return ja;
                }
            }
        }
        return null;
    }


    //////////////////////////////////////////////////////////// 上面处理对象 — 下面处理关系 ///////////////////////////////////////////////////////////////////////////


    /**
     * 添加两个对象之间的关系
     * @param basePath 数据根目录
     * @param obja     对象A
     * @param relation A 的 relation 是B
     * @param mutual   关系是否双向 — 1：是   0：不是    如果是双向，那么   "A 的 relation 是 B"  并且 "B 的 relation 是 A"
     * @param objb     对象B
     * @return
     */
    public static Boolean addDataRelation(String basePath,String obja,String relation,int mutual,String objb){
        Boolean flag=false;
        if (BaseUtil.CreDir(basePath)) {
            //开始去掉对象数据中的不可以当作目录名的字符
            obja = obja.replaceAll("/", "")
                    .replaceAll("\\\\", "")
                    .replaceAll(":", "")
                    .replaceAll("\\*", "")
                    .replaceAll("\\?", "")
                    .replaceAll("\"", "")
                    .replaceAll("<", "")
                    .replaceAll(">", "")
                    .replaceAll("|", "")
                    .replaceAll(" ", "");
            StringBuilder pathA = new StringBuilder(basePath);
            for (int i = 1; i <= obja.length(); i++) {
                pathA.append(obja.substring(0, i)).append("/");
                if (!BaseUtil.CreDir(pathA.toString())) {
                    pathA = null;
                    break;
                }
            }
            objb = objb.replaceAll("/", "")
                    .replaceAll("\\\\", "")
                    .replaceAll(":", "")
                    .replaceAll("\\*", "")
                    .replaceAll("\\?", "")
                    .replaceAll("\"", "")
                    .replaceAll("<", "")
                    .replaceAll(">", "")
                    .replaceAll("|", "")
                    .replaceAll(" ", "");
            StringBuilder pathB = new StringBuilder(basePath);
            for (int i = 1; i <= objb.length(); i++) {
                pathB.append(objb.substring(0, i)).append("/");
                if (!BaseUtil.CreDir(pathB.toString())) {
                    pathB = null;
                    break;
                }
            }
            if (pathA != null && pathB != null) {
                JSONObject jo=new JSONObject();
                jo.put("objA",obja);
                jo.put("relation",relation);
                jo.put("objB",objb);
                jo.put("mutual",mutual);

                ////// 先存 A 对象的关系数据
                String filePathA = pathA.append("relation.tp").toString();//关系数据存储的文件地址
                File fileA = new File(filePathA);
                JSONArray jaA = new JSONArray();
                if (fileA.exists()) {
                    String baseData = BaseUtil.ReadTxt(filePathA);
                    if (!StringUtils.isEmpty(baseData)) {
                        jaA = JSONArray.parseArray(baseData);
                    }
                }
                for (int i=0;i<jaA.size();i++){
                    if(jaA.getJSONObject(i).equals(jo)){//数据重复，就不保存没直接返回成功
                        return true;
                    }
                }
                jaA.add(jo);
                BaseUtil.WriteTxt(filePathA, jaA.toJSONString());

                ////// 再存 B 对象的关系数据
                String filePathB = pathB.append("relation.tp").toString();//关系数据存储的文件地址
                File fileB = new File(filePathB);
                JSONArray jaB = new JSONArray();
                if (fileB.exists()) {
                    String baseData = BaseUtil.ReadTxt(filePathB);
                    if (!StringUtils.isEmpty(baseData)) {
                        jaB = JSONArray.parseArray(baseData);
                    }
                }
                for (int i=0;i<jaB.size();i++){
                    if(jaB.getJSONObject(i).equals(jo)){//数据重复，就不保存没直接返回成功
                        return true;
                    }
                }
                jaB.add(jo);
                BaseUtil.WriteTxt(filePathB, jaB.toJSONString());
                flag=true;
            }
        }
        return flag;
    }

    /**
     * 查询关系
     * @param basePath
     * @param obja
     * @param relation
     * @param objb
     * @return
     */
    public static JSONArray queDataRelation(String basePath,String obja,String relation,String objb){
        if (BaseUtil.CreDir(basePath)) {
            //开始去掉对象数据中的不可以当作目录名的字符
            obja = obja.replaceAll("/", "")
                    .replaceAll("\\\\", "")
                    .replaceAll(":", "")
                    .replaceAll("\\*", "")
                    .replaceAll("\\?", "")
                    .replaceAll("\"", "")
                    .replaceAll("<", "")
                    .replaceAll(">", "")
                    .replaceAll("|", "")
                    .replaceAll(" ", "");
            StringBuilder pathA = new StringBuilder(basePath);
            for (int i = 1; i <= obja.length(); i++) {
                pathA.append(obja.substring(0, i)).append("/");
            }
            File f = new File(pathA.toString());
            if (f.exists()) {
                JSONArray ja = new JSONArray();
                String filePath = pathA.append("relation.tp").toString();//关系数据存储的文件地址
                try {
                    File file = new File(filePath);
                    if (file.exists()) {
                        String baseData = BaseUtil.ReadTxt(filePath);
                        if (!StringUtils.isEmpty(baseData)) {
                            ja = JSONArray.parseArray(baseData);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (ja.size() > 0) {
                    for (int i = 0; i < ja.size(); i++) {
                        JSONObject jo = ja.getJSONObject(i);

                        if (!StringUtils.isEmpty(relation)) {//关系存在
                            if (!StringUtils.isEmpty(objb)) {//关系存在 b对象存在
                                if (jo.getString("relation").indexOf(relation) == -1 || (jo.getString("objA").indexOf(objb) == -1 && jo.getString("objB").indexOf(objb) == -1)) {
                                    ja.remove(i);
                                    i--;
                                }
                            } else {//关系存在 b对象不存在
                                if (jo.getString("relation").indexOf(relation) == -1) {
                                    ja.remove(i);
                                    i--;
                                }
                            }
                        } else {//关系不存在
                            if (!StringUtils.isEmpty(objb)) {//关系不存在 b对象存在
                                if (jo.getString("objA").indexOf(objb) == -1 && jo.getString("objB").indexOf(objb) == -1) {
                                    ja.remove(i);
                                    i--;
                                }
                            } else {//关系不存在 b对象不存在
                                break;
                                //这种就不需要过滤
                            }
                        }
                    }
                }
                return ja;
            }
        }
        return null;
    }

    /**
     * 删除数据关系
     * @param basePath
     * @param obja
     * @param relation
     * @param objb
     * @return
     */
    public static Boolean delDataRelation(String basePath,String obja,String relation,String objb){
        Boolean flag=false;
        
        return flag;
    }
}
