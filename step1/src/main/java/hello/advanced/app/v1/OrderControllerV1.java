package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;

    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String order(String itemId){

        TraceStatus status = trace.begin("orderController.request()");

        try {
            orderService.orderItem(itemId);
            trace.end(status);
        }
        catch(Exception e ){
            trace.exception(status, e);
            throw e;
        }


        return "ok";
    }

}
