package com.team254.frc2017.paths.profiles;

import com.team254.frc2017.Constants;
import com.team254.frc2017.paths.PathBuilder;
import com.team254.frc2017.paths.PathBuilder.Waypoint;
import com.team254.lib.util.control.Path;
import com.team254.lib.util.math.RigidTransform2d;
import com.team254.lib.util.math.Rotation2d;
import com.team254.lib.util.math.Translation2d;

import java.util.ArrayList;

import org.eclipse.jetty.rewrite.handler.RedirectPatternRule;

/**
 * Uses a field and robot profile to calculate Waypoints for the paths used by the GearThenHopperShoot auto modes.
 * 
 * @see RobotProfile
 * @see FieldProfile
 */
public class PathAdapter {

    static final RobotProfile kRobotProfile = new CompBot();
    static final FieldProfile kFieldProfile = new PracticeField();

    static final double kSpeed = 80;
    static final double kGearTurnRadius = 24;

    // Don't mess with these
    static final double kPegOffsetX = 17.77; // center of airship to boiler peg
    static final double kPegOffsetY = 30.66; // front of airship to boiler
                                             // pegkRobotProfile.getBlueBoilerGearXCorrection()
    static final Rotation2d kRedBoilerPegHeading = Rotation2d.fromDegrees(240);
    static final Rotation2d kRedCenterPegHeading = Rotation2d.fromDegrees(180);
    static final Rotation2d kRedFarPegHeading = Rotation2d.fromDegrees(120);
    static final Rotation2d kBluePegHeading = Rotation2d.fromDegrees(120);
    static final Rotation2d kStartHeading = Rotation2d.fromDegrees(180); // start angle (backwards)
    static final double kGearPlacementDist = Constants.kCenterToRearBumperDistance + 0; // distance away from the
                                                                                         // airship wall to place the
                                                                                         // gear at
    static final double kFrontDist = Constants.kCenterToIntakeDistance;
    static final double kSideDist = Constants.kCenterToSideBumperDistance;
    static final double kFieldHeight = 324; // total height of the field in inches (doesn't really have to be accurate,
                                            // everything is relative)

    
    
    
    
    
    // first position in the gear path
    public static RigidTransform2d getRedBoilerStartPose() {
        return new RigidTransform2d(new Translation2d(Constants.kCenterToFrontBumperDistance,
                kFieldHeight / 2 - kFieldProfile.getRedCenterToDivide() - Constants.kCenterToSideBumperDistance),
                kStartHeading);
    }
    
    // second position in the gear path
    private static Translation2d getRedBoilerCenterPosition() {
        RigidTransform2d end = new RigidTransform2d(getRedBoilerGearPosition(), kRedBoilerPegHeading);
        return getRedBoilerStartPose().intersection(end);
    }
    
    // final position in the gear path, first position in the hopper path
    public static Translation2d getRedBoilerGearPosition() {
        Translation2d pegPosition = new Translation2d(kFieldProfile.getRedWallToAirship() + kPegOffsetX,
                kFieldHeight / 2 - kPegOffsetY);
        Translation2d robotOffset = new Translation2d(kRedBoilerPegHeading.cos() * kGearPlacementDist,
                kRedBoilerPegHeading.sin() * kGearPlacementDist);
        return pegPosition.translateBy(robotOffset);
    }
    
    


    
    
    // first position in the gear path
    public static RigidTransform2d getRedCenterStartPose() {
        return new RigidTransform2d(new Translation2d(Constants.kCenterToFrontBumperDistance, kFieldHeight / 2), kStartHeading);
    }
    
    // final position in the gear path, first position in the hopper path
    public static Translation2d getRedCenterGearPosition() {
        Translation2d pegPosition = new Translation2d(kFieldProfile.getRedWallToAirship(),
                kFieldHeight / 2);
        Translation2d robotOffset = new Translation2d(kRedCenterPegHeading.cos() * kGearPlacementDist,
                kRedCenterPegHeading.sin() * kGearPlacementDist);
        return pegPosition.translateBy(robotOffset);
    }
    
    // first position in the gear path
    public static RigidTransform2d getRedFarStartPose() {
        return new RigidTransform2d(new Translation2d(Constants.kCenterToFrontBumperDistance,
                kFieldHeight / 2 + kFieldProfile.getRedCenterToFar() - Constants.kCenterToSideBumperDistance),
                kStartHeading);
    }
    
    // second position in the gear path
    private static Translation2d getRedFarCenterPosition() {
        RigidTransform2d end = new RigidTransform2d(getRedFarGearPosition(), kRedFarPegHeading);
        return getRedFarStartPose().intersection(end);
    }
    
