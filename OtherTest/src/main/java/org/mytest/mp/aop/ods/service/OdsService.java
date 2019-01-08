package org.mytest.mp.aop.ods.service;

import org.mytest.mp.aop.base.BaseService;

public interface OdsService extends BaseService {

    default public void ods() {
        System.out.println("ods default");
    }

    public void other();

}
