Read Me-

1-	We have made a jar file as asked and running it is as simple as “java -jar ce291_team18.jar” from a command line. We have tested it on windows and macbook both just to be sure.
Here are the screenshots provided for simplicity.
•	There are no extra libraries needed except the standard java library.
•	The Main.java contains the main method to be run.

2-	Two CSV files are required to run the product, one for cases and another for deaths. We have derived both from https://coronavirus.data.gov.uk/ which is the official UK Government’s website.
They are already present in the folder as filed named
Cases- Cases_3_March_2021.csv
Deaths -Deaths_3_March_2021.csv	

The absolute path to these should be provided in the FrameTest.java (Right click a file in IntelliJ click copy path and then copy absolute path). Remember it works with absolute path only.

We decided to go with data by nation so its easy to track each nation (Scotland, England, N. Ireland, and wales) individually. The data is updated as of 3rd March 2021.

Image for reference below-

![picture](Final/Pictures/README/Csv.png)






3-	java -jar ce291_team18.jar”
This is tested and works, proof below. 
![picture](Final/Pictures/README/1.png)




4-	No Known Bugs with the product but the data provided for Wales by the Wales government is missing on a few days and that will display as some minor incorrections with the date.
There is no workaround here as we are dependent on the government for the data.
