package cn.itsource.service.impl;

import cn.itsource.domain.ProductType;
import cn.itsource.mapper.ProductTypeMapper;
import cn.itsource.service.IProductTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @Override
    public List<ProductType> loadTypeTree() {
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
