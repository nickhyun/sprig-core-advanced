package hello.advanced.trace.sterategy.code.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {
    public void execute(Callback callback){
        long startTime = System.currentTimeMillis();

        // business logic
        callback.call();
        // business logic - end

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("Res Time : {}", resultTime);
    }
}
