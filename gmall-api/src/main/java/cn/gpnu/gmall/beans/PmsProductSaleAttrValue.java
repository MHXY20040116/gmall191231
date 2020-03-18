package cn.gpnu.gmall.beans;

import javax.persistence.*;
import java.io.Serializable;

public class PmsProductSaleAttrValue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    String id ;
    @Column
    String productId;
    @Column
    String saleAttrId;
    @Column
    String saleAttrValueName;
    @Transient
    String isChecked;

    public PmsProductSaleAttrValue() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getSaleAttrId() {
        return saleAttrId;
    }
    public void setSaleAttrId(String saleAttrId) {
        this.saleAttrId = saleAttrId;
    }
    public String getSaleAttrValueName() {
        return saleAttrValueName;
    }
    public void setSaleAttrValueName(String saleAttrValueName) {
        this.saleAttrValueName = saleAttrValueName;
    }
    public String getIsChecked() {
        return isChecked;
    }
    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    @Override
    public String toString() {
        return "PmsProductSaleAttrValue{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", saleAttrId='" + saleAttrId + '\'' +
                ", saleAttrValueName='" + saleAttrValueName + '\'' +
                ", isChecked='" + isChecked + '\'' +
                '}';
    }

}
