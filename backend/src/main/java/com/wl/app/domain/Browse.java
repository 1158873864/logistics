package com.wl.app.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 版本
 * @author xulei
 */
@ApiModel(description = "浏览 @author xulei.")
@Entity
@Table(name = "browse")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Browse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long ddnid;


    @ApiModelProperty(value = "量", required = true)
    @Column(name = "number")
    private int number;


    public Browse() {
    }


    public Browse(Long ddnid, int number) {
        this.ddnid = ddnid;
        this.number = number;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getDdnid() {
        return ddnid;
    }

    public void setDdnid(Long ddnid) {
        this.ddnid = ddnid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
