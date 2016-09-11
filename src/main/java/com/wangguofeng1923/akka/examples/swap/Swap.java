package com.wangguofeng1923.akka.examples.swap;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

public class Swap {
	public static class Swapper extends UntypedActor{
		 LoggingAdapter log =Logging.getLogger(getContext().system(), this);
		@Override
		public void onReceive(Object message) throws Throwable {
			
			if("SWAP".equals(String.valueOf(message))){
				log.info("HI");
				this.getContext().become(new Procedure<Object>() {
					public void apply(Object msg) throws Exception {
						log.info("HO");
						getContext().unbecome();
						
					}
				});
			}else{
				unhandled(message);
			}
		}
		
	}
	public static void main(String[] args) {
		ActorSystem actorSystem=ActorSystem.create("swap-demo");
//		Inbox inbox=Inbox.create(actorSystem);
		ActorRef swapperActor=actorSystem.actorOf(Props.create(Swapper.class));
		swapperActor.tell("SWAP", ActorRef.noSender());
		swapperActor.tell("SWAP", ActorRef.noSender());
		swapperActor.tell("SWAP", ActorRef.noSender());
		swapperActor.tell("SWAP", ActorRef.noSender());
		
	}

}
