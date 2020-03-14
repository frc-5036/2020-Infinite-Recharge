package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

import java.util.Set;

public class ShooterAndIndexer implements Command
{
    private final Indexer indexer;
    private final Shooter shooter;
    private final Intake intake;
    private double timeToEndCommand;
    private final Set<Subsystem> subsystems;
    Timer timer = new Timer();
    private final double MAX_POWER = 0.85;
    private final double RPM_TO_START_INDEXER_AND_INTAKE = 7000;
    private final double DESIRED_RPM = 7500;


    public ShooterAndIndexer(Indexer indexer, Shooter shooter, Intake intake, double timeToEndCommand)
    {
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


        if(shooter.getRPM() > DESIRED_RPM)
        {
            shooter.setPower(0);

            if(shooter.getRPM() > RPM_TO_START_INDEXER_AND_INTAKE)
            {
                indexer.runIndexer(0.6,0.5,0.5);
                intake.Run(0.8);
            }
            else
            {
                indexer.stopIndexer();
                intake.Stop();
            }
        }

        else if(shooter.getRPM() < DESIRED_RPM)
        {
            shooter.setPower(MAX_POWER);

            if(shooter.getRPM() > RPM_TO_START_INDEXER_AND_INTAKE)
            {
                indexer.runIndexer(0.6,0.5,0.5);
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
    public Set<Subsystem> getRequirements()
    {
        return this.subsystems;
    }

}
