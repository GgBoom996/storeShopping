package cn.itsource.service.impl;

import cn.itsource.domain.Product;
import cn.itsource.mapper.ProductMapper;
import cn.itsource.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
