package com.lmt.data.unstructured.entity.es;

import com.lmt.data.unstructured.base.BaseToString;

import java.util.Date;

/**
 * @apiNote 资源ES实体
 * @author MT-Lin
 * @date 2018/1/17 9:59
 */
public class ResourceEs extends BaseToString{

    /**
     * ES ID
     */
    private String id;

    /**
     * 资源ID
     */
    private String resourceId;

    /**
     * 上传者
     */
    private String author;

    /**
     * 资源名称
     */
    private String designation;

    /**
     * 资源文件名
     */
    private String resourceFileName;

    /**
     * 资源描述
     */
    private String description;

    /**
     * 审核备注
     */
    private String auditRemark;

    /**
     * 资源内容
     */
    private String content;

    /**
     * 资源文件大小
     */
    private double resourceSize;

    /**
     * 下载数
     */
    private int downloadNum;

    /**
     * 收藏数
     */
    private int collectionNum;

    /**
     * 上传时间
     */
    private Date uploadTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getResourceFileName() {
        return resourceFileName;
    }

    public void setResourceFileName(String resourceFileName) {
        this.resourceFileName = resourceFileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getResourceSize() {
        return resourceSize;
    }

    public void setResourceSize(double resourceSize) {
        this.resourceSize = resourceSize;
    }

    public int getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(int downloadNum) {
        this.downloadNum = downloadNum;
    }

    public int getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(int collectionNum) {
        this.collectionNum = collectionNum;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}