package CommandTests;

import Commands.ArcadeDriveForTesting;
import frc.robot.subsystems.Drivetrain;
import org.junit.Test;
import org.mockito.InOrder;
import util.DriveControllerMock;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;


public class arcadeDriveTest
{
    @Test
    public void arcadeDriveExecuteTask()
    {
        Drivetrain drivetrain = mock(Drivetrain.class);

        List<Double> mockForwardVals = Arrays.asList(new Double[] {-0.3, 1.0, 0.6, 0.2, 0.44});
        List<Double> mockRotateVals = Arrays.asList(new Double[]{1.0, -0.8, -0.2, 0.44, 0.1});
        ArcadeDriveForTesting arcadeDrive = new ArcadeDriveForTesting(drivetrain, new DriveControllerMock(mockForwardVals, mockRotateVals, false));

        for (Double d: mockForwardVals)
        {
            arcadeDrive.execute();
        }

        InOrder inOrder = inOrder(drivetrain);
        inOrder.verify(drivetrain).arcadeDrive(-0.3, 1.0);
        inOrder.verify(drivetrain).arcadeDrive(1.0, -0.8);
        inOrder.verify(drivetrain).arcadeDrive(0.6, -0.2);
        inOrder.verify(drivetrain).arcadeDrive(0.2, 0.44);
        inOrder.verify(drivetrain).arcadeDrive(0.44, 0.1);

    }

    @Test
    public void arcadeDriveExecuteAndStopTest()
    {
        Drivetrain drivetrain = mock(Drivetrain.class);

        List<Double> mockForwardVals = Arrays.asList(new Double[] {-0.3, 1.0, 0.6, 0.2, 0.44});
        List<Double> mockRotateVals = Arrays.asList(new Double[]{1.0, -0.8, -0.2, 0.44, 0.1});
        ArcadeDriveForTesting arcadeDrive = new ArcadeDriveForTesting(drivetrain, new DriveControllerMock(mockForwardVals, mockRotateVals, false));

        for (Double d: mockForwardVals)
        {
            arcadeDrive.execute();
        }
        arcadeDrive.end(true);

        InOrder inOrder = inOrder(drivetrain);
        inOrder.verify(drivetrain).arcadeDrive(-0.3, 1.0);
        inOrder.verify(drivetrain).arcadeDrive(1.0, -0.8);
        inOrder.verify(drivetrain).arcadeDrive(0.6, -0.2);
        inOrder.verify(drivetrain).arcadeDrive(0.2, 0.44);
        inOrder.verify(drivetrain).arcadeDrive(0.44, 0.1);
        inOrder.verify(drivetrain).Stop();
    }






}
