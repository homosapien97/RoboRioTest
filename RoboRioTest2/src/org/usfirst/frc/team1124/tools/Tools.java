package org.usfirst.frc.team1124.tools;

public class Tools {
	public static double dir(double x, double y) {
		if(x == 0.0) {
			return Math.PI / 2;
		}
		return ((x < 0) ?
					(Math.atan(y / x) + Math.PI)
				:
					(Math.atan(y / x))
				);
	}
}
