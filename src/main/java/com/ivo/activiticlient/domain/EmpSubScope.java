package com.ivo.activiticlient.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author wangjian
 * @date 2018/10/11
 */
@Entity
@Table(name = "HR_O_EmpSubScope")
public class EmpSubScope implements Serializable {

    private static final long serialVersionUID = 1L;
    private String empSubScope_ID;
    private String empSubScopeDescription;
    private int validFlag;

    //------------------------------------------------------------------
    public EmpSubScope(){}
    //------------------------------------------------------------------
    @Id
    @Column(name = "EmpSubScope_ID")
    public String getEmpSubScope_ID()
    {
        return empSubScope_ID;
    }

    public void setEmpSubScope_ID(String empSubScope_ID)
    {
        this.empSubScope_ID = empSubScope_ID;
    }
    @Column(name = "EmpSubScopeDescription")
    public String getEmpSubScopeDescription()
    {
        return empSubScopeDescription;
    }

    public void setEmpSubScopeDescription(String empSubScopeDescription)
    {
        this.empSubScopeDescription = empSubScopeDescription;
    }
    @Column(name = "ValidFlag")
    public int getValidFlag()
    {
        return validFlag;
    }
    public void setValidFlag(int validFlag)
    {
        this.validFlag = validFlag;
    }
}
