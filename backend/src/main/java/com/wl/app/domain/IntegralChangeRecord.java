package com.wl.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * 积分变化记录
 * @author Donny.
 */
@ApiModel(description = "积分变化记录 @author Donny.")
@Entity
@Table(name = "integral_change_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "integralchangerecord")
public class IntegralChangeRecord extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 类型
     */
    @NotNull
    @ApiModelProperty(value = "类型", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "integral_rule_type", nullable = false)
    private IntegralRuleType integralRuleType;

    /**
     * 内容
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "内容", required = true)
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    /**
     * 值
     */
    @NotNull
    @ApiModelProperty(value = "值", required = true)
    @Column(name = "jhi_value", nullable = false)
    private Integer value;

    @ManyToOne
    @JsonIgnoreProperties("")
    private UserInfo userInfo;

    @ManyToOne
    @JsonIgnoreProperties("")
    private IntegralRule integralRule;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IntegralRuleType getIntegralRuleType() {
        return integralRuleType;
    }

    public IntegralChangeRecord integralRuleType(IntegralRuleType integralRuleType) {
        this.integralRuleType = integralRuleType;
        return this;
    }

    public void setIntegralRuleType(IntegralRuleType integralRuleType) {
        this.integralRuleType = integralRuleType;
    }

    public String getName() {
        return name;
    }

    public IntegralChangeRecord name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public IntegralChangeRecord value(Integer value) {
        this.value = value;
        return this;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public IntegralChangeRecord userInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public IntegralRule getIntegralRule() {
        return integralRule;
    }

    public IntegralChangeRecord integralRule(IntegralRule integralRule) {
        this.integralRule = integralRule;
        return this;
    }

    public void setIntegralRule(IntegralRule integralRule) {
        this.integralRule = integralRule;
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
        IntegralChangeRecord integralChangeRecord = (IntegralChangeRecord) o;
        if (integralChangeRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), integralChangeRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IntegralChangeRecord{" +
            "id=" + getId() +
            ", integralRuleType='" + getIntegralRuleType() + "'" +
            ", name='" + getName() + "'" +
            ", value=" + getValue() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
