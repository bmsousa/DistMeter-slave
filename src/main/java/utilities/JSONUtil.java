package utilities;

import com.google.gson.Gson;
import entities.Slaves;

/**
 * Class to convert Classes to JSON
 */
public class JSONUtil {
    static Gson gson = new Gson();


    public static String SlaveToJson(Slaves slv){
        String aux_conv=gson.toJson(slv);

        return aux_conv;
    }
}
