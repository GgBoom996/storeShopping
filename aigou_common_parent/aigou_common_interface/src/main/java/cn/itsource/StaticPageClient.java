package cn.itsource;

import cn.itsource.util.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(value = "AIGOU-COMMON",fallbackFactory = StaticPageFallBackFactory.class)
@Component
public interface StaticPageClient {
    /*
     * 页面静态化
     * */
    @PostMapping("/genStaticPage")
    public AjaxResult genStaticPage(@RequestBody Map<String, Object> map);

}
