package com.wangguofeng1923.akka.examples.reply;

import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.pattern.Patterns;
import scala.concurrent.duration.Duration;

public class Reply {
	public static class ReceiverActor extends UntypedActor{
//		LoggingAdapter logger=Logging.getLogger(ReceiverActor.class);
		 LoggingAdapter log = Logging.getLogger(getContext().system(), this);
		@Override
		public void preStart() throws Exception {
			super.preStart();
			log.info(this.getSelf().toString());
		}
		@Override
		public void onReceive(Object obj)  {
			log.info("receive message:"+obj+" from: "+this.getSender().toString());
			if("PING".equalsIgnoreCase(String.valueOf(obj))){
				String replyMessage="PONG";
				log.info("will reply message: "+replyMessage);
				ActorRef sender=this.getSender();
				ActorRef self=this.getSelf();
				sender.tell(replyMessage, self);
			}else
			{
				 unhandled(obj);
			}
			     

		
		}
	}
	
	public static class SenderActor extends UntypedActor{
		 LoggingAdapter log = Logging.getLogger(getContext().system(), this);
		@Override
		public void preStart() throws Exception {
			super.preStart();
			log.info(this.getSelf().toString());
//			ActorRef receiver=this.getContext().actorOf(Props.create(ReceiverActor.class));
//			receiver.tell("i'm sender", this.getSelf());
		}

		@Override
		public void onReceive(Object obj)  {
			log.info("receive message:"+obj+" from: "+this.getSender().toString());
			
		}
	}
	public static void main(String[] args) {
		ActorSystem system=ActorSystem.create("reply-demo");
		ActorRef senderActor=system.actorOf(Props.create(SenderActor.class));
		ActorRef receiverActor=system.actorOf(Props.create(ReceiverActor.class));
		receiverActor.tell("PING", senderActor);
		system.shutdown();
	}
}
