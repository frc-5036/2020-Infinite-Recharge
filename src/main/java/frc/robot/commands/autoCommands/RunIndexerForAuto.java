//package frc.robot.commands.autoCommands;
//
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.Subsystem;
//import frc.robot.subsystems.Indexer;
//import frc.robot.subsystems.Shooter;
//
//import java.util.Set;
//
//public class RunIndexerForAuto implements Command, extends TimedCommand {
//    private final Indexer indexer;
//    private final Shooter shooter;
//    private final Set<Subsystem> subsystems;
//    private final double TOLERANCE = 6200;
//    private double timeOut = Timer.getFPGATimestamp();
//
//
//    public RunIndexerForAuto(Indexer indexer, Shooter shooter) {
//        this.indexer = indexer;
//        this.shooter = shooter;
//        this.subsystems = Set.of(this.indexer, this.shooter);
//    }
//
//    @Override
//    public void initialize() {
//
//    }
//
//    @Override
//    public void execute()
//    {
//        if(shooter.getRPM() > TOLERANCE)
//        {
//            indexer.runIndexer(0.9,0.4,0.6);
//        }
//        else
//        {
//            indexer.runIndexer(0,0,0);
//        }
//    }
//
//    @Override
//    public boolean isFinished() {
//        // TODO: Make this return true when this Command no longer needs to run execute()
////        return isTimedOut;
//    }
//
//    @Override
//    public void end(boolean interrupted) {
//
//    }
//
//    @Override
//    public Set<Subsystem> getRequirements() {
//        return this.subsystems;
//    }
//}
