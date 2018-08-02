package components.distmeterslave;

import conf.ConfManager;
import entities.Slaves;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.PumpStreamHandler;


import java.io.IOException;
import java.io.File;

public class DistmeterSlave  {

    ConfManager confManager;
    //ProcessorInfo cpuinfo;
    //MemoryInfo meminfo;
    DismeterRESTClient rstCli;

    //Threads (
    //  to sync with Server
    //  to control tests (run commands)


    public DistmeterSlave(ConfManager confManager){
        this.confManager = confManager;
        //this.cpuinfo = HardwareInfo.getProcessorInfo();
        //this.meminfo = HardwareInfo.getMemoryInfo();
        this.rstCli = new DismeterRESTClient("localhost:8080");//TODO read from configuration
    }

    /**
     * Method to register on the Distmeter server
     */
    public boolean register(){
        boolean res=false;
        try{
            Slaves slv = new Slaves();
            slv.Name="test";
            slv.SlaveID= (long)1;
            slv.n_cpus = 1;
            slv.mem_avai = 1;
            slv.mem_size = 1;
            res = rstCli.do_register(slv);
            return res; // success in register
        }catch (Exception e){
            e.printStackTrace();
            return res;
        }
    }

    public void specs() {
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
        }
    }

    public void execommand_op1(String cmd){
        String line =cmd ;
        CommandLine cmdLine = CommandLine.parse(line);
        DefaultExecutor executor = new DefaultExecutor();
        try {
            int exitValue = executor.execute(cmdLine);
            System.out.println("Exit value = " + exitValue);
        }catch (IOException io){
            io.printStackTrace();
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


