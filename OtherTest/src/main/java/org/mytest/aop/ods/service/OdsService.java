package org.mytest.aop.ods.service;

import org.mytest.aop.base.BaseService;

public interface OdsService extends BaseService {

    default public void ods() {
        System.out.println("ods default");
    }

    public void other();

}
