package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

import java.util.Set;

public class ShooterAndIndexer implements Command {
    private final Indexer indexer;
    private final Shooter shooter;
    private final Intake intake;
    private double timeToEndCommand;
    private final Set<Subsystem> subsystems;
    Timer timer = new Timer();


    public ShooterAndIndexer(Indexer indexer, Shooter shooter, Intake intake, double timeToEndCommand) {
        this.indexer = indexer;
        this.shooter = shooter;
        this.intake = intake;
        this.timeToEndCommand = timeToEndCommand;
        this.subsystems = Set.of(this.indexer, this.shooter,this.intake);
        timer.reset();

    }

    @Override
    public void initialize()
    {
        timer.start();
    }

    @Override
    public void execute()
    {
        intake.intakeOut();


        if(shooter.getRPM() > 7500)
        {
            shooter.setPower(0);

            if(shooter.getRPM() > 6500)
            {
                indexer.runIndexer(0.9,0.5,0.5,0.5);
                intake.Run(0.8);
            }
            else
            {
                indexer.stopIndexer();
                intake.Stop();
            }
        }
        else if(shooter.getRPM() < 7500)
        {
            shooter.setPower(0.85);
            if(shooter.getRPM() > 7000)
            {
                indexer.runIndexer(0.9,0.5,0.5,0.5);
                intake.Run(0.8);
            }
            else
            {
                indexer.stopIndexer();
                intake.Stop();
            }
        }



    }

    @Override
    public boolean isFinished()
    {
        // TODO: Make this return true when this Command no longer needs to run execute()
        if(timer.get() >= timeToEndCommand)
        {
            return true;
        }
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
