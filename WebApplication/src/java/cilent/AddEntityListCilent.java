/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent;

import adt.ArrList;
import cilent.pages.EditEntity;
import entity.AbstractEntity;
import entity.json.ClassSaving;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import static main.Datas.TYPE_SWITCH;
import main.Functions;
import static main.WebConfig.LOCAL_DATETIME_FORMAT;

/**
 *
 * @author ITSUKA KOTORI
 */
public class AddEntityListCilent {

    protected final String prefix = "add_";
    protected String pragma, type;
    protected Class<?> _class;
    protected Type parameter_type;
    protected AbstractEntity entity;
    protected Constructor constructor;
    protected ClassSaving classSaving;
    protected HttpServletRequest request;
    protected int j, identifier_index = -99;
    protected ArrList<AbstractEntity> datas;
    protected ArrList<Method> setter = new ArrList<Method>();
    protected adt.XHashedDictionary<String, String> parameter_data
            = new adt.XHashedDictionary<String, String>();

    /**
     * constructor
     *
     * @param class_name
     * @param request
     */
    public AddEntityListCilent(String class_name, HttpServletRequest request) {
        try {
            _class = Class.forName("entity." + class_name);
            constructor = _class.getConstructor();
            entity = (AbstractEntity) constructor.newInstance();
            this.request = request;
            System.out.println(start_add());
        } catch (ClassNotFoundException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(EditEntity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AddEntityListCilent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * update
     */
    private StringBuilder start_add() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException, ClassNotFoundException {
        StringBuilder sb = new StringBuilder();

        // get class information
        classSaving = main.Functions.getSavingClass(entity.getClass());
        if (classSaving == null) {
            sb.append(xenum.OutputColor.TEXT_RED).append(entity.getClass()).append(" class is not found.").append(xenum.OutputColor.TEXT_RESET);
            return sb;
        }

        // dynamic load the setter of each field in the class.
        for (j = 0; j < classSaving.getFields().size(); j++) {
            pragma = classSaving.getFields().get(j).getName();
            type = classSaving.getFields().get(j).getType();
            this.parameter_data.add(pragma, request.getParameter(prefix + pragma));
            setter.add(entity.getClass().getMethod(
                    Functions.fieldToSetter(pragma), Functions.isGeneralType(type) ? TYPE_SWITCH.getValue(type) : Class.forName(type)
            ));
            sb.append(xenum.OutputColor.TEXT_GREEN).append(pragma).append(", ");
            if (pragma.equals(classSaving.getIdentifier())) {
                this.identifier_index = j;
            }
        }

        // id index not found return
        if (identifier_index < 0) {
            sb.append(xenum.OutputColor.TEXT_RED).append("The id is not found").append(xenum.OutputColor.TEXT_RESET);
            return sb;
        }

        String parameter;
        Object input = null;

        // loop to each field and assign to acture entity
        for (j = 0; j < classSaving.getFields().size(); j++) {
            pragma = classSaving.getFields().get(j).getName();
            parameter_type = setter.get(j).getParameterTypes()[0];
            switch (parameter_type.getTypeName()) {
                case "java.lang.String":
                    input = parameter_data.getValue(pragma);
                    break;
                case "java.util.Date":
                    input = LOCAL_DATETIME_FORMAT.parse(parameter_data.getValue(pragma));
                    break;
                case "adt.ArrList":
                    input = new ArrList(parameter_data.getValue(pragma).split(","));
                    break;
                case "double":
                    input = Double.parseDouble(parameter_data.getValue(pragma));
                    break;
                case "int":
                    input = Integer.parseInt(parameter_data.getValue(pragma));
                    break;
                case "boolean":
                    input = parameter_data.getValue(pragma) == null ? false : true;
                    break;
                default:
                    input = parameter_data.getValue(pragma);
                    break;
            }
            if (input != null) {
                setter.get(j).invoke(entity, input);
            }
        }

        String id = IDManager.generateId(entity, true).toString();
        setter.get(identifier_index).invoke(entity, id);

        // Console display data
        sb.append(xenum.OutputColor.TEXT_PURPLE).append('\n').append(parameter_data);

        // update a record to csv
        entity.addThisToCsv();

        // reset console color and return
        sb.append(xenum.OutputColor.TEXT_RESET);
        return sb;
    }

}
