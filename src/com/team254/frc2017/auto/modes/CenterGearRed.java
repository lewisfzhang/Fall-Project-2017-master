package com.team254.frc2017.auto.modes;

import edu.wpi.first.wpilibj.Timer;

import java.util.Arrays;

import com.team254.frc2017.auto.AutoModeBase;
import com.team254.frc2017.auto.AutoModeEndedException;
import com.team254.frc2017.auto.actions.Action;
import com.team254.frc2017.auto.actions.AutoScoreGearAction;
import com.team254.frc2017.auto.actions.DrivePathAction;
import com.team254.frc2017.auto.actions.ForceEndPathAction;
import com.team254.frc2017.auto.actions.ParallelAction;
import com.team254.frc2017.auto.actions.PrintDebugAction;
import com.team254.frc2017.auto.actions.ResetPoseAction;
import com.team254.frc2017.auto.actions.ResetPoseFromPathAction;
import com.team254.frc2017.auto.actions.SeriesAction;
import com.team254.frc2017.auto.actions.WaitAction;
import com.team254.frc2017.auto.actions.WaitUntilSeesTargetAction;
import com.team254.frc2017.paths.CenterGearCheckmarkLeftRed;
import com.team254.frc2017.paths.CenterGearFromLeftRed;
import com.team254.frc2017.paths.PathContainer;
import com.team254.frc2017.paths.StartToBoilerGearRed;
import com.team254.frc2017.paths.StartToCenterGearRed;
import com.team254.frc2017.subsystems.Drive;
import com.team254.lib.util.DriveSignal;
import com.team254.lib.util.math.RigidTransform2d;
import com.team254.lib.util.math.Rotation2d;
import com.team254.lib.util.math.Translation2d;


public class CenterGearRed extends AutoModeBase {

    @Override
    protected void routine() throws AutoModeEndedException {
        PathContainer centerGearPath = new StartToCenterGearRed();
        double start = Timer.getFPGATimestamp();
        runAction(new ResetPoseFromPathAction(centerGearPath));
        runAction(new DrivePathAction(centerGearPath));
//        runAction(new ParallelAction(Arrays.asList(new Action[] {
//                new DrivePathAction(centerGearPath),
//                new SeriesAction(Arrays.asList(new Action[] {
//                        new WaitUntilSeesTargetAction(), new PrintDebugAction("YO I SEES GEER"), new ForceEndPathAction(), new AutoScoreGearAction(Rotation2d.fromDegrees(180))
//                }))
//        })));
//        runAction(new ResetPoseFromPathAction(new CenterGearCheckmarkLeftRed()));
        runAction(new DrivePathAction(new CenterGearCheckmarkLeftRed()));
//        System.out.println("NOW GAT DAT GEER");
//        
//        runAction(new ResetPoseFromPathAction(new CenterGearFromLeftRed()));
        runAction(new ParallelAction(Arrays.asList(new Action[] {
                new DrivePathAction(new CenterGearFromLeftRed()),
                new SeriesAction(Arrays.asList(new Action[] {
                        new WaitUntilSeesTargetAction(), new PrintDebugAction("YO I SEES GEER"), new ForceEndPathAction(), new AutoScoreGearAction(Rotation2d.fromDegrees(180))
                }))
        })));
        System.out.println("Score Gear Time: " + (Timer.getFPGATimestamp() - start));
    }
}
