package com.codefriday.dao.provider;

import com.codefriday.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;
import java.util.List;

public interface ProviderMapper {
    //增加供应商
    public int add(Provider provider)throws Exception;
    //获得供应商列表
    public List<Provider> getProviderList(@Param("proName") String proName, @Param("proCode") String proCode)throws Exception;
    //删除订单通过id
    public int deleteProviderById(@Param("delId") String delId)throws Exception;
    //通过id获得单个订单
    public Provider getProviderById(@Param("id") String id)throws Exception;
    //修改订单
    public int modify(Provider provider)throws Exception;
}
