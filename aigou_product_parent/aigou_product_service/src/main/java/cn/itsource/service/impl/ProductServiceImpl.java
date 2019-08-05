package cn.itsource.service.impl;


import cn.itsource.domain.Product;
import cn.itsource.domain.ProductExt;
import cn.itsource.domain.Specification;
import cn.itsource.mapper.ProductExtMapper;
import cn.itsource.mapper.ProductMapper;
import cn.itsource.mapper.SpecificationMapper;
import cn.itsource.query.ProductQuery;
import cn.itsource.service.IProductService;
import cn.itsource.util.PageList;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author GgBoom
 * @since 2019-07-30
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Autowired
    private ProductExtMapper productExtMapper;
    @Autowired
    private SpecificationMapper specificationMapper;

    @Override
    public PageList<Product> queryPage(ProductQuery query) {
        IPage<Product> iPage = baseMapper.queryPage(new Page(query.getSize(), query.getNum()), query);
        return new PageList<>(iPage.getTotal(),iPage.getRecords());
    }

    @Override
    public List<Specification> getViewProperties(Long productId) {
        //先到product_ext中查询
        ProductExt productExt = productExtMapper.selectOne(new QueryWrapper<ProductExt>().eq("productId", productId));
        //如果viewProperties属性为null
        if(productExt.getViewProperties()!=null){
            List<Specification> viewPropertiesList = JSON.parseArray(productExt.getViewProperties(), Specification.class);
            return viewPropertiesList;
        }
        //再查询属性表
        Product product = baseMapper.selectById(productId);
        Long productTypeId = product.getProductTypeId();
        List<Specification> specifications = specificationMapper.selectList(new QueryWrapper<Specification>().eq("product_type_id", productTypeId).eq("isSku",0));
        return specifications;
    }

    /**
     * 修改商品的显示属性
     * @param productId
     * @param viewProperties
     */
    @Override
    public void updateViewProperties(long productId, String viewProperties) {
        productExtMapper.updateViewProperties(productId,viewProperties);
    }

    @Override
    public List<Specification> getSkuProperties(Long productId) {
        //先到product_ext中查询
        ProductExt productExt = productExtMapper.selectOne(new QueryWrapper<ProductExt>().eq("productId", productId));
        //如果skuProperties属性不为null
        if(productExt.getSkuProperties()!=null){
            List<Specification> viewPropertiesList = JSON.parseArray(productExt.getSkuProperties(), Specification.class);
            return viewPropertiesList;
        }
        //再查询属性表
        Product product = baseMapper.selectById(productId);
        Long productTypeId = product.getProductTypeId();
        List<Specification> specifications = specificationMapper.selectList(new QueryWrapper<Specification>().eq("product_type_id", productTypeId).eq("isSku",1));
        return specifications;
    }

    /**
     * 重写save方法，同时向product表和productExt表中插入数据
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public boolean save(Product entity) {
        //初始化部分字段的值
        entity.setCreateTime(new Date().getTime());
        entity.setState(0);
        baseMapper.insert(entity);//插入后要获取到id
        System.out.println(entity.getId()+"====================================");
        ProductExt productExt = entity.getProductExt();
        //设置productId
        productExt.setProductId(entity.getId());
        productExtMapper.insert(productExt);
        return true;
    }
}
