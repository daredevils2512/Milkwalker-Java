package org.daredevils2512.powerup.commands;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.SwerveModifier;
import jaci.pathfinder.modifiers.TankModifier;
import org.daredevils2512.powerup.Robot;

public class PathfinderTest extends Command {
    private Waypoint[] points;
    private Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
    private Trajectory trajectory;
    private TankModifier modifier = new TankModifier(trajectory).modify(0.5);
    private EncoderFollower left = new EncoderFollower(modifier.getLeftTrajectory());
    private EncoderFollower right = new EncoderFollower(modifier.getRightTrajectory());
    @Override
    protected void initialize() {
        points = new Waypoint[]{
                new Waypoint(1,2, Pathfinder.d2r(45)),
                new Waypoint(0,0,Pathfinder.d2r(-45)),
                new Waypoint(-1,-2, 0)
        };
        trajectory = Pathfinder.generate(points, config);

        left.configureEncoder(encoder_position, 1000, wheel_diameter);
        left.configurePIDVA(1.0, 0.0, 0.0, 1 / max_velocity, 0);
        right.configureEncoder(encoder_position, 1000, wheel_diameter);
        right.configurePIDVA(1.0, 0.0, 0.0, 1 / max_velocity, 0);
    }

    @Override
    protected void execute() {
        double l = left.calculate(encoder_position_left);
        double r = right.calculate(encoder_position_right);

        double gyro_heading = Robot.m_navX.navx.getCompassHeading();    // Assuming the gyro is giving a value in degrees
        double desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees

        double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading); // set to -180 to 180
        double turn = 0.8 * (-1.0/80.0) * angleDifference;

        setLeftMotors(l + turn);
        setRightMotors(r - turn);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
