package com.zhidisoft.system.entity;

import java.io.Serializable;
import java.util.Date;

public class Function implements Serializable{
    private Integer funcId;  // ID

    private String funcName;  // 权限名称

    private String funcUrl;  // 访问路径，如果路径为null，说明当前的权限是父权限，拥有子权限，不需要访问url

    private String funcCode; // 权限编码，唯一且有意义

    private Integer parentId; // 当前权限的父权限ID

    private Integer funcType; // 权限类型；  1=》菜单  0=》按钮

    private Integer status; // 可用状态，1=》可用，0=》禁用

    private Integer sortNum; // 排序值

    private Date createTime; // 创建时间

    private Date updateTime; // 修改时间

    public Integer getFuncId() {
        return funcId;
    }

    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName == null ? null : funcName.trim();
    }

    public String getFuncUrl() {
        return funcUrl;
    }

    public void setFuncUrl(String funcUrl) {
        this.funcUrl = funcUrl == null ? null : funcUrl.trim();
    }

    public String getFuncCode() {
        return funcCode;
    }

    public void setFuncCode(String funcCode) {
        this.funcCode = funcCode == null ? null : funcCode.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getFuncType() {
        return funcType;
    }

    public void setFuncType(Integer funcType) {
        this.funcType = funcType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}