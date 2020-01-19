/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.subsystems.DrivetrainForCurvatureDrive;

public class CurvatureDrive extends CommandBase {
  /**
   * Creates a new CurvatureDrive.
   */
  final DrivetrainForCurvatureDrive curvaturedrive;
  final OI oi;
  public CurvatureDrive(DrivetrainForCurvatureDrive curvaturedrive, OI oi) 
  {
    this.curvaturedrive = curvaturedrive;
    this.oi = oi;
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    addRequirements(curvaturedrive);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    curvaturedrive.curvatureDrive(oi.getCurvatureForward(), oi.getCurvatureRotate(), oi.isQuickTurn());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    curvaturedrive.curvatureStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
