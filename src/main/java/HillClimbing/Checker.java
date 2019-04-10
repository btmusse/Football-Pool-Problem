package HillClimbing;

import java.util.ArrayList;

public class Checker {
    public int n=6;
    public int R=2;
    public int q=3;
    public int numUn;
    public String[] bettingSystem;
    public String[] outcomes;
    public int numOut;
    public static void main(String args[]){
        Checker check= new Checker();
        check.generateOutcomes();
        System.out.println(check.numOut);

        check.generateSystem();
        check.checkSystem(null);
        System.out.println(check.numUn);
        int[] system= new int[17];
        for(int i=0; i<16; i++){
            system[i]=Integer.parseInt(check.bettingSystem[i], 3);//check.convertFromBaseToBase(Integer.parseInt(check.bettingSystem[i]), 3, 10));
        }
        for(int i=0; i<16;i++){
            for(int j=0; j<15; j++){
                if(system[j]>system[j+1]){
                    int temp= system[j];
                    system[j]=system[j+1];
                    system[j+1]=temp;
                }
            }
        }
        for(int i=0; i<16; i++){
            System.out.println(check.convertFromBaseToBase(Integer.parseInt(check.bettingSystem[i]), 3, 10));
        }
        System.out.println(check.numUn);

    }
    public int findOrder(int n){
        int temp=n;
        int ord=1;
        while(temp!=0){
            temp=(temp+n)%729;
            ord++;
        }
        return ord;
    }
    public void setBettingSystem(ArrayList<Integer> betts){
//        String[] bettingSys= new String[betts.size()];
        bettingSystem= new String[betts.size()];
        for(int i=0; i<betts.size();i++){
            bettingSystem[i]=convertFromBaseToBase(betts.get(i), 10, 3);
        }
        generateOutcomes();
//        System.arraycopy(bettingSys, 0, bettingSystem, 0, betts.size());
    }
    public void generateSystem() {
        //bettingSystem=new String[28];
        bettingSystem = new String[]{
//                // "212012" ,//*
//                "010120",
//                "021201",
//                "101020",
//                "100102",
//                "112211",
//                "221122",
//                "222222",
//                "000000",
//                "210012",
//                "002100",
//                "122021",
//                "121210",
//                "201221",
//                "011002",
//                "111111",
//                "202010",
//                "212112"

//                // "212012" ,//*
//                "010120" ,
//                "021201" ,
//                "101020" ,
//                "100102" ,
//                "112211" ,
//                "221111" ,
//                "222222" ,
//                "210000" ,
//                "000012" ,
//                "002100" ,
//                "122021" ,
//                "121210" ,
//                "201221" ,
//                "011002",
//                "111122" ,
//                "202010" ,
//                "212112"
                "000102", "001111", "002120", "101100", "110021",
                "112012", "112201", "120221", "122001", "122212",
                "202101", "210210", "211000", "211222", "220010",
                "221022", "221200"
//        {
//            "000000", "011111", "022222", "100012", "111120",
//                    "122201", "200021", "211102", "222210", "001201",
//                    "012002", "020110", "102122", "110221", "121010",
//                    "200102", "210212", "222021", "000022", "011011",
//                    "201121" };
//                {
//                        "000000",
//                        "010111",
//                        "101011",
//                        "112222",
//                        "000120",
//                        "021000",
//                        "111102",
//                        "022201",
//                        "102110",
//                        "120022",
//                        "012020",
//                        "021212",
//                        "110200",
//                        "021121",
//                        "100201",
//                        "102002"
//                   "121201",// "010120" ,
//                "102012",//"021201" ,
//                "212101",//"101020" ,
//                "211210",//"100102" ,
//                "220022",//"112211" ,
//                "002222",//"221111" ,
//                "000000",//"222222" ,
//                "021111",//"210000" ,
//                "111120",//"000012" ,
//                "110211",//"002100" ,
//                "200102",//"122021" ,
//                "202021",//"121210" ,
//                "012002",//"201221" ,
//                "122110",//"011002",
//                "222200",//"111122" ,
//                "010121",//"202010" ,
//                "020220" //"212112"
//                        "010120" ,
//                        "021201" ,
//                        "101020" ,
//                        "100102" ,
//                        "112211" ,
//                        "221111" ,
//                        "222222" ,
//                        "210000" ,
//                        "000012" ,
//                        "002100" ,
//                        "122021" ,
//                        "121210" ,
//                        "201221" ,
//                        "011002",
//                        "111122" ,
//                        "202010" ,
//                        "212112"
                };
//       bettingSystem= new String[] {
//               "000111", "011000", "022222", "100002", "111120",
//               "201211", "222100", "120011", "212212", "200021",
//               "012201", "120220", "221102", "102110", "021012",
//               "012122", "101200", "212020", };
//    }
    }
    public String[] convertToSystem(int [] bettingSystem){
        String bettingSys[]= new String[bettingSystem.length];
        for(int i=0; i<bettingSys.length;i++){
            bettingSys[i]=convertFromBaseToBase(bettingSystem[i],10,3);
        }
        return bettingSys;
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
        outcomes=new String[1000];
        for(int i=0; i<q; i++){
            for(int j=0; j<q; j++){
                for(int k=0; k<q; k++){
                   for(int h=0; h<q; h++){
                       for(int l=0; l<q; l++) {
                           for(int m=0; m<q; m++) {
                               outcomes[numOut] = "" + i + j + k + h + l+m;
                               numOut++;
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

