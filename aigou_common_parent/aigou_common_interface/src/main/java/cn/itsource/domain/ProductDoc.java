package cn.itsource.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 搜索
 *  keyword(商品标题 商品副标题 类型名称 品牌名称）
 *  品牌ID
 *  商品类型ID
 *  价格区间（最高价格，最低价格）
 *  销量、上架时间、评论数量、价格（最高价格，最低价格）、浏览量
 *
 * 数据展示
 *  商品的标题
 *  商品副标题
 *  商品的媒体属性
 *  销量
 *  价格（最高价格，最低价格）
 *  skuProperties
 *  viewProperties
 *
 *
 */
@Document(indexName = "aigou-product")
public class ProductDoc {

    //商品ID
    @Id
    private Long id;
    //关键字
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String all;
    //品牌ID
    private Long brandId;
    //类型ID
    private Long productTypeId;
    //最高价格
    private Integer maxPrice; //分
    //最低价格
    private Integer minPrice;
    //销量
    private Integer saleCount;
    //上架时间
    private Long onSaleTime;
    //评论数量
    private Integer commentCount;
    //浏览量
    private Integer viewCount;
    //商品标题
    private String name;
    //商品副标题
    private String subName;
    //媒体属性
    private String medias;
    //显示属性
    private String viewProperties;
    //sku属性
    private String skuProperties;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Long getOnSaleTime() {
        return onSaleTime;
    }

    public void setOnSaleTime(Long onSaleTime) {
        this.onSaleTime = onSaleTime;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getMedias() {
        return medias;
    }

    public void setMedias(String medias) {
        this.medias = medias;
    }

    public String getViewProperties() {
        return viewProperties;
    }

    public void setViewProperties(String viewProperties) {
        this.viewProperties = viewProperties;
    }

    public String getSkuProperties() {
        return skuProperties;
    }

    public void setSkuProperties(String skuProperties) {
        this.skuProperties = skuProperties;
    }
}
