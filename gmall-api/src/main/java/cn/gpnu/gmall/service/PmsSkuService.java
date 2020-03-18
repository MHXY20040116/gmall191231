package cn.gpnu.gmall.service;

import cn.gpnu.gmall.beans.PmsSkuInfo;

import java.util.List;

public interface PmsSkuService {

    String saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    PmsSkuInfo getSkuById(String skuId);

    List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId);

    List<PmsSkuInfo> getAllSku();

}
