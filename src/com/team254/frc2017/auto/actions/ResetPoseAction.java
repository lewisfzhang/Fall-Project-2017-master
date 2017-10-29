package com.team254.frc2017.auto.actions;

import edu.wpi.first.wpilibj.Timer;

import com.team254.frc2017.RobotState;
import com.team254.frc2017.paths.PathContainer;
import com.team254.frc2017.subsystems.Drive;
import com.team254.lib.util.math.RigidTransform2d;

/**
 * Resets the robot's current pose based on the starting pose stored in the pathContainer object.
 * 
 * @see PathContainer
 * @see Action
 * @see RunOnceAction
 */
public class ResetPoseAction extends RunOnceAction {

    protected RigidTransform2d mPose;

    public ResetPoseAction(RigidTransform2d pose) {
        mPose = pose;
    }

    @Override
    public synchronized void runOnce() {
        RobotState.getInstance().reset(Timer.getFPGATimestamp(), mPose);
        Drive.getInstance().setGyroAngle(mPose.getRotation());
    }
}
