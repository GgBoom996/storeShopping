package cn.itsource.controller;

import cn.itsource.util.AjaxResult;
import cn.itsource.util.VelocityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StaticPageController {
    /*页面静态化*/
    @PostMapping("/genStaticPage")
    public AjaxResult genStaticPage(@RequestBody Map<String, Object> map){
        Object model = map.get("model");
        String templatePath = (String) map.get("templatePath");
        String targetPath = (String) map.get("targetPath");
        try {
            VelocityUtils.staticByTemplate(model,templatePath,targetPath);
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("静态化成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("静态化失败");
        }

    }
}
