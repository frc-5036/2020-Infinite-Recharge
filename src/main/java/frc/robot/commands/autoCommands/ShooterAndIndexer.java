package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

import java.util.Set;

public class ShooterAndIndexer implements Command {
    private final Indexer indexer;
    private final Shooter shooter;
    private final Set<Subsystem> subsystems;

    public ShooterAndIndexer(Indexer indexer, Shooter shooter) {
        this.indexer = indexer;
        this.shooter = shooter;
        this.subsystems = Set.of(this.indexer, this.shooter);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute()
    {

        if(shooter.getRPM() > 6500)
        {
            shooter.setPower(0);

            if(shooter.getRPM() > 6200)
            {
                indexer.runIndexer(0.9,0.5,0.5);
            }
            else
            {
                indexer.stopIndexer();
            }
        }
        else if(shooter.getRPM() < 6500)
        {
            shooter.setPower(0.85);
            if(shooter.getRPM() > 6200)
            {
                indexer.runIndexer(0.9,0.5,0.5);
            }
            else
            {
                indexer.stopIndexer();
            }
        }



    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted)
    {

    }

    @Override
    public Set<Subsystem> getRequirements() {
        return this.subsystems;
    }
}
