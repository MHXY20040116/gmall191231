package cn.gpnu.gmall.manage.controller;

import cn.gpnu.gmall.beans.PmsBaseAttrInfo;
import cn.gpnu.gmall.beans.PmsBaseAttrValue;
import cn.gpnu.gmall.beans.PmsBaseSaleAttr;
import cn.gpnu.gmall.service.PmsBaseAttrService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class AttrController {

    @Reference
    PmsBaseAttrService pmsBaseAttrService;

    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<PmsBaseAttrInfo> getAttrInfoList(String catalog3Id){
        List<PmsBaseAttrInfo> pmsBaseAttrInfoList = pmsBaseAttrService.getAttrInfoList(catalog3Id);
        return pmsBaseAttrInfoList;
    }

    @RequestMapping("getAttrValueList")
    @ResponseBody
    public List<PmsBaseAttrValue> getAttrValueList(String attrId){
        List<PmsBaseAttrValue> pmsBaseAttrValueList = pmsBaseAttrService.getAttrValueList(attrId);
        return pmsBaseAttrValueList;
    }

    @RequestMapping("saveAttrInfo")
    @ResponseBody
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo){
        String isSuccess = pmsBaseAttrService.saveAttrInfo(pmsBaseAttrInfo);
        return isSuccess;
    }

    @RequestMapping("baseSaleAttrList")
    @ResponseBody
    public List<PmsBaseSaleAttr> getBaseSaleAttrList(){
        List<PmsBaseSaleAttr> pmsBaseSaleAttrList = pmsBaseAttrService.getBaseSaleAttrList();
        return pmsBaseSaleAttrList;
    }

}
