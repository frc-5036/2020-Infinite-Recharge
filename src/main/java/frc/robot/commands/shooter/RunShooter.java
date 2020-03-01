package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
//import frc.robot.extra.LED;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

import java.util.HashSet;
import java.util.Set;


public class RunShooter implements Command
{
    private final Set<Subsystem> required;
    private final Shooter shooter;
    private final Intake intake;
    private boolean isRampedUp;
    //LED led;


    private final double MAX_POWER = 0.85;

    public RunShooter(Shooter shooter, Intake intake)
    {
        this.shooter = shooter;
        this.required = Set.of(shooter);
        this.intake = intake;
        required.add(shooter);
    }
    public RunShooter(double desiredRPM, Shooter shooter, Intake intake)
    {
        this.shooter = shooter;
        this.intake = intake;
        required = new HashSet<>();
        required.add(shooter);

        //led = new LED();

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

        intake.intakeOut();
        if(shooter.getRPM() > desiredRPM + TOLERANCE)
        {
            shooter.setPower(0);

//            if (shooter.getRPM() > 6200)
//            {
//                led.darkBlueLed();
//            }

        }
        else if(shooter.getRPM() < desiredRPM - TOLERANCE)
        {
            shooter.setPower(MAX_POWER);
//            if (shooter.getRPM() > 6200)
//            {
//                led.darkBlueLed();
//            }

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
