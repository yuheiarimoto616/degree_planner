# My Personal Project

#### Yuhei Arimoto (#36561967)

## Degree Planner Project

Project Description: <br>
My project is to design a **degree planner application**. Its main purpose is 
to *help students plan their degree at UBC*. In the application, users can 
add courses they have completed, are taking, or planning to take to the application. They 
can view the courses they added. In addition to that, they can see average grade of all the courses they've 
completed and letter grade for each course. <br>
The target users are **university students**. And the reason why this project is of interest
to me is I myself want to plan my degree, and I believe many students can benefit
from the application by making the degree planning easier. 

## User Stories: 
  - As a user, I want to be able to add a course to my degree planner
  - As a user, I want to be able to delete a course from my planner
  - As a user, I want to be able to view the list of courses I've added 
to my planner with grades, its status, and number of credits.
  - As a user, I want to be able to mark a course as completed, currently
  taking, or planning
  - As a user, I want to be able to add grade to a course when completed
  - As a user, I want to be able to see the average grade of all the courses I've completed<br> <br>
  - As a user, when I select to save, I want to be able to save my degree planner to file.
  - As a user, when I select to load, I want to be able to load 
my saved degree planner from file.

## Phase 4: Task 2
- CPSC 110 added to Degree Planner.
- CPSC 110's number of credits changed to 4.
- CPSC 110's status changed to Planning.
- CPSC 110's grade changed to 70%.
- CPSC 110 deleted from Degree Planner.
- CPSC 221 changed to CPSC 213.

## Phase 4: Task 3
- Based on UML class design, I don't think there is any refactoring I can do to improve my design because adding
more classes, interfaces, and abstract classes are unnecessary for my application as it is pretty simple. 
However, further development/improvement of my application (for example, adding more features) would require 
refactoring. But one thing I could do is that making more classes like LoadButton to make GUI class simpler. 
For example, I could extract setUpTable method in GUI class as new class that extends JTable. 



### Citations:
Learned how to use scanner:<br>
TellerApp & JsonSerializationDemo <br>

UBC Logo: <br>
https://brand.ubc.ca/guidelines/downloads/logos-signatures-and-visual-identity-assets/ <br>
https://www.freelogovectors.net/ubc-logo-university-of-british-columbia/ <br>
https://brand.ubc.ca/guidelines/downloads/ubc-colour-and-fonts/ <br>

Java Swing Tutorial (including JLabel, JButton, JTextField): <br>
https://www.youtube.com/watch?v=Kmgo00avvEw <br>
https://web.mit.edu/6.005/www/sp14/psets/ps4/java-6-tutorial/components.html <br>

Source for SplashWindow: <br>
https://www.infoworld.com/article/2077467/java-tip-104--make-a-splash-with-swing.html <br>
https://www.roseindia.net/tutorial/java/swing/splashScreen.html <br>

Sources for JTable (tutorial on JTable): <br>
https://www.youtube.com/watch?v=iMBfneE2Ztg <br>
https://www.youtube.com/watch?v=22MBsRYuM4Q <br>
https://1bestcsharp.blogspot.com/2015/05/java-jtable-add-delete-update-row.html <br>
https://stackoverflow.com/questions/3549206/how-to-add-row-in-jtable <br>
https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#data <br>
https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable

JOptionPane: <br>
https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html <br>

Tutorial on Iterator:<br>
https://www.w3schools.com/java/java_iterator.asp <br>

Learned how to customize operation for setDefaultCloseOperation: <br>
https://stackoverflow.com/questions/6084039/create-custom-operation-for-setdefaultcloseoperation