package HillClimbing;

import java.util.ArrayList;

public class Checker {
    public int n=7;
    public int R=3;
    public int q=3;
    public int numUn;
    public String[] bettingSystem;
    public String[] outcomes;
    public int numOut;

    public void setBettingSystem(ArrayList<Integer> betts){
//        String[] bettingSys= new String[betts.size()];
        bettingSystem= new String[betts.size()];
        for(int i=0; i<betts.size();i++){
            bettingSystem[i]=convertFromBaseToBase(betts.get(i), 10, 3);
        }
        generateOutcomes();
//        System.arraycopy(bettingSys, 0, bettingSystem, 0, betts.size());
    }

    public String convertFromBaseToBase(int num, int fromBase, int toBase) {
        String str= Integer.toString(num);
        String str1= Integer.toString(Integer.parseInt(str, fromBase), toBase);
        while(str1.length()<n){
            str1="0"+str1;
        }
        return str1;
    }
    public void generateOutcomes(){
        numOut=0;
        outcomes=new String[3000];
        for(int i=0; i<q; i++){
            for(int j=0; j<q; j++){
                for(int k=0; k<q; k++){
                   for(int h=0; h<q; h++){
                       for(int l=0; l<q; l++) {
                           for(int m=0; m<q; m++) {
                               for(int p=0; p<q; p++) {
                                   outcomes[numOut] = "" + i + j + k + h + l + m+p;
                                   numOut++;
                               }
                           }
                       }
                  }
                }
            }
        }
    }

    public void checkSystem(String [] bettingSystem){
//        if(bettingSystem.length<q){
//            return;
//        }
        for(int i=0; i<this.bettingSystem.length; i++){
            for(int j=0; j<numOut;j++){
                int errors=0;
                for(int k=0; k<n; k++){
                    if(this.bettingSystem[i].charAt(k)!=outcomes[j].charAt(k)){
                        errors++;
                    }
                }
                if(errors<=R){
                    outcomes[j]+=" Covered";
                }
            }
        }
        numUn=0;
        for(int i=0; i<numOut;i++){
          //  System.out.println(outcomes[i]);
            if(!outcomes[i].contains("Covered")){
                numUn++;
            }
        }

    }
}

