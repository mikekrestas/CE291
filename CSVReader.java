package group18;
import java.io.*;
import java.util.ArrayList;

public class CSVReader extends FrameTest {

    String country;
    public static final String delimeter = ",";
    public static ArrayList<String> cases = new ArrayList<String>();

    public CSVReader() throws IOException {
    }

    public static String read(BufferedReader csvfile, String country)

    {
        try {

            BufferedReader br = new BufferedReader(csvfile);
//            File file = new File(csvfile);
//            FileReader fr = new FileReader(file);
//            BufferedReader br = new BufferedReader(fr);
            String line;
            String area = "";
            String[] Arr;


            if(country.equals("wales")){
                while ((line = br.readLine()) != null) {

                    Arr = line.split(delimeter);
                    if (Arr[1].equals("Wales") || Arr[1].equals("areaName") ) {
                        for (String str : Arr) {
                            System.out.print(str + " ");
                            area += str + " | "; //add string to new string to be displayed in text area//

                        }
                        area += "\n";


                        cases.add(Arr[4]);

                /*
                Selective Display, remove for loop.

                System.out.println("Date: " + Arr[3]);
                System.out.println("Daily Cases: " + Arr[4]);
                System.out.println("Total Cases: " + Arr[5]);

                System.out.println();
                 */
                    }
                }
            }
            else if(country.equals("scotland")){
                while ((line = br.readLine()) != null) {

                    Arr = line.split(delimeter);
                    if (Arr[1].equals("Scotland")|| Arr[1].equals("areaName") ) {
                        for (String str : Arr) {
                            System.out.print(str + " ");
                            area += str + " | "; //add string to new string to be displayed in text area//
                        }
                        area += "\n";

                        cases.add(Arr[4]);

                    }
                }
            }
            else if(country.equals("nireland")){
                while ((line = br.readLine()) != null) {

                    Arr = line.split(delimeter);
                    if (Arr[1].equals("Northern Ireland")|| Arr[1].equals("areaName") ) {
                        for (String str : Arr) {
                            System.out.print(str + " ");
                            area += str + " | "; //add string to new string to be displayed in text area//
                        }
                        area += "\n";

                        cases.add(Arr[4]);

                    }
                }
            }

            else if(country.equals("england")){
                while ((line = br.readLine()) != null) {

                    Arr = line.split(delimeter);
                    if (Arr[1].equals("England")|| Arr[1].equals("areaName") ) {
                        for (String str : Arr) {
                            System.out.print(str + " ");
                            area += str + " | "; //add string to new string to be displayed in text area//
                        }
                        area += "\n";

                        cases.add(Arr[4]);

                    }
                }
            }
          //  br.close();

            //System.out.println(cases);
            return area;

        }catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }
}
