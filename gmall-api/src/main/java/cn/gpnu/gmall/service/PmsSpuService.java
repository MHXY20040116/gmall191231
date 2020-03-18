package cn.gpnu.gmall.service;

import cn.gpnu.gmall.beans.PmsBaseSaleAttr;
import cn.gpnu.gmall.beans.PmsProductImage;
import cn.gpnu.gmall.beans.PmsProductInfo;
import cn.gpnu.gmall.beans.PmsProductSaleAttr;

import java.util.List;

public interface PmsSpuService {

    List<PmsProductInfo> getSpuList(String catalog3Id);

    String saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductImage> getSpuImageList(String spuId);

    List<PmsProductSaleAttr> getSpuSaleAttrList(String spuId);

    List<PmsProductSaleAttr> getSpuSaleAttrListCheckBySku(String productId, String skuId);

}
