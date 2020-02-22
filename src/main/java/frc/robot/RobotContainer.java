/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.EmptyCommand;
import frc.robot.commands.intake.DefaultIntakeCommand;
import frc.robot.commands.driveCommands.ArcadeDrive;
import frc.robot.commands.driveCommands.CurvatureDrive;
import frc.robot.commands.ExampleCommand;
//import frc.robot.commands.limelight.AutoAim;
import frc.robot.commands.shooter.RunShooter;
import frc.robot.commands.shooter.shooterSeq.shooterSeq;
import frc.robot.extra.Constants;
import frc.robot.extra.LED;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;

import java.util.Arrays;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public final Drivetrain m_drivetrain = Drivetrain.createForRobot();
  public final Intake m_intake = Intake.createForIntake();
  private final OI m_oi = new OI();
  public final Shooter m_shooter = Shooter.createForRobot();
  public final Indexer m_indexer = Indexer.createForRobot();
  //public final Limelight m_limelight = new Limelight();
  public final LED m_LED = new LED();
  //public final Climber m_climber = Climber.createForRobot();

  //Commands 
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final ArcadeDrive m_arcadeDrive = new ArcadeDrive(m_drivetrain, m_oi);
  private final CurvatureDrive m_curvatureDriveCommand = new CurvatureDrive(m_drivetrain, m_oi);
  private final DefaultIntakeCommand m_defaultIntakeCommand = new DefaultIntakeCommand(m_intake, m_oi);
  //private final ClimbingCommand m_climbingCommand = new ClimbingCommand(m_climber,m_oi);
  private final RunShooter m_runShooter = new RunShooter(6500, m_shooter);
  private final shooterSeq m_runIndexerSeq = new shooterSeq(m_intake, m_indexer);
 // private final AutoAim m_autoAim = new AutoAim(m_drivetrain, m_oi);

  //Buttons
  private final RunCommand m_stopShooter = new RunCommand(() -> m_shooter.stopShooters(), m_shooter);
  private final RunCommand m_resetEnc = new RunCommand(()->m_shooter.resetPostionEnc(), m_shooter);
  private final RunCommand m_indexerReverse = new RunCommand(()->m_indexer.runIndexer(-0.8, -0.3), m_indexer);
  private final RunCommand m_ghettoButton = new RunCommand(()->m_indexer.runIndexer(0.4,0));
  //private final RunCommand m_autoGhetto = new RunCommand(()-> m_drivetrain.aimTurn(m_drivetrain.aimingWithVision()));





  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_drivetrain.setDefaultCommand(m_curvatureDriveCommand);
    m_intake.setDefaultCommand(m_defaultIntakeCommand);
    m_shooter.setDefaultCommand(new RunCommand(() -> m_shooter.setPower(m_oi.getManualShooter()), m_shooter));
    m_indexer.setDefaultCommand(new RunCommand(() -> m_indexer.runIndexer(0, 0), m_indexer));
    //sm_limelight.setDefaultCommand(m_autoAim);
    //m_climber.setDefaultCommand(m_climbingCommand);

  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings()
  {
    new JoystickButton(m_oi.operatorController.getJoystick(), 2).whileHeld(m_runIndexerSeq);
    new JoystickButton(m_oi.operatorController.getJoystick(),1).whenPressed(m_runShooter);
    new JoystickButton(m_oi.operatorController.getJoystick(),1).whenReleased(m_stopShooter);
    new JoystickButton(m_oi.operatorController.getJoystick(), 3).whileHeld(m_indexerReverse);
   // new JoystickButton(m_oi.operatorController.getJoystick(),5).whileHeld(new AutoAim(m_drivetrain,m_oi));


    //new JoystickButton(m_oi.operatorController.getJoystick(), 6).whenPressed(m_resetEnc);
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    // An ExampleCommand will run in autonomous
    TrajectoryConfig config = new TrajectoryConfig(Constants.MAX_VELOCITY, Constants.MAX_ACCELERATION);
    config.setKinematics(m_drivetrain.getKinematics());


    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(Arrays.asList(new Pose2d(), new Pose2d(1.0, 0, new Rotation2d())), config);


    RamseteController ramseteController = new RamseteController(2.0, 0.7);
    RamseteCommand ramseteCommand = new RamseteCommand(
            trajectory,
            m_drivetrain::getPose,
            ramseteController,
            m_drivetrain.getFeedforward(),
            m_drivetrain.getKinematics(),
            m_drivetrain::getSpeeds,
            m_drivetrain.getLeftPIDController(),
            m_drivetrain.getRightPIDController(),
            m_drivetrain::setOutput,
            m_drivetrain);
    return ramseteCommand;
  }
}
