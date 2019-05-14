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
 * 话题转发//
 * @author Donny.
 */
@ApiModel(description = "话题转发 @author Donny.")
@Entity
@Table(name = "topic_forward")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "topicforward")
public class TopicForward implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "o_date_time", nullable = false)
    private Instant oDateTime;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Topic topic;

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

    public Instant getoDateTime() {
        return oDateTime;
    }

    public TopicForward oDateTime(Instant oDateTime) {
        this.oDateTime = oDateTime;
        return this;
    }

    public void setoDateTime(Instant oDateTime) {
        this.oDateTime = oDateTime;
    }

    public Topic getTopic() {
        return topic;
    }

    public TopicForward topic(Topic topic) {
        this.topic = topic;
        return this;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public TopicForward userInfo(UserInfo userInfo) {
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
        TopicForward topicForward = (TopicForward) o;
        if (topicForward.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), topicForward.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TopicForward{" +
            "id=" + getId() +
            ", oDateTime='" + getoDateTime() + "'" +
            "}";
    }
}
