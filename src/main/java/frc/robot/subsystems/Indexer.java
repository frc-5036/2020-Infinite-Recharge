package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotMap;


public class Indexer implements Subsystem
{

    VictorSP sideRoller, bottomRoller;
    IMotorController topRoller;



    @Override
    public void periodic() {
        updateSmartdashboard();
    }

    public Indexer(IMotorController topRoller, VictorSP sideRoller, VictorSP bottomRoller)
    {
        this.topRoller = topRoller;
        this.sideRoller = sideRoller;
        this.bottomRoller = bottomRoller;

    }

    public static Indexer createForRobot()
    {
        IMotorController singleShaftRoller = new TalonSRX(RobotMap.INDEXER_TOP_ROLLER);
        VictorSP sideIndexer = new VictorSP(RobotMap.SIDE_INDEXER);//Try Other port if not working
        VictorSP bottomConveyorRoller = new VictorSP(RobotMap.BOTTOM_ROLLER);

//        VictorSP singleShaftMotor = new VictorSP(RobotMap.INDEXER_ROLLER);
//        VictorSP bottomConveyorRoller = new VictorSP(RobotMap.BOTTOM_ROLLER);
//        IMotorController frontIndexer1 = new TalonSRX(RobotMap.INDEXER_CONVEYOR);
//        VictorSP frontIndexer2 = new VictorSP(RobotMap.INDEXER_CONVEYOR2);


        bottomConveyorRoller.setInverted(true);
        singleShaftRoller.setInverted(true);

        return new Indexer(singleShaftRoller,sideIndexer, bottomConveyorRoller);
    }

    public void runIndexer (double sidePower, double topRollerPower, double bottomRollerPower)
    {
       topRoller.set(ControlMode.PercentOutput,topRollerPower);
       sideRoller.set(sidePower);
       bottomRoller.set(bottomRollerPower);
    }

    public void stopIndexer ()
    {
       runIndexer(0,0,0);
    }

    public void updateSmartdashboard()
    {
        SmartDashboard.putNumber("Indexer top roller" , topRoller.getMotorOutputPercent());
        SmartDashboard.putNumber("Indexer bottom roller", bottomRoller.get());
        SmartDashboard.putNumber("Indexer side rollers",sideRoller.get());
        //SmartDashboard.putNumber("Indexer side rollers 2", indexer2.getMotorOutputPercent());
    }
}