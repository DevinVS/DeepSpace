package frc.robot;

public class Constants{
    
    private static boolean Weld = true;
    public static int encoderTicksToAxleRevs = 4096;

    static{
        
        if(Weld){
                
        final double distance_kP = 0.1;
        final double distance_kI = 0;
        final double distance_kD = 0;
        final double distance_kF = 0;
        final double distance_Iz = 100;
        final double distance_PeakOut = .5;

        final double turning_kP = 2;
        final double turning_kI = 0;
        final double turning_kD = 4;
        final double turning_kF = 0;
        final double turning_Iz = 200;
        final double turning_PeakOut = 1; 

        final double velocity_kP = .1;
        final double velocity_kI = 0;
        final double velocity_kD = 20;
        final double velocity_kF = 1023/6800;
        final double velocity_Iz = 300;
        final double velocity_PeakOut = .5;

        final double motProf_kP = 1;
        final double motProf_kI = 0;
        final double motProf_kD = 0;
        final double motProf_kF = 1023/6800;
        final double motProf_Iz = 400;
        final double motProf_PeakOut = 1;

        }
        else{

        final double distance_kP = 0.1;
        final double distance_kI = 0;
        final double distance_kD = 0;
        final double distance_kF = 0;
        final double distance_Iz = 100;
        final double distance_PeakOut = .5;

        final double turning_kP = 2;
        final double turning_kI = 0;
        final double turning_kD = 4;
        final double turning_kF = 0;
        final double turning_Iz = 200;
        final double turning_PeakOut = 1; 

        final double velocity_kP = .1;
        final double velocity_kI = 0;
        final double velocity_kD = 20;
        final double velocity_kF = 1023/6800;
        final double velocity_Iz = 300;
        final double velocity_PeakOut = .5;

        final double motProf_kP = 1;
        final double motProf_kI = 0;
        final double motProf_kD = 0;
        final double motProf_kF = 1023/6800;
        final double motProf_Iz = 400;
        final double motProf_PeakOut = 1;

        }

         /**
	 * PID Gains may have to be adjusted based on the responsiveness of control loop.
     * kF: 1023 represents output value to Talon at 100%, 6800 represents Velocity units at 100% output
     * Not all set of Gains are used in this project and may be removed as desired.
     * 
	//  * 	                                    			  kP   kI   kD   kF               Iz    PeakOut */
	// public final static Gains kGains_Distanc = new Gains( 0.1, 0.0,  0.0, 0.0,            100,  0.50 );
	// public final static Gains kGains_Turning = new Gains( 2.0, 0.0,  4.0, 0.0,            200,  1.00 );
	// public final static Gains kGains_Velocit = new Gains( 0.1, 0.0, 20.0, 1023.0/6800.0,  300,  0.50 );
	// public final static Gains kGains_MotProf = new Gains( 1.0, 0.0,  0.0, 1023.0/6800.0,  400,  1.00 );

    // public final double kP;
	// public final double kI;
	// public final double kD;
	// public final double kF;
	// public final int kIzone;
    // public final double kPeakOutput;
    
    		/* Configure the left Talon's selected sensor to a QuadEncoder*/
		// _leftMaster.configSelectedFeedbackSensor(	FeedbackDevice.QuadEncoder, 			// Local Feedback Source
        // Constants.PID_PRIMARY,					// PID Slot for Source [0, 1]
        // Constants.kTimeoutMs);


    }
}