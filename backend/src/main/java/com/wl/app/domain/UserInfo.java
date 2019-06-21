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
 * 用户管理
 * @author Donny.
 */
@ApiModel(description = "用户管理 @author Donny.")
@Entity
@Table(name = "user_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "userinfo")

public class UserInfo extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 头像
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "头像", required = true)
    @Column(name = "photo", length = 200, nullable = false)
    private String photo;

    /**
     * 姓名
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "姓名", required = true)
    @Column(name = "fullname", length = 20, nullable = false)
    private String fullname;

    /**
     * 昵称
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "昵称", required = true)
    @Column(name = "nick_name", length = 20, nullable = false)
    private String nickName;

    /**
     * 手机号
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "手机号", required = true)
    @Column(name = "mobile_phone", length = 20, nullable = false)
    private String mobilePhone;

    /**
     * 积分
     */
    @NotNull
    @ApiModelProperty(value = "积分", required = true)
    @Column(name = "integral", nullable = false)
    private Integer integral;

    /**
     * 注册时间
     */
    @NotNull
    @ApiModelProperty(value = "注册时间", required = true)
    @Column(name = "register_date", nullable = false)
    private Instant registerDate;

    /**
     * 注册时长（月）
     */
    @NotNull
    @ApiModelProperty(value = "注册时长（月）", required = true)
    @Column(name = "register_sum", nullable = false)
    private String registerSum;

    /**
     * 上次登录时间
     */
    @NotNull
    @ApiModelProperty(value = "上次登录时间", required = true)
    @Column(name = "last_logined_date", nullable = false)
    private Instant lastLoginedDate;

    /**
     * 发布货源数量（票）
     */
    @NotNull
    @ApiModelProperty(value = "发布货源数量（票）", required = true)
    @Column(name = "goods_source_count", nullable = false)
    private Integer goodsSourceCount;

    /**
     * 微信公众号OpenId
     */
    @Size(max = 200)
    @ApiModelProperty(value = "微信公众号OpenId")
    @Column(name = "open_id", length = 200)
    private String openId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public UserInfo photo(String photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFullname() {
        return fullname;
    }

    public UserInfo fullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNickName() {
        return nickName;
    }

    public UserInfo nickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public UserInfo mobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Integer getIntegral() {
        return integral;
    }

    public UserInfo integral(Integer integral) {
        this.integral = integral;
        return this;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Instant getRegisterDate() {
        return registerDate;
    }

    public UserInfo registerDate(Instant registerDate) {
        this.registerDate = registerDate;
        return this;
    }

    public void setRegisterDate(Instant registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterSum() {
        return registerSum;
    }

    public UserInfo registerSum(String registerSum) {
        this.registerSum = registerSum;
        return this;
    }

    public void setRegisterSum(String registerSum) {
        this.registerSum = registerSum;
    }

    public Instant getLastLoginedDate() {
        return lastLoginedDate;
    }

    public UserInfo lastLoginedDate(Instant lastLoginedDate) {
        this.lastLoginedDate = lastLoginedDate;
        return this;
    }

    public void setLastLoginedDate(Instant lastLoginedDate) {
        this.lastLoginedDate = lastLoginedDate;
    }

    public Integer getGoodsSourceCount() {
        return goodsSourceCount;
    }

    public UserInfo goodsSourceCount(Integer goodsSourceCount) {
        this.goodsSourceCount = goodsSourceCount;
        return this;
    }

    public void setGoodsSourceCount(Integer goodsSourceCount) {
        this.goodsSourceCount = goodsSourceCount;
    }

    public String getOpenId() {
        return openId;
    }

    public UserInfo openId(String openId) {
        this.openId = openId;
        return this;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Status getStatus() {
        return status;
    }

    public UserInfo status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public UserInfo user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
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
        UserInfo userInfo = (UserInfo) o;
        if (userInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserInfo{" +
            "id=" + getId() +
            ", photo='" + getPhoto() + "'" +
            ", fullname='" + getFullname() + "'" +
            ", nickName='" + getNickName() + "'" +
            ", mobilePhone='" + getMobilePhone() + "'" +
            ", integral=" + getIntegral() +
            ", registerDate='" + getRegisterDate() + "'" +
            ", registerSum='" + getRegisterSum() + "'" +
            ", lastLoginedDate='" + getLastLoginedDate() + "'" +
            ", goodsSourceCount=" + getGoodsSourceCount() +
            ", openId='" + getOpenId() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
