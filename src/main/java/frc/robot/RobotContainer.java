/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.limelight.EmptyLimelightCommand;
import frc.robot.commands.autoCommands.DriveStraight;
import frc.robot.commands.autoCommands.DriveStraightBangBang;
import frc.robot.commands.autoCommands.GyroPIDCommand;
import frc.robot.commands.autoRoutines.AutoLineShooter;
import frc.robot.commands.autoRoutines.DriveAndTurn;
import frc.robot.commands.climber.ClimbingCommand;
import frc.robot.commands.climber.EmptyClimberCommand;
import frc.robot.commands.driveCommands.TeleopArcadeDrive;
import frc.robot.commands.indexer.IndexerOnJoystick;
import frc.robot.commands.intake.DefaultIntakeCommand;
import frc.robot.commands.driveCommands.ArcadeDrive;
import frc.robot.commands.driveCommands.CurvatureDrive;
import frc.robot.commands.ExampleCommand;
//import frc.robot.commands.limelight.AutoAim;
import frc.robot.commands.intake.IntakeReverse;
import frc.robot.commands.limelight.AutoAim;
import frc.robot.commands.shooter.ManualShooter;
import frc.robot.commands.shooter.RunShooter;
import frc.robot.commands.shooter.shooterSeq.ShooterSeq;
//import frc.robot.extra.LED;
import frc.robot.extra.Constants;
import frc.robot.extra.ControllerConstants;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  PowerDistributionPanel pdp = new PowerDistributionPanel();



  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public final Drivetrain m_drivetrain = Drivetrain.createForRobot();
  public final Intake m_intake = Intake.createForIntake();
  private final OI m_oi = new OI();
  public final Shooter m_shooter = Shooter.createForRobot();
  public final Indexer m_indexer = Indexer.createForRobot();
  public final Limelight m_limelight = new Limelight();
  //public final LED m_LED = new LED();
  public final Climber m_climber = new Climber();

  //Commands 
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  public final ArcadeDrive m_arcadeDrive = new ArcadeDrive(m_drivetrain, m_oi);
  public final CurvatureDrive m_curvatureDriveCommand = new CurvatureDrive(m_drivetrain, m_oi);
  private final DefaultIntakeCommand m_defaultIntakeCommand = new DefaultIntakeCommand(m_intake, m_oi, m_indexer);
  private final ClimbingCommand m_climbingCommand = new ClimbingCommand(m_climber);
  private final RunShooter m_runShooter = new RunShooter(Constants.SHOOTER_RPM, m_shooter, m_intake, m_limelight);
  private final ShooterSeq m_runIndexerSeq = new ShooterSeq(m_intake, m_indexer,m_shooter);
  private final TeleopArcadeDrive m_teleopArcadeDrive = new TeleopArcadeDrive(m_drivetrain,m_oi);
  private final EmptyLimelightCommand emptyLimelightCommand = new EmptyLimelightCommand(m_limelight);
  private final EmptyClimberCommand emptyClimberCommand = new EmptyClimberCommand(m_climber);
  private final IntakeReverse intakeReverse = new IntakeReverse(m_intake);
  private final ManualShooter manualShooter = new ManualShooter(m_shooter,m_oi);
  private final IndexerOnJoystick manualIndexer = new IndexerOnJoystick(m_indexer,m_oi);
  private final AutoAim m_autoAim = new AutoAim(m_drivetrain, m_limelight, pdp);

  //Auto Commands
  private final DriveStraightBangBang m_driveStraight= new DriveStraightBangBang(m_drivetrain, 61);
  private final DriveAndTurn m_driveAndTurn = new DriveAndTurn(m_drivetrain,65.066,-11);
  private final AutoLineShooter m_autoLineShooter = new AutoLineShooter(m_indexer,m_shooter,m_drivetrain,30,m_intake);
  private final GyroPIDCommand gyroPIDCommand = new GyroPIDCommand(m_drivetrain,95);
  private final DriveStraight PIDDrive = new DriveStraight(m_drivetrain, 43.27); // 43.27

  //Buttons
  private final RunCommand m_stopShooter = new RunCommand(() -> m_shooter.stopShooters(), m_shooter);
  private final RunCommand m_indexerReverse = new RunCommand(()->m_indexer.runIndexer(-0.6,-0.6,-0.6), m_indexer);
  private final RunCommand m_indexerStop = new RunCommand(()->m_indexer.runIndexer(0,0,0), m_indexer);
  private final RunCommand m_intakeOut= new RunCommand(() -> m_intake.intakeOut(), m_intake);
  private final RunCommand m_intakeIn= new RunCommand(() -> m_intake.intakeIn(), m_intake);


  //private final RunCommand m_resetEnc =  new RunCommand(() -> m_drivetrain.encoderReset(), m_drivetrain);





  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {
    // Configure the button bindings
    configureButtonBindings();

    m_drivetrain.setDefaultCommand(m_teleopArcadeDrive);
    m_intake.setDefaultCommand(new RunCommand(() ->m_intake.Run(0.00), m_intake));
    m_shooter.setDefaultCommand(manualShooter);
    m_indexer.setDefaultCommand(new RunCommand(() -> m_indexer.runIndexer(0,0,0), m_indexer));
    m_limelight.setDefaultCommand(emptyLimelightCommand);
    m_climber.setDefaultCommand(emptyClimberCommand);
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings()
  {
    //Operator Buttons
    new JoystickButton(m_oi.operatorController.getJoystick(), ControllerConstants.R1_BUTTON).whileHeld(m_runIndexerSeq);

    new JoystickButton(m_oi.operatorController.getJoystick(),ControllerConstants.GREEN_BUTTON).whenPressed(m_runShooter);
    new JoystickButton(m_oi.operatorController.getJoystick(),ControllerConstants.GREEN_BUTTON).whenReleased(m_stopShooter);

    new JoystickButton(m_oi.operatorController.getJoystick(), ControllerConstants.BLUE_BUTTON).whileHeld(m_indexerReverse);
    new JoystickButton(m_oi.operatorController.getJoystick(), ControllerConstants.BLUE_BUTTON).whenReleased(m_indexerStop);

    new JoystickButton(m_oi.operatorController.getJoystick(),ControllerConstants.YELLOW_BUTTON).whileHeld(m_defaultIntakeCommand);

    new JoystickButton(m_oi.operatorController.getJoystick(),ControllerConstants.L1_BUTTON).whileHeld(m_intakeIn);
    new JoystickButton(m_oi.operatorController.getJoystick(),ControllerConstants.L1_BUTTON).whenReleased(m_intakeOut);

    new JoystickButton(m_oi.operatorController.getJoystick(),ControllerConstants.RED_BUTTON).whileHeld(intakeReverse);

    new POVButton(m_oi.operatorController.getJoystick(), 0).whileHeld(m_climbingCommand);

    //Driver Buttons
    new JoystickButton(m_oi.driverController.getJoystick(), ControllerConstants.R1_BUTTON).whileHeld(m_autoAim);
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    // An ExampleCommand will run in autonomous
//    TrajectoryConfig config = new TrajectoryConfig(Constants.MAX_VELOCITY, Constants.MAX_ACCELERATION);
//    config.setKinematics(m_drivetrain.getKinematics());
//
//
//    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(Arrays.asList(new Pose2d(), new Pose2d(1.0, 0, new Rotation2d())), config);
//
//
//    RamseteController ramseteController = new RamseteController(2.0, 0.7);
//    RamseteCommand ramseteCommand = new RamseteCommand(
//            trajectory,
//            m_drivetrain::getPose,
//            ramseteController,
//            m_drivetrain.getFeedforward(),
//            m_drivetrain.getKinematics(),
//            m_drivetrain::getSpeeds,
//            m_drivetrain.getLeftPIDController(),
//            m_drivetrain.getRightPIDController(),
//            m_drivetrain::setOutput,
//            m_drivetrain);
//    return ramseteCommand;

    return m_driveStraight;
  }
}

