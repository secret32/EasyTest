package org.mytest.mp.aop.biz.service;

import org.mytest.mp.aop.base.BaseService;

public interface BizService extends BaseService {

    default public void biz() {
        System.out.println("biz default");
    }

    public void other();

}
