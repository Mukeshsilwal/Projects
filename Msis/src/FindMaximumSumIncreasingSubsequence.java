import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindMaximumSumIncreasingSubsequence {
    public List<Integer> findMsis(int[] arr){
        int n=arr.length;
        int[] msis=new int[n];
        for(int i=0;i<n;i++){
            msis[i]=arr[i];
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(arr[i]>arr[j]&&msis[i]<msis[j]+arr[i]){
                    msis[i]=msis[j]+arr[i];
                }
            }
        }
        int maxsum=-1;
        int lastInd=0;
        for(int i=0;i<n;i++){
            if(msis[i]>maxsum){
                maxsum=msis[i];
                lastInd=i;
            }
        }
        List<Integer> msisSeq = new ArrayList<>();
        int currentSum = maxsum;
        for (int i = lastInd; i >= 0; i--) {
            if (currentSum == msis[i]) {
                msisSeq.add(arr[i]);
                currentSum -= arr[i];
            }
        }

//        Collections.reverse(msisSeq);
        return msisSeq;
    }
}
