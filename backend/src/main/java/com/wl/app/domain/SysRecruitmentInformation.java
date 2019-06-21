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
 * 招聘信息
 * @author Donny.
 */
@ApiModel(description = "招聘信息 @author Donny.")
@Entity
@Table(name = "sys_recruitment_information")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "sysrecruitmentinformation")
public class SysRecruitmentInformation extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 职位类别
     */
    @NotNull
    @ApiModelProperty(value = "职位类别", required = true)
    @Column(name = "category", nullable = false)
    private String category;

    /**
     * 职位名称
     */
    @NotNull
    @ApiModelProperty(value = "职位名称", required = true)
    @Column(name = "category_name", nullable = false)
    private String categoryName;

    /**
     * 公司名称
     */
    @NotNull
    @ApiModelProperty(value = "公司名称", required = true)
    @Column(name = "nature", nullable = false)
    private String nature;

    /**
     * 月薪范围 开始
     */
    @NotNull
    @ApiModelProperty(value = "月薪范围 开始", required = true)
    @Column(name = "salary_start", nullable = false)
    private String salaryStart;

    /**
     * 月薪范围 结束
     */
    @NotNull
    @ApiModelProperty(value = "月薪范围 结束", required = true)
    @Column(name = "salary_end", nullable = false)
    private String salaryEnd;

    /**
     * 工作城市
     */
    @NotNull
    @ApiModelProperty(value = "工作地点", required = true)
    @Column(name = "addr_city", nullable = false)
    private String addrCity;

    /**
     * 工作经验
     */
    @NotNull
    @ApiModelProperty(value = "工作经验", required = true)
    @Column(name = "experience", nullable = false)
    private String experience;

    /**
     * 最低学历
     */
    @NotNull
    @ApiModelProperty(value = "最低学历", required = true)
    @Column(name = "education", nullable = false)
    private String education;

    /**
     * 招聘人数
     */
    @NotNull
    @ApiModelProperty(value = "招聘人数", required = true)
    @Column(name = "people_count", nullable = false)
    private Integer peopleCount;

    /**
     * 职位描述(福利待遇)
     */
    @NotNull
    @ApiModelProperty(value = "职位描述", required = true)
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

   /*
    *//**
     * 发布人
     *//*
    @ApiModelProperty(value = "发布人")
    @ManyToOne
    @JsonIgnoreProperties("")
    private UserInfo userInfo;
*/

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
   /* public UserInfo getUserInfo() {
        return userInfo;
    }

    public SysRecruitmentInformation userInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public SysRecruitmentInformation category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public SysRecruitmentInformation categoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getNature() {
        return nature;
    }

    public SysRecruitmentInformation nature(String nature) {
        this.nature = nature;
        return this;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getSalaryStart() {
        return salaryStart;
    }

    public SysRecruitmentInformation salaryStart(String salaryStart) {
        this.salaryStart = salaryStart;
        return this;
    }

    public void setSalaryStart(String salaryStart) {
        this.salaryStart = salaryStart;
    }

    public String getSalaryEnd() {
        return salaryEnd;
    }

    public SysRecruitmentInformation salaryEnd(String salaryEnd) {
        this.salaryEnd = salaryEnd;
        return this;
    }

    public void setSalaryEnd(String salaryEnd) {
        this.salaryEnd = salaryEnd;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public SysRecruitmentInformation addrCity(String addrCity) {
        this.addrCity = addrCity;
        return this;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getExperience() {
        return experience;
    }

    public SysRecruitmentInformation experience(String experience) {
        this.experience = experience;
        return this;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public SysRecruitmentInformation education(String education) {
        this.education = education;
        return this;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public SysRecruitmentInformation peopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
        return this;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getDescription() {
        return description;
    }

    public SysRecruitmentInformation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public SysRecruitmentInformation status(Status status) {
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
        SysRecruitmentInformation sysRecruitmentInformation = (SysRecruitmentInformation) o;
        if (sysRecruitmentInformation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysRecruitmentInformation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysRecruitmentInformation{" +
            "id=" + getId() +
            ", category='" + getCategory() + "'" +
            ", categoryName='" + getCategoryName() + "'" +
            ", nature='" + getNature() + "'" +
            ", salaryStart='" + getSalaryStart() + "'" +
            ", salaryEnd='" + getSalaryEnd() + "'" +
            ", addrCity='" + getAddrCity() + "'" +
            ", experience='" + getExperience() + "'" +
            ", education='" + getEducation() + "'" +
            ", peopleCount=" + getPeopleCount() +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
