package com.ivo.activiticlient.domain;

import javax.persistence.*;

/**
 * @author wangjian
 * @date 2018/10/11
 */
@Entity
@Table(name = "SYS_O_Handler")
public class Handler extends ModelAtom {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String sid;
    private String node;
    private int type;
    private Employee employee;

    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "Sid")
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Column(name = "Node")
    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    @Column(name = "Type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Employee_FK")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


}
