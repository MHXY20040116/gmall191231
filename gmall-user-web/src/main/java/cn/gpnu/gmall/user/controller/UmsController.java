package cn.gpnu.gmall.user.controller;

import cn.gpnu.gmall.beans.UmsMember;
import cn.gpnu.gmall.beans.UmsMemberReceiveAddress;
import cn.gpnu.gmall.service.UmsService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UmsController {

    @Reference
    UmsService umsService;

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "Test User Service !";
    }

    @RequestMapping("/getAllUmsMember")
    @ResponseBody
    public List<UmsMember> getAllUmsMember(){
        List<UmsMember> umsMemberList = umsService.getAllUmsMember();
        return umsMemberList;
    }

    @RequestMapping("/getReceiveAddressByUmsMemberId")
    @ResponseBody
    public List<UmsMemberReceiveAddress> getReceiveAddressByUmsMemberId( String UmsMemberId){
        List<UmsMemberReceiveAddress> umsMemberReceiveAddressList = umsService.getReceiveAddressByUmsMemberId(UmsMemberId);
        return umsMemberReceiveAddressList;
    }

}
