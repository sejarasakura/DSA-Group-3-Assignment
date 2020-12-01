/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

/**
 *
 * @author ITSUKA KOTORI
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int i = 2;
        int x = 0;
        Integer pn_list[] = new Integer[1000];
        while (true) {
            if (isPrime(i)) {
                pn_list[x] = i;
                x++;
                System.out.print(i + ",");
            }

            i++;
            if (i == 1000) {
                break;
            }
        }
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i <= n / 2; i++) {
            if ((n % i) == 0) {
                return false;
            }
        }
        return true;
    }

}
