package cn.gpnu.gmall.service;

import cn.gpnu.gmall.beans.UmsMember;
import cn.gpnu.gmall.beans.UmsMemberReceiveAddress;
import java.util.List;

public interface UmsService {

    List<UmsMember> getAllUmsMember();

    List<UmsMemberReceiveAddress> getReceiveAddressByUmsMemberId(String UmsMemberId);

}
