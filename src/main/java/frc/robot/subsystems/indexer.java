/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*----------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class indexer extends subsystem {
    public VictorSP IndexerMotor;

public indexer (){
    IndexerMotor = new VictorSP(RobotMap.INDEXER_MOTOR);

}
public void runIndexer ( double power) {
        IndexerMotor.set(power);
        
}
@Override
protected void initDefaultCommand() {
	// TODO Auto-generated method stub	

}
public static double pulser () {
    Timer.getMatchTime();
    double getCurrentTime = Timer.getCurrentTime ();

        if (getMatchTime % 5 == 0 ){
    
        Timer.delay (1);   
    }
	
}



