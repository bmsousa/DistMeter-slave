package components.distmeterslave;


import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import utilities.MessageSlave;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Commands implements Runnable {
    private ConcurrentLinkedQueue <MessageSlave> myqueue;
    public Commands (ConcurrentLinkedQueue q){
    this.myqueue=q;
    }
    public void run() {
        System.out.println(Thread.currentThread().getName()
                + ", executing run() method!");
        while (true) {
            MessageSlave aux = myqueue.poll();
            if (aux != null) {
                                System.out.println("COMMANMDS" + aux);
                execommand_op1(aux.getCmd());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }
    public void execommand_op1(String cmd) {
        System.out.println(cmd);
        String line = cmd;
        CommandLine cmdLine = CommandLine.parse(line);
        DefaultExecutor executor = new DefaultExecutor();
        try {
            int exitValue = executor.execute(cmdLine);
            System.out.println("Exit value = " + exitValue);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}