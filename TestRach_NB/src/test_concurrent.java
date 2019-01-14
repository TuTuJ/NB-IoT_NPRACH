import java.util.Random;
import java.util.TimerTask;

public class test_concurrent extends TimerTask{
	//Define 3 CE level	
	float CE0_successRate, CE1_successRate, CE2_successRate = 0;
	int[] subcarrier_CE0 = new int [48];
	int[] subcarrier_CE1 = new int [36];
	int[] subcarrier_CE2 = new int [24];
	int[] subcarrier_CE3 = new int [12];
	int CE0_dy_subcarrier = 48, CE1_dy_subcarrier = 36, CE2_dy_subcarrier = 24, CE3_dy_subcarrier = 12;
	int CE0_dy_Count = 110, CE1_dy_Count = 80, CE2_dy_Count = 50, CE3_dy_Count = 25;
	int CE0_Success_Count, CE1_Success_Count, CE2_Success_Count, CE3_Success_Count = 0;
	int CE0_tryCount, CE1_tryCount, CE2_tryCount, CE3_tryCount = 0;
	int MaxTry = 10;
	int backOff_Time = 1024; //ms
	long startTime, endTime;
	long Avg_Proccess_Time = 0;
	    @Override
	    public void run() {
	        //System.out.println("Timer task started at:"+new Date());
	    	//startTime=System.currentTimeMillis();
	    	//CE0_completeTask();
	        //CE1_completeTask();
	        //CE2_completeTask();
	    	AllCE_completeTask();

	    	//endTime=System.currentTimeMillis();
	        System.out.println("CE0 Try Count:" + CE0_tryCount + "次");
	        System.out.println("CE0 剩下:" + CE0_dy_Count + "個");
	        System.out.println("CE1 Try Count:" + CE1_tryCount + "次");
	        System.out.println("CE1 剩下:" + CE1_dy_Count + "個");
	        System.out.println("CE2 Try Count:" + CE2_tryCount + "次");
	        System.out.println("CE2 剩下:" + CE2_dy_Count + "個");
	        System.out.println("CE3 Try Count:" + CE3_tryCount + "次");
	        System.out.println("CE3 剩下:" + CE3_dy_Count + "個");
	        //System.out.println("Timer task finished at:"+new Date());
	    }

