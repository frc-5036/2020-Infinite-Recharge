package frc.robot.commands.autoRoutines;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autoCommands.DriveStraightBangBang;
import frc.robot.commands.autoCommands.IntakeForAuto;
import frc.robot.commands.autoCommands.ShooterAndIndexer;
import frc.robot.commands.autoCommands.TurnToAngleBangBang;
import frc.robot.extra.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class SixBallAuto extends SequentialCommandGroup {
    public SixBallAuto(Indexer indexer, Shooter shooter, Intake intake, Drivetrain drivetrain, double timeToEndShooterAndIndexer) {
        // TODO: Add your sequential commands in the super() call, e.g.
        //           super(new FooCommand(), new BarCommand());
        //super();
        addCommands
                (
                        new ShooterAndIndexer(indexer,shooter,intake,timeToEndShooterAndIndexer),
                        new DriveStraightBangBang(drivetrain, Constants.STRAIGHT_VALUE),
                        new TurnToAngleBangBang(drivetrain,Constants.TURN_AFTER_STRAIGHT),
                        new DriveStraightBangBang(drivetrain,Constants.STRAIGHT_AFTER_TURNING),
                        new TurnToAngleBangBang(drivetrain, Constants.TURN_FOR_TRENCH),
                        new DriveStraightBangBang(drivetrain,Constants.STRAIGHT_IN_TRENCH),
                        parallel(new IntakeForAuto(intake,4))
                );
    }
}