package com.ivo.activiticlient.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author wangjian
 * @date 2018/10/11
 */
@MappedSuperclass
public class Model extends ModelAtom {
    private static final long serialVersionUID = 1L;

    private String creator = "sys";
    private Date dateOfCreate = new Date();
    private String updater = "sys";
    private Date dateOfUpdate = new Date();
    private String memo = "";

    public Model() {}
    @Column(name="Creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    @Column(name="DateOfCreate")
    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }
    @Column(name="Updater")
    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
    @Column(name="DateOfUpdate")
    public Date getDateOfUpdate() {
        return dateOfUpdate;
    }

    public void setDateOfUpdate(Date dateOfUpdate) {
        this.dateOfUpdate = dateOfUpdate;
    }

    @Column(name="Memo")
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }



}
