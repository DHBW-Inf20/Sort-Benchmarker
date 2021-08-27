package logic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public interface DataSave {

    public static void Save (int numberOfInputs, int TimeDuration){

        Path savedFile = Paths.get("src\\logic", "Outcomes.csv");

        try(BufferedWriter input = Files.newBufferedWriter(savedFile)){
            String line = String.format(numberOfInputs + ";" + TimeDuration + "%n");
            String[] availableData = DataLoad.Load();
            int numberOfData = availableData.length;

            if (availableData != null){

                for(int i = 0; i < numberOfData; i++){
                    input.write(availableData[i]);
                }
            }
            input.write(line);
        }
        catch(IOException ex){
            System.out.printf(ex.getMessage());
        }
    }
}
