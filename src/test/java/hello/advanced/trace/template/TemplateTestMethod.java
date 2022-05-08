package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateTestMethod {

    @Test
    void templateMethodV0(){
        logic1();
        logic2();
    }


    @Test
    void templateMethodV1(){
        AbstractTemplate logic1 = new SubClassLogic1();
        AbstractTemplate logic2 = new SubClassLogic2();
        logic1.execute();
        logic2.execute();
    }


    private void logic1(){
        long startTime = System.currentTimeMillis();

        // business logic
        log.info("business logic start");
        // business logic - end

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("Res Time : {}", resultTime);
    }

    private void logic2(){
        long startTime = System.currentTimeMillis();

        // business logic
        log.info("business logic start");
        // business logic - end

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("Res Time : {}", resultTime);
    }

}
