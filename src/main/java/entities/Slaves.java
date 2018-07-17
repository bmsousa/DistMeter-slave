package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Slaves {

    @Id
    @GeneratedValue
    public Long SlaveID;

    @NotNull
    public String Name;

    @NotNull
    public String SlaveIP;


    public String SupportedTests; //TODO: check how string arrays are mapped in the DB


    public int n_cpus;


    public long mem_size;


    public long mem_used;


    public long mem_avai;


    public long disk_free;

    public String status; // status of slave


}
