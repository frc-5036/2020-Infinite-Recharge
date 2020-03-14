package frc.robot.commands.autoRoutines;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autoCommands.DriveStraightBangBang;
import frc.robot.commands.autoCommands.ShooterAndIndexer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class AutoLineShooter extends SequentialCommandGroup {
    public AutoLineShooter(Indexer indexer, Shooter shooter, Drivetrain drivetrain, double desiredDistance, Intake intake) {
        // TODO: Add your sequential commands in the super() call, e.g.
        //           super(new FooCommand(), new BarCommand());
        //super();
        addCommands(new ShooterAndIndexer(indexer, shooter, intake,12), new DriveStraightBangBang(drivetrain,desiredDistance));
    }
}