# mECEge
Android app (pronounced "message") that allows users to comment in specific threads of Virginia Tech ECE classes 

## GUI Design
<p align="center">
<img src="https://github.com/ldev-r3-t4/mECEge-FINAL/blob/master/mECEge_1.png" width="200"> <img src="https://github.com/ldev-r3-t4/mECEge-FINAL/blob/master/mECEge_2.png" width="200"> <img src="https://github.com/ldev-r3-t4/mECEge-FINAL/blob/master/mECEge_3.png" width="200">
</p>

## Test

I created automated user interface tests for the front-end of the mECEge app using the instrumented unit test functionality of the Android Studio. I also utilized Google's Espresso to write reliable Android UI tests. The main purpose of the tests are to verify the functionality of the user interfaces like listview and buttons. Some of the testing capabilities include:
* Send a message and receive it back as one of the most recently stored messages
* Load (up to) 10 of the most recently stored messages 
* Load (up to) 10 more of the most recently stored messages
* Restrict input character counts to 140 characters 
* Refresh and display 10 of the most recently stored message upon refresh

You can find the test at
./app/src/androidTest/java/com/example/takondwakakusa/androidbackgroundcomm/FrontEndTest.java

You can find the video of test demo at 
./TestingVideo.mp4

## How to Run 

To run the tests, you have to run this program in Android Studio. You can use either the built-in phone emulator or an actual Android phone running Android Kitkat or newer. All you have to do is right-click the AndroidTest java directory (./app/src/androidTest/java) and select Run 'All Tests.'
