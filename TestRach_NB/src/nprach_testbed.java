import java.util.*;
public class nprach_testbed{
	static double e = 2.718281828459;
	static double avg_classA, avg_classB, avg_left = 0; // calculate simulate average
	static int simulate_times = 300001;
	static int s1 = 12, s2 = 24, s3 = 36, s4 = 48; //Define subcarrier number
	static double PbclassA, PbclassB; // classA and classB gurantee barring rate
	static double lclassA, lclassB;  //every device preamble arrival rate
	static double lclassA_1, lclassB_1; //single device arrival rate
	static double ACB_parameter = 1;
	static double lget_classA_1 = 0, lget_classB_1 = 0; 
	static double get_PbclassA = 0, get_PbclassB = 0;
	static  int label = 0, label2 =0; //just use to jump out
	
	//For simulate eNB situation (subcarrier condition)
	static int[] subcarrier_A, subcarrier_B;
	static int MaxTry = 5; //over MaxTry count will go next CE level
	static int device_coming_A;
	static int device_coming_B;
	static int deviceA_barred, deviceB_barred;
	static int empty_subcarrier_A, empty_subcarrier_B = 0;
	static int success_transmit_A, success_transmit_B = 0;
	//For simulate eNB situation (subcarrier condition)
	
	//adjust parameter
	static int Snprach = s4; // Define subcarrier number 
	static int NclassA, NclassB; //classA, classB can use number of subcarrier
	static int Ncb;//contention-base preamble
	static double x0 = 0; //Define ClassA device eNB subcarrier has no preamble situation
	static double y0 = 0; //Define ClassB device eNB subcarrier has no preamble situation
	static double PgbclassA = 0.01, PgbclassB = 0.1; //classA and classB guarantee barring rate
	//adjust parameter
	
	static int Sget_nprach, Nget_classA, Nget_classB; //output
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//simulate eNB subcarrier usage(preamble)

		//Get ABC parameter
	    Random Random_ACB = new Random();
	    
