package cn.gpnu.gmall.service;

import cn.gpnu.gmall.beans.PmsBaseAttrInfo;
import cn.gpnu.gmall.beans.PmsBaseAttrValue;
import cn.gpnu.gmall.beans.PmsBaseSaleAttr;

import java.util.List;
import java.util.Set;

public interface PmsBaseAttrService {

    List<PmsBaseAttrInfo> getAttrInfoList(String catalog3Id);

    List<PmsBaseSaleAttr> getBaseSaleAttrList();

    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrInfo> getAttrValueListByValueId(Set<String> valueIdSet);

}
