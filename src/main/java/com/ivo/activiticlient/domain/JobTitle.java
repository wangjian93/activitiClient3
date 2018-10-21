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
@Table(name = "HR_O_JobTitle")
public class JobTitle extends Model {
    private static final long serialVersionUID = 1L;
    private String jobTitle_ID;
    private String jobTitleName;
    private String jobTitleName_EN;
    public JobTitle(){}

    @Id
    @Column(name = "JobTitle_ID")
    public String getJobTitle_ID() {
        return jobTitle_ID;
    }
    public void setJobTitle_ID(String jobTitle_ID) {
        this.jobTitle_ID = jobTitle_ID;
    }

    @Column(name = "JobTitleName")
    public String getJobTitleName() {
        return jobTitleName;
    }
    public void setJobTitleName(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }

    @Column(name = "JobTitleName_EN")
    public String getJobTitleName_EN() {
        return jobTitleName_EN;
    }
    public void setJobTitleName_EN(String jobTitleName_EN) {
        this.jobTitleName_EN = jobTitleName_EN;
    }


}
