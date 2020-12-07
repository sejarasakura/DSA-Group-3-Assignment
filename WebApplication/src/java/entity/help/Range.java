/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.help;

/**
 *
 * @author ITSUKA KOTORI
 * @param <T>
 */
public class Range<T> {

    private T lower;
    private T higher;

    public Range(T lower, T higher) {
        this.lower = lower;
        this.higher = higher;
    }

    public Range() {

    }

    public T getLower() {
        return lower;
    }

    public void setLower(T lower) {
        this.lower = lower;
    }

    public T getHigher() {
        return higher;
    }

    public void setHigher(T higher) {
        this.higher = higher;
    }

    public Object delta() {
        try {
            return (Object) ((Double) this.higher - (Double) this.lower);
        } catch (Exception ex) {
            return null;
        }
    }
}
