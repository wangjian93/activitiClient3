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
@Table(name = "HR_O_EmpGrade")
public class EmpGrade extends Model {

    private static final long serialVersionUID = 1L;
    private String empGrade_ID;
    private String empGradeName;
    private String gradeClass;
    public EmpGrade(){}

    @Id
    @Column(name = "EmpGrade_ID")
    public String getEmpGrade_ID() {
        return empGrade_ID;
    }
    public void setEmpGrade_ID(String empGrade_ID) {
        this.empGrade_ID = empGrade_ID;
    }


    @Column(name = "EmpGradeName")
    public String getEmpGradeName() {
        return empGradeName;
    }
    public void setEmpGradeName(String empGradeName) {
        this.empGradeName = empGradeName;
    }


    @Column(name = "GradeClass")
    public String getGradeClass() {
        return gradeClass;
    }

    public void setGradeClass(String gradeClass) {
        this.gradeClass = gradeClass;
    }





}
