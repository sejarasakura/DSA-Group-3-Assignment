/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.json;

/**
 *
 * @author Lim Sai Keat
 */
public class FeildAccessbility {
    
    private String type;
    private String name;
    private boolean display;
    private boolean update;
    private boolean auto_inc;

    public FeildAccessbility() {
    }

    public FeildAccessbility(String type, String name, boolean display, boolean update, boolean auto_inc) {
        this.type = type;
        this.name = name;
        this.display = display;
        this.update = update;
        this.auto_inc = auto_inc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isAuto_inc() {
        return auto_inc;
    }

    public void setAuto_inc(boolean auto_inc) {
        this.auto_inc = auto_inc;
    }
    
}
