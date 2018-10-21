package com.ivo.activiticlient.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author wangjian
 * @date 2018/10/11
 */
@Entity
@Table(name = "AT_O_ShiftType")
public class ShiftType extends ModelAtom{
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String category_FK;
    private Date slot1Beg;
    private Date slot1End;
    private Date slot2Beg;
    private Date slot2End;
    private Date slot3Beg;
    private Date slot3End;
    private Integer holidayOt;

    public ShiftType(){}

    public ShiftType(String id){
        this.id = id;
    }

    @Id
    @Column(name = "ShiftType_ID")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "ShiftTypeName")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "ShiftCategory_FK")
    public String getCategory_FK() {
        return category_FK;
    }
    public void setCategory_FK(String category_FK) {
        this.category_FK = category_FK;
    }

    @Column(name = "Slot1Beg")
    public Date getSlot1Beg() {
        return slot1Beg;
    }
    public void setSlot1Beg(Date slot1Beg) {
        this.slot1Beg = slot1Beg;
    }

    @Column(name = "Slot1End")
    public Date getSlot1End() {
        return slot1End;
    }
    public void setSlot1End(Date slot1End) {
        this.slot1End = slot1End;
    }

    @Column(name = "Slot2Beg")
    public Date getSlot2Beg() {
        return slot2Beg;
    }
    public void setSlot2Beg(Date slot2Beg) {
        this.slot2Beg = slot2Beg;
    }

    @Column(name = "Slot2End")
    public Date getSlot2End() {
        return slot2End;
    }
    public void setSlot2End(Date slot2End) {
        this.slot2End = slot2End;
    }

    @Column(name = "Slot3Beg")
    public Date getSlot3Beg() {
        return slot3Beg;
    }
    public void setSlot3Beg(Date slot3Beg) {
        this.slot3Beg = slot3Beg;
    }

    @Column(name = "Slot3End")
    public Date getSlot3End() {
        return slot3End;
    }
    public void setSlot3End(Date slot3End) {
        this.slot3End = slot3End;
    }

    @Column(name = "HolidayOt")
    public Integer getHolidayOt() {
        return holidayOt;
    }

    public void setHolidayOt(Integer holidayOt) {
        this.holidayOt = holidayOt;
    }

}
