package cn.itsource.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品属性
 * </p>
 *
 * @author GgBoom
 * @since 2019-08-05
 */
@TableName("t_specification")
public class Specification extends Model<Specification> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 规格名称
     */
    @TableField("specName")
    private String specName;

    private Long productTypeId;

    @TableField("isSku")
    private Integer isSku;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @TableField(exist = false)
    private String value;//显示属性的值

    //sku属性 一个属性对应多个属性选项
    @TableField(exist = false)
    private List<String> options = new ArrayList<>();



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getIsSku() {
        return isSku;
    }

    public void setIsSku(Integer isSku) {
        this.isSku = isSku;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Specification{" +
        "id=" + id +
        ", specName=" + specName +
        ", productTypeId=" + productTypeId +
        ", isSku=" + isSku +
        "}";
    }
}
