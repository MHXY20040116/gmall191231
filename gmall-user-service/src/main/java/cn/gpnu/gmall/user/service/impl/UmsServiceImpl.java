package cn.gpnu.gmall.user.service.impl;

import cn.gpnu.gmall.beans.UmsMember;
import cn.gpnu.gmall.beans.UmsMemberReceiveAddress;
import cn.gpnu.gmall.service.UmsService;
import cn.gpnu.gmall.user.mapper.UmsMemberLevelMapper;
import cn.gpnu.gmall.user.mapper.UmsMemberMapper;
import cn.gpnu.gmall.user.mapper.UmsMemberReceiveAddressMapper;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UmsServiceImpl implements UmsService {

    @Autowired
    UmsMemberMapper umsMemberMapper;
    @Autowired
    UmsMemberLevelMapper umsMemberLevelMapper;
    @Autowired
    UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;

    @Override
    public List<UmsMember> getAllUmsMember() {
        List<UmsMember> umsMemberList = umsMemberMapper.selectAll();
        return umsMemberList;
    }

    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByUmsMemberId(String UmsMemberId) {
//        UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();
//        umsMemberReceiveAddress.setMemberId(UmsMemberId);
//        return umsMemberReceiveAddressMapper.select(umsMemberReceiveAddress);
        Example e = new Example(UmsMemberReceiveAddress.class);
        e.createCriteria().andEqualTo("memberId",UmsMemberId);
        return  umsMemberReceiveAddressMapper.selectByExample(e);
    }

}
