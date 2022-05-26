package hello.advanced.trace.threadlocal.code;

import hello.advanced.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {

    private ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(String name) {
        log.info("save name = {} -> nameStore = {} ", name, nameStore.get());
        nameStore.set(name);
        SleepUtils.sleep(1000);
        log.info("query nameStore={}", nameStore.get());
        return nameStore.get();
    }


}
