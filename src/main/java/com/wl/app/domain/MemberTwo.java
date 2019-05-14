package com.wl.app.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;


@ApiModel(description = "会员录入")
@Entity
@Table(name = "membertwo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "membertwo")
public class MemberTwo extends AbstractAuditingEntity implements Serializable{


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 开通日期
     */
    @NotNull
    @ApiModelProperty(value = "开通日期", required = true)
    @Column(name = "start_date", nullable = false)
    private Instant startDate;

    /**
     * 到期日期
     */
    @NotNull
    @ApiModelProperty(value = "到期日期", required = true)
    @Column(name = "end_date", nullable = false)
    private Instant endDate;

    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "手机号", required = true)
    @Column(name = "mobile_phone", length = 20, nullable = false)
    private String mobilePhone;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public com.wl.app.domain.MemberTwo startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public com.wl.app.domain.MemberTwo endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public MemberTwo mobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        com.wl.app.domain.Member member = (com.wl.app.domain.Member) o;
        if (member.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), member.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MemberTwo{" +
                "id=" + getId() +
                ", startDate='" + getStartDate() + "'" +
                ", endDate='" + getEndDate() + "'" +
                ", createdBy='" + getCreatedBy() + "'" +
                ", lastModifiedBy='" + getLastModifiedBy() + "'" +
                ", createdDate='" + getCreatedDate() + "'" +
                ", lastModifiedDate='" + getLastModifiedDate() + "'" +
                "}";
    }

}