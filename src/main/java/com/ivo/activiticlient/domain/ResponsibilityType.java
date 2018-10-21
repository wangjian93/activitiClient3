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
@Table(name = "HR_O_ResponsibilityType")
public class ResponsibilityType extends Model
{

    private static final long serialVersionUID = 1L;
    private String responsibilityType_ID;
    private String responsibilityTypeName;

    public ResponsibilityType()
    {
    }
    @Id
    @Column(name = "ResponsibilityType_ID")
    public String getResponsibilityType_ID() {

        return responsibilityType_ID;
    }

    public void setResponsibilityType_ID(String responsibilityType_ID) {

        this.responsibilityType_ID = responsibilityType_ID;
    }

    public String getResponsibilityTypeName() {

        return responsibilityTypeName;
    }

    public void setResponsibilityTypeName(String responsibilityTypeName) {

        this.responsibilityTypeName = responsibilityTypeName;
    }

}