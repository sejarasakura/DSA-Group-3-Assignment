/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent.pages;

import adt.ArrList;
import entity.AbstractEntity;
import entity.json.ClassSaving;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ITSUKA KOTORI
 */
public class UpdateEntity {

    private Class<?> _class;
    private Constructor constructor;
    private AbstractEntity entity;
    private AbstractEntity acture_entity;
    private ClassSaving classSaving;
    private ArrList<AbstractEntity> datas;
    private ArrList<Method> setter = new ArrList<Method>();
    private adt.XHashedDictionary<String, String> parameter_data
            = new adt.XHashedDictionary<String, String>();
    private int j, identifier_index;
    private HttpServletRequest request;

    /**
     * constructor
     *
     * @param class_name
     * @param request
     */
    public UpdateEntity(String class_name, HttpServletRequest request) {
        try {
            _class = Class.forName("entity." + class_name);
            constructor = _class.getConstructor();
            entity = (AbstractEntity) constructor.newInstance();
            this.request = request;
            start_update();
        } catch (ClassNotFoundException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(EditEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * update
     */
    private void start_update() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        StringBuilder sb = new StringBuilder();

        // read data form file
        datas = new ArrList(AbstractEntity.readDataFormCsv(entity));

        // get class information
        classSaving = main.Functions.getSavingClass(entity.getClass());
        if (classSaving == null) {
            System.out.println(xenum.OutputColor.TEXT_RED + entity.getClass() + " class is not found." + xenum.OutputColor.TEXT_RESET);
            return;
        }

        // dynamic load the setter of each field in the class.
        for (j = 0; j < classSaving.getFields().size(); j++) {
            setter.add(entity.getClass().getMethod(main.Functions.fieldToSetter(classSaving.getFields().get(j).getName())));
            if (classSaving.getFields().get(j).isUpdate()) {
                sb.append(xenum.OutputColor.TEXT_GREEN).append(classSaving.getFields().get(j).getName()).append(", ");
            } else {
                sb.append(xenum.OutputColor.TEXT_RED).append(classSaving.getFields().get(j).getName()).append(", ");
            }
            if (classSaving.getFields().get(j).getName().equals(classSaving.getIdentifier())) {
                this.identifier_index = j;
            }
        }

        // call runtime setter with id index. and assign
        setter.get(identifier_index).invoke(entity, request.getParameter(classSaving.getFields().get(identifier_index).getName()));

        //
        for (AbstractEntity data : datas) {
            if (data.id_equals(entity)) {
                acture_entity = data;
            }
        }
        for (j = 0; j < classSaving.getFields().size(); j++) {
            if (classSaving.getFields().get(j).isUpdate()) {
                setter.get(j).invoke(acture_entity, request.getParameter(classSaving.getFields().get(j).getName()));
            }
        }

        sb.append(xenum.OutputColor.TEXT_RESET);
        System.out.println(sb.toString());
    }

}
