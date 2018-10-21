package com.ivo.activiticlient.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author wangjian
 * @date 2018/10/11
 */
@Entity
@Table(name = "HR_R_Responsibility")
public class Responsibility extends ModelAtom {

    private static final long serialVersionUID = 1L;
    private long responsibility_ID;
    private Position position;
    private Employee employee;
    private ResponsibilityType responsibilityType;
    private Date dateOfEffect ;
    private Date dateOfExpire ;

    public Responsibility(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Responsibility_ID")
    public long getResponsibility_ID() {
        return responsibility_ID;
    }
    public void setResponsibility_ID(long responsibility_ID) {
        this.responsibility_ID = responsibility_ID;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name = "Position_FK")
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name = "Employee_FK")
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name = "ResponsibilityType_FK")
    public ResponsibilityType getResponsibilityType() {
        return responsibilityType;
    }
    public void setResponsibilityType(ResponsibilityType responsibilityType) {
        this.responsibilityType = responsibilityType;
    }

    public Date getDateOfEffect() {
        return dateOfEffect;
    }
    public void setDateOfEffect(Date dateOfEffect) {
        this.dateOfEffect = dateOfEffect;
    }
    public Date getDateOfExpire() {
        return dateOfExpire;
    }
    public void setDateOfExpire(Date dateOfExpire) {
        this.dateOfExpire = dateOfExpire;
    }

}
