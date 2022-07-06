## Data Structures

To produce graphs from the data in the two CSV files the data must be first collected from the file and stored in a suitable way within the program.

To collect the data a Buffered Reader class is used to store the data initially. Two of these are used for the two seperate CSV files within the program, one for cases and one for deaths.

This is then passed to two seperate classes, CSVReader and CSVReaderCaseRTN. Both these classes operate similarly using a try/catch method to attempt to read the data stored in the buffered reader class provided in the class argument. If the data is located both CSV reader classes will then split the data according to the country provided, also within the class arguement. If the data is not located the class will throw an exception.

CSVReader is used for displaying the data from the CSV file directly in the program with some formatting, to allow for a user to easily distinguish the data on display. 

CSVReaderCaseRTN differs from CSVReader by returning the cases or deaths, depending on which CSV file has been read. The strings for these are then converted to an Integer and stored in an ArrayList for the DrawGraph Class to function.

## Prediction Algorithm

The prediction algorithm has been developed for the purpose of computing a coefficient. Coefficient multiplied with the latest case indicates the expected (forecasted) number of infections and deaths in the next 2, 4 and 6 week time frames.

Algorithm class contains the getPrediction(ArrayList<Integer> preferredData) method. The method takes the cases or deaths in the last thirty days as a parameter. dailyChanges array holds 29 double to keep track of the case/death differences between days. The differences are calculated in the following way;

1. For loop calls the preferredData.get(e) and preferredData.get(e + 1) to compare with each other.
2. The calculation is preferredData.get(e) - preferredData.get(e + 1)) / preferredData.get(e + 1)) * 100. Calculation produces the percentage of the increase or decrease between the days.
3. If preferredData.get(e) is greater than preferredData.get(e + 1), it means there is a decrease in the cases or deaths therefore the percentage is multiplied with -1.
4. The differences of the last thirty days are stored in dailyChanges array. Another for loop adds the differences together and divides it to the number of days to get a percentage average to predict increase or decrease.

Array of dailyChanges can be extracted from the calculation by carrying out the adding inside the for loop. The justification for the using another array is it provides flexibility for the future improvements, developments can be made just by increasing the array dimension if more data type becomes available.

Initial idea was to use more sophisticated mathematical tools to predict but unfortunately the data consists only one variable. This limits the number of things you can perform to extreme and after a long consideration process, the team decided to go with a simple but elegant and consistent solution.

## Class diagrams / Circuit Diagrams

![picture](Final/Pictures/ProjectManagement/class_diagram.JPG)

## An explanation of useful information for future maintainers of your product
Two CSV files are required to run the prediction model one for cases and another for deaths. This data can retrieved from https://coronavirus.data.gov.uk/ which is the official UK Government’s website. Both the cases and deaths stats can be accessed through this website. A copy of each case and death are already present in the folder as filed named 
Cases- Cases_3_March_2021.csv
Deaths -Deaths_3_March_2021.csv.
The absolute path to these should be provided in the FrameTest.java. If this file path is not changed based on the users path, the program wont be able to run.
The data downloaded is shown by nation so its easy to track (Scotland, England, N. Ireland, and wales) individually. The data is updated as of 3rd March 2021. Each new day the prdeiction model is run, the CSV file can be updated to the give the latest results. If this is not updated the results shown will not be useful as they do not have all the information required to get an accurate prediction at that time.
