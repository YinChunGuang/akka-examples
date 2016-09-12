package com.wangguofeng1923.akka.examples.durablemailbox;

import com.typesafe.config.Config;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.ActorSystem.Settings;
import akka.actor.DynamicAccess;
import akka.dispatch.Envelope;
import akka.dispatch.Mailbox;
import akka.dispatch.MailboxType;
import akka.dispatch.Mailboxes;
import akka.dispatch.MessageQueue;
import akka.dispatch.sysmsg.SystemMessage;
import akka.event.EventStream;
import scala.Option;

public class CustomerizedMailboxType implements MailboxType{
	public CustomerizedMailboxType(ActorSystem.Settings settings,Config config){}
	@Override
	public MessageQueue create(Option<ActorRef> opt, Option<ActorSystem> system) {
		return new MyMessageQueue();
	}

 class MyMessageQueue implements MessageQueue{

	@Override
	public void cleanUp(ActorRef actorRef, MessageQueue queue) {
		
		
	}

	@Override
	public Envelope dequeue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enqueue(ActorRef arg0, Envelope arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasMessages() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int numberOfMessages() {
		// TODO Auto-generated method stub
		return 0;
	}}



}
