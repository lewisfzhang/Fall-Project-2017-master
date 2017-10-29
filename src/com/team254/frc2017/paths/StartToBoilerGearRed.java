package com.team254.frc2017.paths;


import java.util.ArrayList;

import com.team254.frc2017.Constants;
import com.team254.frc2017.paths.PathBuilder.Waypoint;
import com.team254.frc2017.paths.profiles.PathAdapter;
import com.team254.lib.util.control.Lookahead;
import com.team254.lib.util.control.Path;
import com.team254.lib.util.control.PathFollower;
import com.team254.lib.util.math.RigidTransform2d;
import com.team254.lib.util.math.Twist2d;

/**
 * Path from the red alliance wall to the red boiler peg.
 * 
 * Used in GearThenHopperShootModeRed
 * 
 * @see GearThenHopperShootModeRed
 * @see PathContainer
 */
public class StartToBoilerGearRed implements PathContainer {

    @Override
    public Path buildPath() {
        return PathAdapter.getRedBoilerGearPath();
    }

    @Override
    public RigidTransform2d getStartPose() {
        return PathAdapter.getRedBoilerStartPose();
    }

    @Override
    public boolean isReversed() {
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(new StartToBoilerGearRed().buildPath());
    }
}