package com.ivo.activiticlient.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wangjian
 * @date 2018/10/11
 */
@Entity
@Table(name = "HR_O_EmpGroup")
public class EmpGroup extends Model {

    private static final long serialVersionUID = 1L;
    private String empGroup_ID;
    private String empGroupName;
    private String empRule;
    private Integer overseaFlag ;
    private Integer dutyFlag;
    //-----------------------------------------
    public EmpGroup(){}
    //-----------------------------------------
    @Id
    @Column(name = "EmpGroup_ID")
    public String getEmpGroup_ID() {
        return empGroup_ID;
    }
    public void setEmpGroup_ID(String empGroup_ID) {
        this.empGroup_ID = empGroup_ID;
    }

    @Column(name = "EmpGroupName")
    public String getEmpGroupName() {
        return empGroupName;
    }
    public void setEmpGroupName(String empGroupName) {
        this.empGroupName = empGroupName;
    }

    @Column(name = "EmpRule")
    public String getEmpRule() {
        return empRule;
    }

    public void setEmpRule(String empRule) {
        this.empRule = empRule;
    }

    @Column(name = "OverseaFlag")
    public Integer getOverseaFlag() {
        return overseaFlag;
    }

    public void setOverseaFlag(Integer overseaFlag) {
        this.overseaFlag = overseaFlag;
    }
    @Column(name = "DutyFlag")
    public Integer getDutyFlag() {
        return dutyFlag;
    }
    public void setDutyFlag(Integer dutyFlag) {
        this.dutyFlag = dutyFlag;
    }

}
