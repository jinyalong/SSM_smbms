package com.codefriday.dao.bill;

import com.codefriday.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;
import java.util.List;

public interface BillMapper {
    //增加订单
    public int add(Bill bill)throws Exception;
    //获得订单列表
    public List<Bill> getBillList(@Param("productName") String productName,@Param("providerId") Integer providerId,@Param("isPayment") Integer isPayment)throws Exception;
    //删除订单
    public int deleteBillById(@Param("delId") String delId)throws Exception;
    //获得单个订单
    public Bill getBillById(@Param("id") String id)throws Exception;
    //修改订单
    public int modify(Bill bill)throws Exception;
    //供应商订单数
    public int getBillCountByProviderId(@Param("providerId") String providerId)throws Exception;
}
