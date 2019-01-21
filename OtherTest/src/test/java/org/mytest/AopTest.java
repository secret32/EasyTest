package org.mytest;

import org.junit.Test;
import org.mytest.aop.biz.service.BizService;
import org.mytest.aop.ods.service.OdsService;
import org.springframework.beans.factory.annotation.Autowired;

public class AopTest extends SpringBootBaseTest {

    @Autowired
    private BizService bizService;
    @Autowired
    private OdsService odsService;

    @Test
    public void test() {
        bizService.biz();
        odsService.ods();
        bizService.other();
        odsService.other();

        bizService.baseFun();
        bizService.baseFun("test");
        odsService.baseFun();
        odsService.baseFun("test");
    }

}
