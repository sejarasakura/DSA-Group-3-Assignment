/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.pages;

import adt.ArrList;
import cilent.IDManager;
import entity.AbstractEntity;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.json.ClassSaving;
import entity.json.FeildAccessbility;

/**
 *
 * @author ITSUKA KOTORI
 */
public class EditEntity {

    private final String enchar = "\u2022";
    private final StringBuilder stringBuilder;
    private final ClassSaving classSaving;
    private ArrList<AbstractEntity> datas;
    private final ArrList<Method> feilds = new ArrList<Method>();
    private int j, identifier_index;
    private Object id;
    private String parameter_id;

    public static void main(String[] args) {
        EditEntity ee = getNewEditEntity("Taxi", "");
        System.out.print(ee.getHtml());
    }

    public static EditEntity getNewEditEntity(String class_name, String ids) {
        EditEntity result = null;
        try {
            Class<?> cls = Class.forName("entity." + class_name);
            Constructor c = cls.getConstructor();
            AbstractEntity x = (AbstractEntity) c.newInstance();
            result = new EditEntity(x, ids);
        } catch (ClassNotFoundException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(EditEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public EditEntity(AbstractEntity refence, String parameter_id) {
        stringBuilder = new StringBuilder();
        classSaving = main.Functions.getSavingClass(refence.getClass());
        if (classSaving == null) {
            System.out.println(xenum.OutputColor.TEXT_RED + refence.getClass() + xenum.OutputColor.TEXT_RESET);
            return;
        }
        stringBuilder.append("<form method='post' id='updateform'　name='updateform' action='updateEntity'>");
        this.parameter_id = parameter_id;
        stringBuilder.append("<h2 class='mb-3'>").append(refence.getClass()).append("</h2><br><table id=\"dtBasicExample\" class=\"table\" width=\"100%\">");
        generateHeader(refence);
        stringBuilder.append("<tbody>");
        Iterator obj = AbstractEntity.readDataFormCsv(refence);
        datas = (obj == null) ? new ArrList() : new ArrList(obj);
        for (int i = 0; i < datas.size(); i++) {
            generateBody(datas.get(i));
        }
        stringBuilder.append("</tbody>");
        generateFooter(refence);
        stringBuilder.append(" </table> </form> <script> $(document).ready(function () { $('#dtBasicExample').DataTable({ \"scrollX\": true }); $('.dataTables_length').addClass('bs-select'); }); </script>");
    }

    public String getHtml() {
        return stringBuilder.toString();
    }

    private String toGetter() {
        StringBuilder sb = new StringBuilder();
        char[] data = classSaving.getFields().get(j).getName().toCharArray();
        data[0] = Character.toUpperCase(data[0]);
        sb.append("get").append(data);
        return sb.toString();
    }

    private void generateFooter(AbstractEntity ref) {
        try {
            stringBuilder.append("<tfoot><tr>");
            stringBuilder.append("<td>");
            submitIcon("glyphicon-plus", "");
            stringBuilder.append("</td>");
            for (j = 0; j < classSaving.getFields().size(); j++) {
                write_one_input(ref, classSaving.getFields().get(j), true);
            }
            stringBuilder.append("</tr></tfoot>");
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(EditEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generateHeader(AbstractEntity ref) {
        try {
            stringBuilder.append("<thead><tr>");
            stringBuilder.append("<th class=\"th-sm\">====</th>");
            for (j = 0; j < classSaving.getFields().size(); j++) {
                System.out.println(classSaving.getFields().get(j).getName() + ref.getClass());
                feilds.add(ref.getClass().getMethod(toGetter()));
                stringBuilder.append("<th class=\"th-sm\">").append(classSaving.getFields().get(j).getName()).append("</th>");
                if (classSaving.getFields().get(j).getName().equals(classSaving.getIdentifier())) {
                    this.identifier_index = j;
                }
            }
            stringBuilder.append("</tr></thead>");
        } catch (SecurityException | NoSuchMethodException ex) {
            Logger.getLogger(EditEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generateBody(AbstractEntity entity) {
        try {
            if (feilds.get(identifier_index).invoke(entity).equals(this.parameter_id)) {
                stringBuilder.append("<tr>");
                stringBuilder.append("<td>");
                displayIcon(entity.getClass().getSimpleName(), "glyphicon-trash");
                displayIcon(entity.getClass().getSimpleName(), "glyphicon-remove");
                submitIcon("glyphicon-circle-arrow-up", "");
                stringBuilder.append("</td>");
                generateInput(entity);
                stringBuilder.append("</tr>");
            } else {
                stringBuilder.append("<tr>");
                stringBuilder.append("<td>");
                displayIcon(entity.getClass().getSimpleName(), "glyphicon-trash");
                displayIcon(entity.getClass().getSimpleName(), (String) feilds.get(identifier_index).invoke(entity), "glyphicon-pencil", "");
                stringBuilder.append("</td>");
                printDisplayBody(entity);
                stringBuilder.append("</tr>");
            }
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(EditEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void submitIcon(String _class, String _att) {
        stringBuilder
                .append("<a href='#'><span class=\"glyphicon ")
                .append(_class)
                .append("\" ").append(_att).append("></span></a>");
    }

    private void displayIcon(String _edit, String _id, String _class, String _atribute) {
        stringBuilder
                .append("<a href=\"")
                .append("?edit=").append(_edit)
                .append("&id=").append(_id)
                .append("\"> <span class=\"glyphicon ")
                .append(_class)
                .append("\" ").append(_atribute).append("></span> </a>");
    }

    private void displayIcon(String _edit, String _class) {
        stringBuilder.append("<a href=\"")
                .append("?edit=").append(_edit)
                .append("\"> <span class=\"glyphicon ")
                .append(_class)
                .append("\"></span> </a>");
    }

    private void printDisplayBody(AbstractEntity entity) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        for (j = 0; j < feilds.size(); j++) {
            if (classSaving.getFields().get(j).isDisplay()) {
                stringBuilder.append("<td>").append(feilds.get(j).invoke(entity)).append("</td>");
            } else {
                stringBuilder.append("<td>")
                        .append(main.Functions.repeat(enchar, ((String) feilds.get(j).invoke(entity)).length()))
                        .append("</td>");
            }
        }
    }

    private void generateInput(AbstractEntity entity) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        for (j = 0; j < feilds.size(); j++) {
            write_one_input(entity, classSaving.getFields().get(j), false);
        }
    }

    private String write_type, write_query, value;
    private Object data, idsss, prefix;

    private void write_one_input(AbstractEntity entity, FeildAccessbility fb, boolean add) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        write_type = "text";
        write_query = "";
        value = "";
        data = null;
        idsss = null;
        prefix = "";
        stringBuilder.append("<td><div class=\"form-group\"><input type=");
        data = feilds.get(j).invoke(entity);
        switch (fb.getType()) {
            case "java.lang.String":
                if (!fb.isDisplay()) {
                    write_type = "password";
                }
                value = (String) ((data == null) ? "" : data);
                break;
            case "java.util.Date":
                write_type = "datetime-local";
                value = main.WebConfig.LOCAL_DATETIME_FORMAT.format(data == null ? new Date() : data);
                break;
            case "adt.ArrList":
                write_type = "text";
                value = data == null ? "" : data.toString();
                break;
            case "double":
                write_type = "number";
                write_query += "step=\"0.01\" min='0' ";
                value = data == null ? "0.00" : String.format("%.2f", data);
                break;
            case "int":
                write_type = "number";
                value = data == null ? "0" : String.format("%d", data);
                break;
            case "boolean":
                write_type = "checkbox";
                value = data == null ? "false" : (boolean) data ? "true" : "false";
                write_query += data == "true" ? "checked " : " ";
                break;
        }
        stringBuilder.append("'").append(write_type).append("' ");
        if (add) {
            prefix = "add_";
            if (fb.isAuto_inc()) {
                write_query += "disabled ";
                idsss = IDManager.generateId(entity);
                value = (idsss instanceof Integer) ? String.format("%d", idsss) : (String) idsss;
            }
        } else {
            if (!fb.isUpdate()) {
                write_query += "disabled ";
            }
        }
        stringBuilder.append("type='").append(write_type).append("' ");
        stringBuilder.append("id='").append(prefix).append(fb.getName()).append("' ");
        stringBuilder.append("name='").append(prefix).append(fb.getName()).append("' ");
        stringBuilder.append("value='").append(value).append("' ");
        stringBuilder.append(write_query).append("></div></td>");
    }
}
