package org.cz.test;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            List<String> result = new ArrayList<>();
            List<List<Integer>> result2 = new ArrayList<List<Integer>>();
//            int[][] result2 = new int[1000][1000];
            int M =in.nextInt();
            int total = M+1;
            for(int i=2;i<=total/2+1;i++){
                if(i%2==1){
                    if(total%i==0){
                        int center=total/i;
                        int step=(i-1)/2;
                        String temp="";
                        if(center-step<1) continue;
                        List<Integer> tmp = new ArrayList<>();
                        for(int j=center-step;j<center+step;j++){
//                            temp+=j;
//                            temp+=" ";
                            tmp.add(j);

                        }
                        temp+=center+step;
                        tmp.add(center+step);
//                        result.add(temp);
                        result2.add(tmp);
                    }
                }else{
                    int temp2 = total-(i-1)*i/2;
                    if(temp2%i==0){
                        int start=temp2/i;
                        if(start<1) continue;
                        String temp="";
                        List<Integer> tmp = new ArrayList<>();
                        for(int j=0;j<i-1;j++){
                            temp=temp+(start+j);
                            temp+=" ";
                            tmp.add(start+j);
                        }
                        temp+=(start+i-1);
                        tmp.add(start+i-1);
//                        result.add(temp);
                        result2.add(tmp);
                    }
                }
            }
            cmp(result2);
            for (int i=0;i<result2.size();i++){
                for(int j=0;j<result2.get(i).size();j++){
                    System.out.print(result2.get(i).get(j)+" ");
                }
                System.out.println();
            }
        }
    }

//    public static void dAM1(List<List<Integer>> a) {
//        for()
//    }

    public static void cmp(List<List<Integer>> src){
        for(int i=0;i<src.size()-1;i++){
            for(int j=i+1;j<src.size();j++){
                int minLength = src.get(i).size()>src.get(j).size()?src.get(j).size():src.get(i).size();
                int k=0;
                for(k=0;k<minLength;k++){
                    if(src.get(i).get(k).intValue()==src.get(j).get(k).intValue()){
                        continue;
                    }else if (src.get(i).get(k).intValue()<src.get(j).get(k).intValue()){
                        break;
                    }else{
                        List<Integer> tmp = src.get(i);
                        src.set(i,src.get(j));
                        src.set(j,tmp);
                        break;
                    }
                }
                if(k==minLength){

                }
            }
        }
    }
}