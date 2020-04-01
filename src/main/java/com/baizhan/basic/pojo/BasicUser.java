package com.baizhan.basic.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;


@Data
@Entity
@Table(name="tb_basic_user")
public class BasicUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	private Integer roleId;
	private String userAccount;
	@Temporal(TemporalType.TIMESTAMP)
	private Date userCreateDate;
	private String userName;
	private String userPassword;
	private Integer userStatus;
	@Transient
	private String  userStatusString;
	@Transient
	private BasicRole role;
	
	@Transient
	private List<BasicModular> modulars;

}