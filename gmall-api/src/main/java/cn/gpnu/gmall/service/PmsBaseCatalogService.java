package cn.gpnu.gmall.service;

import cn.gpnu.gmall.beans.PmsBaseCatalog1;
import cn.gpnu.gmall.beans.PmsBaseCatalog2;
import cn.gpnu.gmall.beans.PmsBaseCatalog3;

import java.util.List;

public interface PmsBaseCatalogService {

    List<PmsBaseCatalog1> getCatalog1();

    List<PmsBaseCatalog2> getCatalog2(String catalog1Id);

    List<PmsBaseCatalog3> getCatalog3(String catalog2Id);

}
