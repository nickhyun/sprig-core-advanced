package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CallServiceV0 {

    public void external() {
        log.info("external()");
        internal();

    }

    public void internal() {
        log.info("internal()");

    }

}
