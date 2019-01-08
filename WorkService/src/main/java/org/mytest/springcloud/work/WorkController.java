package org.mytest.springcloud.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pc
 */
@RestController
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private IWorkService workService;

    @GetMapping("/code/{name}")
    public String code(@PathVariable String name) {
        return workService.punchClock(name);
    }

}
