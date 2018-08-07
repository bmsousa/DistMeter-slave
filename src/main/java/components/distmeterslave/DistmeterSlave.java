package components.distmeterslave;

import conf.ConfManager;
import entities.Slaves;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.PumpStreamHandler;
import utilities.MessageSlave;


import java.io.IOException;
import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DistmeterSlave implements Runnable {

    ConfManager confManager;
    DismeterRESTClient rstCli;
    private ConcurrentLinkedQueue<MessageSlave> myqueue;
    private ConcurrentLinkedQueue<MessageSlave> myqueueCommands;
    private ConcurrentLinkedQueue<MessageSlave> myqueueComServer;
    //Threads (
    //  to sync with Server
    //  to control tests (run commands)
Thread tcommands;
Thread tcomserver;

    public DistmeterSlave(ConfManager confManager,ConcurrentLinkedQueue q, ConcurrentLinkedQueue q1, ConcurrentLinkedQueue q2, Thread tcommands) {
        this.confManager = confManager;
        this.rstCli = new DismeterRESTClient("localhost:8080");//TODO read from configuration
        this.myqueue = q;
        this.myqueueCommands = q1;
        this.myqueueComServer = q2;
        this.tcommands = tcommands;
    }



    /**
     * Method to register on the Distmeter server
     */
    public boolean register() {
        boolean res = false;
        try {
            Slaves slv = new Slaves();
            slv.Name = "test";
            slv.SlaveID = (long) 1;
            slv.n_cpus = 1;
            slv.mem_avai = 1;
            slv.mem_size = 1;
            res = rstCli.do_register(slv);
            return res; // success in register
        } catch (Exception e) {
            e.printStackTrace();
            return res;
        }
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()
                + ", executing run() method!");
        while(true) {
            MessageSlave aux = myqueue.poll();
            if (aux != null) {
                System.out.println(aux);
                switch (aux.getMsgtype()) {
                    case MessageSlave.MESSAGETOCOMMANDS:
                        myqueueCommands.add(aux); //CONTROLAR ESTADO DO PEDIDO
                        System.out.println("ADICIONEI");
                        break; //TODO RESTANTES TIPOS
                }

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class specs implements Runnable {
        @Override
        public void run() {
            /* Total number of processors or cores available to the JVM */
            System.out.println("Available processors (cores): " +
                    Runtime.getRuntime().availableProcessors());

            /* Total amount of free memory available to the JVM */
            System.out.println("Free memory (bytes): " +
                    Runtime.getRuntime().freeMemory());

            /* This will return Long.MAX_VALUE if there is no preset limit */
            long maxMemory = Runtime.getRuntime().maxMemory();
            /* Maximum amount of memory the JVM will attempt to use */
            System.out.println("Maximum memory (bytes): " +
                    (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

            /* Total memory currently available to the JVM */
            System.out.println("Total memory available to JVM (bytes): " +
                    Runtime.getRuntime().totalMemory());

            /* Get a list of all filesystem roots on this system */
            File[] roots = File.listRoots();

            /* For each filesystem root, print some info */
            for (File root : roots) {
                System.out.println("File system root: " + root.getAbsolutePath());
                System.out.println("Total space (bytes): " + root.getTotalSpace());
                System.out.println("Free space (bytes): " + root.getFreeSpace());
                System.out.println("Usable space (bytes): " + root.getUsableSpace());

                System.out.println(Thread.currentThread().getName()
                        + ", executing run() method!");
            }
        }
    }

    // Get output
    public void execommand_op2(String cmd){
        ExecuteStreamHandler streamHandler = new PumpStreamHandler(System.out, System.err, System.in);
        String line =cmd ;
        CommandLine cmdLine = CommandLine.parse(line);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(streamHandler);
        try {
            int exitValue = executor.execute(cmdLine);
            System.out.println("Exit value = " + exitValue);
            System.out.println("output controlado" );
        }catch (IOException io){
            io.printStackTrace();
        }

    }
}
