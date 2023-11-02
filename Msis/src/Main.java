import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        FindMaximumSumIncreasingSubsequence subsequence=new FindMaximumSumIncreasingSubsequence();
        System.out.println("Enter size of the array");
        Scanner sc=new Scanner(System.in);
        int num=sc.nextInt();
        int[] total=new int[num];
        System.out.println("Please enter the element that you want to check");
        for(int i=0;i<total.length;i++){
            total[i]=sc.nextInt();
        }
        List<Integer> result=subsequence.findMsis(total);
        System.out.println("Your expected Maximum sum increasing Subsequence is"+" "+result);

        }

}