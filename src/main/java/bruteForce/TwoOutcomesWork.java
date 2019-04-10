package bruteForce;

public class TwoOutcomesWork {
    public String[] outcomes;
    public int numOut=1024;
    public int n=10;
    public int R=2;

    public static void main(String [] args){
        TwoOutcomesWork t= new TwoOutcomesWork();
        t.generateOutcomes();

        String[] bettingSystem= new String[]{
                "1111111100",
                "1111110011",
                "1111001111",
                "1100111111",
                "0011111111",
                "1111110000",
                "1111000011",
                "1100001111",
                "0000111111",
                "1111000000",
                "1100000011",
                "0000001111",
                "0000000011",
                "0000001100",
                "0000110000",
                "0011000000",
                "1100000000",
                "0000001111",
                "0000111100",
                "0011110000",
                "1111000000",
                "0000111111",
                "0011111100",
                "1111110000"

        };
        t.checkSystem(bettingSystem);
    }

    public void generateOutcomes(){
        int index=0;
        outcomes=new String[numOut];
        for(int i=0; i<2;i++)
            for(int j=0; j<2; j++)
                for(int k=0; k<2; k++)
                    for(int l=0; l<2; l++)
                        for(int m=0; m<2; m++)
                            for(int n=0; n<2; n++)
                                for(int o=0; o<2; o++)
                                    for(int p=0; p<2; p++)
                                        for(int q=0; q<2; q++)
                                            for(int r=0; r<2; r++){
                                                outcomes[index] = "" + i + j + k + l + m+n+o+p+q+r;
                                                index++;
                                            }
    }

    public void checkSystem(String [] bettingSystem){
        int numUn;
        for(int i=0; i<bettingSystem.length; i++){
            for(int j=0; j<numOut;j++){
                int errors=0;
                for(int k=0; k<n; k++){
                    if(bettingSystem[i].charAt(k)!=outcomes[j].charAt(k)){
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
            if(!outcomes[i].contains("Covered")){
                numUn++;
            }
        }
        System.out.println(numUn);

    }
}