	    for(int g=0;g<simulate_times;g++) { //get simulate average result
			Random rr = new Random(); //use for simulating UE select preamble
			Random rr2 = new Random();
	    	device_coming_A = 70;
	    	device_coming_B = 100;
	    	NclassA = 20;
	    	NclassB = 28;
	    	Ncb = NclassA + NclassB;

	    	do {
	    	//simulate classA device
			for(int try_A =0; try_A < MaxTry; try_A++) { //do retransmission until to its maxtry, if failed will go to next CE level
				device_coming_A = device_coming_A - success_transmit_A;
				success_transmit_A = 0;
				empty_subcarrier_A = 0;
				subcarrier_A = new int [NclassA];
			 for(int i = 0; i < (device_coming_A - deviceA_barred); i++) {
				 subcarrier_A[rr.nextInt(NclassA)] += 1;
				}
				for(int t = 0; t<NclassA; t++) {
					System.out.print(subcarrier_A[t] + " ");  //show subcarriers situation, just for observation
					if(subcarrier_A[t] == 0 ) {
						empty_subcarrier_A +=1;
					}else if(subcarrier_A[t] == 1) {
						success_transmit_A += 1;
					}
				}
				System.out.println(); 
			}
		
				//simulate classB device
			for(int try_B =0; try_B < MaxTry; try_B++) {
				device_coming_B = device_coming_B - success_transmit_B;
				success_transmit_B = 0;
				empty_subcarrier_B = 0;
				subcarrier_B = new int [NclassB];
				 for(int i = 0; i < (device_coming_B - deviceB_barred); i++) {
					 subcarrier_B[rr2.nextInt(NclassB)] += 1;
					}
					for(int t = 0; t < NclassB; t++) {
						System.out.print(subcarrier_B[t] + " ");  //show subcarriers situation
						if(subcarrier_B[t] == 0 ) {
							empty_subcarrier_B +=1 ;
						}else if(subcarrier_B[t] == 1) {
							success_transmit_B += 1;
						}
					}
					System.out.println();
			}
				//simulate eNB over get x0,y0
				x0 = (double)empty_subcarrier_A/NclassA;
				y0 = (double)empty_subcarrier_B/NclassB;
			 System.out.println(); 
			
			 lget_classA_1=0; 
			 lget_classB_1 = 0; // after calculation
			 get_PbclassA = 0;
			 get_PbclassB = 0;//after calculation
			lclassA_1 = -Math.log(x0); //find lclassA_1 by inverse function
			lclassB_1 = -Math.log(y0); //find lclassB_1 by inverse function
			PbclassA = 1 - Math.pow(e, -lclassA_1) - lclassA_1*Math.pow(e, -lclassA_1); 
			PbclassB = 1 - Math.pow(e, -lclassB_1) - lclassB_1*Math.pow(e, -lclassB_1);
			System.out.println("lclassA_1: " + lclassA_1);
			System.out.println("lclassB_1: " + lclassB_1);
			System.out.println("PbclassA: " + PbclassA);
			System.out.println("PbclassB: " + PbclassB);
			/*//ACB parameter setting
			if(lclassA_1 > 1 || lclassB_1 > 1) { //setting ACB parameter for each classA and classB
				ACB_parameter = 1/((lclassA_1 + lclassB_1)/2);
				System.out.println("GGGGG");
			}else {
				label = 3;
				ACB_parameter = 1;
				System.out.println("JJJJJJJ");
				}*/
			deviceA_barred = device_coming_A - acb_method(device_coming_A);
			deviceB_barred = device_coming_B - acb_method(device_coming_B);
			System.out.println("Being barred classA device count: " + deviceA_barred);
			System.out.println("ACB_parameter: " + ACB_parameter);
			label++;
	    	}while(label <2);

			
			if(PbclassA <= PgbclassA && PbclassB <= PgbclassB) {
				System.out.println("Enter if");
				//for(int i=1;i<=17;i++) { //at most can use 16 non-anchors (anchor + non-anchor)
					for(int j=1;j<=Ncb;j++) {   
						lget_classA_1 = (lclassA_1)/(j);
						lget_classB_1 = (lclassB_1)/(j);
						get_PbclassA = 1 - (Math.pow(e, -lget_classA_1)) - (lget_classA_1*Math.pow(e, -lget_classA_1)); 
						get_PbclassB = 1 - Math.pow(e, -lget_classB_1) - lget_classB_1*Math.pow(e, -lget_classB_1);
						System.out.println("Enter if lget_classA_1: " + lget_classA_1);
						System.out.println("Enter if lget_classB_1: " + lget_classB_1);
						System.out.println("Enter if get_PbclassA: " + get_PbclassA);
						System.out.println("Enter if get_PbclassB: " + get_PbclassB);
						if(get_PbclassA < PgbclassA || get_PbclassB < PgbclassB) {
							//Sget_nprach = Snprach*j;
							Nget_classA = NclassA;
							Nget_classB = Ncb - Nget_classA;
							//label2++;
							break;
						}	
						NclassA++;
					}
					
					Nget_classA = NclassA;
					Nget_classB = Ncb - Nget_classA;
				//}

			}else {
				System.out.println("Enter else");
				for(int j=1;j<Ncb;j++) {
					lget_classA_1 = (lclassA_1)/(j);
					lget_classB_1 = (lclassB_1)/(j);
					get_PbclassA = 1 - Math.pow(e, -lget_classA_1) - lget_classA_1*Math.pow(e, -lget_classA_1); 
					get_PbclassB = 1 - Math.pow(e, -lget_classB_1) - lget_classB_1*Math.pow(e, -lget_classB_1);
					//System.out.println("Enter else : lget_classA_1  " + lget_classA_1);
					//System.out.println("Enter else get_PbclassA: " + get_PbclassA);
					//System.out.println("Enter else get_PbclassB: " + get_PbclassB);
					if(get_PbclassA <= PgbclassA && get_PbclassB <= PgbclassB) {
						//Sget_nprach = 48*17;
						Nget_classA = j;
						Nget_classB = Ncb-j; 
						break;
					}
				}
				
			}
			
			avg_classA += Nget_classA;
			avg_classB += Nget_classB;
			avg_left += device_coming_A + device_coming_B;
	    } 
	    
		
		//System.out.println("Sget_nprach: " + Sget_nprach);
		System.out.println("classA_Ncb: " + Nget_classA);
		System.out.println("classB_Ncb: " + Nget_classB);
		System.out.println(lclassA);
		System.out.println("Avg_classA_Ncb: " + avg_classA/simulate_times);
		System.out.println("Avg_classB_Ncb: " + avg_classB/simulate_times);
		System.out.println("avg left: " + avg_left/simulate_times);
		System.out.println("ACB parameter: " + ACB_parameter);
		//System.out.println("x0: " + x0);
		//System.out.println("y0: " + y0);
		/*System.out.println("Being barred: " + deviceA_barred);
		System.out.println("Left: " + (deviceA_barred + device_coming_A));
		System.out.println("ACB parameter: " + ACB_parameter);*/
	}

	
	//ACB_scheme
	public static int acb_method(int ue) {
		int ue_count = 0;
		double ue_random;
		for(int t = 0;t < ue; t++) {
			Random ue_Random = new Random();
			ue_random = (double)ue_Random.nextInt(9)/10;
			if(ue_random <= ACB_parameter) {
				ue_count++;
			}
		}
		
		return ue_count;
		
	}
	
}
