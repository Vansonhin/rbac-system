package com.baizhan.basic.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;


@Data
@Entity
@Table(name="tb_basic_permission")
@NamedQuery(name="BasicPermission.findAll", query="SELECT b FROM BasicPermission b")
public class BasicPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer permissionId;
	private Integer modularId;
	private String permissionAction;
	private String permissionName;
	private Integer permissionParent;
	private String permissionWord;
	@Transient
	private BasicModular modular;
	@Transient
	private BasicPermission parentPermission;
}