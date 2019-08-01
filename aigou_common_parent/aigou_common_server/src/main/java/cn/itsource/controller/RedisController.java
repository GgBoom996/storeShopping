package cn.itsource.controller;

import cn.itsource.RedisClient;
import cn.itsource.util.AjaxResult;
import cn.itsource.util.RedisUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController implements RedisClient {

    /*在缓存中存值*/
    @PostMapping("/redis")
    public AjaxResult set(@RequestParam("key") String key, @RequestParam("value")String value) {
        try {
            RedisUtils.INSTANCE.set(key,value);
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("存储成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("存储失败！");
        }
    }

    /*在缓存中拿数据*/
    @GetMapping("/redis")
    public AjaxResult get(@RequestParam("key") String key){
        try {
            String value = RedisUtils.INSTANCE.get(key);
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("获取成功！").setObj(value);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("获取失败！");
        }
    }

}
