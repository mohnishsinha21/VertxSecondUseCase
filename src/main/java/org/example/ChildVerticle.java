package org.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class ChildVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        System.out.println("Child Verticle start at thread : " + Thread.currentThread().getName());
        CoapServer server = new CoapServer(5686);
        server.add(new CoapResource("signin") {
            @Override
            public void handleGET(CoapExchange exchange) {
                exchange.respond("Signin request received! from thread : "+ Thread.currentThread().getName());
            }
        });

        server.add(new CoapResource("ping") {
            @Override
            public void handleGET(CoapExchange exchange) {
                exchange.respond("Pong! from thread : " + Thread.currentThread().getName());
            }
        });

        server.add(new CoapResource("signout") {
            @Override
            public void handleGET(CoapExchange exchange) {
                exchange.respond("Signed out successfully! from thread : "+ Thread.currentThread().getName());
            }
        });

        System.out.println("Server started");
        server.start();
        startPromise.complete();
    }
}
