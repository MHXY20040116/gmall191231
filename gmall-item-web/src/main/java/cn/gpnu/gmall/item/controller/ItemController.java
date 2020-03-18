package cn.gpnu.gmall.item.controller;

import cn.gpnu.gmall.beans.PmsProductSaleAttr;
import cn.gpnu.gmall.beans.PmsSkuInfo;
import cn.gpnu.gmall.beans.PmsSkuSaleAttrValue;
import cn.gpnu.gmall.service.PmsSkuService;
import cn.gpnu.gmall.service.PmsSpuService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class ItemController {

    @Reference
    PmsSkuService pmsSkuService;
    @Reference
    PmsSpuService pmsSpuService;

    @RequestMapping("index")
    public String index(ModelMap modelMap){
        modelMap.put("context","HaHa");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("我是数字" + i);
        }
        modelMap.put("list",list);
        modelMap.put("checked","true");
        return "index";
    }

    @RequestMapping("{skuId}.html")
    public String item(@PathVariable String skuId, ModelMap modelMap){
        // sku对象
        PmsSkuInfo pmsSkuInfo = pmsSkuService.getSkuById(skuId);
        modelMap.put("skuInfo",pmsSkuInfo);
        // 销售属性列表
        List<PmsProductSaleAttr> pmsProductSaleAttrList = pmsSpuService.getSpuSaleAttrListCheckBySku(pmsSkuInfo.getProductId(),pmsSkuInfo.getId());
        modelMap.put("spuSaleAttrListCheckBySku",pmsProductSaleAttrList);
        // 查询当前sku的spu的其他sku的集合，并转换成Hash表，存放到页面(已实现js文件静态化)
//        Map<String, String> skuSaleAttrHash = new HashMap<>();
//        List<PmsSkuInfo> pmsSkuInfoList = pmsSkuService.getSkuSaleAttrValueListBySpu(pmsSkuInfo.getProductId());
//        for (PmsSkuInfo skuInfo : pmsSkuInfoList) {
//            String k = "";
//            String v = skuInfo.getId();
//            List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
//            for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : pmsSkuSaleAttrValueList) {
//                k = k + pmsSkuSaleAttrValue.getSaleAttrValueId() + "|";
//            }
//            skuSaleAttrHash.put(k,v);
//        }
//        String skuSaleAttrHashJsonStr = JSON.toJSONString(skuSaleAttrHash);
//        modelMap.put("skuSaleAttrHashJsonStr",skuSaleAttrHashJsonStr);
        // 页面跳转
        return "item";
    }

}
