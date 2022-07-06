package group18;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


import static group18.Algorithm.getPrediction;

public class FrameTest extends JFrame {
    public static int sizeofrealcases =0 ;
    public static int sizeofrealdeaths =0 ;
  //  String csvFile = "group18/Cases_3_March_2021.csv";
    InputStream csvFile =new FileInputStream("C:\\Users\\micha\\IdeaProjects\\ce291_team18\\src\\group18\\Cases_3_March_2021.csv");
    InputStream csvFileDeaths =  new FileInputStream("C:\\Users\\micha\\IdeaProjects\\ce291_team18\\src\\group18\\Deaths_3_March_2021.csv");

   // File file = new File(csvFile);
   Reader reader = new InputStreamReader(csvFile);  Reader reader1 = new InputStreamReader(csvFileDeaths);
   BufferedReader br =  new BufferedReader(reader); BufferedReader br1 =  new BufferedReader(reader1);


    String delimeter = ",";
    String line;
    String[] Arr;
    Integer ar;
    static ArrayList<Integer> handCopy = new ArrayList<Integer>();
    static ArrayList<Integer> handCopy_death = new ArrayList<Integer>();


    ArrayList<String> cases = new ArrayList<String>();
    ArrayList<String> deaths = new ArrayList<String>();
    ArrayList<Integer> cases1 = new ArrayList<Integer>();
    ArrayList<Integer> deaths1 = new ArrayList<Integer>();
    ArrayList<Integer> predictionOf= new ArrayList<>(); ArrayList<Integer> predictionOf_death= new ArrayList<>();
    ArrayList<Integer> revArrayList = new ArrayList<Integer>();
    ArrayList<Integer> revArrayList_death = new ArrayList<Integer>();

    ArrayList<Integer> prediction_array = new ArrayList<>();
    ArrayList<Integer> prediction_array_death = new ArrayList<>();

    private static String country = "";

