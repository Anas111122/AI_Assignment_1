package ai.assignment.pkg1;
 
import java.util.Queue; 
import java.util.Scanner;
import java.util.*;
import java.lang.NullPointerException;
import static java.lang.System.exit;

public class Driver {
  
    
    
    public static boolean testGoal(int state,int finalState)
    {
        if(state==finalState)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    
    @SuppressWarnings("null")
    public static void main(String[] args) {
        
        System.out.println("============================ Start Program ============================");
        System.out.println("");
    
	int M, N, T,num;
        Scanner input=new Scanner(System.in);
	System.out.print("Enter the total number of States : "); 
        M=input.nextInt();
	System.out.print("Enter the total number of Actions : ");
	N=input.nextInt();
	System.out.print("Enter the total number of Plans : ");
        T=input.nextInt();
	Queue<Node>frontier = new LinkedList<>();               //Frontier is Created
	Set<Node>explored = new HashSet<Node>();		//Explored set is Created
	int [][]plans = new int[T][2];                          //Generate plans Array
        String [][] noOfPlans = new String[T][2];               //Total noOfPlans array
        
        String start=null;
        String Final=null;
        
        
	
        System.out.println("=============================== Enter The States ==================================");
        System.out.println("");
	String [] mState=new String[M]; //States string array is created
	int [][]testCases = new int[M][N];//testCases 2D int array is created

	for (int i = 0; i < M; i++)
	{
		System.out.print("Enter The state "+(i+1)+" : "); 
		mState[i]=input.next();

	}

        
	
	System.out.println("=============================== Enter The Actions ==================================");
        System.out.println("");
	String [] nActions = new String[N];
	for (int i = 0; i < N; i++)
	{
		System.out.print("Enter action No. "+(i+1)+" : ");
		nActions[i]=input.next();
	}
        
        
	
        System.out.println("=============================== Enter The TEST CASES ==================================");
int c=0;
do{
	for (int i = 0; i < M; i++)
	{
            System.out.print("Enter testCase "+(i+1)+" : ");
		for (int j = 0; j < N; j++)
		{
			num=input.nextInt();
			if (num >= M)
			{
                                c++;
			}
			else
			{
				testCases[i][j] = num;
                                System.out.print(" ");
			}
                }
	}System.out.println("");
     if(c!=0)
     {
         System.out.println("Some of numbers is out of range");
         System.out.println("Please Enter this again ");
     }
}while(c!=0);
		System.out.println("=============================== Enter The Starting & Final States ==================================");
		System.out.println(" ");
                int count = 0;
                int seq=0;
              do{  
                    
                    for (int o = 0; o < T; o++)
                    {   seq++;
			System.out.println("========== Plan No. " + seq + " =========");
			System.out.print("Enter the Starting State: ");
			start=input.next();
			System.out.print("Enter the Final State : ");
			Final=input.next();
			noOfPlans[o][0]=start;
			noOfPlans[o][1]=Final;
			System.out.println(" ");

                    }      

		//=============================== Generate Plans Array ==================================//
		
                
		for (int i = 0; i < T;i++)
		{ 
			for (int j = 0; j <M ; j++)
			{

				if (noOfPlans[i][0].equalsIgnoreCase(mState[j])==true)
				{
					plans[i][0] = j;
					count++;
				}
                                if (noOfPlans[i][1].equalsIgnoreCase(mState[j])==true)
				{
					plans[i][1] = j;
					count++;
				}
			}
		}
		if (count-T != T)
		{
			System.out.println("You entered wrong states of plans ");
			System.out.println("Enter them again ");
                        count=0;
		}
              }while(count==0);
		
		//=============================== Move to the Frontier ==================================//

                
                
        int applyCost=0; 
        int act=1;
        int counting=0;
		for (int i = 0; i < T; i++)
		{

                        Node init =new Node();
                        init.setState(plans[i][0]);  //Initial Emtpy Node
                        init.setAction(-1);
                        init.setCost(0);
                        init.setParent(null);
                        System.out.println("=============================== Plan No."+act+" Output ==================================");
			frontier.add(init);
			while (!frontier.isEmpty())
			{
                            if(counting>50)
                            {
                                System.out.println("Given Sequence is not possible");
                                exit(0);
                            }
                            counting++;
                                Node check = new Node();
				check = frontier.remove();
                                boolean test=testGoal(check.getState(),plans[i][1]);
                                if(test!=true)
                                {
                                    applyCost++;
                                    for(int k=0;k<M;k++)
                                    {
                                        if(check.getState()==k)
                                        {
                                            
                                            for(int child=0;child<N;child++)
                                            {
                                                Node p = new Node(); //Generate child nodes
                                                p.setState(testCases[k][child]);
                                                p.setCost(applyCost);
                                                p.setAction(child);
                                                p.setParent(check);
                                                if(p.getState()!=check.getState())
                                                {
                                                    frontier.add(p);
                                                }
                                            }
                                            explored.add(check);
                                        }
                                    }
                                }
                                else{
                                    ArrayList <Node> list=new ArrayList<>();
                                    while(check.getParent()!=null)
                                    {
                                        list.add(check);
                                        check=check.getParent();
                                        //Adding Node in ArrayList
                                    }
                                    for(int s=list.size()-1;s>=0;s--)
                                    {
                                        System.out.print(nActions[list.get(s).getAction()]);
                                        if(s>0)
                                        {
                                            System.out.print(" ---> ");
                                        }
                                        
                                    }System.out.println("");
                                    explored.clear();
                                    frontier.clear();
                                    break;
                                    
                                }
                                
                                
			}
                        act++;
		}
    
}

}
