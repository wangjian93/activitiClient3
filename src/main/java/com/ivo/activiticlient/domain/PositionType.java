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
@Table(name = "HR_O_PositionType")
public class PositionType extends ModelAtom
{

    private static final long serialVersionUID = 1L;
    private String positionType_ID;
    private String positionTypeName;


    public PositionType()
    {
    }
    @Id
    @Column(name = "PositionType_ID")
    public String getPositionType_ID()
    {
        return positionType_ID;
    }

    public void setPositionType_ID(String positionType_ID)
    {
        this.positionType_ID = positionType_ID;
    }

    @Column(name = "PositionTypeName")
    public String getPositionTypeName()
    {
        return positionTypeName;
    }

    public void setPositionTypeName(String positionTypeName)
    {
        this.positionTypeName = positionTypeName;
    }

}
