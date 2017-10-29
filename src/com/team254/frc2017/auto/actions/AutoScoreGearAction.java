package com.team254.frc2017.auto.actions;

import com.team254.frc2017.paths.PathContainer;
import com.team254.frc2017.subsystems.Drive;
import com.team254.lib.util.DriveSignal;
import com.team254.lib.util.control.Path;
import com.team254.lib.util.math.Rotation2d;

/**
 * Drives the robot along the Path defined in the PathContainer object. The action finishes once the robot reaches the
 * end of the path.
 * 
 * @see PathContainer
 * @see Path
 * @see Action
 */
public class AutoScoreGearAction implements Action {

    private Drive mDrive;
    private Rotation2d mHeading;
    
    public AutoScoreGearAction() {
    }
    
    public AutoScoreGearAction(Rotation2d heading) {
        mHeading = heading;
        mDrive = Drive.getInstance();
    }

    @Override
    public boolean isFinished() {
//        return false;
                return mDrive.isGearScored();
    }

    @Override
    public void update() {
        mDrive.setWantDriveGearPath(mHeading);      
        // Nothing done here, controller updates in mEnabedLooper in robot
    }

    @Override
    public void done() {
        mDrive.setOpenLoop(DriveSignal.BRAKE);
        // TODO: Perhaps score gear action here
    }

    @Override
    public void start() {
        mDrive.setTargetHeading(mHeading);
        mDrive.setWantDriveGearPath(mHeading);
    }
}
