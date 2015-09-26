
package org.usfirst.frc.team1124.robot.subsystems;

import org.usfirst.frc.team1124.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Belt extends PIDSubsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private SpeedController beltMotor;
	private Encoder encoder;
	private static final double kP = 999.999, kI = 999.999, kD = 999.999,
			indexBottom = 999.999, dIndex = 999.999;
	private static final int indexMax = 9999999;
	private static final double minSetpoint = 0;
	private static final double maxSetpoint = indexBottom + indexMax * dIndex;
	
	public Belt() {
		super(kP, kI, kD);
		this.setAbsoluteTolerance(0.005);
		this.setInputRange(minSetpoint, maxSetpoint);
		beltMotor = new Talon(RobotMap.beltTalon);
		encoder = new Encoder(RobotMap.beltEncoderAPWM, RobotMap.beltEncoderBPWM);
		//I hope this is how it works
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public boolean goToIndex(int num) { //returns whether bounds are violated
    	if(num > indexMax) {
    		this.setSetpoint(indexBottom + indexMax);
    		return true;
    	} else if(num < 0) {
    		this.setSetpoint(indexBottom);
    		return true;
    	} else {
        	this.setSetpoint(indexBottom + num * dIndex);
        	return false;
    	}
    }
    public boolean indexUp() { //returns whether bounds are violated
    	if(this.getSetpoint() + dIndex > maxSetpoint) {
    		this.setSetpoint(maxSetpoint);
    		return true;
    	} else {
    		this.setSetpointRelative(dIndex);
    		return false;
    	}
    }
    public boolean indexDown() { //returns whether bounds are violated
    	if(this.getSetpoint() - dIndex < minSetpoint) {
    		this.setSetpoint(minSetpoint);
    		return true;
    	} else {
    		this.setSetpointRelative(-dIndex);
    		return false;
    	}
    }
    public void goToBottom() {
    	this.setSetpoint(minSetpoint);
    }
    public void goToTop() {
    	this.setSetpoint(maxSetpoint);
    }
    public boolean goToSafe(double setpoint) { //returns whether bounds are violated
    	if(setpoint < minSetpoint) {
    		this.setSetpoint(minSetpoint);
    		return true;
    	} else if(setpoint > maxSetpoint) {
    		this.setSetpoint(maxSetpoint);
    		return true;
    	} else {
    		this.setSetpoint(setpoint);
    		return false;
    	}
    }
    public boolean goToUnsafe(double setpoint) { //returns whether bounds are violated
    	this.setSetpoint(setpoint);
    	return (setpoint < minSetpoint || setpoint > maxSetpoint);
    }
    

	@Override
	protected double returnPIDInput() {
		return encoder.getDistance();
	}
	@Override
	protected void usePIDOutput(double output) {
		beltMotor.set(output);
	}
}

