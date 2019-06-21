package com.wl.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * 用户专线收藏
 * @author Donny.
 */
@ApiModel(description = "用户专线收藏 @author Donny.")
@Entity
@Table(name = "user_ddn_favorites")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "userddnfavorites")
public class UserDdnFavorites extends AbstractAuditingEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("")
    private LogisticsDdn ddn;

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

    public LogisticsDdn getDdn() {
        return ddn;
    }

    public UserDdnFavorites ddn(LogisticsDdn logisticsDdn) {
        this.ddn = logisticsDdn;
        return this;
    }

    public void setDdn(LogisticsDdn logisticsDdn) {
        this.ddn = logisticsDdn;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public UserDdnFavorites userInfo(UserInfo userInfo) {
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
        UserDdnFavorites userDdnFavorites = (UserDdnFavorites) o;
        if (userDdnFavorites.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userDdnFavorites.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserDdnFavorites{" +
            "id=" + getId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
