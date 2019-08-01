package cn.itsource.service;

import cn.itsource.domain.Brand;
import cn.itsource.query.BrandQuery;
import cn.itsource.util.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 品牌信息 服务类
 * </p>
 *
 * @author GgBoom
 * @since 2019-07-30
 */
public interface IBrandService extends IService<Brand> {

    PageList<Brand> selectPage(BrandQuery query);
}
