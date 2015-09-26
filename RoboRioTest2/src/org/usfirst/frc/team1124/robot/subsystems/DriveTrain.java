
package org.usfirst.frc.team1124.robot.subsystems;

import org.usfirst.frc.team1124.robot.RobotMap;
import org.usfirst.frc.team1124.robot.commands.GenericDriveWithJoystick;
import org.usfirst.frc.team1124.tools.Tools;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private SpeedController flMotor, blMotor, frMotor, brMotor;
	private RobotDrive drive;
	
	public DriveTrain() {
		super();
		flMotor = new Talon(RobotMap.flTalon);
		blMotor = new Talon(RobotMap.blTalon);
		frMotor = new Talon(RobotMap.frTalon);
		brMotor = new Talon(RobotMap.brTalon);
		drive = new RobotDrive(flMotor, blMotor, frMotor, brMotor);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GenericDriveWithJoystick());
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

