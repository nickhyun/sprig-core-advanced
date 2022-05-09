package hello.advanced.trace.sterategy.code;

import hello.advanced.trace.sterategy.code.strategy.ContextV1;
import hello.advanced.trace.sterategy.code.strategy.Strategy;
import hello.advanced.trace.sterategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.sterategy.code.strategy.StrategyLogic2;
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

    @Test
    void strategyV1() {
        StrategyLogic1 logic1 = new StrategyLogic1();
        ContextV1 ctxV1 = new ContextV1(logic1);
        ctxV1.execute();

        StrategyLogic2 logic2 = new StrategyLogic2();
        ContextV1 ctxV2 = new ContextV1(logic2);
        ctxV2.execute();
    }

    @Test
    void strategyV2() {
        Strategy logic1 = new Strategy(){

            @Override
            public void call() {
                log.info("business logic start");
            }
        };
        ContextV1 ctxV1 = new ContextV1(logic1);
        ctxV1.execute();

        Strategy logic2 = new Strategy(){

            @Override
            public void call() {
                log.info("business logic start 2");
            }
        };
        ContextV1 ctxV2 = new ContextV1(logic2);
        ctxV2.execute();

    }

    @Test
    void strategyV3() {
        ContextV1 ctxV1 = new ContextV1(new Strategy(){

            @Override
            public void call() {
                log.info("business logic start");
            }
        });
        ctxV1.execute();

        ContextV1 ctxV2 = new ContextV1(new Strategy(){

            @Override
            public void call() {
                log.info("business logic start 2");
            }
        });
        ctxV2.execute();

    }

    @Test
    void strategyV4() {
        ContextV1 ctxV1 = new ContextV1(() -> log.info("business logic start"));
        ctxV1.execute();

        ContextV1 ctxV2 = new ContextV1(() -> log.info("business logic start 2"));
        ctxV2.execute();

    }

}
