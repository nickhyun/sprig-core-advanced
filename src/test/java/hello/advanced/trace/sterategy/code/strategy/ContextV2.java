package hello.advanced.trace.sterategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV1 {

    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute(){
        long startTime = System.currentTimeMillis();

        // business logic
        strategy.call();
        // business logic - end

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("Res Time : {}", resultTime);
    }


}
