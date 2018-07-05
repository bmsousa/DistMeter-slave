package conf;

import java.util.Properties;

/**
 * Class represting a configuraiton object
 *
 * Has the commonf fields of configuration
 * */
public class ConfObject {
    protected String component;
    protected Properties props;

    private String address_test_servers;
    private int port_REST_test_servers;


    ConfObject(){
        this.component = "";
    }

    public String getAddress_test_servers() {
        return address_test_servers;
    }

    public void setAddress_test_servers(String address_test_servers) {
        this.address_test_servers = address_test_servers;
    }

    public int getPort_REST_test_servers() {
        return port_REST_test_servers;
    }

    public void setPort_REST_test_servers(int port_REST_test_servers) {
        this.port_REST_test_servers = port_REST_test_servers;
    }
}
