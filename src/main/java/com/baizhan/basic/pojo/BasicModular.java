package com.baizhan.basic.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


import lombok.Data;



@Data
@Entity
@Table(name="tb_basic_modular")
public class BasicModular implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer modularId;
	private String modularName;
	private Integer modularSort;
	
	@Transient
	private List<BasicPermission> permissions;

}