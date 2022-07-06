package group18;

import java.io.*;
import java.util.ArrayList;

public class CSVReaderCaseRtn {

    String country;

    public static final String delimeter = ",";
    public static ArrayList<String> read(BufferedReader csvfile, String country)
    {
        try {

            BufferedReader br =  new BufferedReader(csvfile);
            String line;
            String area = "";
            String[] Arr;
            ArrayList<String> cases = new ArrayList<String>();

            if(country.equals("wales")){
                while ((line = br.readLine()) != null) {
                    Arr = line.split(delimeter);
                    if(Arr[1].equals("Wales")) {
                        for (String str : Arr) {
                            // System.out.print(str + " ");
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
                    if (Arr[1].equals("Scotland") ) {
                        for (String str : Arr) {
                            //  System.out.print(str + " ");
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
                    if (Arr[1].equals("Northern Ireland") ) {
                        for (String str : Arr) {
                            //  System.out.print(str + " ");
                            area += str + " | "; //add string to new string to be displayed in text area//
                        }
                        area += "\n";

                        cases.add(Arr[4]);

                    }
                }
            }
            else if(country.equals("england")){while ((line = br.readLine()) != null) {

                Arr = line.split(delimeter);
                if (Arr[1].equals("England") ) {
                    for (String str : Arr) {
                        //   System.out.print(str + " ");
                        area += str + " | "; //add string to new string to be displayed in text area//
                    }
                    area += "\n";

                    cases.add(Arr[4]);

                }
            }
            }
           // br.close();

            return cases;
        }catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }
}

