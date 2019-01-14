import java.util.*;

public class Test_function {
	static double ACB_parameter = 0.1;
	static int ue_waiting_comming = 50;
	static int ue_barred = 0;
	static int total = 0;
	public static void main(String[] args) {
		// Random test = new Random();
		// ACB_parameter =(double)test.nextInt(9)/10; //隨機給定ACB
		for(int t = 0; t < 300001; t++) {
			ue_waiting_comming = 50;
			ue_barred = 0;
			ue_barred = acb_method(ue_waiting_comming);
			System.out.println("原先的UE數:" + ue_waiting_comming);
			System.out.println("阻擋係數:" + ACB_parameter);
			System.out.println("阻擋的UE數:" + ue_barred);
			total += ue_barred;
		}
		System.out.println(total/300001);
		
	}
	
	public static int acb_method(int ue) {
		int ue_count = 0;
		double ue_random;
		for(int t = 0;t < ue; t++) {
			Random ue_Random = new Random();
			ue_random = (double)ue_Random.nextInt(9)/10;
			System.out.println("ue_random:" + ue_random + "  " + "ACB_parameter:" + ACB_parameter );
			if(ue_random <= ACB_parameter) {
				ue_count++;
			}
		}
		
		return ue_count;
		
	}

}
