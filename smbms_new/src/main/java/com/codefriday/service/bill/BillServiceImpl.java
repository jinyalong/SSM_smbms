package com.codefriday.service.bill;

import com.codefriday.dao.bill.BillMapper;
import com.codefriday.pojo.Bill;
import org.junit.Test;

import java.util.List;

public class BillServiceImpl implements BillService {
    private BillMapper billMapper;

    public void setBillMapper(BillMapper billMapper) {
        this.billMapper = billMapper;
    }

    @Override
    public boolean add(Bill bill) {
        int res = 0;
        try {
            res = billMapper.add(bill);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res>0;
    }

    @Override
    public List<Bill> getBillList(Bill bill) {
        List<Bill> billList = null;
        try {
            billList = billMapper.getBillList(bill.getProductName(),bill.getProviderId(),bill.getIsPayment());
        } catch (Exception e){
            e.printStackTrace();
        }
        return billList;
    }

    @Override
    public boolean deleteBillById(String delId) {
        int res = 0;
        try {
            res = billMapper.deleteBillById(delId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res>0;
    }

    @Override
    public Bill getBillById(String id) {
        Bill bill = null;
        try {
            bill = billMapper.getBillById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bill;
    }

    @Override
    public boolean modify(Bill bill) {
        int res = 0;
        try {
            res = billMapper.modify(bill);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res>0;
    }

    @Override
    public int getBillCountByProviderId(String providerId) {
        int res = 0;
        try {
            res = billMapper.getBillCountByProviderId(providerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

}
