package cn.gpnu.gmall.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @param
 * @return
 */
public class PmsBaseCatalog1 implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String name;
    @Transient
    private List<PmsBaseCatalog2> catalog2List;

    public PmsBaseCatalog1() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<PmsBaseCatalog2> getCatalog2s() {
        return catalog2List;
    }
    public void setCatalog2s(List<PmsBaseCatalog2> catalog2s) {
        this.catalog2List = catalog2s;
    }

    @Override
    public String toString() {
        return "PmsBaseCatalog1{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", catalog2List=" + catalog2List +
                '}';
    }

}