	    private void AllCE_completeTask() {

	    	try {
	 	    	//CE0
	        	while(CE0_dy_Count > 0){ 
	        		int[] subcarrier_CE0 = new int [CE0_dy_subcarrier];
	    			CE0_Success_Count = 0;
	    			Random rr = new Random();
	    			//System.out.println(rr.nextInt(24));
	    			for(int i = 0; i<CE0_dy_Count; i++) {
	    				subcarrier_CE0[rr.nextInt(CE0_dy_subcarrier)] += 1;
	    			}
	    			for(int t = 0; t<CE0_dy_subcarrier; t++) {
	    				System.out.print(subcarrier_CE0[t] + " ");  //show subcarriers situation
	    				if(subcarrier_CE0[t] == 1 ) {
	    					CE0_Success_Count +=1 ;
	    				}
	    			}
	    			System.out.println("\r" + CE0_Success_Count);
	    			CE0_dy_Count = CE0_dy_Count - CE0_Success_Count;
	    			System.out.println(CE0_dy_Count);
	    			
	        		Thread.sleep(40);
	        		CE0_tryCount++; 
	        		if(CE0_tryCount == MaxTry) {  //MaxTry
	        			break;
	   
	        		}
	        	}
	        	//CE0
	        		
	        	//CE1
	        	while(CE1_dy_Count > 0){
	        		int[] subcarrier_CE1 = new int [CE1_dy_subcarrier];
	    			CE1_Success_Count = 0;
	    			Random rr1 = new Random();
	    			for(int i = 0; i<CE1_dy_Count; i++) {
	    				subcarrier_CE1[rr1.nextInt(CE1_dy_subcarrier)] += 1;
	    			}
	    			for(int t = 0; t<CE1_dy_subcarrier; t++) {
	    				System.out.print(subcarrier_CE1[t] + " ");  //show subcarriers situation
	    				if(subcarrier_CE1[t] == 1 ) {
	    					CE1_Success_Count +=1 ;
	    				}
	    			}
	    			System.out.println("\r" + CE1_Success_Count);
	    			CE1_dy_Count = CE1_dy_Count - CE1_Success_Count;
	    			System.out.println(CE1_dy_Count);
	    		
	        		Thread.sleep(80);
	        		CE1_tryCount++;
	        		
	        		if(CE1_tryCount == MaxTry) {  //MaxTry
	        			break;
	        		}
	        	}
	        		//CE1
	        		
	        		//CE2
	        	while(CE2_dy_Count > 0){
	        		int[] subcarrier_CE2 = new int [CE2_dy_subcarrier];
	    			CE2_Success_Count = 0;
	    			Random rr2 = new Random();
	    			//System.out.println(rr.nextInt(12));
	    			for(int i = 0; i<CE2_dy_Count; i++) {
	    				subcarrier_CE2[rr2.nextInt(CE2_dy_subcarrier)] += 1;
	    			}
	    			for(int t = 0; t<CE2_dy_subcarrier; t++) {
	    				System.out.print(subcarrier_CE2[t] + " ");  //show subcarriers situation
	    				if(subcarrier_CE2[t] == 1 ) {
	    					CE2_Success_Count +=1 ;
	    				}
	    			}
	    			System.out.println("\r" + CE2_Success_Count);
	    			CE2_dy_Count = CE2_dy_Count - CE2_Success_Count;
	    			System.out.println(CE2_dy_Count);
	    	
	        		Thread.sleep(160);
	        		CE2_tryCount++;
	        		
	        		if(CE2_tryCount == MaxTry) {  //MaxTry
	        			break;
	        		}
	        	}
	        		//CE2
	        	//
	        	while(CE3_dy_Count > 0){
	        		int[] subcarrier_CE3 = new int [CE3_dy_subcarrier];
	    			CE3_Success_Count = 0;
	    			Random rr3 = new Random();
	    			//System.out.println(rr.nextInt(12));
	    			for(int i = 0; i<CE3_dy_Count; i++) {
	    				subcarrier_CE3[rr3.nextInt(CE3_dy_subcarrier)] += 1;
	    			}
	    			for(int t = 0; t<CE3_dy_subcarrier; t++) {
	    				System.out.print(subcarrier_CE3[t] + " ");  //show subcarriers situation
	    				if(subcarrier_CE3[t] == 1 ) {
	    					CE3_Success_Count +=1 ;
	    				}
	    			}
	    			System.out.println("\r" + CE3_Success_Count);
	    			CE3_dy_Count = CE3_dy_Count - CE3_Success_Count;
	    			System.out.println(CE3_dy_Count);
	    	
	        		Thread.sleep(160);
	        		CE3_tryCount++;
	        		
	        		if(CE3_tryCount == MaxTry) {  //MaxTry
	        			break;
	        		}
	        	}
	        	//
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		
	
	    }

	    
	    
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimerTask tsk = new test_concurrent();
			tsk.run();


		
/*
		Thread CE0 = new CE0_Status();
		CE0.start();
		System.out.println("Proccessing time： "+ (endTime-startTime) + "ms");
	
		*/  //thread consider 
		
	}
	

}

/* 			//thread consider 
class CE0_Status extends Thread{
	int[] subcarrier_CE0 = new int [24];
	int CE0_dy_Count = 30;
	int CE0_dy_subcarrier = 24;
	int CE0_Success_Count =0;
	int CE0_tryCount = 0;
	int MaxTry = 5;
	public CE0_Status() {
		
	}
	public void run() {
		
		while(CE0_dy_Count > 0){
		if(CE0_dy_Count != 0 && CE0_tryCount < MaxTry) {   //處理maxtry problem
    		int[] subcarrier_CE0 = new int [CE0_dy_subcarrier];
			CE0_Success_Count = 0;
			Random rr = new Random();
			//System.out.println(rr.nextInt(24));
			for(int i = 0; i<CE0_dy_Count; i++) {
				subcarrier_CE0[rr.nextInt(CE0_dy_subcarrier)] += 1;
			}
			for(int t = 0; t<CE0_dy_subcarrier; t++) {
				System.out.print(subcarrier_CE0[t] + " ");  //show subcarriers situation
				if(subcarrier_CE0[t] == 1 ) {
					CE0_Success_Count +=1 ;
				}
			}
			System.out.println("\r" + CE0_Success_Count);
			CE0_dy_Count = CE0_dy_Count - CE0_Success_Count;
			System.out.println(CE0_dy_Count);
		
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
	
