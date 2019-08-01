package cn.itsource.service.impl;

import cn.itsource.RedisClient;
import cn.itsource.StaticPageClient;
import cn.itsource.domain.ProductType;
import cn.itsource.mapper.ProductTypeMapper;
import cn.itsource.service.IProductTypeService;
import cn.itsource.util.AjaxResult;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品目录 服务实现类
 * </p>
 *
 * @author GgBoom
 * @since 2019-07-30
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private StaticPageClient staticPageClient;

    @Override
    public void genHomePage() {
        //第一步 ： 生成product.type.vm.html
        Map<String,Object> map = new HashMap<>();

        String templatePath = "G:\\ideacode\\temp\\storeShopping\\aigou-parent\\aigou_product_parent\\aigou_product_service\\src\\main\\resources\\template\\product.type.vm";
        String targetPath = "G:\\ideacode\\temp\\storeShopping\\aigou-parent\\aigou_product_parent\\aigou_product_service\\src\\main\\resources\\template\\product.type.vm.html";
        //model 就是List 存放所有的商品类型
        List<ProductType> productTypes = loadTypeTree();
        map.put("model",productTypes);
        map.put("templatePath",templatePath);
        map.put("targetPath",targetPath);
        staticPageClient.genStaticPage(map);

        //第二步 ： 生成home.html
        map = new HashMap<>();
        templatePath = "G:\\ideacode\\temp\\storeShopping\\aigou-parent\\aigou_product_parent\\aigou_product_service\\src\\main\\resources\\template\\home.vm";
        targetPath = "G:\\ideacode\\temp\\aigou-web-parent\\aigou-web-home\\home.html";
        //model 中要有一个数据是staticRoot
        Map<String,String> model = new HashMap<>();
        model.put("staticRoot","G:\\ideacode\\temp\\storeShopping\\aigou-parent\\aigou_product_parent\\aigou_product_service\\src\\main\\resources\\");
        map.put("model",model);
        map.put("templatePath",templatePath);
        map.put("targetPath",targetPath);

        staticPageClient.genStaticPage(map);
    }

    @Override
    public List<ProductType> loadTypeTree() {
        //从Redis中获取数据
        AjaxResult productTypesResult = redisClient.get("productTypes");
        String productTypesResultObj = (String) productTypesResult.getObj();
        List<ProductType> productTypes = JSON.parseArray(productTypesResultObj, ProductType.class);
        //如果拿到的集合没有值  那么从数据库中拿值
        if (productTypes == null || productTypes.size()<=0) {
            productTypes = getProductTypes();
            redisClient.set("productTypes", JSON.toJSONString(productTypes));
        }
        return productTypes;
    }


    private List<ProductType> getProductTypes() {
        List<ProductType> productTypes = baseMapper.selectList(null);
        //定义一个集合存储一级菜单  将子菜单全部加入对象中的children集合中
        ArrayList<ProductType> list = new ArrayList<>();
        //定义一个map装所有的对象   key为对象的id  value为对象
        HashMap<Long, ProductType> allProductType= new HashMap<>();
        for (ProductType productType : productTypes) {
            allProductType.put(productType.getId(), productType);
        }
        for (ProductType productType : productTypes) {
            if (productType.getPid() == 0) {
                list.add(productType);
            }else {
                ProductType parent = allProductType.get(productType.getPid());
                parent.getChildren().add(productType);
            }
        }
        return list;
    }
}
