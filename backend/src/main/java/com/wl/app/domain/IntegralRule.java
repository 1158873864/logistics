package com.wl.app.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.wl.app.domain.enumeration.IntegralRuleType;

/**
 * 积分规则
 * @author Donny.
 */
@ApiModel(description = "积分规则 @author Donny.")
@Entity
@Table(name = "integral_rule")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "integralrule")
public class IntegralRule extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 编码
     */
//    @NotNull
//    @Size(max = 40)
    @ApiModelProperty(value = "编码")
    @Column(name = "code", length = 40)
    private String code;

    /**
     * 名称
     */
    @NotNull
    @Size(max = 40)
    @ApiModelProperty(value = "名称", required = true)
    @Column(name = "name", length = 40, nullable = false)
    private String name;

    /**
     * 类型
     */
    @NotNull
    @ApiModelProperty(value = "类型", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "integral_rule_type", nullable = false)
    private IntegralRuleType integralRuleType;

    /**
     * 值
     */
    @NotNull
    @ApiModelProperty(value = "值", required = true)
    @Column(name = "jhi_value", nullable = false)
    private Integer value;

    /**
     * 备注
     */
    @NotNull
    @Size(max = 300)
    @ApiModelProperty(value = "备注", required = true)
    @Column(name = "remark", length = 300, nullable = false)
    private String remark;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public IntegralRule code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public IntegralRule name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IntegralRuleType getIntegralRuleType() {
        return integralRuleType;
    }

    public IntegralRule integralRuleType(IntegralRuleType integralRuleType) {
        this.integralRuleType = integralRuleType;
        return this;
    }

    public void setIntegralRuleType(IntegralRuleType integralRuleType) {
        this.integralRuleType = integralRuleType;
    }

    public Integer getValue() {
        return value;
    }

    public IntegralRule value(Integer value) {
        this.value = value;
        return this;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public IntegralRule remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IntegralRule integralRule = (IntegralRule) o;
        if (integralRule.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), integralRule.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IntegralRule{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", integralRuleType='" + getIntegralRuleType() + "'" +
            ", value=" + getValue() +
            ", remark='" + getRemark() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
