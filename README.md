## Source Code for FRC Team 5036 for The 2020-Infinite-Recharge Season 
Team 5036's 2020 FRC Robot code. The code is written in Java and is based off WPILib's Java Control System. 

### Highlights of the Code
* Control Systems 

  For the 2020 season, a [PID](https://github.com/frc-5036/2020-Infinite-Recharge/blob/master/src/main/java/frc/robot/extra/PID.java) system was used for the autonomous code. They were used to detect whether or not the robot had reached the targeted distance. Additionally, **Bang Bang** was used to check whether the shooting mechanism was up to speed or not. 

* Limelight-Based Vision System for Target Detection

  This year the team decided to use limelight to accurately shoot at the target, and the usage of the vision system made it extremely easy to shot at the target. Additionally, on a press of a button the robot was able to reach the desired distance from the target and the desired angle to shoot the ball. 

* Automatic Shooter 

  On a press of a button the shotter was moter starts spinning upto the optimal RPM and if the Vision Systems detects that it is at the right distance and the right angle, then it will automaticaly shoot the ball without any human instruction. 
  
* Automatic Intake System 
  
    To prevent intaking more than 5 balls, we added a sensor by the intake that would count how many balls the robot has in the system at that instance. If there are 5 balls then the intake button will not put down the intake. When the ball is shot out of the robot, only then the intake will work again. 
    
* LED Signals

  The LEDs provided crucial information, and depending the LED the driver got information on what is happening with the robot. For example, the LEDs would turn red if the Shooter has reached the optimal speed and is ready to shoot. Another example was that the colour for the LEDs would change based on how many balls are in the robot.  
