/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Graph_allocation {
    private int x;
    private int y;
    private String indexLabel;

    public Graph_allocation(int x, int y, String indexLabel) {
        this.x = x;
        this.y = y;
        this.indexLabel = indexLabel;
    }

    public Graph_allocation(int x, int y) {
        this.x = x;
        this.y = y;
        indexLabel = "";
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getIndexLabel() {
        return indexLabel;
    }

    public void setIndexLabel(String indexLabel) {
        this.indexLabel = indexLabel;
    }
    
}
