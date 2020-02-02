package util;

import frc.robot.customInterfaces.DriveController;

import java.util.List;

public class DriveControllerMock implements DriveController
{
    List<Double> mockForward;
    List<Double> mockRotate;
    int forwardValIndex;
    int rotationValIndex;
    boolean isQuickTurn;

    public DriveControllerMock(List<Double> mockForward, List<Double> mockRotate, boolean isQuickTurn)
    {
        this.mockForward = mockForward;
        this.mockRotate = mockRotate;
        this.isQuickTurn = isQuickTurn;

        forwardValIndex = 0;
        rotationValIndex = 0;
        isQuickTurn = false;
    }


    @Override
    public double getForward() {
        if(forwardValIndex < mockForward.size())
        {
            return mockForward.get(forwardValIndex++);
        }
        return 0;
    }

    @Override
    public double getRotate() {
        if (rotationValIndex < mockRotate.size())
        {
            return mockRotate.get(rotationValIndex++);
        }
        return 0;
    }

    @Override
    public boolean getQuickTurn() {
        return isQuickTurn;
    }
}
