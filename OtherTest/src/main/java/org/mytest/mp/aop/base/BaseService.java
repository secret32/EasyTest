package org.mytest.mp.aop.base;

public interface BaseService {

    default public void baseFun() {
        baseFun("base default");
    }

    public void baseFun(String param);

}
