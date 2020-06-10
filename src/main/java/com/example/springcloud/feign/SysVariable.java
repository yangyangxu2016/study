package com.example.springcloud.feign;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class SysVariable  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String varName;

    private String varDesc;

    private Integer varType;

    private String varValue;

    private String custom1;

    private String custom2;

    private String custom3;

    private Integer dataStatus;

    private Long createTime;

    private Long modifyTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public String getVarDesc() {
        return varDesc;
    }

    public void setVarDesc(String varDesc) {
        this.varDesc = varDesc;
    }

    public Integer getVarType() {
        return varType;
    }

    public void setVarType(Integer varType) {
        this.varType = varType;
    }

    public String getVarValue() {
        return varValue;
    }

    public void setVarValue(String varValue) {
        this.varValue = varValue;
    }

    public String getCustom1() {
        return custom1;
    }

    public void setCustom1(String custom1) {
        this.custom1 = custom1;
    }

    public String getCustom2() {
        return custom2;
    }

    public void setCustom2(String custom2) {
        this.custom2 = custom2;
    }

    public String getCustom3() {
        return custom3;
    }

    public void setCustom3(String custom3) {
        this.custom3 = custom3;
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "SysVariable{" +
                "id=" + id +
                ", varName='" + varName + '\'' +
                ", varDesc='" + varDesc + '\'' +
                ", varType=" + varType +
                ", varValue='" + varValue + '\'' +
                ", custom1='" + custom1 + '\'' +
                ", custom2='" + custom2 + '\'' +
                ", custom3='" + custom3 + '\'' +
                ", dataStatus=" + dataStatus +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
