import components.distmeterslave.DistmeterSlave;
import conf.ConfManager;



public class DistMeter {

    public static void main(String [] args){

        ConfManager confManager = new ConfManager(); // Configuration object



        DistmeterSlave distmeterslave = new DistmeterSlave(confManager);
        distmeterslave.run(args);

        System.out.println("cmd 1");
        distmeterslave.execommand_op1("ls -l");

        System.out.println("cmd 2");
        distmeterslave.execommand_op2("ls -l");
    }
}
