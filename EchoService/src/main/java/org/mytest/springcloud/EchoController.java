package org.mytest.springcloud;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pc
 */
@Slf4j
@RestController
@RequestMapping("/echo")
public class EchoController {

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name", name);
        jsonObj.put("time", System.currentTimeMillis());
        return jsonObj.toJSONString();
    }

}
