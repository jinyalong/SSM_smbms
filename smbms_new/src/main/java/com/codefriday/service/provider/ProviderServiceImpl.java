package com.codefriday.service.provider;

import com.codefriday.dao.provider.ProviderMapper;
import com.codefriday.pojo.Provider;

import java.util.List;

public class ProviderServiceImpl implements ProviderService{
    private ProviderMapper providerMapper;

    public void setProviderMapper(ProviderMapper providerMapper) {
        this.providerMapper = providerMapper;
    }

    @Override
    public boolean add(Provider provider) {
        int res = 0;
        try {
            res = providerMapper.add(provider);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res>0;
    }

    @Override
    public List<Provider> getProviderList(String proName, String proCode) {
        List<Provider> providerList = null;
        try {
            providerList = providerMapper.getProviderList(proName,proCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return providerList;
    }

    @Override
    public int deleteProviderById(String delId) {
        int res = 0;
        try {
            res = providerMapper.deleteProviderById(delId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Provider getProviderById(String id) {
        Provider provider = null;
        try {
            provider = providerMapper.getProviderById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return provider;
    }

    @Override
    public boolean modify(Provider provider) {
        int res = 0;
        try {
            res = providerMapper.modify(provider);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res>0;
    }
}
