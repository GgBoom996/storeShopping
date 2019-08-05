package cn.itsource.mapper;

import cn.itsource.domain.ProductExt;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 商品扩展 Mapper 接口
 * </p>
 *
 * @author GgBoom
 * @since 2019-08-05
 */
@Component
public interface ProductExtMapper extends BaseMapper<ProductExt> {

    void updateViewProperties(@Param("productId") long productId, @Param("viewProperties") String viewProperties);

}
