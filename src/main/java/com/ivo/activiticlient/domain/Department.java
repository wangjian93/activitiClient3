package com.ivo.activiticlient.domain;

import javax.persistence.*;

/**
 * @author wangjian
 * @date 2018/10/11
 */
@Entity
@Table(name = "HR_O_Department")
public class Department extends ModelAtom {

    private static final long serialVersionUID = 1L;
    private String department_ID;
    private String departmentName;
    private Employee deptHead;
    private Department parentDept;
    private String deptName_EN;
    private String deptName_S;
    private double deptLevel;
    private String deptPath;
    private String costCenter;


    public Department(){}

    @Id
    @Column(name = "Department_ID")
    public String getDepartment_ID() {
        return department_ID;
    }
    public void setDepartment_ID(String department_ID) {
        this.department_ID = department_ID;
    }


    @Column(name = "DeptName")
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name = "DeptHead_FK")
    public Employee getDeptHead() {
        return deptHead;
    }

    public void setDeptHead(Employee deptHead) {
        this.deptHead = deptHead;
    }


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name = "Parent_FK")
    public Department getParentDept() {
        return parentDept;
    }

    public void setParentDept(Department parentDept) {
        this.parentDept = parentDept;
    }

    @Column(name = "DeptName_EN")
    public String getDeptName_EN() {
        return deptName_EN;
    }
    public void setDeptName_EN(String deptName_EN) {
        this.deptName_EN = deptName_EN;
    }


    @Column(name = "DeptName_S")
    public String getDeptName_S() {
        return deptName_S;
    }
    public void setDeptName_S(String deptName_S) {
        this.deptName_S = deptName_S;
    }

    @Column(name = "DeptLevel_FK")
    public double getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(double deptLevel) {
        this.deptLevel = deptLevel;
    }


    @Column(name = "DeptPath")
    public String getDeptPath() {
        return deptPath;
    }

    public void setDeptPath(String deptPath) {
        this.deptPath = deptPath;
    }

    @Column(name = "CostCenter_FK")
    public String getCostCenter() {
        return costCenter;
    }
    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

}
