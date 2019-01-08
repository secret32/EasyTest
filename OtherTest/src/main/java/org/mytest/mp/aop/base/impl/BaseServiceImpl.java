package org.mytest.mp.aop.base.impl;

import org.mytest.mp.aop.base.BaseService;

public class BaseServiceImpl implements BaseService {
    @Override
    public void baseFun(String param) {
        System.out.println(getClass().getName() + "..... param: " + param);
    }
}
