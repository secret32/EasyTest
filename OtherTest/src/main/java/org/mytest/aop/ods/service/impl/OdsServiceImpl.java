package org.mytest.aop.ods.service.impl;

import org.mytest.aop.ods.service.OdsService;
import org.mytest.aop.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OdsServiceImpl extends BaseServiceImpl implements OdsService {

    @Override
    public void other() {
        System.out.println("ods other");
    }

}
