package cn.gpnu.gmall.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class PmsProductSaleAttr implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    String id ;
    @Column
    String productId;
    @Column
    String saleAttrId;
    @Column
    String saleAttrName;
    @Transient
    List<PmsProductSaleAttrValue> spuSaleAttrValueList;

    public PmsProductSaleAttr() {
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
    public String getSaleAttrName() {
        return saleAttrName;
    }
    public void setSaleAttrName(String saleAttrName) {
        this.saleAttrName = saleAttrName;
    }
    public List<PmsProductSaleAttrValue> getSpuSaleAttrValueList() {
        return spuSaleAttrValueList;
    }
    public void setSpuSaleAttrValueList(List<PmsProductSaleAttrValue> spuSaleAttrValueList) {
        this.spuSaleAttrValueList = spuSaleAttrValueList;
    }

    @Override
    public String toString() {
        return "PmsProductSaleAttr{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", saleAttrId='" + saleAttrId + '\'' +
                ", saleAttrName='" + saleAttrName + '\'' +
                ", spuSaleAttrValueList=" + spuSaleAttrValueList +
                '}';
    }

}