    // final position in the gear path, first position in the hopper path
    public static Translation2d getRedFarGearPosition() {
        Translation2d pegPosition = new Translation2d(kFieldProfile.getRedWallToAirship() + kPegOffsetX,
                kFieldHeight / 2 + kPegOffsetY);
        Translation2d robotOffset = new Translation2d(kRedFarPegHeading.cos() * kGearPlacementDist,
                kRedFarPegHeading.sin() * kGearPlacementDist);
        return pegPosition.translateBy(robotOffset);
    }

    public static Path getRedBoilerGearPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(getRedBoilerStartPose().getTranslation(), 0, kSpeed));
        sWaypoints.add(new Waypoint(getRedBoilerCenterPosition(), kGearTurnRadius, kSpeed));
        sWaypoints.add(new Waypoint(getRedBoilerGearPosition(), 0, kSpeed));

        return PathBuilder.buildPathFromWaypoints(sWaypoints, 0, kSpeed);
    }
    
    public static Path getRedCenterGearPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(getRedCenterStartPose().getTranslation(), 0, 120));
        sWaypoints.add(new Waypoint(getRedCenterGearPosition(), 0, 120));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    public static Translation2d getRedCenterGearPosition1() {
        Translation2d pegPosition = getRedCenterGearPosition();
        return new Translation2d(Constants.kCenterToSideBumperDistance + 32, pegPosition.y());
    }
    
    public static Translation2d getRedCenterGearPosition2() {
        Translation2d pegPosition = getRedCenterGearPosition1();
        return new Translation2d(Constants.kCenterToSideBumperDistance + 26, pegPosition.y() + 80);
    }
    
    public static Translation2d getRedCenterGearPosition3() {
        Translation2d pegPosition = getRedCenterGearPosition1();
        return new Translation2d(Constants.kCenterToSideBumperDistance - 8, pegPosition.y() + 80);
    }
    
    public static Translation2d getRedCenterGearPosition4() {
        Translation2d pegPosition = getRedCenterGearPosition();
        return new Translation2d(Constants.kCenterToSideBumperDistance - 8, pegPosition.y()+30);
    }
    
    public static Translation2d getRedCenterGearPosition5() {
        Translation2d pegPosition = getRedCenterGearPosition();
        return new Translation2d(pegPosition.x(), pegPosition.y()+30);
    }
    
    public static Path getRedCenterGearPath2() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(getRedCenterGearPosition3(), 0, 120));
        sWaypoints.add(new Waypoint(getRedCenterGearPosition4(), 48, 120));
        sWaypoints.add(new Waypoint(getRedCenterGearPosition5(), 0, 120));
        

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    public static Path getRedCenterGearPath1() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(getRedCenterGearPosition(), 0, 120));
        sWaypoints.add(new Waypoint(getRedCenterGearPosition1(), 48, 120));
        sWaypoints.add(new Waypoint(getRedCenterGearPosition2(), 0, 120));
        

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    public static Path getRedFarGearPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(getRedFarStartPose().getTranslation(), 0, kSpeed));
        sWaypoints.add(new Waypoint(getRedFarCenterPosition(), kGearTurnRadius, kSpeed));
        sWaypoints.add(new Waypoint(getRedFarGearPosition(), 0, kSpeed));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    public static void main(String[] args) {
        System.out.println("");
    }

