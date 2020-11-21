/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;

/**
 *
 * @author ITSUKA KOTORI
 */
public class InfoMessage extends AbstractEntity {

    private String code;
    private String name;
    private String decription;
    private String cssClass;

    public InfoMessage() {
    }

    public InfoMessage(String code, String name, String decription, String cssClass) {
        this.code = code;
        this.name = name;
        this.decription = decription;
        this.cssClass = cssClass;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String toString() {
        return "infoMessage{" + "code=" + code + ", name=" + name + ", decription=" + decription + ", cssClass=" + cssClass + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InfoMessage other = (InfoMessage) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isNotNull() {
        return this.code != null;
    }

    @Override
    public boolean id_equals(Object obj) {
        return this.code.equals(((InfoMessage) obj).code);
    }

    @Override
    public int compareTo(Object t) {
        return this.code.compareTo(((InfoMessage) t).code);
    }

}
