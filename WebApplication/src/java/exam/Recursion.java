/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Recursion {

    static int call_time = 0;
    final static String PREFIX = "  ";
    static StringBuilder sb = new StringBuilder();
    static Stack<String> all = new Stack<String>();

    /**
     * @param args the command line arguments
     */
    public static void main_set2(String[] args) {
        // TODO code application logic here
        String[] str = {"I", "Love", "Programming"};
        Object fr = method(str.length - 1, str);
        while (!all.isEmpty()) {
            System.out.print(all.pop());
        }
        System.out.println("\nFinal output = [" + fr + "]\n\n\n\n");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        main_set2(null);
    }

    public static void main_passyear(String[] args) {
        // TODO code application logic here
        String[] str = {"I", "Love", "Programming"};
        Object fr = sumSeries(5.0);
        while (!all.isEmpty()) {
            System.out.print(all.pop());
        }
        System.out.println("\nFinal output = [" + fr + "]\n\n\n\n");
    }

    public static double sumSeries(double num) {
        double result;
        if (num <= 0.0) {
            result = 0;
        } else if (num == 1.0) {
            result = 1;
        } else {
            result = (1.0 / num) + sumSeries(num - 1);
        }
        // <editor-fold defaultstate="collapsed" desc="Not code">
        Map<String, Object> parma = new HashMap<String, Object>();
        List<String> query = new ArrayList<String>();
        String query1 = String.format("return = (1.0 / %.2f) + sumSeries(%.2f)", num, num - 1);
        parma.put("num", num);
        query.add(query1);
        backEndCode(result, parma, query);
        // </editor-fold>
        return result;
    }

    public static String method(int len, String[] str) {
        String result;
        if (len >= 0) {
            result = str[len] + " " + method(len - 1, str);
        } else {
            result = "";
        }
        // <editor-fold defaultstate="collapsed" desc="Not code">
        Map<String, Object> parma = new HashMap<String, Object>();
        List<String> query = new ArrayList<String>();
        String str_r = arrayToStirng(str);
        String query1 = String.format("return = str[%d] + \" \" + method(%d, str);", len, len - 1);
        parma.put("len", len);
        parma.put("str", str_r);
        query.add(query1);
        query.add("result = \"" + result + "\"");
        backEndCode(result, parma, query);
        // </editor-fold>
        return result;
    }

    public static String arrayToStirng(Object[] objs) {
        StringBuilder r = new StringBuilder();
        for (Object obj : objs) {
            r.append(obj);
            r.append(",");
        }
        r.deleteCharAt(r.length() - 1);
        r.insert(0, "[");
        r.append("]");
        return r.toString();
    }

    public static void backEndCode(Object return_value, Map<String, Object> parma, List<String> query) {
        final String spaces = repeat(call_time, "           ");
        // 50 =
        sb.setLength(0);

        sb.append(spaces);
        sb.append(String.format("┌────────────────────────────return[%-7.7s]───────┐\n", return_value));

        // Parameter
        sb.append(spaces);
        sb.append(String.format("│  %-48.48s│\n", "[Parameter]"));

        parma.entrySet().forEach((entry) -> {
            String key = entry.getKey();
            Object value = entry.getValue();
            sb.append(spaces);
            sb.append(String.format("│  %-20.20s = %-25.25s│\n", key, value));
        });

        // Query
        sb.append(spaces);
        sb.append(String.format("│  %-48s│\n", "[Query]"));

        for (String q : query) {
            sb.append(spaces);
            sb.append(String.format("│  %-48.48s│\n", q));
        }
        sb.append(spaces);
        sb.append("└").append(repeat(50, "─")).append("┘\n");
        all.push(sb.toString());
        call_time++;
    }

    public static String repeat(int i, String s) {
        if (i == 0) {
            return "";
        }
        return String.format("%0" + i + "d", 0).replace("0", s);
    }

}
