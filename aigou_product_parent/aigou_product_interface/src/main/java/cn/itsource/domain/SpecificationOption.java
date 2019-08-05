package cn.itsource.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 商品选项
 * </p>
 *
 * @author GgBoom
 * @since 2019-08-05
 */
@TableName("t_specification_option")
public class SpecificationOption extends Model<SpecificationOption> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 规格ID
     */
    private Long specId;

    /**
     * 选项名称
     */
    @TableField("optionName")
    private String optionName;

    private Integer index;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SpecificationOption{" +
        "id=" + id +
        ", specId=" + specId +
        ", optionName=" + optionName +
        ", index=" + index +
        "}";
    }
}
