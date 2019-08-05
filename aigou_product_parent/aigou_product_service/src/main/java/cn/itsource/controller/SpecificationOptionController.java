package cn.itsource.controller;

import cn.itsource.domain.SpecificationOption;
import cn.itsource.query.SpecificationOptionQuery;
import cn.itsource.service.ISpecificationOptionService;
import cn.itsource.util.AjaxResult;
import cn.itsource.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specificationOption")
public class SpecificationOptionController {
    @Autowired
    public ISpecificationOptionService specificationOptionService;

    /**
    * 保存和修改公用的
    * @param specificationOption  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody SpecificationOption specificationOption){
        try {
            if(specificationOption.getId()!=null){
                specificationOptionService.updateById(specificationOption);
            }else{
                specificationOptionService.save(specificationOption);
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
            specificationOptionService.removeById(id);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.getAjaxResult().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //通过id获取信息
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public SpecificationOption get(@RequestParam(value="id",required=true) Long id)
    {
        return specificationOptionService.getById(id);
    }


    /**
    * 查看所有的信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<SpecificationOption> list(){

        return specificationOptionService.list(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<SpecificationOption> json(@RequestBody SpecificationOptionQuery query)
    {
        IPage<SpecificationOption> page = specificationOptionService.page(new Page<SpecificationOption>(query.getSize(),query.getNum()));
        return new PageList<>(page.getTotal(),page.getRecords());
    }
}
