package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

import java.util.Set;

public class AutoAim implements Command {
    private final Drivetrain drivetrain;
    private final Limelight limelight;
    private final Set<Subsystem> subsystems;
    PowerDistributionPanel pdp;

    public AutoAim(Drivetrain drivetrain, Limelight limelight, PowerDistributionPanel pdp) {
        this.drivetrain = drivetrain;
        this.limelight = limelight;
        this.pdp = pdp;
        this.subsystems = Set.of(this.drivetrain, this.limelight);
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        limelight.limelightLED(3);
        double autoAimVal = limelight.autoAim();
        double currentVoltage = pdp.getVoltage();
        double autoAimValFilter = (12 / currentVoltage) * autoAimVal;

        drivetrain.arcadeDrive(0, autoAimVal);
        SmartDashboard.putNumber("Auto Aim", autoAimVal);
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        limelight.limelightLED(1);
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return this.subsystems;
    }
}
