package com.ivo.activiticlient.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author wangjian
 * @date 2018/10/11
 */
@MappedSuperclass
public class ModelAtom implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer validFlag = 1;

    public ModelAtom(){
        validFlag = 1;
    }

    @Column(name="ValidFlag")
    public Integer getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
    }

}
