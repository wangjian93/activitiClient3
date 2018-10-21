package com.ivo.activiticlient.mm.iec;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangjian
 * @date 2018/10/13
 */
@Entity
@Table(name = "PUR_D_EquipmentClearance")
public class PoInformation {


    private long id;
    /**供应商*/
    private String vendor;
    /**电话*/
    private String vendorContactExt;
    /**传真*/
    private String vendorFax;
    /**地址*/
    private String address;
    /**邮编*/
    private String zipCode;
    /**联系人*/
    private String vendorContact;
    /**保固期*/
    private	String warrantyTerm;
    /**收货联系人*/
    private String goodsReceiveContact;
    /**收货联系人电话*/
    private String contactExt;
    /**收货地点*/
    private String deliveryPlace;
    /**交货方式*/
    private String deliveryTerm;
    /**po单号*/
    private String purchaseOrder;
    /**po日期*/
    private Date dateOfOrder;
    /**采购员*/
    private String requisitioner;
    /**po表中的明细项*/
    private List<PoInformationItem> poInformationItem = new ArrayList<PoInformationItem>();

    @Column(name = "ZipCode")
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Column(name = "PurchaseOrder")
    public String getPurchaseOrder() {
        return purchaseOrder;
    }
    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    @Column(name = "DateOfOrder")
    public Date getDateOfOrder() {
        return dateOfOrder;
    }
    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    @Column(name = "Requisitioner_FK")
    public String getRequisitioner() {
        return requisitioner;
    }
    public void setRequisitioner(String requisitioner) {
        this.requisitioner = requisitioner;
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

    @Column(name = "Vendor")
    public String getVendor() {
        return vendor;
    }
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @Column(name = "VendorContactExt")
    public String getVendorContactExt() {
        return vendorContactExt;
    }
    public void setVendorContactExt(String vendorContactExt) {
        this.vendorContactExt = vendorContactExt;
    }

    @Column(name = "VendorFax")
    public String getVendorFax() {
        return vendorFax;
    }
    public void setVendorFax(String vendorFax) {
        this.vendorFax = vendorFax;
    }

    @Column(name = "Address")
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "VendorContact")
    public String getVendorContact() {
        return vendorContact;
    }
    public void setVendorContact(String vendorContact) {
        this.vendorContact = vendorContact;
    }

    @Column(name = "WarrantyTerm")
    public String getWarrantyTerm() {
        return warrantyTerm;
    }
    public void setWarrantyTerm(String warrantyTerm) {
        this.warrantyTerm = warrantyTerm;
    }

    @Column(name = "GoodsReceiveContact")
    public String getGoodsReceiveContact() {
        return goodsReceiveContact;
    }
    public void setGoodsReceiveContact(String goodsReceiveContact) {
        this.goodsReceiveContact = goodsReceiveContact;
    }

    @Column(name = "ContactExt")
    public String getContactExt() {
        return contactExt;
    }
    public void setContactExt(String contactExt) {
        this.contactExt = contactExt;
    }

    @Column(name = "DeliveryPlace")
    public String getDeliveryPlace() {
        return deliveryPlace;
    }
    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    @Column(name = "DeliveryTerm")
    public String getDeliveryTerm() {
        return deliveryTerm;
    }
    public void setDeliveryTerm(String deliveryTerm) {
        this.deliveryTerm = deliveryTerm;
    }

    @OneToMany
    @Cascade(value={org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name="poInformation")
    public List<PoInformationItem> getPoInformationItem() {
        return poInformationItem;
    }
    public void setPoInformationItem(List<PoInformationItem> poInformationItem) {
        this.poInformationItem = poInformationItem;
    }



}
