package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    String port;

    String memoryLimit;

    String cfInstanceIndex;

    String cfInstanceAddr;

    Map<String, String> env = new HashMap<String, String>();

    public EnvController(@Value("${port}") String port,  @Value("${memory.limit}")
            String memoryLimit, @Value("${cf.instance.index}") String cfInstanceIndex, @Value("${cf.instance.addr}") String cfInstanceAddr) {
        this.port =port;
        this.memoryLimit = memoryLimit;
        this.cfInstanceAddr = cfInstanceAddr;
        this.cfInstanceIndex = cfInstanceIndex;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        env.put("PORT", port);
        env.put("MEMORY_LIMIT", memoryLimit);
        env.put("CF_INSTANCE_INDEX", cfInstanceIndex);
        env.put("CF_INSTANCE_ADDR", cfInstanceAddr);

        return env;
    }
}
