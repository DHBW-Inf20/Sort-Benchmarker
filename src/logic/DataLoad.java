package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataLoad {

    public void DataLoad () {

        String file = "src\\logic\\Outcomes.csv";
        BufferedReader reader = null;
        String line = "";

        try{
            reader = new BufferedReader(new FileReader(file));

            while((line = reader.readLine()) != null){

                String[] row = line.split(",");

                for(String index : row){

                    System.out.printf(index);
                }

                System.out.println();
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            try{
                reader.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
