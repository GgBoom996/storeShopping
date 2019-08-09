package cn.itsource;

import cn.itsource.util.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "AIGOU-COMMON",fallbackFactory = RedisFallBackFactory.class)
@Component
public interface RedisClient {
    /*缓存存数据*/
    @PostMapping("/redis")
    public AjaxResult set(@RequestParam("key") String key,@RequestParam("value") String value);
    /*缓存拿数据*/
    @GetMapping("/redis")
    public AjaxResult get(@RequestParam("key") String key);

}
