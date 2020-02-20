package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Indexer;

import java.util.Set;

public class IndexerPulser implements Command {
    private final Indexer indexer;
    private final Set<Subsystem> subsystems;


    public IndexerPulser(Indexer indexer) {
        this.indexer = indexer;
        this.subsystems = Set.of(indexer);
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        if(Timer.getFPGATimestamp() % 2.5 == 0 )
        {
            indexer.runIndexer(-0.5, -0.5);
        }
        else
        {
            indexer.runIndexer(0.5,0.5);
        }
    }
    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public Set<Subsystem> getRequirements() {
        return this.subsystems;
    }
}
