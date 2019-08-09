package cn.itsource.repository;

import cn.itsource.domain.ProductDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDocRepository extends ElasticsearchRepository<ProductDoc,Long>{
}
