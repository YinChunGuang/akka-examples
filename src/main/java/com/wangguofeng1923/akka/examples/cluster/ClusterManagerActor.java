package com.wangguofeng1923.akka.examples.cluster;

import akka.actor.UntypedActor;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import akka.cluster.ClusterEvent.MemberEvent;
import akka.cluster.ClusterEvent.UnreachableMember;

public class ClusterManagerActor extends UntypedActor {
	Cluster cluster=Cluster.get(getContext().system());
	
	@Override
	public void preStart() throws Exception {
		cluster.subscribe(getSelf(), ClusterEvent.initialStateAsEvents(),MemberEvent.class, UnreachableMember.class);
	};
    @Override
    public void postStop() {
        cluster.unsubscribe(self());
    }


	@Override
	public void onReceive(Object obj) throws Throwable {
		System.out.println("cluster event:"+obj);
		
	}

}
