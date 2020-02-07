/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.customInterfaces.DriveController;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDriveForTesting extends CommandBase {
  /**
   * 
   * 
   * Creates a new ArcadeDrive.
   */
  final Drivetrain drivetrain;
  final DriveController driveController;

  
  public ArcadeDriveForTesting(Drivetrain drivetrain, DriveController driveController)
  {
    this.drivetrain = drivetrain;
    this.driveController = driveController;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    addRequirements(drivetrain);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
   drivetrain.arcadeDrive(driveController.getForward(), driveController.getRotate());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    drivetrain.Stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
