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
			+ "\r\n	    default-dispatcher {"
			+ "\r\n	      # Throughput for default Dispatcher, set to 1 for as fair as possible"
			+ "\r\n	      throughput = 10"
			+ "\r\n	    }"
			+ "\r\n	  }"
			+ "\r\n	   remote {"
			+ "\r\n	    enabled-transports = [\"akka.remote.netty.tcp\"]"
			+ "\r\n	  }"
+ "\r\n"
			+ "\r\n	  remote {"
			+ "\r\n	    log-remote-lifecycle-events = off"
			+ "\r\n	    ##如果不进行覆盖，则加载当前配置的ClusterActor会在端口(port=2551)上提供服务"
			+ "\r\n	    netty.tcp {"
			+ "\r\n	      hostname = \"127.0.0.1\""
			+ "\r\n	      port = 2551"
			+ "\r\n	    }"
			+ "\r\n	  }"
			+ "\r\n	  ##seed节点，用来作为cluster的初始化和加入点"
			+ "\r\n	  ###一开始能够预料的节点们被叫做种子节点（seed nodes），有节点加入的时候，会等种子节点的返回确认才算是加入成功。"
			+ "\r\n	  cluster {"
			+ "\r\n	    seed-nodes = ["
			+ "\r\n	      \"akka.tcp://%s@127.0.0.1:2552\","
			+ "\r\n	      \"akka.tcp://%s@127.0.0.1:2551\""
			+ "\r\n	    ]"
			+ "\r\n	    ##被失败检测出来不可达的节点，会被leader进行处理，也可以手动搞下来。"
			+ "\r\n	    auto-down-unreachable-after = 10s  ###10秒不可达就自动关"
			+ "\r\n	    ##也可以写代码 Cluster.get(system).down(address)"
			+ "\r\n	    ##网络分裂时，这个自动down有可能会出现脑裂。"
			+ "\r\n	  }"
			+ "\r\n	  log-dead-letters = off"
			+ "\r\n	  jvm-exit-on-fatal-error = on"
			+ "\r\n	  loglevel = \"INFO\""
			+ "\r\n	  #loggers = [\"akka.event.slf4j.Slf4jLogger\"]"
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
//        akka.actor.Extension extension=ClusterMetricsExtension.apply(system);
//
//
//        ClusterMetricsExtension clusterExtension=(ClusterMetricsExtension)extension;
//        clusterExtension.subscribe(clusterManagerActor);

        

	}
}
