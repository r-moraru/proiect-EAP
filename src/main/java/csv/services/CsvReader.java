package csv.services;

import entities.Chitara;
import entities.Claviatura;
import entities.Diverse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReader {
    public static CsvReader instance = null;

    public static CsvReader getInstance() {
        if (instance == null)
            instance = new CsvReader();
        return instance;
    }

    private CsvReader() {}

    public <T> List<T> readAll(Class<T> type, String fileName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        List<T> obiecte = new ArrayList<>();

        // Iau lista cu toate campurile obiectului pe care vreau sa il scriu
        List<Field> allFields = new ArrayList<>();
        if (type == Chitara.class || type == Claviatura.class || type == Diverse.class) {
            allFields.addAll(Arrays.asList(type.getSuperclass().getDeclaredFields()));
        }

        allFields.addAll(Arrays.asList(type.getDeclaredFields()));

        // trec peste header
        reader.readLine();

        String line;
        while ((line = reader.readLine()) != null) {
            // pentru fiecare linie, o despart, creez un obiect nou din tipul dorit,
            // ii setez campurile dupa cum apare pe linie
            List<String> row = Arrays.asList(line.split(","));
            T newObject = type.getDeclaredConstructor().newInstance();

            for (int i = 0; i < allFields.size(); i++) {
                // System.out.println("Field type is '" + allFields.get(i).getType().getName()
                //         + "'");
                assign(newObject, allFields.get(i), row.get(i));
            }

            obiecte.add(newObject);
        }

        return obiecte;
    }

    private <T> void assign(T object, Field field, String value) throws IllegalAccessException {
        String type = field.getType().getName();

        field.setAccessible(true);

        if (type.equals("java.lang.Long")) {
            field.set(object, Long.parseLong(value));
        }
        else if (type.equals("java.lang.Integer")){
            field.set(object, Integer.parseInt(value));
        }
        else if (type.equals("java.lang.Double")) {
            field.set(object, Double.parseDouble(value));
        }
        else if (type.equals("java.lang.Boolean")) {
            field.set(object, Boolean.parseBoolean(value));
        }
        else if (type.equals("java.lang.String")) {
            field.set(object, value);
        }
        else if (type.equals("entities.Chitara$Tip")) {
            field.set(object, Chitara.Tip.valueOf(value));
        }
        else if (type.equals("entities.Chitara$Lemn")) {
            field.set(object, Chitara.Lemn.valueOf(value));
        }
        else if (type.equals("entities.Claviatura$Tip")) {
            field.set(object, Claviatura.Tip.valueOf(value));
        }
        else if (type.equals("java.util.Date")) {
            try {
                field.set(object,
                        new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy").parse(value));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.printf("Unrecognized type '" + type + "'");
        }

        field.setAccessible(false);
    }
}
