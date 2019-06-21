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

import com.wl.app.domain.enumeration.Status;

/**
 * 商品
 * @author Donny.
 */
@ApiModel(description = "商品 @author Donny.")
@Entity
@Table(name = "goods")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "goods")
public class Goods extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品名称
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "商品名称", required = true)
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    /**
     * 商品图片
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "商品图片", required = true)
    @Column(name = "cover", length = 200, nullable = false)
    private String cover;

    /**
     * 介绍说明
     */
    @NotNull
    @ApiModelProperty(value = "介绍说明", required = true)
    @Column(name = "introduce", nullable = false)
    private String introduce;

    /**
     * 实际金额
     */
    @NotNull
    @ApiModelProperty(value = "实际金额", required = true)
    @Column(name = "payment", nullable = false)
    private String payment;

    /**
     * 支付积分（个）
     */
    @NotNull
    @ApiModelProperty(value = "支付积分（个）", required = true)
    @Column(name = "integral", nullable = false)
    private Integer integral;

    /**
     * 总数
     */
    @NotNull
    @ApiModelProperty(value = "总数", required = true)
    @Column(name = "total", nullable = false)
    private Integer total;

    /**
     * 兑换人数
     */
    @ApiModelProperty(value = "兑换人数")
    @Column(name = "people_count")
    private Integer peopleCount;

    /**
     * 状态
     */
    @NotNull
    @ApiModelProperty(value = "状态", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

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

    public Goods name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public Goods cover(String cover) {
        this.cover = cover;
        return this;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getIntroduce() {
        return introduce;
    }

    public Goods introduce(String introduce) {
        this.introduce = introduce;
        return this;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPayment() {
        return payment;
    }

    public Goods payment(String payment) {
        this.payment = payment;
        return this;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Integer getIntegral() {
        return integral;
    }

    public Goods integral(Integer integral) {
        this.integral = integral;
        return this;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getTotal() {
        return total;
    }

    public Goods total(Integer total) {
        this.total = total;
        return this;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public Goods peopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
        return this;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Status getStatus() {
        return status;
    }

    public Goods status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        Goods goods = (Goods) o;
        if (goods.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), goods.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Goods{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", cover='" + getCover() + "'" +
            ", introduce='" + getIntroduce() + "'" +
            ", payment='" + getPayment() + "'" +
            ", integral=" + getIntegral() +
            ", total=" + getTotal() +
            ", peopleCount=" + getPeopleCount() +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
