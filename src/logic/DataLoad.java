package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public interface DataLoad {

    public static String[] Load () {

        String file = "src\\Outcomes\\Outcomes.csv";
        BufferedReader reader = null;
        String line = "";
        String[] data = null;

        try{
            reader = new BufferedReader(new FileReader(file));
            int i = 0;
            while((line = reader.readLine()) != null){
                data[i] = line;
                i++;
                System.out.println(data[i]);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            try{
                reader.close();
                return data;
            }
            catch (IOException e){
                e.printStackTrace();
                return null;
            }
        }
    }
}
