/**
 * Created by wunan on 16-9-27.
 */
package org.wnsoft.entity;

import java.io.Serializable;

public class Department implements Serializable {
    private int id;
    private String name;
    private int parentid;
    private int order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
