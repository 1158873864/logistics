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

import com.wl.app.domain.enumeration.GoodsSourcePacking;

import com.wl.app.domain.enumeration.GoodsSourceFreight;

import com.wl.app.domain.enumeration.GoodsSourceProperty;

import com.wl.app.domain.enumeration.Status;

/**
 * 货源
 * @author Donny.
 */
@ApiModel(description = "货源 @author Donny.")
@Entity
@Table(name = "goods_source")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "goodssource")
public class GoodsSource extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 货源名称
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "货源名称", required = true)
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    /**
     * 货源路线起始
     */
    @NotNull
    @Size(max = 80)
    @ApiModelProperty(value = "货源路线起始", required = true)
    @Column(name = "jhi_start", length = 80, nullable = false)
    private String start;

    /**
     * 货源路线终点
     */
    @NotNull
    @Size(max = 80)
    @ApiModelProperty(value = "货源路线终点", required = true)
    @Column(name = "jhi_end", length = 80, nullable = false)
    private String end;

    /**
     * 吨数（吨）
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "吨数（吨）", required = true)
    @Column(name = "ton", length = 20, nullable = false)
    private String ton;

    /**
     * 体积（m³）
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "体积（m³）", required = true)
    @Column(name = "volume", length = 20, nullable = false)
    private String volume;

    /**
     * 包装
     */
    @NotNull
    @ApiModelProperty(value = "包装", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "goods_source_packing", nullable = false)
    private GoodsSourcePacking goodsSourcePacking;

    /**
     * 发布人手机号码
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "发布人手机号码", required = true)
    @Column(name = "mobile_phone", length = 20, nullable = false)
    private String mobilePhone;

    /**
     * 运价
     */
    @NotNull
    @ApiModelProperty(value = "运价", required = true)
    @Column(name = "goods_source_freight", nullable = false)
    private String goodsSourceFreight;

    /**
     * 货源属性
     */
    @NotNull
    @ApiModelProperty(value = "货源属性", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "goods_source_property", nullable = false)
    private GoodsSourceProperty goodsSourceProperty;

    /**
     * 货源有效时间
     */
    @NotNull
    @ApiModelProperty(value = "货源有效时间", required = true)
    @Column(name = "effective_date", nullable = false)
    private Instant effectiveDate;

    /**
     * 装货时间
     */
    @NotNull
    @ApiModelProperty(value = "装货时间", required = true)
    @Column(name = "lay_time", nullable = false)
    private Instant layTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne
    @JsonIgnoreProperties("")
    private UserInfo userInfo;

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

    public GoodsSource name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public GoodsSource start(String start) {
        this.start = start;
        return this;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public GoodsSource end(String end) {
        this.end = end;
        return this;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getTon() {
        return ton;
    }

    public GoodsSource ton(String ton) {
        this.ton = ton;
        return this;
    }

    public void setTon(String ton) {
        this.ton = ton;
    }

    public String getVolume() {
        return volume;
    }

    public GoodsSource volume(String volume) {
        this.volume = volume;
        return this;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public GoodsSourcePacking getGoodsSourcePacking() {
        return goodsSourcePacking;
    }

    public GoodsSource goodsSourcePacking(GoodsSourcePacking goodsSourcePacking) {
        this.goodsSourcePacking = goodsSourcePacking;
        return this;
    }

    public void setGoodsSourcePacking(GoodsSourcePacking goodsSourcePacking) {
        this.goodsSourcePacking = goodsSourcePacking;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public GoodsSource mobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getGoodsSourceFreight() {
        return goodsSourceFreight;
    }

    public GoodsSource goodsSourceFreight(String goodsSourceFreight) {
        this.goodsSourceFreight = goodsSourceFreight;
        return this;
    }

    public void setGoodsSourceFreight(String goodsSourceFreight) {
        this.goodsSourceFreight = goodsSourceFreight;
    }

    public GoodsSourceProperty getGoodsSourceProperty() {
        return goodsSourceProperty;
    }

    public GoodsSource goodsSourceProperty(GoodsSourceProperty goodsSourceProperty) {
        this.goodsSourceProperty = goodsSourceProperty;
        return this;
    }

    public void setGoodsSourceProperty(GoodsSourceProperty goodsSourceProperty) {
        this.goodsSourceProperty = goodsSourceProperty;
    }

    public Instant getEffectiveDate() {
        return effectiveDate;
    }

    public GoodsSource effectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
        return this;
    }

    public void setEffectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Instant getLayTime() {
        return layTime;
    }

    public GoodsSource layTime(Instant layTime) {
        this.layTime = layTime;
        return this;
    }

    public void setLayTime(Instant layTime) {
        this.layTime = layTime;
    }

    public Status getStatus() {
        return status;
    }

    public GoodsSource status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public GoodsSource userInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
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
        GoodsSource goodsSource = (GoodsSource) o;
        if (goodsSource.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), goodsSource.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GoodsSource{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", start='" + getStart() + "'" +
            ", end='" + getEnd() + "'" +
            ", ton='" + getTon() + "'" +
            ", volume='" + getVolume() + "'" +
            ", goodsSourcePacking='" + getGoodsSourcePacking() + "'" +
            ", mobilePhone='" + getMobilePhone() + "'" +
            ", goodsSourceFreight='" + getGoodsSourceFreight() + "'" +
            ", goodsSourceProperty='" + getGoodsSourceProperty() + "'" +
            ", effectiveDate='" + getEffectiveDate() + "'" +
            ", layTime='" + getLayTime() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
