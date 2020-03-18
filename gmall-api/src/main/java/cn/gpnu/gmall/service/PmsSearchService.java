package cn.gpnu.gmall.service;

import cn.gpnu.gmall.beans.PmsSearchParam;
import cn.gpnu.gmall.beans.PmsSearchSkuInfo;

import java.io.IOException;
import java.util.List;

public interface PmsSearchService {

    List<PmsSearchSkuInfo> list(PmsSearchParam pmsSearchParam);

}
