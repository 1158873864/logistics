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

import com.wl.app.domain.enumeration.ExchangeStatus;

/**
 * 商品兑换
 * @author Donny.
 */
@ApiModel(description = "商品兑换 @author Donny.")
@Entity
@Table(name = "goods_exchange")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "goodsexchange")
public class GoodsExchange extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 收货人手机
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "收货人手机", required = true)
    @Column(name = "mobile_phone", length = 20, nullable = false)
    private String mobilePhone;

    /**
     * 收货人昵称
     */
    @NotNull
    @Size(max = 10)
    @ApiModelProperty(value = "收货人昵称", required = true)
    @Column(name = "fullname", length = 10, nullable = false)
    private String fullname;

    /**
     * 收货人地址
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "收货人地址", required = true)
    @Column(name = "address", length = 200, nullable = false)
    private String address;

    /**
     * 发货单号
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "发货单号", required = true)
    @Column(name = "odd_numbers", length = 100, nullable = false)
    private String oddNumbers;

    /**
     * 兑换数量
     */
    @NotNull
    @ApiModelProperty(value = "兑换数量", required = true)
    @Column(name = "exchange_count", nullable = false)
    private Integer exchangeCount;

    /**
     * 兑换状态
     */
    @NotNull
    @ApiModelProperty(value = "兑换状态", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "exchange_status", nullable = false)
    private ExchangeStatus exchangeStatus;

    /**
     * 备注说明
     */
    @Size(max = 500)
    @ApiModelProperty(value = "备注说明")
    @Column(name = "remark", length = 500)
    private String remark;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Goods goods;

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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public GoodsExchange mobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getFullname() {
        return fullname;
    }

    public GoodsExchange fullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public GoodsExchange address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOddNumbers() {
        return oddNumbers;
    }

    public GoodsExchange oddNumbers(String oddNumbers) {
        this.oddNumbers = oddNumbers;
        return this;
    }

    public void setOddNumbers(String oddNumbers) {
        this.oddNumbers = oddNumbers;
    }

    public Integer getExchangeCount() {
        return exchangeCount;
    }

    public GoodsExchange exchangeCount(Integer exchangeCount) {
        this.exchangeCount = exchangeCount;
        return this;
    }

    public void setExchangeCount(Integer exchangeCount) {
        this.exchangeCount = exchangeCount;
    }

    public ExchangeStatus getExchangeStatus() {
        return exchangeStatus;
    }

    public GoodsExchange exchangeStatus(ExchangeStatus exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
        return this;
    }

    public void setExchangeStatus(ExchangeStatus exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }

    public String getRemark() {
        return remark;
    }

    public GoodsExchange remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Goods getGoods() {
        return goods;
    }

    public GoodsExchange goods(Goods goods) {
        this.goods = goods;
        return this;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public GoodsExchange userInfo(UserInfo userInfo) {
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
        GoodsExchange goodsExchange = (GoodsExchange) o;
        if (goodsExchange.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), goodsExchange.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GoodsExchange{" +
            "id=" + getId() +
            ", mobilePhone='" + getMobilePhone() + "'" +
            ", fullname='" + getFullname() + "'" +
            ", address='" + getAddress() + "'" +
            ", oddNumbers='" + getOddNumbers() + "'" +
            ", exchangeCount=" + getExchangeCount() +
            ", exchangeStatus='" + getExchangeStatus() + "'" +
            ", remark='" + getRemark() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
