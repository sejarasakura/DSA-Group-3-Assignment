/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.pages;

import adt.XArrayList;
import cilent.IDManager;
import entity.AbstractEntity;
import entity.json.ClassSaving;
import entity.json.FeildAccessbility;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import xenum.AbstractEnum;

/**
 *
 * @author ITSUKA KOTORI
 */
public class EditEntity extends AbstractPage {

    private final String enchar = "\u2022";
    private final ClassSaving classSaving;
    private XArrayList<AbstractEntity> datas;
    private final XArrayList<Method> feilds = new XArrayList<Method>();
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
            class_name = class_name.substring(0, 1).toUpperCase() + class_name.substring(1);
            Class<?> cls = Class.forName("entity." + class_name);
            Constructor c = cls.getConstructor();
            AbstractEntity x = (AbstractEntity) c.newInstance();
            result = new EditEntity(x, ids);
        } catch (ClassNotFoundException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(EditEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public EditEntity(AbstractEntity refence, String parameter_id) {
        super(null);
        classSaving = main.Functions.getSavingClass(refence.getClass());
        if (classSaving == null) {
            System.out.println(xenum.OutputColor.TEXT_RED + refence.getClass() + xenum.OutputColor.TEXT_RESET);
            return;
        }
        stringBuilder.append("<form method='post' id='updateform'　name='updateform' action='/WebApplication/admin/updateEntity'>");
        this.parameter_id = parameter_id;
        stringBuilder.append("<h2 class='mb-3'>");
        stringBuilder.append(refence.getClass());
        stringBuilder.append("</h2><br><table id=\"dtBasicExample\" class=\"table\" width=\"100%\">");
        generateHeader(refence);
        stringBuilder.append("<tbody>");
        generateFooter(refence);
        XArrayList obj = AbstractEntity.readDataFormCsv(refence);
        datas = (obj == null) ? new XArrayList() : obj;
        for (int i = 0; i < datas.size(); i++) {
            generateBody(datas.get(i));
        }
        stringBuilder.append("</tbody>");
        stringBuilder.append(" </table> </form>");
    }

    @Override
    public String getHtml() {
        return stringBuilder.toString();
    }

    private void generateFooter(AbstractEntity ref) {
        try {
            stringBuilder.append("<tr>");
            stringBuilder.append("<td>");
            if (this.classSaving.isAdd()) {
                submitIcon("glyphicon-plus", "id='add-btn'");
            }
            stringBuilder.append("</td>");
            for (j = 0; j < classSaving.getFields().size(); j++) {
                write_one_input(ref, classSaving.getFields().get(j), true);
            }
            stringBuilder.append("</tr>");
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException | NoSuchMethodException ex) {
            Logger.getLogger(EditEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generateHeader(AbstractEntity ref) {
        try {
            stringBuilder.append("<thead><tr>");
            stringBuilder.append("<th class=\"th-sm\">=======</th>");
            for (j = 0; j < classSaving.getFields().size(); j++) {
                feilds.add(ref.getClass().getMethod(main.Functions.fieldToGetter(classSaving.getFields().get(j).getName())));
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
                stringBuilder.append("<td><span id='").append(entity.getId()).append("'>");
                if (this.classSaving.isDelete()) {
                    submitIcon("glyphicon-trash", " data-id='" + entity.getId() + "' ");
                }
                displayIcon(entity.getClass().getSimpleName(), "glyphicon-remove");
                submitIcon("glyphicon-circle-arrow-up", "");
                stringBuilder.append("</span></td>");
                generateInput(entity);
                stringBuilder.append("</tr>");
            } else {
                stringBuilder.append("<tr>");
                stringBuilder.append("<td><span id='").append(entity.getId()).append("'>");
                if (this.classSaving.isDelete()) {
                    submitIcon("glyphicon-trash", " data-id='" + entity.getId() + "' ");
                }
                displayIcon(entity.getClass().getSimpleName(), (String) feilds.get(identifier_index).invoke(entity), "glyphicon-pencil", "");
                stringBuilder.append("</span></td>");
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
                .append("\" ").append(_att).append(" ></span></a>");
    }

    private void displayIcon(String _edit, String _id, String _class, String _atribute) {
        stringBuilder
                .append("<a href=\"")
                .append("/WebApplication/admin/edit/").append(_edit)
                .append("?id=").append(_id).append("#").append(_id)
                .append("\"> <span class=\"glyphicon ")
                .append(_class)
                .append("\" ").append(_atribute).append("></span> </a>");
    }

    private void displayIcon(String _edit, String _class) {
        stringBuilder.append("<a href=\"")
                .append("/WebApplication/admin/edit/").append(_edit)
                .append("\"> <span class=\"glyphicon ")
                .append(_class)
                .append("\"></span> </a>");
    }

    private void printDisplayBody(AbstractEntity entity) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object data;
        for (j = 0; j < feilds.size(); j++) {
            data = feilds.get(j).invoke(entity);
            if (classSaving.getFields().get(j).isDisplay()) {
                if (data instanceof AbstractEnum) {
                    stringBuilder.append("<td>").append(((AbstractEnum) data).getName()).append("</td>");
                } else if (data instanceof Date) {
                    stringBuilder.append("<td>").append(main.WebConfig.LOCAL_DATETIME_FORMAT.format(data)).append("</td>");
                } else {
                    stringBuilder.append("<td>").append(data).append("</td>");
                }
            } else {
                stringBuilder.append("<td>").append(main.Functions.repeat(enchar, ((String) data).length())).append("</td>");
            }
        }
    }

    private void generateInput(AbstractEntity entity) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        for (j = 0; j < feilds.size(); j++) {
            try {
                write_one_input(entity, classSaving.getFields().get(j), false);
            } catch (ClassNotFoundException | NoSuchMethodException ex) {
                Logger.getLogger(EditEntity.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private String write_type, write_query, value;
    private Object data, idsss, prefix;
    private Class<?> _class_ref;
    private boolean check;

    private void write_one_input(AbstractEntity entity, FeildAccessbility fb, boolean add) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException {
        write_type = "text";
        write_query = "";
        value = "";
        data = null;
        idsss = null;
        prefix = "";
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
            case "adt.XArrayList":
                write_type = "text";
                value = data == null ? "" : ((XArrayList) data).toInput();
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
                write_query += "true".equals(data.toString()) ? "checked " : " ";
                break;
            default:
                if (fb.getType().contains("xenum.")) {
                    value = (String) ((data == null) ? "" : ((AbstractEnum) data).getStringCode());
                }
                break;
        }
        if (add) {
            prefix = "add_";
            if (fb.isAuto_inc()) {
                write_query += "disabled ";
                idsss = IDManager.generateId(entity);
                value = (idsss instanceof Integer) ? String.format("%d", idsss) : (String) idsss;
            }
        } else {
            prefix = "update_";
            if (!fb.isUpdate()) {
                write_query += "disabled ";
            }
        }
        if (fb.getType().contains("xenum.")) {
            _class_ref = Class.forName(fb.getType());
            XArrayList<AbstractEnum> enum_list = new XArrayList<AbstractEnum>((AbstractEnum[]) _class_ref.getMethod("values").invoke(null));
            stringBuilder.append("<td>")
                    .append("<div class=\"form-group\">")
                    .append("<select class=\"\"")
                    .append("id='").append(prefix).append(fb.getName()).append("' ")
                    .append("name='").append(prefix).append(fb.getName()).append("'>");
            for (AbstractEnum _enum : enum_list) {
                check = data == null ? false : (((AbstractEnum) data).getStringCode() == null ? _enum.getStringCode() == null : ((AbstractEnum) data).getStringCode().equals(_enum.getStringCode()));
                stringBuilder.append("<option value='")
                        .append(_enum.getStringCode()).append("' ")
                        .append(check ? "selected " : " ")
                        .append(">").append(_enum.getName())
                        .append("</option>");
            }
            stringBuilder.append("</select>")
                    .append("</div>").append("</td>");
        } else {
            stringBuilder
                    .append("<td><div class=\"form-group\"><input ")
                    .append("type='").append(write_type).append("' ")
                    .append("id='").append(prefix).append(fb.getName()).append("' ")
                    .append("name='").append(prefix).append(fb.getName()).append("' ")
                    .append("value='").append(value).append("' ")
                    .append(write_query).append("></div></td>");
        }
    }

    @Override
    public XArrayList<String> getHtmls() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
