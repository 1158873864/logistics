package com.wl.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 省市县
 * @author Donny.
 */
@ApiModel(description = "省市县 @author Donny.")
@Entity
@Table(name = "tb_da_area")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    
    /**
     * 父ID
     */
    @NotNull
    @ApiModelProperty(value = "父ID", required = true)
    @Column(name = "parentid", nullable = false)
    private int parentId;
    
    /**
     * 名称
     */
    @NotNull
    @Size(max = 500)
    @ApiModelProperty(value = "名称", required = true)
    @Column(name = "city", nullable = false)
    private String city;

	public Area() {
	}

	public Area(Long id, @NotNull int parentId, @NotNull @Size(max = 500) String city) {
		this.id = id;
		this.parentId = parentId;
		this.city = city;
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

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}

