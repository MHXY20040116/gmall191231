package cn.gpnu.gmall.search.controller;

import cn.gpnu.gmall.beans.*;
import cn.gpnu.gmall.service.PmsBaseAttrService;
import cn.gpnu.gmall.service.PmsSearchService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@CrossOrigin
public class SearchController {

    @Reference
    PmsBaseAttrService pmsBaseAttrService;
    @Reference
    PmsSearchService pmsSearchService;

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("list")
    public String list(PmsSearchParam pmsSearchParam, ModelMap modelMap){
        // 调用搜索服务，返回搜索结果
        List<PmsSearchSkuInfo> pmsSearchSkuInfos = pmsSearchService.list(pmsSearchParam);
        modelMap.put("skuLsInfoList", pmsSearchSkuInfos);
        // 抽取检索结果所包含的平台属性集合
        Set<String> valueIdSet = new HashSet<>();
        for (PmsSearchSkuInfo pmsSearchSkuInfo : pmsSearchSkuInfos) {
            List<PmsSkuAttrValue> skuAttrValueList = pmsSearchSkuInfo.getSkuAttrValueList();
            for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
                String valueId = pmsSkuAttrValue.getValueId();
                valueIdSet.add(valueId);
            }
        }
        // 查询平台属性列表
        List<PmsBaseAttrInfo> pmsBaseAttrInfoList = pmsBaseAttrService.getAttrValueListByValueId(valueIdSet);
        modelMap.put("attrList", pmsBaseAttrInfoList);
        // 对平台属性集合进一步处理，排除当前选择属性的属性组
        String[] delValueIds = pmsSearchParam.getValueId();
        if(delValueIds != null){
            // 面包屑
            List<PmsSearchCrumb> pmsSearchCrumbs = new ArrayList<>();
            // 集合处理
            for (String delValueId : delValueIds) {
                Iterator<PmsBaseAttrInfo> iterator = pmsBaseAttrInfoList.iterator();
                PmsSearchCrumb pmsSearchCrumb = new PmsSearchCrumb();
                // 封装面包屑数据
                pmsSearchCrumb.setValueId(delValueId);
                pmsSearchCrumb.setUrlParam(getUrlParamForCrumb(pmsSearchParam, delValueId));
                // 集合迭代
                while (iterator.hasNext()) {
                    PmsBaseAttrInfo pmsBaseAttrInfo = iterator.next();
                    List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
                    for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                        String valueId = pmsBaseAttrValue.getId();
                        // 封装面包屑的属性值名称
                        pmsSearchCrumb.setValueName(pmsBaseAttrValue.getValueName());
                        if (valueId.equals(delValueId)) {
                            //删除该属性所在的属性组
                            iterator.remove();
                        }
                    }
                }
                pmsSearchCrumbs.add(pmsSearchCrumb);
            }
            modelMap.put("attrValueSelectedList", pmsSearchCrumbs);
        }
        // 处理URL以及搜索关键字
        String urlParam = getUrlParam(pmsSearchParam);
        modelMap.put("urlParam", urlParam);
        String keyword = pmsSearchParam.getKeyword();
        if(StringUtils.isNotBlank(keyword)){
            modelMap.put("keyword", keyword);
        }

        // 页面跳转
        return "list";
    }

    private String getUrlParam(PmsSearchParam pmsSearchParam) {
        String keyword = pmsSearchParam.getKeyword();
        String catalog3Id = pmsSearchParam.getCatalog3Id();
        String[] skuAttrValueList = pmsSearchParam.getValueId();
        // 拼接url
        String urlParam = "";
        if(StringUtils.isNotBlank(keyword)){
            if(StringUtils.isNotBlank(urlParam)){
                urlParam = urlParam + "&";
            }
            urlParam = urlParam + "keyword" + keyword;
        }
        if(StringUtils.isNotBlank(catalog3Id)){
            if(StringUtils.isNotBlank(urlParam)){
                urlParam = urlParam + "&";
            }
            urlParam = urlParam + "catalog3Id" + catalog3Id;
        }
        if(skuAttrValueList != null){
            for (String pmsSkuAttrValue : skuAttrValueList) {
                urlParam = urlParam + "&valueId" + pmsSkuAttrValue;
            }
        }
        return  urlParam;
    }

    private String getUrlParamForCrumb(PmsSearchParam pmsSearchParam, String delValueId) {
        String keyword = pmsSearchParam.getKeyword();
        String catalog3Id = pmsSearchParam.getCatalog3Id();
        String[] skuAttrValueList = pmsSearchParam.getValueId();
        // 拼接url
        String urlParam = "";
        if(StringUtils.isNotBlank(keyword)){
            if(StringUtils.isNotBlank(urlParam)){
                urlParam = urlParam + "&";
            }
            urlParam = urlParam + "keyword" + keyword;
        }
        if(StringUtils.isNotBlank(catalog3Id)){
            if(StringUtils.isNotBlank(urlParam)){
                urlParam = urlParam + "&";
            }
            urlParam = urlParam + "catalog3Id" + catalog3Id;
        }
        if(skuAttrValueList != null){
            for (String pmsSkuAttrValue : skuAttrValueList) {
                if(!pmsSkuAttrValue.equals(delValueId)){
                    urlParam = urlParam + "&valueId" + pmsSkuAttrValue;
                }
            }
        }
        return  urlParam;
    }

}
