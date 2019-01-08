package org.mytest.mp.aop.ods.service.impl;

import org.mytest.mp.aop.base.impl.BaseServiceImpl;
import org.mytest.mp.aop.ods.service.OdsService;
import org.springframework.stereotype.Service;

@Service
public class OdsServiceImpl extends BaseServiceImpl implements OdsService {

    @Override
    public void other() {
        System.out.println("ods other");
    }

}
