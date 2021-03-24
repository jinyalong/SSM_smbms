package com.codefriday.controller;

import com.alibaba.fastjson.JSON;
import com.codefriday.pojo.Bill;
import com.codefriday.pojo.Provider;
import com.codefriday.pojo.User;
import com.codefriday.service.bill.BillService;
import com.codefriday.service.provider.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class ProviderController {
    @Autowired
    @Qualifier("ProviderServiceImpl")
    ProviderService providerService;

    @Autowired
    @Qualifier("BillServiceImpl")
    BillService billService;

    @RequestMapping("/providers")
    public String getProviders(String queryProName, String queryProCode, Model model){
        List<Provider> providerList = null;
        if(providerService!=null){
            providerList = providerService.getProviderList(queryProName, queryProCode);
            model.addAttribute("providerList",providerList);
        }
        return "providerlist";

    }

    //跳转到添加页面
    @GetMapping("/addprovider")
    public String goAdd(){
        return "provideradd";
    }

    //跳转到查看页面
    @GetMapping("/provider/{uid}")
    public String providerView(@PathVariable String uid,Model model){
        model.addAttribute("provider",providerService.getProviderById(uid));
        return "providerview";
    }

    //跳转到修改页面
    @GetMapping("/providermodify/{uid}")
    public String goModify(@PathVariable String uid,Model model){
        model.addAttribute("provider",providerService.getProviderById(uid));
        return "providermodify";
    }

    //添加
    @PostMapping("/addprovider")
    public String addProvider(Provider provider, HttpSession session){
        if(provider!=null){
            provider.setCreatedBy(((User)session.getAttribute("userSession")).getId());
            provider.setCreationDate(new Date());
        }
        providerService.add(provider);
        return "redirect:/sys/providers";
    }

    //修改
    @PostMapping("providermodify")
    public String providerModify(Provider provider,HttpSession session){
        if(provider!=null){
            provider.setModifyBy(((User)session.getAttribute("userSession")).getId());
            provider.setModifyDate(new Date());
        }
        providerService.modify(provider);
        return "redirect:/sys/providers";
    }

    @DeleteMapping("/deleteprovider/{uid}")
    @ResponseBody
    public String deleteProvider(@PathVariable String uid){
        HashMap<String,Object> res = new HashMap<String,Object>();
        int count = billService.getBillCountByProviderId(uid);
        System.out.println("deleteProvider====>"+count);
        if(uid==null||uid.equals("")){
            res.put("delResult","notexist");
        }else {
            if(count>0){
                res.put("delResult",new Integer(count));
            }else{
                if(providerService.deleteProviderById(uid)>0){
                    res.put("delResult","true");
                }else {
                    res.put("delResult","false");
                }
            }
        }
        return JSON.toJSONString(res);
    }
}
