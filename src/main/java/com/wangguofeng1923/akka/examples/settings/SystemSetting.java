package com.wangguofeng1923.akka.examples.settings;

import akka.actor.ActorSystem;

public class SystemSetting {
public static void main(String[] args) {
	ActorSystem sys=ActorSystem.create();
	System.out.println(sys.settings());
	//System.out.println(sys.settings().config().root().render());
//	System.out.println(sys.settings().config().getAnyRef("akka"));
}
}
