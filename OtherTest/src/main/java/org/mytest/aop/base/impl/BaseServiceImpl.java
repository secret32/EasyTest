package org.mytest.aop.base.impl;

import org.mytest.aop.base.BaseService;

public class BaseServiceImpl implements BaseService {
    @Override
    public void baseFun(String param) {
        System.out.println(getClass().getName() + "..... param: " + param);
    }
}
