import components.distmeterslave.DistmeterSlave;
import components.distmeterslave.Commands;
import conf.ConfManager;
import utilities.MessageSlave;

import java.util.concurrent.ConcurrentLinkedQueue;


public class DistMeter {

    public static void main(String [] args) {

        ConfManager confManager = new ConfManager(); // Configuration object
        ConcurrentLinkedQueue<MessageSlave> myqueue=new ConcurrentLinkedQueue<>();
        ConcurrentLinkedQueue<MessageSlave> myqueueCommands=new ConcurrentLinkedQueue<>();
        ConcurrentLinkedQueue<MessageSlave> myqueueComServer=new ConcurrentLinkedQueue<>();
        Thread tcommands = new Thread(new Commands(myqueueCommands));
        DistmeterSlave distmeterslave = new DistmeterSlave(confManager,myqueue,myqueueCommands,myqueueComServer,tcommands);

        Thread tslave = new Thread(distmeterslave);
        try {
            tcommands.join();
            tslave.join();
            tslave.start();
            tcommands.start();
           // myqueue.add(new MessageSlave(2,"java -version"));
            myqueueComServer.add(new MessageSlave(3,"register"));


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
