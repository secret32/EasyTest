package org.mytest.mp.aop.biz.service.impl;

import org.mytest.mp.aop.base.impl.BaseServiceImpl;
import org.mytest.mp.aop.biz.service.BizService;
import org.springframework.stereotype.Service;

@Service
public class BizServiceImpl extends BaseServiceImpl implements BizService {

    @Override
    public void other() {
        System.out.println("biz other");
    }

}
