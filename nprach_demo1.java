import java.util.*;

public class nprach_demo1 extends TimerTask{
	//Define 3 CE level	
	float CE0_successRate, CE1_successRate, CE2_successRate = 0;
	static int s1 = 12, s2 = 24, s3 = 36, s4 = 48; //Define subcarrier number
	int[] subcarrier_CE0 = new int [s2];
	int[] subcarrier_CE1 = new int [s1];
	int[] subcarrier_CE2 = new int [s1];
	static int CE0_dy_subcarrier = s2, CE1_dy_subcarrier = s1, CE2_dy_subcarrier = s1;
	static int CE0_wait_comming = 100, CE1_wait_comming = 50, CE2_wait_comming = 50; //initial
	static int CE0_afterACB, CE1_afterACB, CE2_afterACB = 0;
	static int CE0_Success_Count, CE1_Success_Count, CE2_Success_Count = 0;
	int CE0_tryCount, CE1_tryCount, CE2_tryCount = 0;
	//control element
	static double ACB_parameter = 0.4;
	int MaxTry = 10;
	int backOff_Time = 1024; //ms
	//control element
	long startTime, endTime;
	long Avg_Proccess_Time = 0;
	    @Override
	    public void run() {
	    	Random Random_ACB = new Random();
	    	ACB_parameter =(double)Random_ACB.nextInt(9)/10;
	        //System.out.println("Timer task started at:"+new Date());
	    	//startTime=System.currentTimeMillis();

	    	AllCE_completeTask();
	    	//According to successrate to Dynamic Adjust Resource 
	    	CE0_successRate = CE0_Success_Count / CE0_dy_subcarrier;
	    	CE1_successRate = CE1_Success_Count / CE1_dy_subcarrier;
	    	CE2_successRate = CE2_Success_Count / CE2_dy_subcarrier;
	    	//According to successrate to Dynamic Adjust Resource 
	    	
	    	//endTime=System.currentTimeMillis();
	    	//System.out.println("Proccessing time： "+ Avg_Proccess_Time + "ms");
	        //System.out.println("Proccessing time： "+ (endTime-startTime) + "ms");
	        System.out.println("CE0 Try Count:" + CE0_tryCount + "次");
	        System.out.println("CE0 剩下:" + CE0_afterACB + "個");
	        System.out.println("CE1 Try Count:" + CE1_tryCount + "次");
	        System.out.println("CE1 剩下:" + CE1_afterACB + "個");
	        System.out.println("CE2 Try Count:" + CE2_tryCount + "次");
	        System.out.println("CE2 剩下:" + CE2_afterACB + "個");
	        //System.out.println("Timer task finished at:"+new Date());
	    }

