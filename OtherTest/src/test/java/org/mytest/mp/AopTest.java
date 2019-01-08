package org.mytest.mp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mytest.mp.aop.biz.service.BizService;
import org.mytest.mp.aop.ods.service.OdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopTest {

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
