package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.extra.Constants;
import frc.robot.extra.PID;
import frc.robot.subsystems.Drivetrain;

import java.util.Set;

public class GyroPIDCommand implements Command {
    private final Drivetrain drivetrain;
    private final Set<Subsystem> subsystems;
    private final double kP = Constants.kP_DRIVE_ROTATE;
    private final double kD = Constants.kD_DRIVE_ROTATE;
    double target, counter;
    final double EPSILON = 2.5;
    private PID pid;

    public GyroPIDCommand(Drivetrain drivetrain, double target) {
        this.drivetrain = drivetrain;
        this.subsystems = Set.of(drivetrain);
        this.target = target;
    }

    @Override
    public void initialize()
    {
        pid = new PID(target, kP, kD);
        drivetrain.encoderReset();
        drivetrain.gyroReset();
        counter = 0;
    }

    @Override
    public void execute()
    {
        double currentError = target - drivetrain.getGyroYaw();
        double output = pid.getOutput(currentError) * 0.75;
        drivetrain.arcadeDrive(0, output);
        System.out.println("From Gyro Pid, Error: " + currentError + ", Output: " + output);
    }

    @Override
    public boolean isFinished()
    {
        // TODO: Make this return true when this Command no longer needs to run execute()
        double currentError = Math.abs(target - drivetrain.getGyroPitch());
        if(currentError < EPSILON)
        {
            counter++;
        }
        if(counter > 10)
        {
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public Set<Subsystem> getRequirements() {
        return Set.of(drivetrain);
    }
}
