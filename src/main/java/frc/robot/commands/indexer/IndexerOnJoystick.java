package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.OI;
import frc.robot.subsystems.Indexer;

import java.util.Set;

public class IndexerOnJoystick implements Command {
    private final Indexer indexer;
    private final OI oi;
    private final Set<Subsystem> subsystems;

    public IndexerOnJoystick(Indexer indexer, OI oi) {
        this.indexer = indexer;
        this.oi = oi;
        this.subsystems = Set.of(this.indexer);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute()
    {
        indexer.runIndexer(0,oi.getManualIndexer(),0);
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
