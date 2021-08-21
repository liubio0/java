package io.github.kimmking.gateway.router;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class PolicyHttpEndpointRouter implements HttpEndpointRouter {

    private static AtomicInteger nextServerCyclicCounter = new AtomicInteger(0);

    @Override
    public String route(List<String> urls, int policy) {
        int size = urls.size();
        Random random = new Random(System.currentTimeMillis());
        switch (policy) {
            case 1:     // policy == 1   : RoundRibbon
                return urls.get(incrementAndGetModulo(size));
            case 2:     // policy == 2   : Weight
                if (random.nextInt(100) < 60) {
                    return urls.get(0);     // 60%
                } else {
                    return urls.get(1);     // 40%
                }
            default:    // policy == 3   : Random
                return urls.get(random.nextInt(size));
        }
    }

    private int incrementAndGetModulo(int modulo) {
        for (; ; ) {
            int current = nextServerCyclicCounter.get();
            int next = (current + 1) % modulo;
            if (nextServerCyclicCounter.compareAndSet(current, next)) {
                return next;
            }
        }
    }
}
