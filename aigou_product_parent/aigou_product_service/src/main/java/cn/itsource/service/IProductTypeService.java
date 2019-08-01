package cn.itsource.service;

import cn.itsource.domain.ProductType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品目录 服务类
 * </p>
 *
 * @author GgBoom
 * @since 2019-07-30
 */
public interface IProductTypeService extends IService<ProductType> {

    List<ProductType> loadTypeTree();

}
