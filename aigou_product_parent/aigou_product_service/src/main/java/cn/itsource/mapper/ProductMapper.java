package cn.itsource.mapper;


import cn.itsource.domain.Product;
import cn.itsource.query.ProductQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author GgBoom
 * @since 2019-07-30
 */
public interface ProductMapper extends BaseMapper<Product> {

    IPage<Product> queryPage(Page page, @Param("query") ProductQuery query);

}
