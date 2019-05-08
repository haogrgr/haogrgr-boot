package com.haogrgr.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * description
 *
 * @author shengde.tds
 * @since 2019-05-08 14:07
 */
@Controller
public class SseController {

    private static AtomicInteger inc = new AtomicInteger(0);
    private static List<SseEmitter> connections = new CopyOnWriteArrayList<>();

    @GetMapping("/sse/connect")
    public SseEmitter connect(@RequestParam String uid) {
        System.out.println("connect emitter " + uid + " " + connections.size());

        SseEmitter emitter = new SseEmitter(0L);
        connections.add(emitter);
        emitter.onCompletion(new RemoveConnectionHandler(emitter));
        emitter.onTimeout(new RemoveConnectionHandler(emitter));
        emitter.onError(new RemoveConnectionHandler(emitter));

        return emitter;
    }

    @ResponseBody
    @GetMapping("/sse/push")
    public String push(@RequestParam String event) throws Exception {
        System.out.println("push emitter " + event + " " + connections.size());

        if (StringUtils.isEmpty(event)) {
            event = "null";
        }

        String msg = "event : " + event + " " + inc.getAndIncrement();
        for (SseEmitter e : connections) {
            try {
                e.send(msg);
            } catch (Exception exp) {
                System.out.println("push error : " + exp.getMessage());
            }
        }

        return "send success : " + msg;
    }

    public static class RemoveConnectionHandler implements Runnable, Consumer<Throwable> {

        private SseEmitter emitter;

        public RemoveConnectionHandler(SseEmitter emitter) {
            this.emitter = emitter;
        }

        @Override
        public void run() {
            System.out.println("remove emitter !");
            connections.remove(emitter);
        }

        @Override
        public void accept(Throwable throwable) {
            System.out.println("remove emitter with error : " + throwable.getMessage());
            connections.remove(emitter);
        }

    }

}
