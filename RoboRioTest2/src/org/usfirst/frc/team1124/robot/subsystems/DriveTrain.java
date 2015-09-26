
package org.usfirst.frc.team1124.robot.subsystems;

import org.usfirst.frc.team1124.robot.Robot;
import org.usfirst.frc.team1124.robot.RobotMap;
import org.usfirst.frc.team1124.robot.commands.MecanumDriveWithJoystick;
import org.usfirst.frc.team1124.tools.Tools;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private SpeedController front_left_motor, back_left_motor,
	front_right_motor, back_right_motor;
	private RobotDrive drive;
	
	public DriveTrain() {
		super();
		front_left_motor = new Talon(RobotMap.front_left_talon);
		back_left_motor = new Talon(RobotMap.back_left_talon);
		front_right_motor = new Talon(RobotMap.front_right_talon);
		back_right_motor = new Talon(RobotMap.back_right_talon);
		drive = new RobotDrive(front_left_motor, back_left_motor,
							   front_right_motor, back_right_motor);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new MecanumDriveWithJoystick());
    }
    public void driveM(double magnitude, double direction, double rotation) {
		drive.mecanumDrive_Polar(magnitude, direction, rotation);
	}
    public void driveM(Joystick joy) {
//		drive(-joy.getY(), -joy.getAxis(AxisType.kThrottle));
		driveM(Math.sqrt(joy.getX() * joy.getX() + joy.getY() * joy.getY()), Tools.dir(joy.getX(), joy.getY()), 0);
	}
    public void driveA(double magnitude, double direction) {
    	drive.arcadeDrive(magnitude, direction);
    }
    public void driveA(Joystick joy) {
    	drive.arcadeDrive(joy);
    }
}

