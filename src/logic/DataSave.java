package logic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataSave {

    public void DataSave (int[] x, int[] y, int numberOfInput, String givenPath, String saveName){
        String path = System.getProperty(givenPath);
        saveName = saveName + ".csv";
        Path savedFile = Paths.get(path, saveName);

        try(BufferedWriter input = Files.newBufferedWriter(savedFile)){
            String line;

            for(int i = 0; i < numberOfInput; i++){
                line = String.format(x[i] + ";" + y[i] + "%n");
                input.write(line);
            }
        }
        catch(IOException ex){
            System.out.printf(ex.getMessage());
        }
    }
}
