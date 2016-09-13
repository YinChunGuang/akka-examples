package com.wangguofeng1923.akka.examples.dispatcher;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ForkJoinExecutor {
private static final String str=""+/**~{*/""
			+ "my-dispatcher {"
			+ "\r\n # Dispatcher is the name of the event-based dispatcher"
			+ "\r\n type = Dispatcher"
			+ "\r\n # What kind of ExecutionService to use"
			+ "\r\n executor = \"fork-join-executor\""
			+ "\r\n # Configuration forthe forkjoinpool"
			+ "\r\n fork-join-executor {"
			+ "\r\n # Minnumberofthreadstocap factor-based parallelismnumberto"
			+ "\r\n parallelism-min=2"
			+ "\r\n # Parallelism (threads) ... ceil(available processors * factor)"
			+ "\r\n parallelism-factor = 2.0"
			+ "\r\n # Maxnumberofthreadstocap factor-based parallelismnumberto"
			+ "\r\n parallelism-max=10"
			+ "\r\n }"
			+ "\r\n # Throughput defines the maximum numberofmessagestobe"
			+ "\r\n # processed per actor beforethe thread jumpstothenextactor."
			+ "\r\n # Setto1forasfairaspossible."
			+ "\r\n throughput = 100"
			+ "\r\n}"
+ "\r\n"/**}*/;
public static void main(String[] args) {
	Config config=	ConfigFactory.parseString(str);
	ActorSystem system = ActorSystem.create("myActorSystem",config);
    ActorRef myActor = system.actorOf(Props.create(MyActor.class).
            withDispatcher("my-dispatcher"), "myactor");
}
}
