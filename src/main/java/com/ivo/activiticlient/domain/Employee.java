package com.ivo.activiticlient.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author wangjian
 * @date 2018/10/11
 */
@Entity
@Table(name = "HR_O_Employee")
public class Employee extends ModelAtom {

    private static final long serialVersionUID = 1L;

    private String employee_ID;
    private String employeeName;
    private String employeeName_EN;
    private Department department;
    private JobTitle jobTitle;
    private EmpGrade empGrade;   //人员职等
    private EmpGroup empGroup;   //员工组别 C\T
    private EmpSubScope empSubScope;   // 厂别 KS、M1
    private Date dateOfBirth ;
    private ProductionGroup productionGroup; //直接、间接
    private Date dateOfRegister	;
    private Date dateOfRegular;
    private Date dateOfResign;
    private String emailAddress;
    private Gender gender;  //性别
    private int visibleFlag;
    //------------------------------------------------
    public Employee(){}

    public Employee(String employee_ID) {
        this.employee_ID = employee_ID;
    }
    //------------------------------------------------
    @Id
    @Column(name = "Employee_ID")
    public String getEmployee_ID() {
        return employee_ID;
    }
    public void setEmployee_ID(String employee_ID) {
        this.employee_ID = employee_ID;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
    @JoinColumn(name = "Department_FK")
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name = "JobTitle_FK")
    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name = "EmpGrade_FK")
    public EmpGrade getEmpGrade() {
        return empGrade;
    }
    public void setEmpGrade(EmpGrade empGrade) {
        this.empGrade = empGrade;
    }

    @Column(name = "DateOfBirth")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name = "ProductionGroup_FK")
    public ProductionGroup getProductionGroup() {
        return productionGroup;
    }
    public void setProductionGroup(ProductionGroup productionGroup) {
        this.productionGroup = productionGroup;
    }

    @Column(name = "DateOfRegister")
    public Date getDateOfRegister() {
        return dateOfRegister;
    }
    public void setDateOfRegister(Date dateOfRegister) {
        this.dateOfRegister = dateOfRegister;
    }

    @Column(name = "DateOfRegular")
    public Date getDateOfRegular() {
        return dateOfRegular;
    }
    public void setDateOfRegular(Date dateOfRegular) {
        this.dateOfRegular = dateOfRegular;
    }

    @Column(name = "DateOfResign")
    public Date getDateOfResign() {
        return dateOfResign;
    }
    public void setDateOfResign(Date dateOfResign) {
        this.dateOfResign = dateOfResign;
    }

    @Column(name = "EmailAddress")
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name = "EmpGroup_FK")
    public EmpGroup getEmpGroup(){
        return empGroup;
    }
    public void setEmpGroup(EmpGroup empGroup){
        this.empGroup = empGroup;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name = "EmpSubScope_FK")
    public EmpSubScope getEmpSubScope(){
        return empSubScope;
    }
    public void setEmpSubScope(EmpSubScope empSubScope){
        this.empSubScope = empSubScope;
    }

    @Column(name = "VisibleFlag")
    public int getVisibleFlag(){
        return visibleFlag;
    }
    public void setVisibleFlag(int visibleFlag){
        this.visibleFlag = visibleFlag;
    }

    @Column(name = "EmployeeName")
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Column(name = "EmployeeName_EN")
    public String getEmployeeName_EN() {
        return employeeName_EN;
    }
    public void setEmployeeName_EN(String employeeName_EN) {
        this.employeeName_EN = employeeName_EN;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name = "Gender_FK")
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }



}
