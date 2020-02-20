package frc.robot.commands.shooter.shooterSeq;


import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.OI;
import frc.robot.RobotContainer;
import frc.robot.commands.indexer.RunIndexer;
import frc.robot.commands.intake.DefaultIntakeCommand;
import frc.robot.commands.intake.RunIntake;
import frc.robot.commands.shooter.RunShooter;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class shooterSeq extends ParallelCommandGroup {

    public shooterSeq(Intake intake,  Indexer indexer)
    {
        // TODO: Add your sequential commands in the super() call, e.g.
        //           super(new FooCommand(), new BarCommand());
        super(new RunIntake(intake), new RunIndexer(indexer));
    }
}