package org.mytest.springcloud.work;

import org.springframework.stereotype.Service;

@Service
public class WorkFallbackServiceImpl implements IWorkService {
    @Override
    public String punchClock(String name) {
        return "fallback";
    }
}
