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
@Table(name = "HR_O_DeptLevel")
public class DeptLevel extends Model {

    private static final long serialVersionUID = 1L;
    private String deptLevel_ID;
    private String deptLevelName;
    public DeptLevel(){}



    @Id
    @Column(name = "DeptLevel_ID")
    public String getDeptLevel_ID() {
        return deptLevel_ID;
    }

    public void setDeptLevel_ID(String deptLevel_ID) {
        this.deptLevel_ID = deptLevel_ID;
    }

    @Column(name = "DeptLevelName")
    public String getDeptLevelName() {
        return deptLevelName;
    }

    public void setDeptLevelName(String deptLevelName) {
        this.deptLevelName = deptLevelName;
    }

}
