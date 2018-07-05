package conf;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Class to manage the configuration operations
 */
public class ConfManager {
    public static final String configFile = "distmeter.conf";

    public static final int DISTMETER_CONFIGURATION_MANAGER = 1;
    public static final int DISTMETER_TEST_SERVER = 2;
    public static final int DISTMETER_TEST_SLAVE = 3;
    public static final int DISTMETER_TEST_ANALYTICS_SERVER = 4;
    public static final int DISTMETER_COMMON_CONF = 5;

    public ConfManager(){    }


    public ConfObject readConf(int component){
        ConfObject conf_obj = null;
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);

            switch(component) {
                case DISTMETER_CONFIGURATION_MANAGER:
                    conf_obj = new ConfObjManager(props);
                    break;

                case DISTMETER_TEST_SERVER:
                    //TODO:
                    break;

                case DISTMETER_TEST_SLAVE:
                    //TODO:
                    break;

                case DISTMETER_TEST_ANALYTICS_SERVER:
                    //TODO:
                    break;

                case DISTMETER_COMMON_CONF:
                    //TODO:
                    break;
            }
            reader.close();
        }catch(FileNotFoundException ex){

        }catch (IOException ex){

        }
        return conf_obj;

    }
}
