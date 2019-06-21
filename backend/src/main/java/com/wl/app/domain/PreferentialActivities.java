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
 * 优惠活动表
 * @author Donny.
 */
@ApiModel(description = "优惠活动表 @author Donny.")
@Entity
@Table(name = "preferential_activities")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "preferentialactivities")
public class PreferentialActivities extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 活动名称
     */
    @NotNull
    @ApiModelProperty(value = "活动名称", required = true)
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 活动封面图片
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "活动封面图片", required = true)
    @Column(name = "cover", length = 200, nullable = false)
    private String cover;

    /**
     * 开始时间
     */
    @NotNull
    @ApiModelProperty(value = "开始时间", required = true)
    @Column(name = "start_date", nullable = false)
    private Instant startDate;

    /**
     * 截止时间
     */
    @NotNull
    @ApiModelProperty(value = "截止时间", required = true)
    @Column(name = "end_date", nullable = false)
    private Instant endDate;

    /**
     * 活动内容
     */
    @ApiModelProperty(value = "活动内容")
    @Column(name = "content")
    private String content;

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

    public String getName() {
        return name;
    }

    public PreferentialActivities name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public PreferentialActivities cover(String cover) {
        this.cover = cover;
        return this;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public PreferentialActivities startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public PreferentialActivities endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getContent() {
        return content;
    }

    public PreferentialActivities content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Status getStatus() {
        return status;
    }

    public PreferentialActivities status(Status status) {
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
        PreferentialActivities preferentialActivities = (PreferentialActivities) o;
        if (preferentialActivities.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), preferentialActivities.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PreferentialActivities{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", cover='" + getCover() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", content='" + getContent() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
