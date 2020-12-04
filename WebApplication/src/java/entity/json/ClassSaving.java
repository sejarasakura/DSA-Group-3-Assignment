/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.json;

import adt.XArrayList;

/**
 *
 * @author ITSUKA KOTORI
 */
public class ClassSaving {
    
    private String classname;
    private String extension;
    private String identifier;
    private boolean add;
    private boolean delete;
    private XArrayList<FeildAccessbility> fields;

    public ClassSaving(String classname, String extension, XArrayList<FeildAccessbility> fields) {
        this.classname = classname;
        this.extension = extension;
        this.fields = fields;
    }

    public ClassSaving() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public XArrayList<FeildAccessbility> getFields() {
        return fields;
    }

    public void setFields(XArrayList<FeildAccessbility> fields) {
        this.fields = fields;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
    
    
}
