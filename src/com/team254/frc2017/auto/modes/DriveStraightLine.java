package com.team254.frc2017.auto.modes;

import com.team254.frc2017.auto.AutoModeBase;
import com.team254.frc2017.auto.AutoModeEndedException;
import com.team254.frc2017.auto.actions.DrivePathAction;
import com.team254.frc2017.auto.actions.ResetPoseFromPathAction;
import com.team254.frc2017.paths.PathContainer;
import com.team254.frc2017.paths.StraightLine;

import edu.wpi.first.wpilibj.Timer;

/**
 * Fallback for when all autonomous modes do not work, resulting in a robot standstill
 */
public class DriveStraightLine extends AutoModeBase {
    @Override
    protected void routine() throws AutoModeEndedException {
        PathContainer linePath = new StraightLine();
        runAction(new ResetPoseFromPathAction(linePath));
        double startTime = Timer.getFPGATimestamp();
        runAction(new DrivePathAction(linePath));
    }
}