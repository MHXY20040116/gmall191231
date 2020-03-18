package cn.gpnu.gmall.manage.controller;

import cn.gpnu.gmall.beans.PmsBaseCatalog1;
import cn.gpnu.gmall.beans.PmsBaseCatalog2;
import cn.gpnu.gmall.beans.PmsBaseCatalog3;
import cn.gpnu.gmall.service.PmsBaseCatalogService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class CatalogController {

    @Reference
    PmsBaseCatalogService pmsBaseCatalogService;

    @RequestMapping("getCatalog1")
    @ResponseBody
    public List<PmsBaseCatalog1> getCatalog1(){
        List<PmsBaseCatalog1> pmsBaseCatalog1List = pmsBaseCatalogService.getCatalog1();
        return pmsBaseCatalog1List;
    }

    @RequestMapping("getCatalog2")
    @ResponseBody
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id){
        List<PmsBaseCatalog2> pmsBaseCatalog2List = pmsBaseCatalogService.getCatalog2(catalog1Id);
        return pmsBaseCatalog2List;
    }

    @RequestMapping("getCatalog3")
    @ResponseBody
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id){
        List<PmsBaseCatalog3> pmsBaseCatalog2List = pmsBaseCatalogService.getCatalog3(catalog2Id);
        return pmsBaseCatalog2List;
    }

}
