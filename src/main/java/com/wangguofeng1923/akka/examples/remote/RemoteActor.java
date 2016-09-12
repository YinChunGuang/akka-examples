package com.wangguofeng1923.akka.examples.remote;

import java.util.HashMap;
import java.util.Map;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.dispatch.CachingConfig;
import akka.util.Timeout;
import scala.concurrent.duration.Duration;

public class RemoteActor extends UntypedActor{
	
	@Override
	public void preStart() throws Exception {
	
		
		super.preStart();
		System.out.println(this.getSelf());
	}
	@Override
	public void onReceive(Object message) throws Throwable {
			System.out.println(message);
		
	}
	public static void main(String[] args)  {
		String conf=""+/**~{*/""
				+ "akka {"
				+ "\r\n  actor {"
				+ "\r\n    provider = \"akka.remote.RemoteActorRefProvider\""
				+ "\r\n  }"
				+ "\r\n  remote {"
				+ "\r\n    netty.tcp {"
				+ "\r\n      hostname = \"127.0.0.1\""
				+ "\r\n      port = 2552"
				+ "\r\n    }"
				+ "\r\n  }"
				+ "\r\n}"
		+ "\r\n"/**}*/;
		Config config=ConfigFactory.parseString(conf);
		
		ActorSystem system= ActorSystem.create("remote-demo", config);
		 //不使用默认的配置，而是选择加载选定的remote actor配置


        //remote actor的ref
        final ActorRef remoteActor = system.actorOf(Props.create(RemoteActor.class), "RemoteActor");

        System.out.println("Started RemoteActor");

        final Timeout timeout = new Timeout(Duration.create(5, "seconds"));
     
	}
     


}
