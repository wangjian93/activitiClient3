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
@Table(name = "HR_O_ProductionGroup")
public class ProductionGroup extends Model {

    private static final long serialVersionUID = 1L;
    private String productionGroup_ID;
    private String productionGroupName;
    public ProductionGroup(){}

    @Id
    @Column(name = "ProductionGroup_ID")
    public String getProductionGroup_ID() {
        return productionGroup_ID;
    }
    public void setProductionGroup_ID(String productionGroup_ID) {
        this.productionGroup_ID = productionGroup_ID;
    }


    @Column(name = "ProductionGroupName")
    public String getProductionGroupName() {
        return productionGroupName;
    }
    public void setProductionGroupName(String productionGroupName) {
        this.productionGroupName = productionGroupName;
    }





}
