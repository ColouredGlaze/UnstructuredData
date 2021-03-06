package com.lmt.data.unstructured.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.lmt.data.unstructured.base.BaseEntity;

/**
 * @author MT-Lin
 * @date 2018/1/2 10:19
 * @apiNote 专题表
 */
@Entity(name = "dissertation")
public class Dissertation extends BaseEntity {

	/**
	 * 所属分类ID
	 */
	@Column(name = "classify_id", nullable = false, length = 36)
	private String classifyId;

	/**
	 * 专题名称
	 */
	@Column(name = "designation", nullable = false, length = 32)
	private String designation;

	/**
	 * 父专题
	 */
	@Column(name = "parent_id", length = 36)
	private String parentId;

	/**
	 * 专题描述
	 */
	@Column(name = "description", length = 100)
	private String description;

	/**
	 * 收藏数
	 */
	@Column(name = "collection_num")
	private int collectionNum;

	/**
	 * 文档数量
	 */
	@Column(name = "download_num")
	private int downloadNum;

	/**
	 * 上传数
	 */
	@Column(name = "document_num")
	private int documentNum;

	/**
	 * 创建者
	 */
	@Column(name = "creator", nullable = false, length = 36)
	private String creator;

	/**
	 * 创建时间
	 */
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, updatable = false)
	private Date createTime;

	/**
	 * 修改者
	 */
	@Column(name = "modifier", length = 36)
	private String modifier;

	/**
	 * 修改时间
	 */
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modification_time")
	private Date modificationTime;

	public String getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(String classifyId) {
		this.classifyId = classifyId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCollectionNum() {
		return collectionNum;
	}

	public void setCollectionNum(int collectionNum) {
		this.collectionNum = collectionNum;
	}

	public int getDownloadNum() {
		return downloadNum;
	}

	public void setDownloadNum(int downloadNum) {
		this.downloadNum = downloadNum;
	}

	public int getDocumentNum() {
		return documentNum;
	}

	public void setDocumentNum(int documentNum) {
		this.documentNum = documentNum;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
