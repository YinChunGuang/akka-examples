package com.wangguofeng1923.akka.examples.cluster;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class Client {
	public static void main(String[] args) {
		String conf=""+/**~{*/""
				+ "akka {"
				+ "\r\n  actor {"
				+ "\r\n    provider = \"akka.cluster.ClusterActorRefProvider\""
				+ "\r\n  }"
				+ "\r\n  contrib.cluster.client {"
				+ "\r\n    mailbox {"
				+ "\r\n      mailbox-type = \"akka.dispatch.UnboundedDequeBasedMailbox\""
				+ "\r\n      stash-capacity = 1000"
				+ "\r\n    }"
				+ "\r\n  }"
				+ "\r\n}"
			+ "\r\n"
			+ "\r\n"/**}*/;
			Config config=ConfigFactory.parseString(conf);
			
			ActorSystem system= ActorSystem.create("client", config);
			for(int i=0;i<10;i++){
				 system.actorSelection("akka.tcp://cluster@127.0.0.1:2552/user/nodeActor").tell("hello", ActorRef.noSender());
						
			}
	}
}
