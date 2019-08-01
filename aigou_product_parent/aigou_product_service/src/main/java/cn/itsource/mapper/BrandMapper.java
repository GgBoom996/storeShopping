package cn.itsource.mapper;

import cn.itsource.domain.Brand;
import cn.itsource.query.BrandQuery;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 品牌信息 Mapper 接口
 * </p>
 *
 * @author GgBoom
 * @since 2019-07-30
 */
@Component
public interface BrandMapper extends BaseMapper<Brand> {

    IPage<Brand> selectPage(Page page, @Param("query")BrandQuery query);
}