//    public static Translation2d getBlueHopperPosition() {
//        Translation2d contactPoint = new Translation2d(
//                kFieldProfile.getBlueWallToHopper() + kHopperOffsetX + kRobotProfile.getBlueHopperXOffset(),
//                kFieldHeight / 2 + kFieldProfile.getBlueCenterToHopper() + kRobotProfile.getBlueHopperYOffset());
//        Translation2d robotOffset = new Translation2d(kFrontDist, -kSideDist);
//        robotOffset = robotOffset.direction().rotateBy(kBlueHopperHeading).toTranslation().scale(robotOffset.norm());
//        return contactPoint.translateBy(robotOffset);
//    }
//
//    public static Translation2d getBlueHopperTurnPosition() {
//        Translation2d hopperPosition = getBlueHopperPosition();
//        Translation2d turnOffset = new Translation2d(kBlueHopperHeading.cos() * kHopperTurnDistance,
//                kBlueHopperHeading.sin() * kHopperTurnDistance);
//        return hopperPosition.translateBy(turnOffset);
//    }
//
//    public static Translation2d getBlueGearTurnPosition() {
//        Translation2d gearPosition = getBlueGearPosition();
//        Translation2d turnOffset = new Translation2d(kBluePegHeading.cos() * kGearTurnDistance,
//                kBluePegHeading.sin() * kGearTurnDistance);
//        return gearPosition.translateBy(turnOffset);
//    }
//
//    public static Translation2d getBlueGearCorrection() {
//        return RigidTransform2d.fromRotation(kBluePegHeading)
//                .transformBy(RigidTransform2d
//                        .fromTranslation((new Translation2d(-kRobotProfile.getBlueBoilerGearXCorrection(),
//                                -kRobotProfile.getBlueBoilerGearYCorrection()))))
//                .getTranslation();
//    }
//
//    private static Translation2d getBlueGearPosition() {
//        Translation2d pegPosition = new Translation2d(kFieldProfile.getBlueWallToAirship() + kPegOffsetX,
//                kFieldHeight / 2 + kPegOffsetY);
//        Translation2d robotOffset = new Translation2d(kBluePegHeading.cos() * kGearPlacementDist,
//                kBluePegHeading.sin() * kGearPlacementDist);
//        return pegPosition.translateBy(robotOffset);
//    }
//
//    private static Translation2d getBlueGearPositionCorrected() {
//        return getBlueGearPosition().translateBy(getBlueGearCorrection());
//    }
//
//    public static RigidTransform2d getBlueStartPose() {
//        return new RigidTransform2d(new Translation2d(Constants.kCenterToFrontBumperDistance,
//                kFieldHeight / 2 + kFieldProfile.getBlueCenterToBoiler() - Constants.kCenterToSideBumperDistance),
//                kStartHeading);
//    }
//
//    private static Translation2d getBlueCenterPosition() {
//        RigidTransform2d end = new RigidTransform2d(getBlueGearPositionCorrected(), kBluePegHeading);
//        return getBlueStartPose().intersection(end);
//    }
//
//    private static Path sBlueGearPath = null;
//
//    public static Path getBlueGearPath() {
//        if (sBlueGearPath == null) {
//            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
//            sWaypoints.add(new Waypoint(getBlueStartPose().getTranslation(), 0, kSpeed));
//            sWaypoints.add(new Waypoint(getBlueCenterPosition(), kLargeRadius, kSpeed));
//            sWaypoints.add(new Waypoint(getBlueGearPositionCorrected(), 0, kSpeed));
//
//            sBlueGearPath = PathBuilder.buildPathFromWaypoints(sWaypoints);
//        }
//        return sBlueGearPath;
//    }
//
//    private static Path sBlueHopperPath = null;
//
//    public static Path getBlueHopperPath() {
//        if (sBlueHopperPath == null) {
//            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
//            sWaypoints.add(new Waypoint(getBlueGearPosition(), 0, 0));
//            sWaypoints.add(new Waypoint(getBlueGearTurnPosition(), kSmallRadius, kSpeed));
//            sWaypoints.add(new Waypoint(getBlueHopperTurnPosition(), kModerateRadius, kSpeed));
//            sWaypoints.add(new Waypoint(getBlueHopperPosition(), kSmallRadius, kSpeed));
//
//            Translation2d blueHopperEndPosition = new Translation2d(getBlueHopperPosition());
//            blueHopperEndPosition.setX(kEndHopperPathX); // move x position to desired place
//            blueHopperEndPosition.setY(blueHopperEndPosition.y() + kHopperSkew);
//            sWaypoints.add(new Waypoint(blueHopperEndPosition, 0, kSpeed));
//
//            sBlueHopperPath = PathBuilder.buildPathFromWaypoints(sWaypoints);
//        }
//        return sBlueHopperPath;
//    }
//
//    public static void calculatePaths() {
//        getBlueHopperPath();
//        getRedHopperPath();
//        getBlueGearPath();
//        getRedGearPath();
//    }
//
//    public static void main(String[] args) {
//        System.out.println("Red:\n" + getRedBoilerStartPose().getTranslation());
//        System.out.println("Center: " + getRedBoilerCenterPosition());
//        System.out.println("Gear: " + getRedGearPositionCorrected());
//        System.out.println("Gear turn: " + getRedGearTurnPosition());
//        System.out.println("Hopper turn: " + getRedHopperTurnPosition());
//        System.out.println("Hopper: " + getRedHopperPosition());
//        System.out.println("Start to boiler gear path:\n" + getRedGearPath());
//        System.out.println("Boiler gear to hopper path:\n" + getRedHopperPath());
//        System.out.println("\nBlue:\n" + getBlueStartPose().getTranslation());
//        System.out.println("Center: " + getBlueCenterPosition());
//        System.out.println("Gear: " + getBlueGearPositionCorrected());
//        System.out.println("Gear turn: " + getBlueGearTurnPosition());
//        System.out.println("Hopper turn: " + getBlueHopperTurnPosition());
//        System.out.println("Hopper: " + getBlueHopperPosition());
//        System.out.println("Start to boiler gear path:\n" + getBlueGearPath());
//        System.out.println("Boiler gear to hopper path:\n" + getBlueHopperPath());
//    }

}
