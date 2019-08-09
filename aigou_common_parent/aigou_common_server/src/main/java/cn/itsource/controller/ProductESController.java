package cn.itsource.controller;

import cn.itsource.ProductESClient;
import cn.itsource.domain.ProductDoc;
import cn.itsource.repository.ProductDocRepository;
import cn.itsource.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/es")
public class ProductESController implements ProductESClient{

    @Autowired
    private ProductDocRepository productDocRepository;

    //单个保存
    @PostMapping("/save")
    public AjaxResult save(@RequestBody ProductDoc productDoc){
        try {
            productDocRepository.save(productDoc);
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("单个保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("单个保存失败！");
        }
    }
    //批量保存
    @PostMapping("/saveBatch")
    public AjaxResult saveBatch(@RequestBody List<ProductDoc> productDocList){
        try {
            productDocRepository.saveAll(productDocList);
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("单个保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("单个保存失败！");
        }
    }
    //单个删除   传入的不是对象   那么就用@RequestParam
    @GetMapping("/delete")
    public AjaxResult delete(@RequestParam("productId") Long id){
        try {
            productDocRepository.deleteById(id);
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("单个保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("单个保存失败！");
        }
    }
    //批量删除
    @PostMapping("/deleteBatch")
    public AjaxResult deleteBatch(@RequestBody List<Long> ids){
        try {
            for (Long id : ids) {
                productDocRepository.deleteById(id);
            }
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("单个保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("单个保存失败！");
        }
    }
}
