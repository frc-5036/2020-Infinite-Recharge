package frc.robot.commands.autoRoutines;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autoCommands.DriveStraightBangBang;
import frc.robot.commands.autoCommands.ShooterAndIndexer;
import frc.robot.commands.autoCommands.TurnToAngleBangBang;
import frc.robot.commands.shooter.RunShooter;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class DriveAndTurn extends SequentialCommandGroup {
    public DriveAndTurn(Drivetrain drivetrain, double forwardDistance, double angle) {
        // TODO: Add your sequential commands in the super() call, e.g.
        //           super(new FooCommand(), new BarCommand());
        addCommands(
                new DriveStraightBangBang(drivetrain,forwardDistance), new TurnToAngleBangBang(drivetrain,angle)
        );
        //super(new DriveStraightBangBang(drivetrain,forwardDistance), new TurnToAngleBangBang(drivetrain,angle));
    }
}