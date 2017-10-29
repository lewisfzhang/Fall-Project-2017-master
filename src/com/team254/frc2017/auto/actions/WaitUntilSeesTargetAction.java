package com.team254.frc2017.auto.actions;

import edu.wpi.first.wpilibj.Timer;

import com.team254.frc2017.RobotState;
import com.team254.frc2017.GearAimingParameters;
import com.team254.frc2017.subsystems.Drive;
import com.team254.frc2017.subsystems.LED;
import com.team254.lib.util.DriveSignal;

import java.util.Optional;

/**
 * Runs the drivebase in open loop until the robot sees the boiler.
 *
 * @see Action
 */
public class WaitUntilSeesTargetAction implements Action {

    RobotState mState = RobotState.getInstance();

    public WaitUntilSeesTargetAction() {
    }

    public boolean isFinished() {
        double now = Timer.getFPGATimestamp();
        Optional<GearAimingParameters> aimParams = mState.getAimingParameters();
        if (aimParams.isPresent() && Math.abs(now - aimParams.get().getLastSeenTimestamp()) < 0.5) {
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void done() {
        // TODO Auto-generated method stub

    }

    @Override
    public void start() {
        LED.getInstance().setWantedState(LED.WantedState.FIND_RANGE);
    }

}
