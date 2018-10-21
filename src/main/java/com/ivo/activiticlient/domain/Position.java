package com.ivo.activiticlient.domain;

import javax.persistence.*;

/**
 * @author wangjian
 * @date 2018/10/11
 */
@Entity
@Table(name = "HR_O_Position")
public class Position extends ModelAtom
{
    private static final long serialVersionUID = 1L;

    private String position_ID;
    private String positionName;
    private PositionType positiontype;
    private Department department;

    public Position(){}
    @Id
    @Column(name = "Position_ID")
    public String getPosition_ID()
    {
        return position_ID;
    }
    public void setPosition_ID(String position_ID)
    {
        this.position_ID = position_ID;
    }

    @Column(name = "PositionName")
    public String getPositionName()
    {
        return positionName;
    }
    public void setPositionName(String positionName)
    {
        this.positionName = positionName;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name = "PositionType_FK")
    public PositionType getPositiontype()
    {
        return positiontype;
    }
    public void setPositiontype(PositionType positiontype)
    {
        this.positiontype = positiontype;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name = "Department_FK")
    public Department getDepartment()
    {
        return department;
    }
    public void setDepartment(Department department)
    {
        this.department = department;
    }
}
