package hello.advanced.trace.sterategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class StrategyV1Test {

    @Test
    void strategyV0() {
        logic1();
        logic2();
    }


    private void logic1() {
        long startTime = System.currentTimeMillis();

        // business logic
        log.info("business logic start 1");
        // business logic - end

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("Res Time : {}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();

        // business logic
        log.info("business logic start 2");
        // business logic - end

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("Res Time : {}", resultTime);
    }

}
