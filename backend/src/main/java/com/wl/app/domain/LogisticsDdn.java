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
 * 物流专线
 * @author Donny.
 */
@ApiModel(description = "物流专线 @author Donny.")
@Entity
@Table(name = "logistics_ddn")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "logisticsddn")
public class LogisticsDdn extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 专线名称
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "专线名称", required = true)
    @Column(name = "title", length = 200, nullable = false)
    private String title;

    /**
     * 经理名字
     */
    @NotNull
    @Size(max = 10)
    @ApiModelProperty(value = "经理名字", required = true)
    @Column(name = "manager_fullname", length = 10, nullable = false)
    private String managerFullname;

    /**
     * 经理手机号码
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "经理手机号码", required = true)
    @Column(name = "manager_mobile_phone", length = 100, nullable = false)
    private String managerMobilePhone;
    
    /**
     * 经理电话号码
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "查货电话", required = true)
    @Column(name = "manager_phone", length = 100, nullable = false)
    private String managerPhone;
    
    /**
     * 经理电话号码
     */
    @Size(max = 100)
    @ApiModelProperty(value = "业务电话")
    @Column(name = "business_phone")
    private String businessPhone;
    
    /**
     * 专线所在城市
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "专线所在城市", required = true)
    @Column(name = "location_city", length = 20, nullable = false)
    private String locationCity;

    /**
     * 专线地址
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "专线地址", required = true)
    @Column(name = "address", length = 200, nullable = false)
    private String address;

    /**
     * 专线展示图
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "专线展示图", required = true)
    @Column(name = "pic", length = 200, nullable = false)
    private String pic;

    /**
     * 专线覆盖城市
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "专线覆盖城市", required = true)
    @Column(name = "cover_city", length = 100, nullable = false)
    private String coverCity;

    /**
     * 直达城市
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "直达城市", required = true)
    @Column(name = "through_city", length = 100, nullable = false)
    private String throughCity;

    /**
     * 网站
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "网站", required = true)
    @Column(name = "www", length = 200, nullable = false)
    private String www;

    /**
     * 是否特种运输
     */
    @NotNull
    @ApiModelProperty(value = "是否特种运输", required = true)
    @Column(name = "special_transport", nullable = false)
    private Boolean specialTransport;

    /**
     * 是否好专线
     */
    @NotNull
    @ApiModelProperty(value = "是否好专线", required = true)
    @Column(name = "good", nullable = false)
    private Boolean good;
    
    /**
     * 是否往返
     */
    @NotNull
    @ApiModelProperty(value = "是否往返", required = true)
    @Column(name = "go_back", nullable = false)
    private boolean goThereAndback;
    
    /**
     * 是否认证
     */
    @NotNull
    @ApiModelProperty(value = "是否认证", required = true)
    @Column(name = "auth", nullable = false)
    private boolean auth;
    
    /**
     * 是否首页显示
     */
    @NotNull
    @ApiModelProperty(value = "是否首页显示", required = true)
    @Column(name = "home", nullable = false)
    private boolean home;
    
    /**
     * 是否Banner
     */
    @NotNull
    @ApiModelProperty(value = "是否Banner", required = true)
    @Column(name = "banner", nullable = false)
    private boolean banner;
    
    /**
     * 是否vip
     */
    @NotNull
    @ApiModelProperty(value = "是否vip", required = true)
    @Column(name = "vip", nullable = false)
    private boolean vip;

    @NotNull
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

    public String getTitle() {
        return title;
    }

    public LogisticsDdn title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getManagerFullname() {
        return managerFullname;
    }

    public LogisticsDdn managerFullname(String managerFullname) {
        this.managerFullname = managerFullname;
        return this;
    }

    public void setManagerFullname(String managerFullname) {
        this.managerFullname = managerFullname;
    }

    public String getManagerMobilePhone() {
        return managerMobilePhone;
    }

    public LogisticsDdn managerMobilePhone(String managerMobilePhone) {
        this.managerMobilePhone = managerMobilePhone;
        return this;
    }

    public void setManagerMobilePhone(String managerMobilePhone) {
        this.managerMobilePhone = managerMobilePhone;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public LogisticsDdn locationCity(String locationCity) {
        this.locationCity = locationCity;
        return this;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getAddress() {
        return address;
    }

    public LogisticsDdn address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPic() {
        return pic;
    }

    public LogisticsDdn pic(String pic) {
        this.pic = pic;
        return this;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCoverCity() {
        return coverCity;
    }

    public LogisticsDdn coverCity(String coverCity) {
        this.coverCity = coverCity;
        return this;
    }

    public void setCoverCity(String coverCity) {
        this.coverCity = coverCity;
    }

    public String getThroughCity() {
        return throughCity;
    }

    public LogisticsDdn throughCity(String throughCity) {
        this.throughCity = throughCity;
        return this;
    }

    public void setThroughCity(String throughCity) {
        this.throughCity = throughCity;
    }

    public String getWww() {
        return www;
    }

    public LogisticsDdn www(String www) {
        this.www = www;
        return this;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public Boolean isSpecialTransport() {
        return specialTransport;
    }

    public LogisticsDdn specialTransport(Boolean specialTransport) {
        this.specialTransport = specialTransport;
        return this;
    }

    public void setSpecialTransport(Boolean specialTransport) {
        this.specialTransport = specialTransport;
    }

    public Boolean isGood() {
        return good;
    }

    public LogisticsDdn good(Boolean good) {
        this.good = good;
        return this;
    }

    public void setGood(Boolean good) {
        this.good = good;
    }

    public Status getStatus() {
        return status;
    }

    public LogisticsDdn status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public boolean isBanner() {
		return banner;
	}

	public void setBanner(boolean banner) {
		this.banner = banner;
	}

	public boolean isGoThereAndback() {
		return goThereAndback;
	}

	public void setGoThereAndback(boolean goThereAndback) {
		this.goThereAndback = goThereAndback;
	}

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}
	
	public boolean isHome() {
		return home;
	}

	public void setHome(boolean home) {
		this.home = home;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LogisticsDdn logisticsDdn = (LogisticsDdn) o;
        if (logisticsDdn.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), logisticsDdn.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LogisticsDdn{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", managerFullname='" + getManagerFullname() + "'" +
            ", managerMobilePhone='" + getManagerMobilePhone() + "'" +
            ", locationCity='" + getLocationCity() + "'" +
            ", address='" + getAddress() + "'" +
            ", pic='" + getPic() + "'" +
            ", coverCity='" + getCoverCity() + "'" +
            ", throughCity='" + getThroughCity() + "'" +
            ", www='" + getWww() + "'" +
            ", specialTransport='" + isSpecialTransport() + "'" +
            ", good='" + isGood() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
