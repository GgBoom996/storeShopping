package cn.itsource.mapper;

import cn.itsource.domain.Brand;
import cn.itsource.query.BrandQuery;
import cn.itsource.service.IBrandService;
import cn.itsource.service.IProductTypeService;
import cn.itsource.util.PageList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrandMapperTest {
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private IBrandService brandService;

    @Autowired
    private IProductTypeService productTypeService;
    @Test
    public void test(){
        brandMapper.selectList(null).forEach(e -> System.out.println(e));
    }

    @Test
    public void test1(){
        BrandQuery brandQuery = new BrandQuery();
        brandQuery.setSize(2);
        PageList<Brand> brandPageList = brandService.selectPage(brandQuery);
        System.out.println(brandPageList.getTotal());
        brandPageList.getRows().forEach(e-> System.out.println(e));
    }
    @Test
    public void test2(){
        productTypeService.loadTypeTree().forEach(e -> System.out.println(e));
    }
}