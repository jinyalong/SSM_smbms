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
public class BillController {
    @Autowired
    @Qualifier("BillServiceImpl")
    BillService billService;
    @Autowired
    @Qualifier("ProviderServiceImpl")
    ProviderService providerService;

    //get post请求
    @RequestMapping("/bills")
    public String getBills(String queryProductName, Integer queryProviderId, Integer queryIsPayment, Model model){
        Bill bill = new Bill();
        if(queryProductName!=null&&!queryProductName.equals("")) bill.setProductName(queryProductName);
        if(queryProviderId!=null&&queryProviderId>0){
            bill.setProviderId(queryProviderId);
        }else{
            bill.setProviderId(0);
        }
        if(queryIsPayment!=null&&queryIsPayment>0){
            bill.setIsPayment(queryIsPayment);
        }else
            bill.setIsPayment(0);
        List<Bill> billList = billService.getBillList(bill);
        List<Provider> providerList = providerService.getProviderList(null, null);
        model.addAttribute("billList",billList);
        model.addAttribute("providerList",providerList);
        model.addAttribute("queryProductName",queryProductName);
        model.addAttribute("queryProviderId",queryProviderId);
        model.addAttribute("queryIsPayment",queryIsPayment);
        return "billlist";
    }

    //跳转到添加页面
    @GetMapping("addbill")
    public String goAdd(){
        return "billadd";
    }

    //添加页面的异步请求获得供应商
    @GetMapping("/getproviders")
    @ResponseBody
    public List<Provider> getProviders(){
        return providerService.getProviderList(null,null);
    }

    //跳转到视图界面
    @GetMapping("/bill/{uid}")
    public String billView(@PathVariable String uid,Model model){
        model.addAttribute("bill",billService.getBillById(uid));
        return "billview";
    }

    //条跳转到修改界面
    @GetMapping("/billmodify/{uid}")
    public String billModify(@PathVariable String uid,Model model){
        model.addAttribute("bill",billService.getBillById(uid));
        return "billmodify";
    }

    //删除
    @DeleteMapping("/billdelete/{uid}")
    @ResponseBody
    public String billDelete(@PathVariable String uid){
        HashMap<String,String> res = new HashMap<String,String>();
        boolean flag = billService.deleteBillById(uid);
        if(uid==null||uid.equals("")){
            res.put("delResult","notexist");
        }else {
            if(flag){
                res.put("delResult","true");
            }else{
                res.put("delResult","false");
            }
        }
        return JSON.toJSONString(res);
    }

    //添加
    @PostMapping("/addbill")
    public String addBill(Bill bill,HttpSession session){
        if(bill!=null){
            bill.setCreatedBy(((User)session.getAttribute("userSession")).getId());
            bill.setCreationDate(new Date());
        }
        boolean flag = billService.add(bill);
        return "redirect:/sys/bills";
    }

    //修改
    @PostMapping("/billmodify")
    public String modifyBill(Bill bill,HttpSession session){
        if(bill!=null){
            bill.setModifyBy(((User)session.getAttribute("userSession")).getId());
            bill.setModifyDate(new Date());
        }
        billService.modify(bill);
        return "redirect:/sys/bills";
    }
}
