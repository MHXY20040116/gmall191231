package cn.gpnu.gmall.manage.controller;

import cn.gpnu.gmall.beans.PmsSkuInfo;
import cn.gpnu.gmall.service.PmsSkuService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class SkuController {

    @Reference
    PmsSkuService pmsSkuService;

    @RequestMapping("saveSkuInfo")
    @ResponseBody
    public String saveSkuInfo(@RequestBody PmsSkuInfo pmsSkuInfo){
        // 封装productId属性
        pmsSkuInfo.setProductId(pmsSkuInfo.getSpuId());
        // 处理默认图片
        String skuDefaultImg = pmsSkuInfo.getSkuDefaultImg();
        if(StringUtils.isBlank(skuDefaultImg)){
            if(pmsSkuInfo.getSkuImageList().size() == 0){
                pmsSkuInfo.setSkuDefaultImg("http://192.168.199.128/group1/M00/00/00/wKgcgF4ZelqAa698AAAUowIdUkI412.jpg");
            }else{
                pmsSkuInfo.setSkuDefaultImg(pmsSkuInfo.getSkuImageList().get(0).getImgUrl());
            }
        }
        String isSuccess = pmsSkuService.saveSkuInfo(pmsSkuInfo);
        return "success";
    }

}
