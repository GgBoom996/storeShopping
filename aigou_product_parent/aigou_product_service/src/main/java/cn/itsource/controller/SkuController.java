package cn.itsource.controller;

import cn.itsource.domain.Sku;
import cn.itsource.query.SkuQuery;
import cn.itsource.service.ISkuService;
import cn.itsource.util.AjaxResult;
import cn.itsource.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sku")
public class SkuController {
    @Autowired
    public ISkuService skuService;

    /**
    * 保存和修改公用的
    * @param sku  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Sku sku){
        try {
            if(sku.getId()!=null){
                skuService.updateById(sku);
            }else{
                skuService.save(sku);
            }
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            skuService.removeById(id);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.getAjaxResult().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //通过id获取信息
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Sku get(@RequestParam(value="id",required=true) Long id)
    {
        return skuService.getById(id);
    }


    /**
    * 查看所有的信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Sku> list(){

        return skuService.list(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Sku> json(@RequestBody SkuQuery query)
    {
        IPage<Sku> page = skuService.page(new Page<Sku>(query.getSize(),query.getNum()));
        return new PageList<>(page.getTotal(),page.getRecords());
    }
}
