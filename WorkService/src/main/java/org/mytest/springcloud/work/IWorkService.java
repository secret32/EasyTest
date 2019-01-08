package org.mytest.springcloud.work;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author pc
 */
@FeignClient(name = "EchoServer")
public interface IWorkService {

    /**
     * 打卡
     * @param name 姓名
     * @return 打卡问候语
     */
    @GetMapping("/echo/hello/{name}")
    public String punchClock(@PathVariable(value = "name") String name);

}
