package cn.itsource;

import cn.itsource.domain.ProductDoc;
import cn.itsource.util.AjaxResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductESFallBackFactory implements FallbackFactory<ProductESClient> {
    @Override
    public ProductESClient create(Throwable throwable) {
        return new ProductESClient() {
            @Override
            public AjaxResult save(ProductDoc productDoc) {
                return AjaxResult.getAjaxResult().setSuccess(false).setMessage("系统异常！");
            }

            @Override
            public AjaxResult saveBatch(List<ProductDoc> productDocList) {
                return AjaxResult.getAjaxResult().setSuccess(false).setMessage("系统异常！");
            }

            @Override
            public AjaxResult delete(Long id) {
                return AjaxResult.getAjaxResult().setSuccess(false).setMessage("系统异常！");
            }

            @Override
            public AjaxResult deleteBatch(List<Long> ids) {
                return AjaxResult.getAjaxResult().setSuccess(false).setMessage("系统异常！");
            }
        };
    }
}
