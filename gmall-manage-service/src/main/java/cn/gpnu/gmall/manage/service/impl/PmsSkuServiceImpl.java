package cn.gpnu.gmall.manage.service.impl;

import cn.gpnu.gmall.beans.PmsSkuAttrValue;
import cn.gpnu.gmall.beans.PmsSkuImage;
import cn.gpnu.gmall.beans.PmsSkuInfo;
import cn.gpnu.gmall.beans.PmsSkuSaleAttrValue;
import cn.gpnu.gmall.manage.mapper.PmsSkuAttrValueMapper;
import cn.gpnu.gmall.manage.mapper.PmsSkuImageMapper;
import cn.gpnu.gmall.manage.mapper.PmsSkuInfoMapper;
import cn.gpnu.gmall.manage.mapper.PmsSkuSaleAttrValueMapper;
import cn.gpnu.gmall.service.PmsSkuService;
import cn.gpnu.gmall.utils.RedisUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class PmsSkuServiceImpl implements PmsSkuService {

    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;
    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;
    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;
    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public String saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        // 保存Sku信息
        int i = pmsSkuInfoMapper.insertSelective(pmsSkuInfo);
        String skuId = pmsSkuInfo.getId();
        // 关联平台属性信息
        List<PmsSkuAttrValue> pmsSkuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        for (PmsSkuAttrValue pmsSkuAttrValue : pmsSkuAttrValueList) {
            pmsSkuAttrValue.setSkuId(skuId);
            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }
        // 关联销售属性信息
        List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : pmsSkuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(skuId);
            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
        }
        // 关联图片信息
        List<PmsSkuImage> pmsSkuImageList = pmsSkuInfo.getSkuImageList();
        for (PmsSkuImage pmsSkuImage : pmsSkuImageList) {
            pmsSkuImage.setSkuId(skuId);
            pmsSkuImageMapper.insertSelective(pmsSkuImage);
        }
        return "success";
    }

    public PmsSkuInfo getSkuByIdFromMySQL(String skuId) {
        // sku对象
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        pmsSkuInfo.setId(skuId);
        PmsSkuInfo skuInfo = pmsSkuInfoMapper.selectOne(pmsSkuInfo);
        // 图片集合
        PmsSkuImage pmsSkuImage = new PmsSkuImage();
        pmsSkuImage.setSkuId(skuId);
        List<PmsSkuImage> images = pmsSkuImageMapper.select(pmsSkuImage);
        skuInfo.setSkuImageList(images);
        return skuInfo;
    }

    @Override
    public PmsSkuInfo getSkuById(String skuId) {
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        // 连接缓存
        Jedis jedis = redisUtil.getJedis();
        try {
            // 查询缓存
            String sukKey = "sku:" + skuId + ":info";
            String skuJson = jedis.get(sukKey);
            if (StringUtils.isNotBlank(skuJson)) {
                pmsSkuInfo = JSON.parseObject(skuJson, PmsSkuInfo.class);
            } else {
                // 缓存中没有，查询MySQL
                // 设置分布式锁
                String token = UUID.randomUUID().toString();
                String OK = jedis.set("sku:" + skuId + ":lock", token, "nx", "px", 30 * 1000);
                if (StringUtils.isNotBlank(OK) && OK.equals("OK")) {
                    // 设置redis自带分布式锁成功，有权限在60秒的过期时间内访问MySQL数据库
                    pmsSkuInfo = getSkuByIdFromMySQL(skuId);
                    // MySQL查询结果存入缓存
                    if (pmsSkuInfo != null) {
                        jedis.set("sku:" + skuId + ":info", JSON.toJSONString(pmsSkuInfo));
                    } else {
                        // MySQL数据库不存在该sku记录，为防止缓存穿透，null值或空字符串设置到redis缓存
                        jedis.setex("sku:" + skuId + ":info", 60 * 5, JSON.toJSONString(""));
                        // 解决缓存雪崩的方法：设置不同的失效时间或者更换热点数据并更换新的数据淘汰策略
                        // 解决缓存击穿的方法：redis分布式锁
                    }
                    // 不管访问数据结果，释放redis分布式锁
                    String lockToken = jedis.get("sku:" + skuId + ":lock");
                    if (StringUtils.isNotBlank(lockToken) && lockToken.equals(token)) {
                        // 用token确认删除的是自己的sku的锁
                        jedis.del("sku:" + skuId + ":lock");
                        // 可以用lua脚本在查询到key的同时删除该key，防止高并发下意外的发生
//                        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//                        jedis.eval(script, Collections.singletonList("sku:" + skuId + ":lock"), Collections.singletonList(token));
                    }
                } else {
                    // 设置redis自带分布式锁失败，自旋(该线程在睡眠几秒后，重新进行访问本方法)
                    Thread.sleep(3000);
                    return getSkuById(skuId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return pmsSkuInfo;
    }

    @Override
    public List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId) {
        List<PmsSkuInfo> pmsSkuInfoList = pmsSkuInfoMapper.selectSkuSaleAttrValueListBySpu(productId);
        return pmsSkuInfoList;
    }

    @Override
    public List<PmsSkuInfo> getAllSku() {
        List<PmsSkuInfo> pmsSkuInfoList = pmsSkuInfoMapper.selectAll();
        for (PmsSkuInfo pmsSkuInfo : pmsSkuInfoList) {
            String skuId = pmsSkuInfo.getId();
            PmsSkuAttrValue pmsSkuAttrValue = new PmsSkuAttrValue();
            pmsSkuAttrValue.setSkuId(skuId);
            List<PmsSkuAttrValue> pmsSkuAttrValues = pmsSkuAttrValueMapper.select(pmsSkuAttrValue);
            pmsSkuInfo.setSkuAttrValueList(pmsSkuAttrValues);
        }
        return pmsSkuInfoList;
    }

}
