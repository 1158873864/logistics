package com.wl.app.domain;

import com.wl.app.domain.enumeration.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * 物流专线
 * @author Donny.
 */
@ApiModel(description = "专线更新 @author Donny.")
@Entity
@Table(name = "ddn_update")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "ddnupdate")
public class DdnUpdate implements Serializable {

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
    @Size(max = 500)
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
    @Size(max = 5000)
    @ApiModelProperty(value = "网点", required = true)
    @Column(name = "www", length = 200, nullable = false)
    private String www;

    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "更新代码", required = true)
    @Column(name = "code", length = 200, nullable = false)
    private String code;

    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "更新备注", required = true)
    @Column(name = "remark", length = 200, nullable = false)
    private String remark;

    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "更新类型", required = true)
    @Column(name = "type", length = 200, nullable = false)
    private String type;

    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "用户手机号码", required = true)
    @Column(name = "user_mobilephone", length = 200, nullable = false)
    private String userMobilephone;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public DdnUpdate() {
    }

    public DdnUpdate(@NotNull @Size(max = 200) String title, @NotNull @Size(max = 10) String managerFullname, @NotNull @Size(max = 100) String managerMobilePhone, @NotNull @Size(max = 100) String managerPhone, @Size(max = 100) String businessPhone, @NotNull @Size(max = 20) String locationCity, @NotNull @Size(max = 200) String address, @NotNull @Size(max = 500) String pic, @NotNull @Size(max = 100) String coverCity, @NotNull @Size(max = 100) String throughCity, @NotNull @Size(max = 5000) String www, @NotNull @Size(max = 200) String code, @NotNull @Size(max = 200) String remark, @NotNull @Size(max = 200) String type, @NotNull @Size(max = 200) String userMobilephone, @NotNull Status status) {
        this.title = title;
        this.managerFullname = managerFullname;
        this.managerMobilePhone = managerMobilePhone;
        this.managerPhone = managerPhone;
        this.businessPhone = businessPhone;
        this.locationCity = locationCity;
        this.address = address;
        this.pic = pic;
        this.coverCity = coverCity;
        this.throughCity = throughCity;
        this.www = www;
        this.code = code;
        this.remark = remark;
        this.type = type;
        this.userMobilephone = userMobilephone;
        this.status = status;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getManagerFullname() {
        return managerFullname;
    }

    public void setManagerFullname(String managerFullname) {
        this.managerFullname = managerFullname;
    }

    public String getManagerMobilePhone() {
        return managerMobilePhone;
    }

    public void setManagerMobilePhone(String managerMobilePhone) {
        this.managerMobilePhone = managerMobilePhone;
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

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCoverCity() {
        return coverCity;
    }

    public void setCoverCity(String coverCity) {
        this.coverCity = coverCity;
    }

    public String getThroughCity() {
        return throughCity;
    }

    public void setThroughCity(String throughCity) {
        this.throughCity = throughCity;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserMobilephone() {
        return userMobilephone;
    }

    public void setUserMobilephone(String userMobilephone) {
        this.userMobilephone = userMobilephone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
