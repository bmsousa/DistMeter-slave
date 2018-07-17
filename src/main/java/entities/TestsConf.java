package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
public class TestsConf {
    @Id
    @GeneratedValue
    public Long ID;

    @NotNull
    public String Name;

    @NotNull
    public int n_threads;

    @NotNull
    public int ramp_up;

    @NotNull
    public int runs;

    @NotNull
    public int n_clients;

    @NotNull
    public int duration;

    @NotNull
    public String protocol;

    @NotNull
    public String server_name;

    @NotNull
    public int server_port;


    public String test_file;


    public Date date_conf;


}
