package com.ivo.activiticlient.mm.iec;

import javax.persistence.*;
import java.util.Date;

/**
 * @author wangjian
 * @date 2018/10/13
 */
@Entity
@Table(name = "PUR_D_EquipmentClearanceItem")
public class PoInformationItem {

    private long id;
    /**行项目*/
    private String itemLine;
    /**物料号*/
    private String material;
    /**品名规格*/
    private String materialDescription;
    /**数量*/
    private String orderQty;
    /**单位*/
    private String measureUnit;
    /**机台编号*/
    private String equipment;
    /**交付日期*/
    private Date dateOfDelivery;
    /**存放地点*/
    private String storageLocation;
    private PoInformation poInformation;

    @ManyToOne
    @JoinColumn(name="poInformation")
    public PoInformation getPoInformation() {
        return poInformation;
    }
    public void setPoInformation(PoInformation poInformation) {
        this.poInformation = poInformation;
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

    @Column(name = "ItemLine")
    public String getItemLine() {
        return itemLine;
    }
    public void setItemLine(String itemLine) {
        this.itemLine = itemLine;
    }

    @Column(name = "Material")
    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }

    @Column(name = "MaterialDescription")
    public String getMaterialDescription() {
        return materialDescription;
    }
    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    @Column(name = "OrderQty")
    public String getOrderQty() {
        return orderQty;
    }
    public void setOrderQty(String orderQty) {
        this.orderQty = orderQty;
    }

    @Column(name = "MeasureUnit")
    public String getMeasureUnit() {
        return measureUnit;
    }
    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    @Column(name = "Equipment")
    public String getEquipment() {
        return equipment;
    }
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    @Column(name = "DateOfDelivery")
    public Date getDateOfDelivery() {
        return dateOfDelivery;
    }
    public void setDateOfDelivery(Date dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    @Column(name = "StorageLocation")
    public String getStorageLocation() {
        return storageLocation;
    }
    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }
}
