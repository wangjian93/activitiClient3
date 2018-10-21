package com.ivo.activiticlient.biz;

import com.ivo.activiticlient.domain.Department;
import com.ivo.activiticlient.domain.Employee;
import com.ivo.activiticlient.domain.ModelAtom;
import com.ivo.activiticlient.restful.RestfulImpl;

import javax.persistence.*;
import java.util.Date;

/**
 * @author wangjian
 * @date 2018/10/12
 */
@MappedSuperclass
public class Model extends ModelAtom {

    private static final long serialVersionUID = 1L;

    private long id;

    private String orderNumber;

    private Employee drafter;

    private Department draftGroup;

    private Date draftDate = new Date();

    private Date endDate;

    private String memo;

    private String status = RestfulImpl.Drafting;

    public Model() {
    }

    public Model(String orderNumber, Employee drafter, Department draftGroup, Date draftDate,
                 String status, String memo) {
        this.orderNumber = orderNumber;
        this.drafter = drafter;
        this.draftGroup = draftGroup;
        this.draftDate = draftDate;
        this.status = status;
        this.memo = memo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "OrderNumber")
    public String getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Drafter")
    public Employee getDrafter() {
        return drafter;
    }
    public void setDrafter(Employee drafter) {
        this.drafter = drafter;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DrafterGroup")
    public Department getDraftGroup() {
        return draftGroup;
    }
    public void setDraftGroup(Department draftGroup) {
        this.draftGroup = draftGroup;
    }

    @Column(name = "DraftDate")
    public Date getDraftDate() {
        return draftDate;
    }
    public void setDraftDate(Date draftDate) {
        this.draftDate = draftDate;
    }

    @Column(name = "EndDate")
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "Memo")
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Column(name = "Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}