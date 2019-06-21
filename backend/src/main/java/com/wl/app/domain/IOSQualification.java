package com.wl.app.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@ApiModel(description = "审核 @author Donny.")
@Entity
@Table(name = "IOSQualification")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class IOSQualification {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(columnDefinition="bit(1) default false",nullable=false)
    private boolean status; //ios是否通过审核

    public IOSQualification() {
    }

    public IOSQualification(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
