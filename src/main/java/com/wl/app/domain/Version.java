package com.wl.app.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

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
@ApiModel(description = "商品 @author xulei.")
@Entity
@Table(name = "version")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Version implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "该版本版本号", required = true)
    @Column(name = "number", nullable = false)
    private String number;
    /**
     * app版本
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "该版本保存路径", required = true)
    @Column(name = "path", nullable = false)
    private String path;

    @NotNull
    @Size(max = 500)
    @ApiModelProperty(value = "版本信息", required = true)
    @Column(name = "info", nullable = false)
    private String info;

    public Version() {
    }

    public Version(Long id, @NotNull @Size(max = 200) String number, @NotNull @Size(max = 200) String path, @NotNull @Size(max = 500) String info) {
        this.id = id;
        this.number = number;
        this.path = path;
        this.info = info;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
