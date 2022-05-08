package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace {

    private final String START_PREFIX = "-->";
    private final String COMPLETE_PREFIX = "<--";
    private final String EX_PREFIX = "<X-";

    //    private TraceId traceIdHolder;
    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

    @Override
    public TraceStatus begin(String message) {
        syncTraceId();

        TraceId tid = traceIdHolder.get();

        long startTime = System.currentTimeMillis();
        log.info("[{}] {}{}", tid.getId(), addSpace(START_PREFIX, tid.getLevel()), message);
        return new TraceStatus(tid, startTime, message);
    }

    private void syncTraceId() {
        TraceId tid = traceIdHolder.get();
        if (tid == null) {
            traceIdHolder.set(new TraceId());
        } else {
            traceIdHolder.set(tid.createNextId());
        }
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);

    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }


    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(),
                    addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(),
                    resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(),
                    addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs,
                    e.toString());
        }

        releaseTraceId();
    }

    private void releaseTraceId() {
        TraceId tid = traceIdHolder.get();
        if (tid.isFirstLevel()) {
            traceIdHolder.remove();
        } else {
            traceIdHolder.set(tid.createPrevId());
        }
    }
}
