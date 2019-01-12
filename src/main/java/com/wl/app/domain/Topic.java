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
 * 话题
 * @author Donny.
 */
@ApiModel(description = "话题 @author Donny.")
@Entity
@Table(name = "topic")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "topic")
public class Topic extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 话题标题
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "话题标题", required = true)
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    /**
     * 话题内容
     */
    @NotNull
    @Size(max = 500)
    @ApiModelProperty(value = "话题内容", required = true)
    @Column(name = "content", length = 500, nullable = false)
    private String content;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    @Column(name = "published")
    private Instant published;

    /**
     * 点赞
     */
    @ApiModelProperty(value = "点赞")
    @Column(name = "fabulous_count")
    private Integer fabulousCount;

    /**
     * 评论
     */
    @ApiModelProperty(value = "评论")
    @Column(name = "comment_count")
    private Integer commentCount;

    /**
     * 转发
     */
    @ApiModelProperty(value = "转发")
    @Column(name = "forward_count")
    private Integer forwardCount;

    /**
     * 浏览
     */
    @ApiModelProperty(value = "浏览")
    @Column(name = "viewed_count")
    private Integer viewedCount;

    /**
     * 是否转发
     */
    @ApiModelProperty(value = "是否转发")
    @Column(name = "forwarded")
    private Boolean forwarded;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    /**
     * 发布人
     */
    @ApiModelProperty(value = "发布人")
    @ManyToOne
    @JsonIgnoreProperties("")
    private UserInfo userInfo;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Topic source;

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

    public Topic title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public Topic content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getPublished() {
        return published;
    }

    public Topic published(Instant published) {
        this.published = published;
        return this;
    }

    public void setPublished(Instant published) {
        this.published = published;
    }

    public Integer getFabulousCount() {
        return fabulousCount;
    }

    public Topic fabulousCount(Integer fabulousCount) {
        this.fabulousCount = fabulousCount;
        return this;
    }

    public void setFabulousCount(Integer fabulousCount) {
        this.fabulousCount = fabulousCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public Topic commentCount(Integer commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getForwardCount() {
        return forwardCount;
    }

    public Topic forwardCount(Integer forwardCount) {
        this.forwardCount = forwardCount;
        return this;
    }

    public void setForwardCount(Integer forwardCount) {
        this.forwardCount = forwardCount;
    }

    public Integer getViewedCount() {
        return viewedCount;
    }

    public Topic viewedCount(Integer viewedCount) {
        this.viewedCount = viewedCount;
        return this;
    }

    public void setViewedCount(Integer viewedCount) {
        this.viewedCount = viewedCount;
    }

    public Boolean isForwarded() {
        return forwarded;
    }

    public Topic forwarded(Boolean forwarded) {
        this.forwarded = forwarded;
        return this;
    }

    public void setForwarded(Boolean forwarded) {
        this.forwarded = forwarded;
    }

    public Status getStatus() {
        return status;
    }

    public Topic status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public Topic userInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Topic getSource() {
        return source;
    }

    public Topic source(Topic topic) {
        this.source = topic;
        return this;
    }

    public void setSource(Topic topic) {
        this.source = topic;
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
        Topic topic = (Topic) o;
        if (topic.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), topic.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Topic{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", published='" + getPublished() + "'" +
            ", fabulousCount=" + getFabulousCount() +
            ", commentCount=" + getCommentCount() +
            ", forwardCount=" + getForwardCount() +
            ", viewedCount=" + getViewedCount() +
            ", forwarded='" + isForwarded() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
