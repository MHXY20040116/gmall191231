package cn.gpnu.gmall.manage.controller;

import cn.gpnu.gmall.beans.PmsProductImage;
import cn.gpnu.gmall.beans.PmsProductInfo;
import cn.gpnu.gmall.beans.PmsProductSaleAttr;
import cn.gpnu.gmall.service.PmsSpuService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utils.PmsUploadUtil;

import java.util.List;

@Controller
@CrossOrigin
public class SpuController {

    @Reference
    PmsSpuService pmsSpuService;

    @RequestMapping("spuList")
    @ResponseBody
    public List<PmsProductInfo> getSpuList(String catalog3Id){
        List<PmsProductInfo> pmsProductInfoList = pmsSpuService.getSpuList(catalog3Id);
        return pmsProductInfoList;
    }

    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile){
        //将图片或者音视频上传到分布式文件存储系统，并将图片的存储路径返回给页面
        String imgUrl = PmsUploadUtil.uploadImage(multipartFile);
        return imgUrl;
    }

    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){
        String isSuccess = pmsSpuService.saveSpuInfo(pmsProductInfo);
        return isSuccess;
    }

    @RequestMapping("spuImageList")
    @ResponseBody
    public List<PmsProductImage> getSpuImageList(String spuId){
        List<PmsProductImage> pmsProductImageList = pmsSpuService.getSpuImageList(spuId);
        return pmsProductImageList;
    }

    @RequestMapping("spuSaleAttrList")
    @ResponseBody
    public List<PmsProductSaleAttr> getSpuSaleAttrList(String spuId){
        List<PmsProductSaleAttr> pmsProductSaleAttrList = pmsSpuService.getSpuSaleAttrList(spuId);
        return pmsProductSaleAttrList;
    }

}
