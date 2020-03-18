package cn.gpnu.gmall.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @param
 * @return
 */
public class PmsSkuInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    String id;
    @Column
    String productId;
    @Column
    BigDecimal price;
    @Column
    String skuName;
    @Column
    BigDecimal weight;
    @Column
    String skuDesc;
    @Column
    String tmId;
    @Column
    String catalog3Id;
    @Column
    String skuDefaultImg;

    @Transient
    List<PmsSkuImage> skuImageList;
    @Transient
    List<PmsSkuAttrValue> skuAttrValueList;
    @Transient
    List<PmsSkuSaleAttrValue> skuSaleAttrValueList;

    @Transient
    String spuId;
    public String getSpuId() {
        return spuId;
    }
    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public PmsSkuInfo() {
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
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getSkuName() {
        return skuName;
    }
    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }
    public BigDecimal getWeight() {
        return weight;
    }
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
    public String getSkuDesc() {
        return skuDesc;
    }
    public void setSkuDesc(String skuDesc) {
        this.skuDesc = skuDesc;
    }
    public String getTmId() {
        return tmId;
    }
    public void setTmId(String tmId) {
        this.tmId = tmId;
    }
    public String getCatalog3Id() {
        return catalog3Id;
    }
    public void setCatalog3Id(String catalog3Id) {
        this.catalog3Id = catalog3Id;
    }
    public String getSkuDefaultImg() {
        return skuDefaultImg;
    }
    public void setSkuDefaultImg(String skuDefaultImg) {
        this.skuDefaultImg = skuDefaultImg;
    }
    public List<PmsSkuImage> getSkuImageList() {
        return skuImageList;
    }
    public void setSkuImageList(List<PmsSkuImage> skuImageList) {
        this.skuImageList = skuImageList;
    }
    public List<PmsSkuAttrValue> getSkuAttrValueList() {
        return skuAttrValueList;
    }
    public void setSkuAttrValueList(List<PmsSkuAttrValue> skuAttrValueList) {
        this.skuAttrValueList = skuAttrValueList;
    }
    public List<PmsSkuSaleAttrValue> getSkuSaleAttrValueList() {
        return skuSaleAttrValueList;
    }
    public void setSkuSaleAttrValueList(List<PmsSkuSaleAttrValue> skuSaleAttrValueList) {
        this.skuSaleAttrValueList = skuSaleAttrValueList;
    }

    @Override
    public String toString() {
        return "PmsSkuInfo{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", price=" + price +
                ", skuName='" + skuName + '\'' +
                ", weight=" + weight +
                ", skuDesc='" + skuDesc + '\'' +
                ", tmId='" + tmId + '\'' +
                ", catalog3Id='" + catalog3Id + '\'' +
                ", skuDefaultImg='" + skuDefaultImg + '\'' +
                ", skuImageList=" + skuImageList +
                ", skuAttrValueList=" + skuAttrValueList +
                ", skuSaleAttrValueList=" + skuSaleAttrValueList +
                '}';
    }

}
