
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class part_cost {
    //public Optimal opt;
    public static void main(String[] args) throws IOException{

        double[][] cost,temp = null;
    	File myObj = new File("inp.txt");
    	Scanner myReader = new Scanner(myObj);
    	FileWriter myWriter = new FileWriter("output3_raf1.txt");
    	Optimal opt;
        int r=10, c=10;
        cost = new double[r][c];
        temp = new double[r][c];
        for (int i = 0; i < r; i++)
        {
            for (int j = 0; j < c; j++)
            {
                cost[i][j] = myReader.nextInt();
            }
        }

        for (int i = 0; i < r; i++)
        {
            for (int j = 0; j < c; j++)
            {
                System.out.print(cost[i][j] + " ");
            }
            System.out.println();
        }
        for (int cust = 1; cust < r+1; cust++)
        {
            for(int i=0;i<cust;i++)
            {
                for(int j=0;j<c;j++)
                {
                    temp[i][j] = cost[i][j];
                }
            }
            opt = new Optimal(temp);
            int[] result = opt.execute();
            
            
            for(int i=0;i<cust;i++)
            {   
                System.out.print(result[i]+1 + " ");
                myWriter.write(result[i]+1 + " ");
            }
            myWriter.write("\n");
            System.out.println();
        
        }

        // opt = new Optimal(cost);
        // long start = System.nanoTime();
        // int[] result = opt.execute();
        // long finish = System.nanoTime();
        // long time = finish - start;
        // double sum=0;
        // for(int i=0;i<c;i++)
        //     sum+=cost[i][result[i]];
        
        // myWriter.write(String. format("%.4f", sum) + "\t" + time+"\t");
        // for(int i=0;i<c;i++)
        //     myWriter.write(result[i] + " ");
        // myWriter.write("\n");
        
//	        System.out.println("Matching: " + Arrays.toString(result));
//	        System.out.println("Total cost: " + sum + time);
        myReader.close();
        myWriter.close();
    }
}
