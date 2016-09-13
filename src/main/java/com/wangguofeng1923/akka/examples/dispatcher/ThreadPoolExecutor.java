package com.wangguofeng1923.akka.examples.dispatcher;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

public class ThreadPoolExecutor {
private static final String str=""+/**~{*/""
		+ "my-thread-pool-dispatcher {"
		+ "\r\n	 # Dispatcher is the name of the event-based dispatcher"
		+ "\r\n	 type = Dispatcher"
		+ "\r\n	 # What kind of ExecutionService to use"
		+ "\r\n	 executor = \"thread-pool-executor\""
		+ "\r\n	 # Configuration forthe thread pool"
		+ "\r\n	 thread-pool-executor {"
		+ "\r\n	 # minimum numberofthreadstocap factor-based corenumberto"
		+ "\r\n	 core-pool-size-min=2"
		+ "\r\n	 # Noofcore threads ...ceil(available processors * factor)"
		+ "\r\n	 core-pool-size-factor =2.0"
		+ "\r\n	 # maximum numberofthreadstocap factor-basednumberto"
		+ "\r\n	 core-pool-size-max=10"
		+ "\r\n	 }"
		+ "\r\n	 # Throughput defines the maximum numberofmessagestobe"
		+ "\r\n	 # processed per actor beforethe thread jumpstothenextactor."
		+ "\r\n	 # Setto1forasfairaspossible."
		+ "\r\n	 throughput = 100"
		+ "\r\n	}"
		+ "\r\n	"
		+ "\r\n	akka {"
		+ "\r\n	  loglevel = debug"
		+ "\r\n	}"
		+ "\r\n	"
	+ "\r\n#		akka.actor.deployment {"
	+ "\r\n#		 /myactor {"
	+ "\r\n#		 dispatcher =my-dispatcher"
	+ "\r\n#		 }"
	+ "\r\n#		}"
+ "\r\n"/**}*/;
public static void main(String[] args) throws Exception {
	Config config=	ConfigFactory.parseString(str);
	ActorSystem system = ActorSystem.create("myActorSystem",config);
    ActorRef myActor = system.actorOf(Props.create(MyActor.class).
            withDispatcher("my-thread-pool-dispatcher"), "myactor");
    
    
    Timeout timeout = new Timeout(Duration.create(5, "seconds"));
    Future<Object> future = Patterns.ask(myActor, "are you ready?", timeout);

    // This will cause the current thread to block and wait for the UntypedActor to ‘complete’
    // the Future with it’s reply.
    // 在这里会阻塞到 Await.result 方法上，但这会导致性能的损失。
    Integer result = (Integer) Await.result(future, timeout.duration());
    System.out.println(result);
}
}
