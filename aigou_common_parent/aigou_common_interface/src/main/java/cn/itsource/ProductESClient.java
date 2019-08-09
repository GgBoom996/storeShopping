package cn.itsource;

import cn.itsource.domain.ProductDoc;
import cn.itsource.util.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "AIGOU-COMMON",fallbackFactory = ProductESFallBackFactory.class)
@Component
@RequestMapping("/es")
public interface ProductESClient {
    //单个保存
    @PostMapping("/save")
    public AjaxResult save(@RequestBody ProductDoc productDoc);
    //批量保存
    @PostMapping("/saveBatch")
    public AjaxResult saveBatch(@RequestBody List<ProductDoc> productDocList);
    //单个删除   传入的不是对象   那么就用@RequestParam
    @GetMapping("/delete")
    public AjaxResult delete(@RequestParam("productId") Long id);
    //批量删除
    @PostMapping("/deleteBatch")
    public AjaxResult deleteBatch(@RequestBody List<Long> ids);
}
