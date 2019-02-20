package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Constants{
    
    private static boolean Weld = true;
    public static int encoderTicksToAxleRevs = 4096;

    public static int kNeoMaxVelocity = 5600;

    public static int kTimeoutMs = 20;
    public static int screenWidth = 300;
    public static int screenHeight = 200;
    public static double kMaxVelocity = 25000;
    public static int kAllowableClosedLoopError;

    public static double centerBallHeight = 4.5; //in inches
    public static double centerHatchHeight; //in inches

    public static double lDistance_kP;
    public static double lDistance_kI;
    public static double lDistance_kD;
    public static double lDistance_kF;
    public static double lDistance_Iz;
    public static double lDistance_PeakOut;

    public static double lVelocity_kP;
    public static double lVelocity_kI;
    public static double lVelocity_kD;
    public static double lVelocity_kF;
    public static double lVelocity_Iz;
    public static double lVelocity_PeakOut;


    public static double rDistance_kP;
    public static double rDistance_kI;
    public static double rDistance_kD;
    public static double rDistance_kF;
    public static double rDistance_Iz;
    public static double rDistance_PeakOut;

    public static double rVelocity_kP;
    public static double rVelocity_kI;
    public static double rVelocity_kD;
    public static double rVelocity_kF;
    public static double rVelocity_Iz;
    public static double rVelocity_PeakOut;

    public static double elevatorkF = 0.00015;
    public static double elevatorkP = 5e-5;
    public static double elevatorkI = 1e-6;
    public static double elevatorkD = 0;
    public static double elevatorkMaxAccel = 3000;
    public static double elevatorkMaxVelocity = 3000;

    static{
        
        if(Weld){

            kAllowableClosedLoopError = 200;
                
             lDistance_kP = 0.1;
             lDistance_kI = 0;
             lDistance_kD = 0;
             lDistance_kF = 0;
             lDistance_Iz = 100;
             lDistance_PeakOut = .5;

             lVelocity_kP = 0;
             lVelocity_kI = 0;
             lVelocity_kD = 0;
             lVelocity_kF = .01;
             //lVelocity_Iz = 300;
             //lVelocity_PeakOut = .5;

             rDistance_kP = 0.1;
             rDistance_kI = 0;
             rDistance_kD = 0;
             rDistance_kF = 0;
             rDistance_Iz = 100;
             rDistance_PeakOut = .5;

             rVelocity_kP = .1;
             rVelocity_kI = 0;
             rVelocity_kD = 20;
             rVelocity_kF = 1023/6800;
             rVelocity_Iz = 300;
             rVelocity_PeakOut = .5;

        }
        else{

            kAllowableClosedLoopError = 200;

             lDistance_kP = 0.1;
             lDistance_kI = 0;
             lDistance_kD = 0;
             lDistance_kF = 0;
             lDistance_Iz = 100;
             lDistance_PeakOut = .5;


             lVelocity_kP = .1;
             lVelocity_kI = 0;
             lVelocity_kD = 20;
             lVelocity_kF = 1023/6800;
             lVelocity_Iz = 300;
             lVelocity_PeakOut = .5;

             

             rDistance_kP = 0.1;
             rDistance_kI = 0;
             rDistance_kD = 0;
             rDistance_kF = 0;
             rDistance_Iz = 100;
             rDistance_PeakOut = .5;


             rVelocity_kP = .1;
             rVelocity_kI = 0;
             rVelocity_kD = 20;
             rVelocity_kF = 1023/6800;
             rVelocity_Iz = 300;
             rVelocity_PeakOut = .5;


        }

         /**
	 * PID Gains may have to be adjusted based on the responsiveness of control loop.
     * kF: 1023 represents output value to Talon at 100%, 6800 represents Velocity units at 100% output
     * Not all set of Gains are used in this project and may be removed as desired.
     * 
	//  * 	                                    			  kP   kI   kD   kF               Iz    PeakOut */
	// publicpublic static Gains kGains_Distanc = new Gains( 0.1, 0.0,  0.0, 0.0,            100,  0.50 );
	// publicpublic static Gains kGains_Turning = new Gains( 2.0, 0.0,  4.0, 0.0,            200,  1.00 );
	// publicpublic static Gains kGains_Velocit = new Gains( 0.1, 0.0, 20.0, 1023.0/6800.0,  300,  0.50 );
	// publicpublic static Gains kGains_MotProf = new Gains( 1.0, 0.0,  0.0, 1023.0/6800.0,  400,  1.00 );

    // public        kP;
	// public        kI;
	// public        kD;
	// public        kF;
	// publicpublic int kIzone;
    // public        kPeakOutput;
    
    		/* Configure the left Talon's selected sensor to a QuadEncoder*/
		// _leftMaster.configSelectedFeedbackSensor(	FeedbackDevice.QuadEncoder, 			// Local Feedback Source
        // Constants.PID_PRIMARY,					// PID Slot for Source [0, 1]
        // Constants.kTimeoutMs);


    }
}