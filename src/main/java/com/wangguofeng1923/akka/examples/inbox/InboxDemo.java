package com.wangguofeng1923.akka.examples.inbox;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.actor.UntypedActor;
import scala.concurrent.Await;
import scala.concurrent.Awaitable;
import scala.concurrent.duration.FiniteDuration;

public class InboxDemo {

	public static class MessagePrinterActor extends UntypedActor{
		@Override
		public void onReceive(Object obj)  {
			System.out.println("hello, "+obj);
			this.getSender().tell("welcome,"+String.valueOf(obj), this.getSelf());
		}
	}
	

	
	public static void main(String[] args) throws TimeoutException {
		ActorSystem system=ActorSystem.create("helloworld-demo");
		Inbox inbox=Inbox.create(system);
		
		ActorRef messagePrinterActor=system.actorOf(Props.create(MessagePrinterActor.class));
	
		inbox.send(messagePrinterActor, "guofeng");
		Object msg=inbox.receive(FiniteDuration.create(5, TimeUnit.SECONDS));
		System.out.println("receive: "+msg);
		

		messagePrinterActor.tell("wangguofeng", inbox.getRef());
		Object obj=inbox.receive(FiniteDuration.create(5, TimeUnit.SECONDS));
		System.out.println("receive: "+obj);
//		messagePrinterActor.tell("wangguofeng", ActorRef.noSender());
		system.shutdown();
//		Await.result(Awaitable<T>, arg1)
	}

}
