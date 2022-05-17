package csv.services;

import entities.Chitara;
import entities.Claviatura;
import entities.Diverse;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvWriter {
    public static CsvWriter instance = null;

    public static CsvWriter getInstance() {
        if (instance == null)
            instance = new CsvWriter();
        return instance;
    }

    private CsvWriter() {}

    // TODO: functie care sa printeze headerul

    public <T> void saveData(Class<T> type, T data, String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName, true);

        // Iau lista cu toate campurile obiectului pe care vreau sa il scriu
        List<Field> allFields = new ArrayList<>();
        if (type == Chitara.class || type == Claviatura.class || type == Diverse.class) {
            allFields.addAll(Arrays.asList(type.getSuperclass().getDeclaredFields()));
        }

        allFields.addAll(Arrays.asList(type.getDeclaredFields()));

        // pentru fiecare camp al obiectului, ii printez valoarea
        List<String> values = new ArrayList<>();
        allFields.forEach(
                field -> {
                    Object value;
                    try {
                        field.setAccessible(true);
                        value = field.get(data);
                    }
                    catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return;
                    }
                    values.add(value.toString());
                }
        );

        String line = String.join(",", values);
        System.out.println(line);
        writer.write(line + "\n");

        writer.close();
    }
}
