package hello.advanced.trace.sterategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy){
        long startTime = System.currentTimeMillis();

        // business logic
        strategy.call();
        // business logic - end

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("Res Time : {}", resultTime);
    }


}
