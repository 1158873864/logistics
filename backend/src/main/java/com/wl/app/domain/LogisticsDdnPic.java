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
 * 物流专线图片
 * @author Donny.
 */
@ApiModel(description = "物流专线图片 @author Donny.")
@Entity
@Table(name = "logistics_ddn_pic")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "logisticsddnpic")
public class LogisticsDdnPic extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标题
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "标题", required = true)
    @Column(name = "title", length = 200, nullable = false)
    private String title;

    /**
     * 图片
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "图片", required = true)
    @Column(name = "path", length = 200, nullable = false)
    private String path;

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

    public String getTitle() {
        return title;
    }

    public LogisticsDdnPic title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public LogisticsDdnPic path(String path) {
        this.path = path;
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRemark() {
        return remark;
    }

    public LogisticsDdnPic remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Status getStatus() {
        return status;
    }

    public LogisticsDdnPic status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LogisticsDdn getLogisticsDdn() {
        return logisticsDdn;
    }

    public LogisticsDdnPic logisticsDdn(LogisticsDdn logisticsDdn) {
        this.logisticsDdn = logisticsDdn;
        return this;
    }

    public void setLogisticsDdn(LogisticsDdn logisticsDdn) {
        this.logisticsDdn = logisticsDdn;
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
        LogisticsDdnPic logisticsDdnPic = (LogisticsDdnPic) o;
        if (logisticsDdnPic.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), logisticsDdnPic.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LogisticsDdnPic{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", path='" + getPath() + "'" +
            ", remark='" + getRemark() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
