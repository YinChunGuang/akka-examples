package com.wangguofeng1923.akka.examples.nosender;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.wangguofeng1923.akka.examples.inbox.InboxDemo.MessagePrinterActor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.actor.UntypedActor;
import scala.concurrent.duration.FiniteDuration;

public class NoSenderDemo {
	public static class MessagePrinterActor extends UntypedActor{
		@Override
		public void onReceive(Object obj)  {
			System.out.println("hello, "+obj);
			System.out.println(this.getSender());
			this.getSender().tell("welcome,"+String.valueOf(obj), this.getSelf());
		}
	}
	public static void main(String[] args) throws TimeoutException {
		ActorSystem system=ActorSystem.create("helloworld-demo");
		
		ActorRef messagePrinterActor=system.actorOf(Props.create(MessagePrinterActor.class));
	


		messagePrinterActor.tell("guofeng", ActorRef.noSender());
		
		messagePrinterActor.tell("wangguofeng", null);
//		messagePrinterActor.tell("wangguofeng", ActorRef.noSender());
		system.shutdown();
//		Await.result(Awaitable<T>, arg1)
	}

}
