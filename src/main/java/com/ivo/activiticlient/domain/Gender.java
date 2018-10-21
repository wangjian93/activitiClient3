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
@Table(name = "HR_O_Gender")
public class Gender extends Model {

    private static final long serialVersionUID = 1L;
    private String gender_ID;
    private String genderName;
    public Gender(){}

    @Id
    @Column(name = "Gender_ID")
    public String getGender_ID() {
        return gender_ID;
    }
    public void setGender_ID(String gender_ID) {
        this.gender_ID = gender_ID;
    }


    @Column(name = "GenderName")
    public String getGenderName() {
        return genderName;
    }
    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

}
