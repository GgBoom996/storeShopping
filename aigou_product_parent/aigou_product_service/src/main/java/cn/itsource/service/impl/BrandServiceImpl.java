package cn.itsource.service.impl;

import cn.itsource.domain.Brand;
import cn.itsource.mapper.BrandMapper;
import cn.itsource.query.BrandQuery;
import cn.itsource.service.IBrandService;
import cn.itsource.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 品牌信息 服务实现类
 * </p>
 *
 * @author GgBoom
 * @since 2019-07-30
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public PageList<Brand> selectPage(BrandQuery query) {
        Page<Brand> page = new Page<>(query.getSize(),query.getNum());
        IPage<Brand> brandIPage = brandMapper.selectPage(page, query);
        return new PageList<Brand>(brandIPage.getTotal(),brandIPage.getRecords());
    }
}
