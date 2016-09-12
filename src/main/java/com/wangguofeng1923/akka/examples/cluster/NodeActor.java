package com.wangguofeng1923.akka.examples.cluster;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class NodeActor extends UntypedActor{
	LoggingAdapter log=	Logging.getLogger(getContext().system(), this);

	@Override
	public void onReceive(Object msg) {
		log.info("receive msg: "+msg);
		
	}

}
