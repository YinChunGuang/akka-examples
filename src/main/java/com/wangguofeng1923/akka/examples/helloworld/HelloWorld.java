package com.wangguofeng1923.akka.examples.helloworld;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.actor.UntypedActor;
import scala.concurrent.Await;
import scala.concurrent.Awaitable;

public class HelloWorld {

	public static class HelloWorldActor extends UntypedActor{
		@Override
		public void onReceive(Object obj)  {
			System.out.println("hello, "+obj);
		}
	}
	

	
	public static void main(String[] args) {
		ActorSystem system=ActorSystem.create("helloworld-demo");
//		Inbox inbox=Inbox.create(system);
		
		ActorRef helloWorldActor=system.actorOf(Props.create(HelloWorldActor.class));
		helloWorldActor.tell("guofeng", ActorRef.noSender());

		
		system.shutdown();
//		Await.result(Awaitable<T>, arg1)
	}

}
