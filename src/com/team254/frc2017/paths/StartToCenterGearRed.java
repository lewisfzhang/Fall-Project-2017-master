package com.team254.frc2017.paths;

import com.team254.frc2017.paths.PathBuilder.Waypoint;
import com.team254.frc2017.paths.profiles.PathAdapter;
import com.team254.lib.util.control.Path;
import com.team254.lib.util.math.RigidTransform2d;
import com.team254.lib.util.math.Rotation2d;
import com.team254.lib.util.math.Translation2d;

import java.util.ArrayList;

/**
 * Path from the red alliance wall to the red center peg.
 * 
 * Used in CenterGearToShootRed
 * 
 * @see CenterGearToShootRed
 * @see PathContainer
 */
public class StartToCenterGearRed implements PathContainer {

    @Override
    public Path buildPath() {
        return PathAdapter.getRedCenterGearPath();
    }

    @Override
    public RigidTransform2d getStartPose() {
        return PathAdapter.getRedCenterStartPose();
    }

    @Override
    public boolean isReversed() {
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(new StartToCenterGearRed().getStartPose());
        System.out.println(new StartToCenterGearRed().buildPath());
    }
    // WAYPOINT_DATA:
    // [{"position":{"x":16,"y":89},"speed":0,"radius":0,"comment":""},{"position":{"x":80,"y":89},"speed":30,"radius":0,"comment":""},{"position":{"x":109,"y":121},"speed":30,"radius":0,"comment":""}]
    // IS_REVERSED: true
    // FILE_NAME: StartToGearRed
}