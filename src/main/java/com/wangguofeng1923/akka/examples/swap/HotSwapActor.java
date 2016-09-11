package com.wangguofeng1923.akka.examples.swap;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Procedure;

public class HotSwapActor extends UntypedActor {

	Procedure<Object> angry = new Procedure<Object>() {
		@Override
		public void apply(Object message) {
			if (message.equals("bar")) {
				getSender().tell("I am already angry?", getSelf());
			} else if (message.equals("foo")) {
				getContext().become(happy);
			}else {
				unhandled(message);
			}
		}
	};

	Procedure<Object> happy = new Procedure<Object>() {
		@Override
		public void apply(Object message) {
			if (message.equals("foo")) {
				getSender().tell("I am already happy :-)", getSelf());
			} else if (message.equals("foo")) {
				getContext().become(angry);
			}else {
				unhandled(message);
			}
		}

	};

	public void onReceive(Object message) {
		if (message.equals("bar")) {
			getContext().become(angry);
		} else if (message.equals("foo")) {
			getContext().become(happy);
		} else {
			unhandled(message);
		}
	}
	private static class SenderActor extends UntypedActor{

		@Override
		public void onReceive(Object msg) throws Throwable {
			System.out.println("msg:"+msg);
			
		}
		
	}
	public static void main(String[] args) {
		ActorSystem system=ActorSystem.create("hotswap-demo");
		ActorRef hotSwapActor=system.actorOf(Props.create(HotSwapActor.class));
		ActorRef senderActor=system.actorOf(Props.create(SenderActor.class));
		hotSwapActor.tell("bar", senderActor);
		hotSwapActor.tell("bar", senderActor);
		hotSwapActor.tell("bar", senderActor);
		
		hotSwapActor.tell("foo", senderActor);
		hotSwapActor.tell("foo", senderActor);
		hotSwapActor.tell("foo", senderActor);
	}
}