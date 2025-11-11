//package com.javaweb.repository.entity;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Table(name = "user_role")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
////trong user_role co 2 many to one 
//public class UserRoleEntity {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	@ManyToOne
//	@JoinColumn(name="userid")
//	UserEntity user;
//	
//	@ManyToOne
//	@JoinColumn(name="roleid")
//	RoleEntity role;
//
//}
