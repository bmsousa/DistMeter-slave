package components.distmeterslave;

import conf.ConfManager;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.PumpStreamHandler;
import org.jutils.jhardware.HardwareInfo;
import org.jutils.jhardware.model.MemoryInfo;
import org.jutils.jhardware.model.ProcessorInfo;

import java.io.IOException;

public class DistmeterSlave {

    ConfManager confManager;

    public DistmeterSlave(ConfManager confManager){
        this.confManager = confManager;
    }


    public void run(String []args){
        ProcessorInfo info = HardwareInfo.getProcessorInfo();
        //Get named info
        System.out.println("Cache size: " + info.getCacheSize());
        System.out.println("Family: " + info.getFamily());
        System.out.println("Speed (Mhz): " + info.getMhz());
        System.out.println("N cores: " + info.getNumCores());

        MemoryInfo meminfo = HardwareInfo.getMemoryInfo();
        //Get named info
        System.out.println("Available mem: " + meminfo.getAvailableMemory());
        System.out.println("Totalmemory: " + meminfo.getTotalMemory());
        System.out.println(": " + meminfo.getFreeMemory());
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


