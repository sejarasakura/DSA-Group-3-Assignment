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
import javax.servlet.http.HttpServletResponse;
import static main.Datas.TYPE_SWITCH;
import main.Functions;

/**
 *
 * @author ITSUKA KOTORI
 */
public class DeleteEntityListCilent {

    protected String pragma, type;
    protected Class<?> _class;
    protected Type parameter_type;
    protected AbstractEntity entity;
    protected Constructor constructor;
    protected ClassSaving classSaving;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected AbstractEntity acture_entity;
    protected int j, identifier_index = -99;
    protected ArrList<AbstractEntity> datas;
    protected ArrList<Method> setter = new ArrList<Method>();
    protected adt.XHashedDictionary<String, String> parameter_data
            = new adt.XHashedDictionary<String, String>();
    public boolean status = false;

    /**
     * constructor
     *
     * @param class_name
     * @param request
     */
    public DeleteEntityListCilent(String class_name, HttpServletRequest request, HttpServletResponse response) {
        try {
            _class = Class.forName("entity." + class_name);
            constructor = _class.getConstructor();
            entity = (AbstractEntity) constructor.newInstance();
            this.request = request;
            this.response = response;
            System.out.println(start_update());
        } catch (ClassNotFoundException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(EditEntity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DeleteEntityListCilent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * update
     */
    private StringBuilder start_update() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException, ClassNotFoundException {
        StringBuilder sb = new StringBuilder();

        // read data form file
        datas = new ArrList(AbstractEntity.readDataFormCsv(entity));

        // get class information
        classSaving = main.Functions.getSavingClass(entity.getClass());
        if (classSaving == null) {
            sb.append(xenum.OutputColor.TEXT_RED).append(entity.getClass()).append(" class is not found.").append(xenum.OutputColor.TEXT_RESET);
            return sb;
        }
        if (classSaving.isDelete()) {
            sb.append(xenum.OutputColor.TEXT_GREEN).append("This entity can be delete! start deleting").append(", ");
        } else {
            sb.append(xenum.OutputColor.TEXT_RED).append("This entity cannot be delete please try agian").append(", ");
            return sb;
        }

        // dynamic load the setter of each field in the class.
        for (j = 0; j < classSaving.getFields().size(); j++) {
            pragma = classSaving.getFields().get(j).getName();
            type = classSaving.getFields().get(j).getType();

            setter.add(entity.getClass().getMethod(
                    main.Functions.fieldToSetter(pragma),
                    Functions.isGeneralType(type) ? TYPE_SWITCH.getValue(type) : Class.forName(type)
            ));
            if (pragma.equals(classSaving.getIdentifier())) {
                this.identifier_index = j;
                break;
            }
        }

        // id index not found return
        if (identifier_index < 0) {
            sb.append(xenum.OutputColor.TEXT_RED).append("The id is not found").append(xenum.OutputColor.TEXT_RESET);
            return sb;
        }

        // call runtime setter with id index. and assign
        String id = request.getParameter("id");
        if (id == null) {
            sb.append(xenum.OutputColor.TEXT_RED).append("The id is not found").append(xenum.OutputColor.TEXT_RESET);
            return sb;
        }
        setter.get(identifier_index).invoke(entity, id);

        // from entity get the actural entity
        for (AbstractEntity data : datas) {
            if (data.id_equals(entity)) {
                acture_entity = data;
            }
        }

        // Console display data
        sb.append(xenum.OutputColor.TEXT_PURPLE).append(parameter_data);

        // update a record to csv
        status = acture_entity.deleteThisToCsv();

        // reset console color and return
        sb.append(xenum.OutputColor.TEXT_RESET);
        return sb;
    }

}
