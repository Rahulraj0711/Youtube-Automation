# QA Assignment - Automate YouTube - Rahul Raj 

## Introduction:
Automate the youtube and check the Films, Music and News tab. Use DataProvider to Search for various items from a data-set.

## Prerequisites:
1. Java
2. Java Data Handling (Using Hashmaps)
3. Selenium
4. Locators
5. Xpath
6. Buttons
7. Text Box
8. Hyperlink
9. Waits
10. Exceptions
11. TestNG
12. Keyboard and Mouse Actions
13. Data Providers
14. Apache POI

## Scenario:

### 1) Setup:
Integrated TestNG framework by 
(i) Modifying Build.Gradle file 
(ii) Introducing testng.xml file
(iii) Created WrapperMethods.java file which is used as a helper class to implement all actions in a test case.
(iv) Created DataHandlingHelper.java file to return the data from an excel sheet which is used as a data provider class for TestCase 5. 

### 2) TestCase 01:
1. Navigate to "www.youtube.com".
2. Applied an Assert to check whether you are on the correct URL.
3. Opened the menu and clicked on the “About” at the bottom of the sidebar.
4. Printed the message shown on the About screen.

### 3) TestCase 02:
1. Navigate to "www.youtube.com".
2. Opened the menu and clicked on the “Films” tab.
3. In the “Top Selling” section, scrolled to the extreme right.
4. Applied a Soft Assert to check whether the movie is marked “A” for mature or not.
5. Applied a Soft Assert to check whether the movie is either “Comedy” or “Animation”.

### 4) TestCase 03:
1. Navigate to "www.youtube.com".
2. Opened the menu and clicked on the “Music” tab.
3. In the 1st section, scrolled to the extreme right.
4. Printed the name of the playlist.
5. Applied a Soft Assert on whether the number of tracks listed is <= 50.

### 5) TestCase 04:
1. Navigate to "www.youtube.com".
2. Opened the menu and clicked on “News” tab.
3. Printed the title and body of the first 3 “Latest News Posts”.
4. Also printed the total sum of number of likes on all 3 of them.

### 6) TestCase 05:
1. Navigate to "www.youtube.com".
2. Searched for each of the items given in this "https://1drv.ms/x/s!AodrcZDKg1ruoC1X0pFGOK00tfAa" excel sheet (Using Apache POI).
3. Scrolled till the sum of each video’s views reach 10 Cr(i.e., >= 10 crores).
