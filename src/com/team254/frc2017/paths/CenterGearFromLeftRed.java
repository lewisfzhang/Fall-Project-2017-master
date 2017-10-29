package com.team254.frc2017.paths;


import java.util.ArrayList;

import com.team254.frc2017.Constants;
import com.team254.frc2017.paths.PathBuilder.Waypoint;
import com.team254.frc2017.paths.profiles.PathAdapter;
import com.team254.frc2017.subsystems.Drive;
import com.team254.lib.util.control.Lookahead;
import com.team254.lib.util.control.Path;
import com.team254.lib.util.control.PathFollower;
import com.team254.lib.util.math.RigidTransform2d;
import com.team254.lib.util.math.Rotation2d;
import com.team254.lib.util.math.Twist2d;

/**
 * Path from the red alliance wall to the red boiler peg.
 * 
 * Used in GearThenHopperShootModeRed
 * 
 * @see GearThenHopperShootModeRed
 * @see PathContainer
 */
public class CenterGearFromLeftRed implements PathContainer {

    @Override
    public Path buildPath() {
        return PathAdapter.getRedCenterGearPath2();
    }

    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(PathAdapter.getRedCenterGearPosition2(), Drive.getInstance().getGyroAngle());
    }

    @Override
    public boolean isReversed() {
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(new CenterGearFromLeftRed().buildPath());
    }
}