package com.wangguofeng1923.akka.examples.mailbox;

import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.CircuitBreaker;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.compat.java8.FutureConverters;
import scala.concurrent.duration.FiniteDuration;
import scala.concurrent.impl.Future;

public class Main {
public static void main(String[] args) {
    ActorSystem system = ActorSystem.create("MailBox-demo");
    ActorRef pingActorWithMailbox = system.actorOf(Props.create(PingActor.class).withMailbox("akka.actor.boundedmailbox"), "pingActor");
    ActorRef pongActor = system.actorOf(Props.create(PongActor.class).withMailbox("akka.actor.boundedmailbox"), "pingActor2");
    CircuitBreaker breaker =
            new CircuitBreaker(system.scheduler(),
                    1,
                    FiniteDuration.create(1, "second"),
                    FiniteDuration.create(1, "second"),
                    system.dispatcher()).
                    onOpen(() -> {
                        System.out.println("circuit breaker opened!");
                    }).
                    onClose(() -> {
                        System.out.println("circuit breaker opened!");
                    }).
                    onHalfOpen(() -> {
                        System.out.println("circuit breaker opened!");
                    });

    Timeout timeout = Timeout.apply(2, TimeUnit.SECONDS);
    breaker.call
    Future future1 = breaker.callWithCircuitBreaker(()
            -> Patterns.ask(pongActor, new PingActor.PingMessage("ping"), timeout));

    Future future2 = breaker.callWithCircuitBreaker(()
            -> Patterns.ask(pongActor, new PingActor.PingMessage("ping"), timeout));


    FutureConverters.toJava(future1).handle((x,t) -> {
        if(t != null){
            System.out.println("got it: " + x);
        }else{
            System.out.println("error: " + t.toString());
        }
        return null;
    });

    FutureConverters.toJava(future2).handle((x,t) -> {
        if(t != null){
            System.out.println("got it: " + x);
        }else{
            System.out.println("error: " + t.toString());
        }
        return null;
    });

    //play around with sending futures and see how the breaker responds

    system.awaitTermination();
}
}
