/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.json;

import adt.XArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.opencsv.bean.CsvBindByName;
import entity.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import main.WebConfig;

/**
 *
 * @author Lim Sai Keat
 */
public class CreateDisplayClassJson {

    private final XArrayList<Class> class_s = new XArrayList<Class>();

//    public static void main(String[] args) {
//        ClassSaving c = new ClassSaving();
//        new CreateDisplayClassJson().readData(WebConfig.PROJECT_URL + "data/access.json");
//    }
    public void generateJsonFile() throws IOException {
        XArrayList<ClassSaving> data = new XArrayList<ClassSaving>();
        addAllClassToList();
        for (Class cs : class_s) {
            data.add(getCS(cs));
        }

        Gson gson = new Gson();
        String json = gson.toJson(data);
        System.out.print(json);
        String dir = WebConfig.PROJECT_URL + "data/access.json";
        File jsonFile = new File(dir);
        // write new json string into jsonfile1.json file
        try (OutputStream outputStream = new FileOutputStream(jsonFile)) {
            outputStream.write(gson.toJson(data).getBytes());
            outputStream.flush();
        }
    }

    public XArrayList<ClassSaving> readData(String dir) {
        try {
            Gson gson = new Gson();
            Type typeMyType = new TypeToken<XArrayList<ClassSaving>>() {
            }.getType();
            XArrayList<ClassSaving> data;
            JsonReader reader = new JsonReader(new FileReader(dir));
            data = gson.fromJson(reader, typeMyType);
            return data;
        } catch (FileNotFoundException ex) {

        }
        return null;
    }

    public void addAllClassToList() {
        class_s.add(Chat.class);
        class_s.add(Chats.class);
        class_s.add(Mapping.class);
        class_s.add(Booking.class);
        class_s.add(Payment.class);
        class_s.add(Review.class);
        class_s.add(Customer.class);
        class_s.add(Driver.class);
        class_s.add(Withdraw.class);
        class_s.add(Plate.class);
        class_s.add(Car.class);
        class_s.add(Taxi.class);
    }

    public ClassSaving getCS(Class cls) {
        ClassSaving result = new ClassSaving();
        XArrayList<FeildAccessbility> fields = new XArrayList<FeildAccessbility>();
        XArrayList<Field> field = new XArrayList(cls.getDeclaredFields());
        Class<?> current = cls;
        FeildAccessbility fieldAccess;
        while (current.getSuperclass() != null) {
            current = current.getSuperclass();
            field.addAll(current.getDeclaredFields());
        }
        for (Field f : field) {
            if (f.isAnnotationPresent(CsvBindByName.class)) {
                fieldAccess = new FeildAccessbility();
                fieldAccess.setDisplay(true);
                fieldAccess.setUpdate(true);
                fieldAccess.setAuto_inc(false);
                fieldAccess.setType(f.getType().getName());
                fieldAccess.setName(f.getName());
                fields.add(fieldAccess);
            }
        }
        result.setAdd(true);
        result.setFields(fields);
        result.setClassname(cls.getName());
        result.setExtension(".csv");
        return result;
    }
}
