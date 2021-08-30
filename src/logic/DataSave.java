package logic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public interface DataSave {

    public static void Save (String algorithm, int numberOfInputs, float maxTime, float minTime, float meanTime, float deviation, float range, boolean timeBenchmark){

        Path saveFile = Paths.get("src\\Outcomes", "Outcomes.csv");

        try(BufferedWriter input = Files.newBufferedWriter(saveFile)){

            StringBuilder data = new StringBuilder();

            data.append("Algorithm;");
            data.append("Number of Inputs;");

            if (timeBenchmark) {
                data.append("Max. time duration;");
                data.append("Min. time duration;");
                data.append("Mean time duration\n");
            } else {
                data.append("Experimental standard deviation;");
                data.append("Range\n");
            }
            data.append(algorithm + ";");
            data.append(numberOfInputs + ";");
            data.append(maxTime + ";");
            data.append(minTime + ";");
            data.append(meanTime + ";");
            data.append(deviation + ";");
            data.append(range + '\n');

            input.write(data.toString());
        }
            catch(IOException ex){
            System.out.printf(ex.getMessage());
        }
    }
}
