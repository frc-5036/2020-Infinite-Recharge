/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.driveCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDrive extends CommandBase {
 /**
  *
  *
  * Creates a new ArcadeDrive.
  */
 final Drivetrain drivetrain;
 final OI oi;


 public ArcadeDrive(Drivetrain drivetrain, OI oi)
 {
   this.drivetrain = drivetrain;
   this.oi = oi;
   addRequirements(drivetrain);
   System.out.println("Coming from DrivetrainCommand Constuctor");
   // Use addRequirements() here to declare subsystem dependencies.
 }

 // Called when the command is initially scheduled.
 @Override
 public void initialize()
 {
   System.out.println("Coming from DrivetrainCommand");
 }

 // Called every time the scheduler runs while the command is scheduled.
 @Override
 public void execute()
 {
  drivetrain.arcadeDrive(oi.getForward(), oi.getRotate());
 }

 // Called once the command ends or is interrupted.
 @Override
 public void end(boolean interrupted)
 {
   
 }

 // Returns true when the command should end.
 @Override
 public boolean isFinished() {
   return false;
 }
}
