package benchmarker.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CSVUtils {

    private final File file;

    private final ArrayList<String[]> contents;
    private String[] header;

    public CSVUtils(File file) {
        this.file = file;
        contents = new ArrayList<>();
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public boolean addContent(String[] content) {
        if (header.length == content.length) {
            contents.add(content);
            return true;
        }
        return false;
    }

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
