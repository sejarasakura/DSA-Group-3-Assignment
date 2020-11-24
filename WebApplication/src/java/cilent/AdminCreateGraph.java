/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent;

import adt.ArrList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Random;

/**
 *
 * @author ITSUKA KOTORI
 */
public class AdminCreateGraph {

    public ArrList<String> getSampleData() {

        Gson gsonObj = new GsonBuilder().setPrettyPrinting().create();
        Graph_allocation map = null;
        ArrList<Graph_allocation>[] list = new ArrList[3];
        ArrList<String> result = new ArrList<String>();

        int count = 100;
        int yVal = 100;
        Random rand = new Random();

        for (int j = 0; j < 3; j++) {
            list[j] = new ArrList<Graph_allocation>();
            for (int i = 0; i < count; i++) {
                yVal += rand.nextInt(11) - 5;
                map = new Graph_allocation(i + 1, yVal);
                list[j].add(map);
            }
            result.add(gsonObj.toJson(list[j].toArray()));
        }
        return result;
    }

    public static void main(String args[]) {
        new AdminCreateGraph().getSampleData();
    }

    public String getSampleData2() {

        Gson gsonObj2 = new Gson();
        ArrList<Graph_allocation> list2 = new ArrList<Graph_allocation>();

        list2.add(new Graph_allocation(10, 31));
        list2.add(new Graph_allocation(20, 65));
        list2.add(new Graph_allocation(30, 40));
        list2.add(new Graph_allocation(40, 84, "Highest"));
        list2.add(new Graph_allocation(50, 68));
        list2.add(new Graph_allocation(60, 64));
        list2.add(new Graph_allocation(70, 38));
        list2.add(new Graph_allocation(80, 71));
        list2.add(new Graph_allocation(90, 54));
        list2.add(new Graph_allocation(100, 60));
        list2.add(new Graph_allocation(110, 21, "Lowest"));
        list2.add(new Graph_allocation(120, 49));
        list2.add(new Graph_allocation(130, 41));

        return gsonObj2.toJson(list2.toArray());
    }
}
