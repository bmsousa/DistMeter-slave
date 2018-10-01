package components.distmeterslave;

import entities.Slaves;
import jdk.nashorn.internal.parser.JSONParser;
import org.rapidoid.data.JSON;
import org.rapidoid.http.HTTP;
import org.rapidoid.http.HttpClient;
import org.rapidoid.http.RESTClient;
import org.rapidoid.u.U;
import utilities.JSONUtil;

import java.io.IOException;
import java.util.Map;

import static org.rapidoid.html.HTML.header;


public class DismeterRESTClient {
    RESTClient rstClient;
    String distmeterServer;


    public DismeterRESTClient(String server){
        this.distmeterServer= server;
        this.rstClient = new RESTClient();
    }

    public boolean do_register(Slaves slv){
        String url = "http://" + this.distmeterServer + "/server/slaves/register";
        System.out.println("to make registration of " + slv + " at url " + url);

        HttpClient client = new HttpClient();

        Map<String, ?> slaux = U.map("SlaveID", 1, "Name", "SLAVE DEMONSTRACAO", "SlaveIP", "192.168.1.10");
        System.out.println(HTTP.post(url).data(slaux).fetch());

       // rstClient.post(url, slv.getClass() );
        // curl -X POST http://localhost:8080/server/slaves/register -d '{"SlaveID": 1, "Name":"teste", "SlaveIP":"192.168.2.50:8886"}' -H 'Content-Type: application/json'
        return true;
    }
}
