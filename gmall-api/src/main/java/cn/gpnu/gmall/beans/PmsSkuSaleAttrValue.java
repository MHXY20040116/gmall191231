package cn.gpnu.gmall.beans;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @param
 * @return
 */
public class PmsSkuSaleAttrValue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    String id;
    @Column
    String skuId;
    @Column
    String saleAttrId;
    @Column
    String saleAttrName;
    @Column
    String saleAttrValueId;
    @Column
    String saleAttrValueName;

    public PmsSkuSaleAttrValue() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSkuId() {
        return skuId;
    }
    public void setSkuId(String skuId) {
        this.skuId = skuId;
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
    public String getSaleAttrValueId() {
        return saleAttrValueId;
    }
    public void setSaleAttrValueId(String saleAttrValueId) {
        this.saleAttrValueId = saleAttrValueId;
    }
    public String getSaleAttrValueName() {
        return saleAttrValueName;
    }
    public void setSaleAttrValueName(String saleAttrValueName) {
        this.saleAttrValueName = saleAttrValueName;
    }

    @Override
    public String toString() {
        return "PmsSkuSaleAttrValue{" +
                "id='" + id + '\'' +
                ", skuId='" + skuId + '\'' +
                ", saleAttrId='" + saleAttrId + '\'' +
                ", saleAttrValueId='" + saleAttrValueId + '\'' +
                ", saleAttrName='" + saleAttrName + '\'' +
                ", saleAttrValueName='" + saleAttrValueName + '\'' +
                '}';
    }

}
