/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xenum;

/**
 *
 * @author Lim Sai Keat
 */
public interface AbstractEnum {

    String getStringCode();

    String getName();

    public AbstractEnum setMyValue(Object string);

    public int compare(AbstractEnum x);

}
