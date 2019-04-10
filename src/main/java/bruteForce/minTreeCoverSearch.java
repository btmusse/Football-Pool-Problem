package bruteForce;

import java.util.*;
public class minTreeCoverSearch {
    public int numOutcomes=729;
    public int q=2;
    public int numSpaces=6;
    public int graph[][]= new int[numOutcomes][numOutcomes];
    public boolean visited[]= new boolean[numOutcomes];
    public List<Integer> bettingSystem= new ArrayList<Integer>();
    public Stack<Integer> spanningTree= new Stack<Integer>();

    public void generateCoverings(){
        String num1, num2;
        int numDiff;
        for(int i=0; i<numOutcomes; i++){
            visited[i]=false;
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

    private void addNewBet(int n){
        bettingSystem.add(n);
        for(int i=0; i<numOutcomes; i++) {
            if (graph[n][i] == 1 && !visited[i]) {
                visited[i]=true;
            }
        }
    }
    public void findBettingSystem(int startNum){
        int num=startNum;
        bettingSystem.clear();
        for(int i=0; i<numOutcomes; i++){
            visited[i]=false;
        }
        addNewBet(startNum);
        int n;
        while(!complete()) {
            n=calcBestNext();
            addNewBet(n);
        }
//        bettingSystem.clear();
//        for(int i=0; i<729; i++){
//            visited[i]=false;
//        }
//        int pos=startNum;
//        bettingSystem.add(startNum);
//        while(true){
//            visited[pos]=true;
//            spanningTree.push(pos);
//            if(!isCovered(pos)){
//                bettingSystem.add(pos);
//            }
//            pos= findNext(pos);
//            while(pos==-1){
//                if(spanningTree.isEmpty()){
//                    return;
//                }
//                pos=findNext(spanningTree.pop());
//            }

        }
    private boolean isCovered(int bet){
        for(int i : bettingSystem){
            if(graph[i][bet]==1){
                return true;
            }
        }
        return false;
    }
    private int findNext(int current){
        for(int i=0; i<numOutcomes; i++){
            if(graph[current][i]==1&&!visited[i]){
                return i;
            }
        }
        return -1;
    }
    private boolean complete() {
        for(int i=0;i<numOutcomes;i++) {
            if(!visited[i]) {
                return false;
            }
        }
        return true;
    }
    private int calcBestNext(){
        int num=0;
        int max=0;
        int temp;
        for(int i=0; i<numOutcomes; i++){
            temp=calcNext(i);
            if(max<temp){
                max=temp;
                num=i;
            }
        }
        return num;
    }
    private int calcNext(int current) {
        int numCov=0;
        for(int i=0; i<numOutcomes; i++) {
            if(graph[current][i]==1&&!visited[i]) {
                numCov++;
            }
        }
        return numCov;
    }
}
