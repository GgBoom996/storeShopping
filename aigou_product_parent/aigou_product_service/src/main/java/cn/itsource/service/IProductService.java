package cn.itsource.service;

import cn.itsource.domain.Product;
import cn.itsource.domain.Specification;
import cn.itsource.query.ProductQuery;
import cn.itsource.util.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author GgBoom
 * @since 2019-07-30
 */
public interface IProductService extends IService<Product> {

    PageList<Product> queryPage(ProductQuery query);

    List<Specification> getViewProperties(Long productId);

    void updateViewProperties(long productId, String viewProperties);

    List<Specification> getSkuProperties(Long productId);

    void updateSkuProperties(long productId, List<Map<String, String>> skus, List<Map<String, String>> skuProperties);

    void onSale(List<Long> longs);

    void offSale(List<Long> longs);
}
