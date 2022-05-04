package services;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class AuditService {
    FileWriter outStream;

    public AuditService(String fileName) throws IOException {
        this.outStream = new FileWriter(fileName, true);
    }

    public void log(String command) throws IOException {
        outStream.append(command + "," +
                LocalDate.now() + "," +
                LocalTime.now() + "\n");

        outStream.flush();
    }
}
