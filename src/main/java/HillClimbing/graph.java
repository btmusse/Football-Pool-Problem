package HillClimbing;

public class graph {
    public int[][] graph;
    private int q;
    private int numSpaces;
    private int numOutcomes;
    public graph(int numberOut, int q, int numSpaces){
        graph=new int [numberOut][numberOut];
        this.numOutcomes= numberOut;
        this.q=q;
        this.numSpaces=numSpaces;
        generateCoverings();

    }
    private void generateCoverings(){
        String num1, num2;
        int numDiff;
        for(int i=0; i<numOutcomes; i++){
            num1=convertFromBaseToBase(i,10,3);
            for(int j=0;j<numOutcomes; j++){
                num2=convertFromBaseToBase(j,10,3);
                numDiff=0;
                for(int k=0; k<num1.length(); k++){
                    if(num1.charAt(k)!=num2.charAt(k)){
                        numDiff++;
                    }
                }
                if(numDiff<q+1){
                    graph[i][j]=1;
                }
                else{
                    graph[i][j]=0;
                }
            }
        }
    }

    public String convertFromBaseToBase(int num, int fromBase, int toBase) {
        String str= Integer.toString(num);
        String str1= Integer.toString(Integer.parseInt(str, fromBase), toBase);
        while(str1.length()<numSpaces){
            str1="0"+str1;
        }
        return str1;
    }


    public void printGraph(){
        for(int i=0; i<numOutcomes; i++){
            for(int j=0;j<numOutcomes; j++){
                System.out.print(graph[i][j]);
            }
            System.out.println();
        }
    }
}
