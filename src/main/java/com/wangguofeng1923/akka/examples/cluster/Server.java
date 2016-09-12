package com.wangguofeng1923.akka.examples.cluster;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.metrics.ClusterMetricsExtension;
import akka.routing.BalancingPool;

public class Server {
public static void main(String[] args) {
	String conf=""+/**~{*/""
			+ "akka {"
			+ "\r\n	  actor {"
			+ "\r\n	    provider = \"akka.cluster.ClusterActorRefProvider\""
			+ "\r\n	  }"
			+ "\r\n	   remote {"
			+ "\r\n	    enabled-transports = [\"akka.remote.netty.tcp\"]"
			+ "\r\n	  }"
+ "\r\n"
			+ "\r\n	  remote {"
			+ "\r\n	    log-remote-lifecycle-events = off"
			+ "\r\n	    netty.tcp {"
			+ "\r\n	      hostname = \"127.0.0.1\""
			+ "\r\n	      port = 2552"
			+ "\r\n	    }"
			+ "\r\n	  }"
			+ "\r\n	"
			+ "\r\n	  cluster {"
			+ "\r\n	    seed-nodes = ["
			+ "\r\n	      \"akka.tcp://%s@127.0.0.1:2552\","
			+ "\r\n	      \"akka.tcp://%s@127.0.0.1:2551\""
			+ "\r\n	    ]"
			+ "\r\n	    auto-down-unreachable-after = 10s"
			+ "\r\n	  }"
			+ "\r\n	  log-dead-letters = off"
			+ "\r\n	  jvm-exit-on-fatal-error = on"
			+ "\r\n	  loglevel = \"DEBUG\""
			+ "\r\n	 "
			+ "\r\n	}"
			+ "\r\n	# Disable legacy metrics in akka-cluster."
			+ "\r\n	akka.cluster.metrics.enabled=off"
			+ "\r\n	"
			+ "\r\n	# Enable metrics extension in akka-cluster-metrics."
			+ "\r\n	akka.extensions=[\"akka.cluster.metrics.ClusterMetricsExtension\"]"
			+ "\r\n	"
			+ "\r\n	# Sigar native library extract location during tests."
			+ "\r\n	# Note: use per-jvm-instance folder when running multiple jvm on one host."
			+ "\r\n	#akka.cluster.metrics.native-library-extract-folder=${user.dir}/target/native"
			+ "\r\n	akka.cluster.metrics.native-library-extract-folder=/target/native"
			+ "\r\n"
			+ "\r\n"/**}*/;
		String clusterName="cluster";
		String configStr=String.format(conf, clusterName, clusterName);
		System.out.println(configStr);
		Config config=	ConfigFactory.parseString(configStr);
		ActorSystem system=ActorSystem.create(clusterName,config);
		
		
	
				
 
        
        ActorRef clusterManagerActor = system.actorOf(Props.create(ClusterManagerActor.class), "clusterController");
        ActorRef nodeActor = system.actorOf(new BalancingPool(5).props(Props.create(NodeActor.class)), "nodeActor");
        akka.actor.Extension extension=ClusterMetricsExtension.apply(system);


        ClusterMetricsExtension clusterExtension=(ClusterMetricsExtension)extension;
        clusterExtension.subscribe(clusterManagerActor);

//        clusterExtension.registerService(nodeActor);

        



        

	}
}
