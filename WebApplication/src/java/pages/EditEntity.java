/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import adt.ArrList;
import entity.AbstractEntity;
import entity.Withdraw;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import json.ClassSaving;
import json.FeildAccessbility;

/**
 *
 * @author ITSUKA KOTORI
 */
public class EditEntity {

    private StringBuilder stringBuilder;
    private ClassSaving classSaving;
    private ArrList<AbstractEntity> datas;
    private ArrList<Method> feilds = new ArrList<Method>();
    private int j;
    
    public static void main(String args[]){
        EditEntity ee = getNewEditEntity("Customer");
        System.out.print(ee.getHtml());
    }
    
    public static EditEntity getNewEditEntity(String class_name){
        EditEntity result = null;
        try {  
            Class<?> cls = Class.forName("entity."+class_name);
            Constructor c = cls.getConstructor();
            AbstractEntity x = (AbstractEntity)c.newInstance();
            result = new EditEntity(x);
        } catch (ClassNotFoundException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(EditEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public EditEntity(AbstractEntity refence) {
        stringBuilder = new StringBuilder();
        classSaving = main.Functions.getSavingClass(refence.getClass());
        if (classSaving == null) {
            return;
        }
        
        stringBuilder.append("<h2 class='mb-3'>").append(refence.getClass()).append("</h2><br><table id=\"dtBasicExample\" class=\"table\" width=\"100%\">");
        generateHeader(refence);
        stringBuilder.append("<tbody>");
        datas = new ArrList(AbstractEntity.readDataFormCsv(refence));
        for (int i = 0; i < datas.size(); i++) {
            generateBody(datas.get(i));
        }
        stringBuilder.append("</tbody> </table> <script> $(document).ready(function () { $('#dtBasicExample').DataTable(); $('.dataTables_length').addClass('bs-select'); }); </script>");
    }

    public String getHtml() {
        return stringBuilder.toString();
    }
    
    private String toGetter(){
        StringBuilder sb = new StringBuilder();
        char[] data = classSaving.getFields().get(j).getName().toCharArray();
        data[0] = Character.toUpperCase(data[0]);
        sb.append("get").append(data);
        return sb.toString();
    }

    private void generateHeader(AbstractEntity ref) {
        try {
            stringBuilder.append("<thead><tr>");
            for (j = 0; j < classSaving.getFields().size(); j++) {
                System.out.println(classSaving.getFields().get(j).getName() + ref.getClass());
                feilds.add(ref.getClass().getMethod(toGetter()));
                stringBuilder.append("<th class=\"th-sm\">").append(classSaving.getFields().get(j).getName()).append("</th>");
            }
            stringBuilder.append("</tr></thead>");
        } catch (SecurityException | NoSuchMethodException ex) {
            Logger.getLogger(EditEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generateBody(AbstractEntity entity) {
        try {
            stringBuilder.append("<tr>");
            for (j = 0; j < feilds.size(); j++) {
                stringBuilder.append("<td>").append(feilds.get(j).invoke(entity)).append("</td>");
            }
            stringBuilder.append("</tr>");
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(EditEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
