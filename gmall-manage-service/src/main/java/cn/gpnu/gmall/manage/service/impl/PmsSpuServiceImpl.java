package cn.gpnu.gmall.manage.service.impl;

import cn.gpnu.gmall.beans.*;
import cn.gpnu.gmall.manage.mapper.*;
import cn.gpnu.gmall.service.PmsSpuService;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class PmsSpuServiceImpl implements PmsSpuService {

    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;
    @Autowired
    PmsProductImageMapper pmsProductImageMapper;
    @Autowired
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;
    @Autowired
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;

    @Override
    public List<PmsProductInfo> getSpuList(String catalog3Id) {
        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);
        return pmsProductInfoMapper.select(pmsProductInfo);
    }

    @Override
    public String saveSpuInfo(PmsProductInfo pmsProductInfo) {
        String id = pmsProductInfo.getId();
        if(StringUtils.isBlank(id)){
            // 新增操作
            // 保存商品信息
            pmsProductInfoMapper.insertSelective(pmsProductInfo);
            // 保存商品图片信息
            List<PmsProductImage> pmsProductImageList = pmsProductInfo.getSpuImageList();
            for (PmsProductImage pmsProductImage : pmsProductImageList) {
                pmsProductImage.setProductId(pmsProductInfo.getId());
                pmsProductImageMapper.insertSelective(pmsProductImage);
            }
            // 保存销售属性信息和其销售值信息
            List<PmsProductSaleAttr> pmsProductSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
            for (PmsProductSaleAttr pmsProductSaleAttr : pmsProductSaleAttrList) {
                // 先保存当前销售属性信息
                pmsProductSaleAttr.setProductId(pmsProductInfo.getId());
                pmsProductSaleAttrMapper.insertSelective(pmsProductSaleAttr);
                // 再保存当前销售属性的所有销售值信息
                List<PmsProductSaleAttrValue> pmsProductSaleAttrValueList = pmsProductSaleAttr.getSpuSaleAttrValueList();
                for (PmsProductSaleAttrValue pmsProductSaleAttrValue : pmsProductSaleAttrValueList) {
                    pmsProductSaleAttrValue.setProductId(pmsProductInfo.getId());
                    pmsProductSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);
                }
            }
        }else{
            // 更新操作
            // 更新保存商品
            Example example = new Example(PmsProductInfo.class);
            example.createCriteria().andEqualTo("id",pmsProductInfo.getId());
            pmsProductInfoMapper.updateByExampleSelective(pmsProductInfo,example);
            // 按照商品属性id删除所有图片的记录，再重新保存
            PmsProductImage pmsProductImageDel = new PmsProductImage();
            pmsProductImageDel.setProductId(pmsProductInfo.getId());
            pmsProductImageMapper.delete(pmsProductImageDel);
            // 重新保存相关信息
            List<PmsProductImage> pmsProductImageList = pmsProductInfo.getSpuImageList();
            for (PmsProductImage pmsProductImage : pmsProductImageList) {
                pmsProductImage.setProductId(pmsProductInfo.getId());
                pmsProductImageMapper.insertSelective(pmsProductImage);
            }
            // 按照商品属性id删除所有其销售属性和销售属性值的记录，再重新保存
            // 按照商品属性id删除所有销售属性
            PmsProductSaleAttr pmsProductSaleAttrDel = new PmsProductSaleAttr();
            pmsProductSaleAttrDel.setProductId(pmsProductInfo.getId());
            pmsProductSaleAttrMapper.delete(pmsProductSaleAttrDel);
            // 按照商品属性id删除所有销售属性值
            PmsProductSaleAttrValue pmsProductSaleAttrValueDel = new PmsProductSaleAttrValue();
            pmsProductSaleAttrValueDel.setProductId(pmsProductInfo.getId());
            pmsProductSaleAttrValueMapper.delete(pmsProductSaleAttrValueDel);
            // 重新保存相关信息
            List<PmsProductSaleAttr> pmsProductSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
            for (PmsProductSaleAttr pmsProductSaleAttr : pmsProductSaleAttrList) {
                pmsProductSaleAttr.setProductId(pmsProductInfo.getId());
                pmsProductSaleAttrMapper.insertSelective(pmsProductSaleAttr);
                List<PmsProductSaleAttrValue> pmsProductSaleAttrValueList = pmsProductSaleAttr.getSpuSaleAttrValueList();
                for (PmsProductSaleAttrValue pmsProductSaleAttrValue : pmsProductSaleAttrValueList) {
                    pmsProductSaleAttrValue.setProductId(pmsProductInfo.getId());
                    pmsProductSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);
                }
            }
        }

        return "success";
    }

    @Override
    public List<PmsProductSaleAttr> getSpuSaleAttrList(String spuId) {
        // 双层集合
        PmsProductSaleAttr pmsProductSaleAttr = new PmsProductSaleAttr();
        pmsProductSaleAttr.setProductId(spuId);
        List<PmsProductSaleAttr> pmsProductSaleAttrList = pmsProductSaleAttrMapper.select(pmsProductSaleAttr);
        for (PmsProductSaleAttr productSaleAttr : pmsProductSaleAttrList) {
            PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();
            pmsProductSaleAttrValue.setProductId(spuId);
            pmsProductSaleAttrValue.setSaleAttrId(productSaleAttr.getSaleAttrId());// 销售属性id用的是系统的字典表中id，不是销售属性表的主键
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValues = pmsProductSaleAttrValueMapper.select(pmsProductSaleAttrValue);
            productSaleAttr.setSpuSaleAttrValueList(pmsProductSaleAttrValues);
        }
        return pmsProductSaleAttrList;
    }

    @Override
    public List<PmsProductImage> getSpuImageList(String spuId) {
        PmsProductImage pmsProductImage = new PmsProductImage();
        pmsProductImage.setProductId(spuId);
        return pmsProductImageMapper.select(pmsProductImage);
    }

    @Override
    public List<PmsProductSaleAttr> getSpuSaleAttrListCheckBySku(String productId, String skuId) {
        List<PmsProductSaleAttr> pmsProductSaleAttrList = pmsProductSaleAttrMapper.selectSpuSaleAttrListCheckBySku(productId,skuId);
        return pmsProductSaleAttrList;
    }

}
