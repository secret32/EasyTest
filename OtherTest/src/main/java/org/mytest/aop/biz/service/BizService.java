package org.mytest.aop.biz.service;

import org.mytest.aop.base.BaseService;

public interface BizService extends BaseService {

    default public void biz() {
        System.out.println("biz default");
    }

    public void other();

}
