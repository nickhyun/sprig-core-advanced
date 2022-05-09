package hello.advanced.trace.sterategy.code;

import hello.advanced.trace.sterategy.code.strategy.ContextV2;
import hello.advanced.trace.sterategy.code.strategy.Strategy;
import hello.advanced.trace.sterategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.sterategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @Test
    void strategyV1(){
        ContextV2 context1 = new ContextV2();
        context1.execute(new StrategyLogic1());
        context1.execute(new StrategyLogic2());
    }

    @Test
    void strategyV2(){
        ContextV2 context1 = new ContextV2();
        context1.execute(new Strategy() {
            @Override
            public void call() {
                log.info("business 1");

            }
        });
        context1.execute(new Strategy() {
            @Override
            public void call() {
                log.info("business 2");

            }
        });
    }

    @Test
    void strategyV3(){
        ContextV2 context1 = new ContextV2();
        context1.execute(() -> log.info("business 1"));
        context1.execute(() -> log.info("business 2"));
    }
}
