package cn.itsource.controller;

import cn.itsource.domain.Product;
import cn.itsource.domain.Specification;
import cn.itsource.query.ProductQuery;
import cn.itsource.service.IProductService;
import cn.itsource.util.AjaxResult;
import cn.itsource.util.PageList;
import cn.itsource.util.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.json.AbstractJackson2Decoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    public IProductService productService;

    /**
    * 保存和修改公用的
    * @param product  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Product product){
        try {
            if(product.getId()!=null){
                productService.updateById(product);
            }else{
                productService.save(product);
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
            productService.removeById(id);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.getAjaxResult().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //通过id获取信息
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Product get(@RequestParam(value="id",required=true) Long id)
    {
        return productService.getById(id);
    }


    /**
    * 查看所有的信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Product> list(){

        return productService.list(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Product> json(@RequestBody ProductQuery query)
    {
        return productService.queryPage(query);
    }

    /**
     * 查询商品的显示属性
     * @param productId
     * @return
     */
    @GetMapping("/getViewProperties")
    public List<Specification> getViewProperties(@RequestParam("productId")Long productId){
        return productService.getViewProperties(productId);
    }

    /**
     * 查询商品的SKU属性
     * @param productId
     * @return
     */
    @GetMapping("/getSkuProperties")
    public List<Specification> getSkuProperties(@RequestParam("productId")Long productId){
        return productService.getSkuProperties(productId);
    }

    /**
     * 修改商品的显示属性
     * @param para
     * @return
     */
    @PostMapping("/updateViewProperties")
    public AjaxResult updateViewProperties(@RequestBody Map<String,Object> para){
        int productId = (Integer)para.get("productId");
        String viewProperties = (String) para.get("viewProperties");
        try {
            productService.updateViewProperties(productId,viewProperties);
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("修改成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("操作失败!"+e.getMessage());
        }
    }

    //对sku属性的更改 如果没有则直接从数据库拿取值
    @PostMapping("/updateSkuProperties")
    public AjaxResult updateSkuProperties(@RequestBody Map<String,Object> param){
        //获取参数
        long productId = (Integer)param.get("productId");
        List<Map<String, String>> skus = (List<Map<String, String>>) param.get("skus");
        List<Map<String, String>> skuProperties = (List<Map<String, String>>) param.get("skuProperties");

        //调用service方法  在同一个事务中处理
        try {
            productService.updateSkuProperties(productId,skus,skuProperties);
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("失败！");
        }
    }

    //上架
    @GetMapping("/onSale")
    public AjaxResult onSale(String ids){
        List<Long> longs = StrUtils.splitStr2LongArr(ids);
        try {
            productService.onSale(longs);
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("上架成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("上架失败");
        }
    }

    //下架
    @GetMapping("offSale")
    public AjaxResult offSale(String ids){
        List<Long> longs = StrUtils.splitStr2LongArr(ids);
        try {
            productService.offSale(longs);
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("下架成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("下架失败");
        }
    }
}
