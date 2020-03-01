package frc.robot.commands.driveCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.OI;
import frc.robot.subsystems.Drivetrain;

import java.util.Set;

public class TeleopArcadeDrive implements Command {
    private final Drivetrain drivetrain;
    private final Set<Subsystem> subsystems;
    private final OI oi;

    public TeleopArcadeDrive(Drivetrain drivetrain, OI oi) {
        this.drivetrain = drivetrain;
        this.oi = oi;
        this.subsystems = Set.of(this.drivetrain);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute()
    {
        drivetrain.arcadeDrive(oi.getForward(),oi.getRotate());
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
