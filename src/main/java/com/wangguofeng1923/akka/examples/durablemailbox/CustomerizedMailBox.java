package com.wangguofeng1923.akka.examples.durablemailbox;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class CustomerizedMailBox {
	private static final String str=""+/**~{*/""
	    + "my-dispatcher {"
	    + "\r\n  mailbox-type = com.wangguofeng1923.akka.examples.durablemailbox.CustomerizedMailboxType"
	    + "\r\n}"
	+ "\r\n"
	+ "\r\n"/**}*/;
	public static class MessageConsumerActor extends UntypedActor{
		LoggingAdapter log=Logging.getLogger(getContext().system(), this.getSelf());
		@Override
		public void onReceive(Object msg) throws Throwable {
			log.info("receive: "+msg);
			
		}
		
	}
	public static void main(String[] args) {
//		system.actorOf(Props.create(MessageConsumerActor.class).
//			      withDispatcher("my-dispatcher"), name = "myactor")
		ActorSystem system=ActorSystem.create("durablebox-demo", ConfigFactory.parseString(str));
		ActorRef actor=system.actorOf(Props.create(MessageConsumerActor.class).withDispatcher("my-dispatcher"),"myactor");
		
		Inbox inbox=Inbox.create(system);
		inbox.send(actor, "demo");
		system.awaitTermination();
	}
}
