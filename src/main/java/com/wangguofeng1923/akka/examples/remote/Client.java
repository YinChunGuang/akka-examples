package com.wangguofeng1923.akka.examples.remote;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class Client {
	public static void main(String[] args) {
		String conf=""+/**~{*/""
			+ "akka {"
	  + "\r\n        actor {"
	  + "\r\n          provider = \"akka.remote.RemoteActorRefProvider\""
	  + "\r\n        }"
	  + "\r\n        remote {"
	  + "\r\n          ##enabled-transports = [\"akka.remote.netty.tcp\"]"
	  + "\r\n          netty.tcp {"
	  + "\r\n            hostname = \"127.0.0.1\""
	  + "\r\n            port = 2551"
	  + "\r\n          }"
	  + "\r\n        }"
	  + "\r\n      }"
		+ "\r\n"
		+ "\r\n"/**}*/;
		Config config=ConfigFactory.parseString(conf);
		
		ActorSystem system= ActorSystem.create("client", config);
		for(int i=0;i<10;i++){
			 system.actorSelection("akka.tcp://remote-demo@127.0.0.1:2552/user/RemoteActor").tell("hello", ActorRef.noSender());
					
		}
	
	}
}
