package components.distmeterslave;

import conf.ConfManager;
import entities.Slaves;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.PumpStreamHandler;
import org.jutils.jhardware.HardwareInfo;
import org.jutils.jhardware.model.MemoryInfo;
import org.jutils.jhardware.model.ProcessorInfo;

import java.io.IOException;

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

    /**
     * TODO: remove prints...
     *
     */
    /*
    public void get_info(String []args){
        //Get named info
        System.out.println("Cache size: " + this.cpuinfo.getCacheSize());
        System.out.println("Family: " + this.cpuinfo.getFamily());
        System.out.println("Speed (Mhz): " + this.cpuinfo.getMhz());
        System.out.println("N cores: " + this.cpuinfo.getNumCores());

        //Get named info
        System.out.println("Available mem: " + this.meminfo.getAvailableMemory());
        System.out.println("Totalmemory: " + this.meminfo.getTotalMemory());
        System.out.println(": " + this.meminfo.getFreeMemory());
    }*/

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


