package hello.advanced.trace.sterategy.code.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class TimeLogTemplateTest {

    @Test
    void callbackV1() {
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스 1");
            }
        });
        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스 2");
            }
        });
    }

    @Test
    void callbackV2() {
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(() -> log.info("비즈니스 1"));
        template.execute(() -> log.info("비즈니스 2"));
    }

}