    public FrameTest() throws IOException {



        // Create buttons for the frame //
        JLabel label = new JLabel("Choose the country to show data");
        //JButton importData = new JButton("Import Data");
        JButton showData = new JButton("Show Data");

        JButton showDataDisplay = new JButton("Display Data");

        JButton england = new JButton("England");
        JButton wales = new JButton("Wales");
        JButton scotland = new JButton("Scotland");
        JButton nireland = new JButton("N. Ireland");

        JPanel btnPanel = new JPanel();

        //btnPanel.add(importData);
        btnPanel.add(showData);

        btnPanel.add(showDataDisplay);
        add(btnPanel, BorderLayout.SOUTH);

        JPanel topBtnPanel = new JPanel();


        String choices[] = {"Select Country ", "England", "Wales", "Scotland", "Northern Ireland"};
        JComboBox<String> dropdown = new JComboBox<String>(choices);
        dropdown.setVisible(true);
        dropdown.setSelectedItem(" ");
        dropdown.getSelectedItem();
        topBtnPanel.add(label);
        topBtnPanel.add(dropdown);

        add(topBtnPanel, BorderLayout.NORTH);

        //create an area to display data once read//

        JTextArea dispOut = new JTextArea(20, 20);
        JScrollPane dispOutPanel = new JScrollPane(dispOut);
        dispOutPanel.setPreferredSize(new Dimension(100, 500));
        add(dispOutPanel, BorderLayout.CENTER);

        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Start Frame");
        setVisible(true);
        //setResizable(false);

        //read csv file//
        CSVReader.read(br, country);
        CSVReader.read(br1, country);

        //create Button Handler classes to action button presses//

        class ButtonHandler implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                   /* if (e.getSource() == importData) {
                        JOptionPane.showMessageDialog(null, "Feature not implemented.");
                    }*/


                if (e.getSource() == showData) {
                    dispOut.setText("");
                    dispOut.append(CSVReader.read(br, country));
                    if ((country == "england") || (country == "nireland") ||
                            (country == "scotland") || (country == "wales")) {
                        JOptionPane.showMessageDialog(null, "Data displayed!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Data not displayed!");
                    }

                }





                else if (e.getSource() == showDataDisplay) {
                    dispose();
                    btnPanel.setVisible(false); topBtnPanel.setVisible(false);

                    dispOut.setText("");
                    cases.clear();deaths.clear();cases1.clear();deaths1.clear();prediction_array.clear();prediction_array_death.clear();
                    predictionOf.clear();predictionOf_death.clear(); handCopy.clear();handCopy_death.clear();


                    cases = CSVReaderCaseRtn.read(br, country);
                    deaths = CSVReaderCaseRtn.read(br1, country);

                    // System.out.println(Arrays.toString(casesArr.toInt()));

                    //Arrays for Cases and Deaths to be used in the graph







                    for (int i = cases.size() - 1; i >= 0; i--) {
                        int strToInt = Integer.parseInt(cases.get(i));
                        cases1.add(strToInt);
                        sizeofrealcases += 1 ;
                    }


                    for (int i = deaths.size() - 1; i >= 0; i--) {
                        int strToInt = Integer.parseInt(deaths.get(i));
                        deaths1.add(strToInt);
                        sizeofrealdeaths += 1 ;
                    }









                    DrawGraph a = new DrawGraph();

                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                                /* I made the whole draw graph class take in two functions
                                    as a parameter and now it plots two graph lines in two different colors
                                    using two different arrays.
                                    Hope that's clear :p
                                     -- Ratnesh
                                */
                            DrawGraph.createAndShowGui( cases1,deaths1);


                        }

                        /* Saw you used cases1 here as the arraylist for the graph, I'm not sure what you need for the deaths array but I've created the exact same component, called deaths1 for deaths - Chris*/
                    });


                    // using array sizes parsing it adding to handcopy arrays
                    // later used in prediction
                    // - Ratnesh
                    int pr = cases1.size() -1; int pr_death = deaths1.size() -1;
                    int pr1 = pr -31 ;      int pr1_death = pr_death -31 ;


                    for (int x = pr; x > pr1; x--) {
                        ar = cases1.get(x);
                        Integer.parseInt(String.valueOf(ar));
                        handCopy.add(ar);
                    }

                    for (int x = pr_death; x > pr1_death; x--) {
                        ar = deaths1.get(x);

                        Integer.parseInt(String.valueOf(ar));
                        handCopy_death.add(ar);
                    }





                    double lastcase, coeff;  double lastcase_death, coeff_death;
                    int predictedcase ;    int predictedcase_death ;

                    int n  = FrameTest.handCopy.size(); int n1 = FrameTest.handCopy_death.size();







                    // cases added from handcopy to prediction  array
                    for (int e1 : FrameTest.handCopy) {
                        predictionOf.add(e1);
                    }

                    for (int i = predictionOf.size() - 1; i >= 0; i--) {

                        // Append the elements in reverse order
                        revArrayList.add(predictionOf.get(i));
                    }


                    // deaths added from handcopy death_array to prediction death array
                    for (int e1 : FrameTest.handCopy_death) {
                        predictionOf_death.add(e1);
                    }

                    // Reversing it

                    for (int i = predictionOf_death.size() - 1; i >= 0; i--) {

                        // Append the elements in reverse order
                        revArrayList_death.add(predictionOf_death.get(i));
                    }




                    // getting the coefficients of deaths and cases - Ratnesh
                    coeff = getPrediction(revArrayList); coeff_death = getPrediction(revArrayList_death);
                    // System.out.println(coeff); System.out.println(coeff_death);


                    /// cases prediction ADDED to prediction array  - Ratnesh
                    for (int i = 0; i < 42; i++) {
                        lastcase = revArrayList.get(revArrayList.size() -1 )  ; //gets the last element from the array

                        predictedcase = (int) (lastcase + (lastcase * coeff / 100));  //predicts next day cases



                        double x = predictedcase;   //-


                        revArrayList.set(n-1, (int) x); //adds the last case to the array    //-7
                        prediction_array.add(predictedcase) ;

                    }



                    // death prediction ADDED to prediction array death - Ratnesh
                    for (int i = 0; i < 42; i++) {
                        lastcase_death = revArrayList_death.get(revArrayList_death.size() -1 )  ; //gets the last element from the array

                        predictedcase_death = (int) (lastcase_death + (lastcase_death * coeff_death/ 100));  //predicts next day cases



                        double x = predictedcase_death;   //-


                        revArrayList_death.set(n1-1, (int) x); //adds the last case to the array    //-7
                        prediction_array_death.add(predictedcase_death) ;

                    }
                    //  System.out.println("Prediction array is " + prediction_array);


                    // appending prediciton to real array cases
                    cases1.addAll(prediction_array);

                    deaths1.addAll(prediction_array_death);



                }

                //SHOW DATA DISPLAY ENDS


                if (e.getSource() == dropdown) {

                    JComboBox dropdown = (JComboBox) e.getSource();
                    String s = (String) dropdown.getSelectedItem();

                    if (s.equals("England")) {
                        country = "england";
                        dispOut.setText("");
                    } else if (s.equals("Scotland")) {
                        country = "scotland";
                        dispOut.setText("");
                    } else if (s.equals("Northern Ireland")) {
                        country = "nireland";
                        dispOut.setText("");
                    } else if (s.equals("Wales")) {
                        country = "wales";
                        dispOut.setText("");
                    } else if (s.equals("Select Country ")) {
                        JOptionPane.showMessageDialog(null, "Please select a country");
                    }
                }
            }
        }

        showData.addActionListener(new ButtonHandler());

        showDataDisplay.addActionListener(new ButtonHandler());

        dropdown.addActionListener(new ButtonHandler());


//        while ((line = br.readLine()) != null) {
//            Arr = line.split(delimeter);
//            CSVReader.cases.add(Arr[4]);
//        }

    }
}
