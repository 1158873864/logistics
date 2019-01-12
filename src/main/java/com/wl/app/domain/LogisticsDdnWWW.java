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

import com.wl.app.domain.enumeration.Status;

/**
 * 物流专线网点
 * @author Donny.
 */
@ApiModel(description = "物流专线网点 @author Donny.")
@Entity
@Table(name = "logistics_ddn_www")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "logisticsddnwww")
public class LogisticsDdnWWW extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名称
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "名称", required = true)
    @Column(name = "name", length = 200, nullable = false)
    private String name;
    
    /**
     * 联系人
     */
    @Size(max = 20)
    @ApiModelProperty(value = "联系人")
    @Column(name = "contacts")
    private String contacts;

    /**
     * 手机号
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "手机号", required = true)
    @Column(name = "mobile_phone", length = 20, nullable = false)
    private String mobilePhone;

    /**
     * 电话号码
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "电话号码", required = true)
    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    /**
     * 说明
     */
    @NotNull
    @Size(max = 500)
    @ApiModelProperty(value = "说明", required = true)
    @Column(name = "remark", length = 500, nullable = false)
    private String remark;

    /**
     * 状态
     */
    @NotNull
    @ApiModelProperty(value = "状态", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne
    @JsonIgnoreProperties("")
    private LogisticsDdn logisticsDdn;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public LogisticsDdnWWW name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public LogisticsDdnWWW mobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPhone() {
        return phone;
    }

    public LogisticsDdnWWW phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public LogisticsDdnWWW remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Status getStatus() {
        return status;
    }

    public LogisticsDdnWWW status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LogisticsDdn getLogisticsDdn() {
        return logisticsDdn;
    }

    public LogisticsDdnWWW logisticsDdn(LogisticsDdn logisticsDdn) {
        this.logisticsDdn = logisticsDdn;
        return this;
    }

    public void setLogisticsDdn(LogisticsDdn logisticsDdn) {
        this.logisticsDdn = logisticsDdn;
    }
    
    
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LogisticsDdnWWW logisticsDdnWWW = (LogisticsDdnWWW) o;
        if (logisticsDdnWWW.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), logisticsDdnWWW.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LogisticsDdnWWW{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", mobilePhone='" + getMobilePhone() + "'" +
            ", phone='" + getPhone() + "'" +
            ", remark='" + getRemark() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
