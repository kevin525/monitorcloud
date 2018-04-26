package com.sys.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 参照字典实体
 */
/*@Entity
@Table(name = "CODE_ITEM")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DiscriminatorColumn(name = "itemTypeId", discriminatorType = DiscriminatorType.STRING)*/
public class CodeItem implements java.io.Serializable {	
	private static final long serialVersionUID = -3681878501743289352L;

	public CodeItem(){}
	public CodeItem(String oid){
		this.oid = oid;
	}	
	
	@Id
	private String oid;//主键, 参照代码
	
	@Column(name="itemTypeId", insertable=false, updatable=false)
	private String itemTypeId;//参照组代码
	
	private String itemCode;//代码
	
	private String itemName;// 名称
	
	private String itemDesc;// 描述
	
	private Integer itemOrder;//顺序
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "poid", referencedColumnName = "oid")
	private CodeItem parent;//上一级条目
	
	@Column(name="poid", insertable=false, updatable=false)
	private String parentOid;
	
	@OneToMany(mappedBy = "parent", targetEntity = CodeItem.class, fetch = FetchType.LAZY)
	private List<CodeItem> children;//子节点

	private String isOptional;// 是否可选,0:可选，1:不可选
	
	private String activate;//是否激活,0:激活，1:禁用

	private Integer layer;//层级
	
	private Boolean isLeaf;//是否是叶子节点
	
	private String  color;//颜色标示
	
	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Integer getItemOrder() {
		return itemOrder;
	}

	public void setItemOrder(Integer itemOrder) {
		this.itemOrder = itemOrder;
	}

	public CodeItem getParent() {
		return parent;
	}

	public void setParent(CodeItem parent) {
		this.parent = parent;
	}

	public List<CodeItem> getChildren() {
		return children;
	}

	public void setChildren(List<CodeItem> children) {
		this.children = children;
	}

	public String getIsOptional() {
		return isOptional;
	}

	public void setIsOptional(String isOptional) {
		this.isOptional = isOptional;
	}

	public String getActivate() {
		return activate;
	}

	public void setActivate(String activate) {
		this.activate = activate;
	}

	public Integer getLayer() {
		return layer;
	}

	public void setLayer(Integer layer) {
		this.layer = layer;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(String itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public String getParentOid() {
		return parentOid;
	}

	public void setParentOid(String parentOid) {
		this.parentOid = parentOid;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}