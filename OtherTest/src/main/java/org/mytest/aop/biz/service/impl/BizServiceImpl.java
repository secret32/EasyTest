package org.mytest.aop.biz.service.impl;

import org.mytest.aop.base.impl.BaseServiceImpl;
import org.mytest.aop.biz.service.BizService;
import org.springframework.stereotype.Service;

@Service
public class BizServiceImpl extends BaseServiceImpl implements BizService {

    @Override
    public void other() {
        System.out.println("biz other");
    }

}
