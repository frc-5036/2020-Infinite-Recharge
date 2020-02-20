package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Shooter;

import java.util.HashSet;
import java.util.Set;


public class RunShooter implements Command
{
    private final Set<Subsystem> required;
    private final Shooter shooter;
    private boolean isRampedUp;


    private final double MAX_POWER = 0.85;

    public RunShooter(Shooter shooter)
    {
        this.shooter = shooter;
        this.required = Set.of(shooter);
        required.add(shooter);
    }
    public RunShooter(double desiredRPM, Shooter shooter)
    {
        this.shooter = shooter;
        required = new HashSet<>();
        required.add(shooter);

        shooter.setDesiredRPM(desiredRPM);
    }


    @Override
    public void initialize()
    {
        isRampedUp = false;
    }

    @Override
    public void execute()
    {

        double desiredRPM = shooter.getDesiredRPM();
        double RPMDiff = desiredRPM - shooter.getRPM();
        final double TOLERANCE = 0;
        final double RAMP_UP_TOLERANCE = 1000;
        double currentPower = shooter.getMotorPower();
        final boolean RAMP_UP = false;


        if (RAMP_UP && RPMDiff < RAMP_UP_TOLERANCE)
        {
            currentPower =+ 0.1;
            shooter.setPower(currentPower);
        }
        else if(shooter.getRPM() > desiredRPM + TOLERANCE)
        {
            shooter.setPower(0);
        }
        else if(shooter.getRPM() < desiredRPM - TOLERANCE)
        {
            shooter.setPower(MAX_POWER);
        }

    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return required;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