	    private void AllCE_completeTask() {
	    	startTime=System.currentTimeMillis();
	    	try {
	 	    	//CE0
	        	while(CE0_afterACB > 0){ 
	        		int[] subcarrier_CE0 = new int [CE0_dy_subcarrier];
	    			CE0_Success_Count = 0;
	    			Random rr = new Random();
	    			//System.out.println(rr.nextInt(24));
	    			for(int i = 0; i<CE0_afterACB; i++) {
	    				subcarrier_CE0[rr.nextInt(CE0_dy_subcarrier)] += 1;
	    			}
	    			for(int t = 0; t<CE0_dy_subcarrier; t++) {
	    				System.out.print(subcarrier_CE0[t] + " ");  //show subcarriers situation
	    				if(subcarrier_CE0[t] == 1 ) {
	    					CE0_Success_Count +=1 ;
	    				}
	    			}
	    			System.out.println("\r" + CE0_Success_Count);
	    			CE0_afterACB = CE0_afterACB - CE0_Success_Count;
	    			System.out.println(CE0_afterACB);
	    			
	        		Thread.sleep(40);
	        		CE0_tryCount++; 
	        		if(CE0_tryCount == MaxTry) {  //MaxTry
	        			break;
	   
	        		}
	        	}
	        	//CE0
	        		
	        	//CE1
	        	while(CE1_afterACB > 0){
	        		int[] subcarrier_CE1 = new int [CE1_dy_subcarrier];
	    			CE1_Success_Count = 0;
	    			Random rr1 = new Random();
	    			for(int i = 0; i<CE1_afterACB; i++) {
	    				subcarrier_CE1[rr1.nextInt(CE1_dy_subcarrier)] += 1;
	    			}
	    			for(int t = 0; t<CE1_dy_subcarrier; t++) {
	    				System.out.print(subcarrier_CE1[t] + " ");  //show subcarriers situation
	    				if(subcarrier_CE1[t] == 1 ) {
	    					CE1_Success_Count +=1 ;
	    				}
	    			}
	    			System.out.println("\r" + CE1_Success_Count);
	    			CE1_afterACB = CE1_afterACB - CE1_Success_Count;
	    			System.out.println(CE1_afterACB);
	    		
	        		Thread.sleep(80);
	        		CE1_tryCount++;
	        		
	        		if(CE1_tryCount == MaxTry) {  //MaxTry
	        			break;
	        		}
	        	}
	        		//CE1
	        		
	        		//CE2
	        	while(CE2_afterACB > 0){
	        		int[] subcarrier_CE2 = new int [CE2_dy_subcarrier];
	    			CE2_Success_Count = 0;
	    			Random rr2 = new Random();
	    			//System.out.println(rr.nextInt(12));
	    			for(int i = 0; i<CE2_afterACB; i++) {
	    				subcarrier_CE2[rr2.nextInt(CE2_dy_subcarrier)] += 1;
	    			}
	    			for(int t = 0; t<CE2_dy_subcarrier; t++) {
	    				System.out.print(subcarrier_CE2[t] + " ");  //show subcarriers situation
	    				if(subcarrier_CE2[t] == 1 ) {
	    					CE2_Success_Count +=1 ;
	    				}
	    			}
	    			System.out.println("\r" + CE2_Success_Count);
	    			CE2_afterACB = CE2_afterACB - CE2_Success_Count;
	    			System.out.println(CE2_afterACB);
	    	
	        		Thread.sleep(160);
	        		CE2_tryCount++;
	        		
	        		if(CE2_tryCount == MaxTry) {  //MaxTry
	        			break;
	        		}
	        	}
	        		//CE2
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		    endTime=System.currentTimeMillis();
		    Avg_Proccess_Time += (endTime- startTime); 
	    }

	    
	    
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimerTask tsk = new nprach_demo1();
		CE0_afterACB = acb_method(CE0_wait_comming);
		CE1_afterACB = acb_method(CE1_wait_comming);
		CE2_afterACB = acb_method(CE2_wait_comming);
		CE0_wait_comming = CE0_wait_comming - CE0_afterACB;
		CE1_wait_comming = CE1_wait_comming - CE1_afterACB;
		CE2_wait_comming = CE2_wait_comming - CE2_afterACB;
		//System.out.println("CE0_wait_comming:" + CE0_wait_comming + " CE0_afterACB:" + CE0_afterACB);
		tsk.run();
		CE0_wait_comming = CE0_wait_comming + CE0_afterACB;
		CE1_wait_comming = CE1_wait_comming + CE1_afterACB;
		CE2_wait_comming = CE2_wait_comming + CE2_afterACB;
		System.out.println("CE0_wait_comming:" + CE0_wait_comming);
		System.out.println("CE1_wait_comming:" + CE1_wait_comming);
		System.out.println("CE2_wait_comming:" + CE2_wait_comming);
		
/*
		Thread CE0 = new CE0_Status();
		CE0.start();
		System.out.println("Proccessing time： "+ (endTime-startTime) + "ms");
	
		*/  //thread consider 
		
	}
	
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

/* 			//thread consider 
class CE0_Status extends Thread{
	int[] subcarrier_CE0 = new int [24];
	int CE0_wait_comming = 30;
	int CE0_dy_subcarrier = 24;
	int CE0_Success_Count =0;
	int CE0_tryCount = 0;
	int MaxTry = 5;
	public CE0_Status() {
		
	}
	public void run() {
		
		while(CE0_wait_comming > 0){
		if(CE0_wait_comming != 0 && CE0_tryCount < MaxTry) {   //處理maxtry problem
    		int[] subcarrier_CE0 = new int [CE0_dy_subcarrier];
			CE0_Success_Count = 0;
			Random rr = new Random();
			//System.out.println(rr.nextInt(24));
			for(int i = 0; i<CE0_wait_comming; i++) {
				subcarrier_CE0[rr.nextInt(CE0_dy_subcarrier)] += 1;
			}
			for(int t = 0; t<CE0_dy_subcarrier; t++) {
				System.out.print(subcarrier_CE0[t] + " ");  //show subcarriers situation
				if(subcarrier_CE0[t] == 1 ) {
					CE0_Success_Count +=1 ;
				}
			}
			System.out.println("\r" + CE0_Success_Count);
			CE0_wait_comming = CE0_wait_comming - CE0_Success_Count;
			System.out.println(CE0_wait_comming);
		
    		try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		CE0_tryCount++; 
			}
		}
		
	}
	
}*/		 //thread consider 
	
