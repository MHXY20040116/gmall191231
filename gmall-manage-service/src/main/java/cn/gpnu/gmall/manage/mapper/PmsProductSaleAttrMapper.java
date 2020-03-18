package cn.gpnu.gmall.manage.mapper;

import cn.gpnu.gmall.beans.PmsProductSaleAttr;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsProductSaleAttrMapper extends Mapper<PmsProductSaleAttr> {

    List<PmsProductSaleAttr> selectSpuSaleAttrListCheckBySku(@Param("productId") String productId, @Param("skuId") String skuId);

}