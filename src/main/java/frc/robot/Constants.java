package frc.robot;

public class Constants{
    
    private static boolean Weld = true;
    public static int encoderTicksToAxleRevs = 4096;

    public static double kTimeoutMs = 20;

    public static double distance_kP;
    public static double distance_kI;
    public static double distance_kD;
    public static double distance_kF;
    public static double distance_Iz;
    public static double distance_PeakOut;

    public static double turning_kP; 
    public static double turning_kI;
    public static double turning_kD;
    public static double turning_kF;
    public static double turning_Iz;
    public static double turning_PeakOut; 

    public static double velocity_kP;
    public static double velocity_kI;
    public static double velocity_kD;
    public static double velocity_kF;
    public static double velocity_Iz;
    public static double velocity_PeakOut;

    public static double motProf_kP;
    public static double motProf_kI;
    public static double motProf_kD;
    public static double motProf_kF;
    public static double motProf_Iz;
    public static double motProf_PeakOut;


    static{
        
        if(Weld){
                
             distance_kP = 0.1;
             distance_kI = 0;
             distance_kD = 0;
             distance_kF = 0;
             distance_Iz = 100;
             distance_PeakOut = .5;

             turning_kP = 2;
             turning_kI = 0;
             turning_kD = 4;
             turning_kF = 0;
             turning_Iz = 200;
             turning_PeakOut = 1; 

             velocity_kP = .1;
             velocity_kI = 0;
             velocity_kD = 20;
             velocity_kF = 1023/6800;
             velocity_Iz = 300;
             velocity_PeakOut = .5;

             motProf_kP = 1;
             motProf_kI = 0;
             motProf_kD = 0;
             motProf_kF = 1023/6800;
             motProf_Iz = 400;
             motProf_PeakOut = 1;

        }
        else{

             distance_kP = 0.1;
             distance_kI = 0;
             distance_kD = 0;
             distance_kF = 0;
             distance_Iz = 100;
             distance_PeakOut = .5;

             turning_kP = 2;
             turning_kI = 0;
             turning_kD = 4;
             turning_kF = 0;
             turning_Iz = 200;
             turning_PeakOut = 1; 

             velocity_kP = .1;
             velocity_kI = 0;
             velocity_kD = 20;
             velocity_kF = 1023/6800;
             velocity_Iz = 300;
             velocity_PeakOut = .5;

             motProf_kP = 1;
             motProf_kI = 0;
             motProf_kD = 0;
             motProf_kF = 1023/6800;
             motProf_Iz = 400;
             motProf_PeakOut = 1;

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