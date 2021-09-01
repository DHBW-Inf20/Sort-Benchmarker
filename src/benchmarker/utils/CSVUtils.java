package benchmarker.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class CSVUtils {

    private final File file;

    private final ArrayList<String[]> contents;
    private String[] header;

    /**
     * Konstruktor zur Initialisierung der CSV-Datei und des Inhalts
     * @param file      File-Objekt zur Speicherung der Daten in CSV-Format
     */
    public CSVUtils(File file) {
        this.file = file;
        contents = new ArrayList<>();   // Leere Arrayliste für die zu speichernden Daten
    }

    /**
     * Zuweisung der Spaltenüberschrift für die CSV-Datei
     * @param header        Name der Spaltenüberschrift
     */
    public void setHeader(String[] header) {
        this.header = header;
    }

    /**
     *
     * @param content
     * @return
     */
    public boolean addContent(String[] content) {
        if (header.length == content.length) {
            contents.add(content);
            return true;
        }
        return false;
    }

    /**
     * Versuch die Daten in die CSV-Datei einzufügen.
     * @return              Wahr, wenn das Schreiben in die CSV-Datei erfolgreich.
     *                      Falsch, ansonsten.
     * @throws IOException  Fehlermeldung bei missglücktem Zugriff auf die CSV-Datei.
     */
    public boolean writeFile() throws IOException {
        if (header == null || contents.isEmpty()) {
            return false;
        }
        if (!file.exists()) {
            file.createNewFile();
        }

        try(BufferedWriter out = Files.newBufferedWriter(file.toPath())){
            StringBuilder data = new StringBuilder();

            int columns = header.length;

            for (int i = 0; i < columns; i++) {
                data.append(header[i]);
                if (i != columns - 1) {
                    data.append(";");
                }
            }
            data.append("\n");
            for (String[] content : contents) {
                for (int i = 0; i < columns; i++) {
                    data.append(content[i]);
                    if (i != columns - 1) {
                        data.append(";");
                    }
                }
                data.append("\n");
            }

            out.write(data.toString());
            out.flush();
            return true;
        } catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
