package com.wangguofeng1923.akka.examples.durablemailbox;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class FileBasedMailBox {
	private static final String str=""+/**~{*/""
	+ ""
	    + "\r\n#############################################"
    + "\r\n# Akka File Mailboxes Reference Config File #"
    + "\r\n#############################################"
     + "\r\n"
    + "\r\n# This is the reference config file that contains all the default settings."
    + "\r\n# Make your edits/overrides in your application.conf."
    + "\r\n#"
    + "\r\n# For more information see <https://github.com/robey/kestrel/>"
     + "\r\n"
    + "\r\nakka {"
     + "\r\n actor {"
     + "\r\n   mailbox {"
     + "\r\n     file-based {"
     + "\r\n       # directory below which this queue resides"
     + "\r\n       directory-path = \"./_mb\""
     + "\r\n"
     + "\r\n       # attempting to add an item after the queue reaches this size (in items)"
     + "\r\n       # will fail."
     + "\r\n       max-items = 2147483647"
     + "\r\n"
     + "\r\n       # attempting to add an item after the queue reaches this size (in bytes)"
     + "\r\n       # will fail."
     + "\r\n       max-size = 2147483647 bytes"
     + "\r\n"
     + "\r\n       # attempting to add an item larger than this size (in bytes) will fail."
     + "\r\n       max-item-size = 2147483647 bytes"
     + "\r\n"
     + "\r\n       # maximum expiration time for this queue (seconds)."
     + "\r\n       max-age = 0s"
     + "\r\n"
     + "\r\n       # maximum journal size before the journal should be rotated."
     + "\r\n       max-journal-size = 16 MiB"
     + "\r\n"
     + "\r\n       # maximum size of a queue before it drops into read-behind mode."
     + "\r\n       max-memory-size = 128 MiB"
     + "\r\n"
     + "\r\n       # maximum overflow (multiplier) of a journal file before we re-create it."
     + "\r\n       max-journal-overflow = 10"
     + "\r\n"
     + "\r\n       # absolute maximum size of a journal file until we rebuild it,"
     + "\r\n       # no matter what."
     + "\r\n       max-journal-size-absolute = 9223372036854775807 bytes"
     + "\r\n"
     + "\r\n       # whether to drop older items (instead of newer) when the queue is full"
     + "\r\n       discard-old-when-full = on"
     + "\r\n"
     + "\r\n       # whether to keep a journal file at all"
     + "\r\n       keep-journal = on"
     + "\r\n"
     + "\r\n       # whether to sync the journal after each transaction"
     + "\r\n       sync-journal = off"
     + "\r\n"
     + "\r\n       # circuit breaker configuration"
     + "\r\n       circuit-breaker {"
     + "\r\n         # maximum number of failures before opening breaker"
     + "\r\n         max-failures = 3"
     + "\r\n"
     + "\r\n         # duration of time beyond which a call is assumed to be timed out and"
     + "\r\n         # considered a failure"
     + "\r\n         call-timeout = 3 seconds"
     + "\r\n"
     + "\r\n         # duration of time to wait until attempting to reset the breaker during"
     + "\r\n         # which all calls fail-fast"
     + "\r\n         reset-timeout = 30 seconds"
     + "\r\n       }"
     + "\r\n     }"
     + "\r\n   }"
     + "\r\n }"
    + "\r\n}"
+ "\r\n"
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
		ActorSystem system=ActorSystem.create("durablebox-demo", ConfigFactory.parseString(str));
		ActorRef actor=system.actorOf(Props.create(MessageConsumerActor.class));
		Inbox inbox=Inbox.create(system);
		inbox.send(actor, "demo");
		system.awaitTermination();
	}
}
