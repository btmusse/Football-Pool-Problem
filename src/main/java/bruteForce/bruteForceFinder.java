package bruteForce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//Gives system for n=6 that is 17!
public class bruteForceFinder {
    public int numOutcomes=729;
    public int q=2;
    public int numSpaces=6;
    public int graph[][]= new int[numOutcomes][numOutcomes];
    public boolean visited[]= new boolean[numOutcomes];
    public List<Integer> bettingSystem= new ArrayList<Integer>();
    public List<Integer> possibleNext= new ArrayList<Integer>();
    public List<Integer> possibleNextNext= new ArrayList<Integer>();

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

        bettingSystem.clear();
        for(int i=0; i<numOutcomes; i++){
            visited[i]=false;
        }
//        while(!complete(visited)) {
//            System.out.println("Starting recursive Algorithm...");
//            findBettingSystem(3);
//            System.out.println("Recursion finished!");
//        }
//        while(!complete(visited)) {
//            nums = check16();
//            for (int i = 0; i < nums.length; i++) {
//                addNewBet(nums[i]);
//                if(complete(visited)){
//                    return;
//                }
//            }
//        }

//        int n;
//        while(!complete(visited)) {
//            calcBestNext();
//            calcBestNextNext();
//            n=calcBestNext3();
//            addNewBet(n);
//            recAlg();
//        }
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
    private boolean complete(boolean temp[]) {
        for(int i=0;i<numOutcomes;i++) {
            if(!temp[i]) {
                return false;
            }
        }
        return true;
    }
    private List<Integer> calcBestNext(){
        possibleNext.clear();
        int max=0;
        int temp;
        for(int i=0; i<numOutcomes; i++){
            temp=calcNext(i);
            if(max<temp){
                max=temp;
            }
        }
        for(int i=0; i<numOutcomes; i++){
            temp=calcNext(i);
            if(temp==max){
                possibleNext.add(i);
            }
        }
        return possibleNext;
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
    private int calcNextNext(int current, boolean temp[]) {
        int numCov=0;
        for(int i=0; i<numOutcomes; i++) {
            if(graph[current][i]==1&&!temp[i]) {
                numCov++;
            }
        }
        return numCov;
    }
    //    private void setTempArray(){
//        for(int j=0; i<numOutcomes; j++) {
//            System.out.println(i +" , "+j );
//            if (HillClimbing.graph[i][j] == 1 && !temp[j]) {
//                temp[j]=true;
//            }
//        }
//    }
    private List<Integer> calcBestNextNext(){
        boolean temp[]= new boolean[numOutcomes];
        int nextBet=0;
        int max=0;
        int tempNum;

        for(int i=0; i<possibleNext.size();i++){
            System.arraycopy(visited, 0,temp,0,numOutcomes );
            for(int j=0; j<numOutcomes; j++) {
                if (graph[i][j] == 1 && !temp[j]) {
                    temp[j]=true;
                }
            }
//            if(complete(temp)){
//                return i;
//           }
            tempNum=calcNextNext(possibleNext.get(i), temp);
            if(max<tempNum){
                max=tempNum;
            }
        }
        for(int i=0; i<possibleNext.size(); i++){
            System.arraycopy(visited, 0,temp,0,numOutcomes );
            if(calcNextNext(possibleNext.get(i), temp)==max){
                possibleNextNext.add(i);
            }
        }
        return possibleNextNext;
    }
    private int calcBestNext3(){
        boolean temp[]= new boolean[numOutcomes];
        int nextBet=0;
        int max=0;
        int tempNum;

        for(int i=0; i<possibleNextNext.size();i++){
            System.arraycopy(visited, 0,temp,0,numOutcomes );
            for(int j=0; j<numOutcomes; j++) {
                if (graph[i][j] == 1 && !temp[j]) {
                    temp[j]=true;
                }
            }
            if(complete(temp)){
                return i;
           }
            tempNum=calcNextNext(possibleNextNext.get(i), temp);
            if(max<tempNum){
                max=tempNum;
                nextBet=possibleNextNext.get(i);
            }
        }
        return possibleNext.get(possibleNextNext.get(nextBet));
    }
    private int calcBestNextMaxHamDist(){
        int current =bettingSystem.get(bettingSystem.size()-1);
        int numDiff, index;
        String num1,num2;
        int max=0;
        index=0;
        num1=convertFromBaseToBase(current,10,3);
        for(int i=0;i<possibleNext.size();i++) {
            numDiff=0;
            num2=convertFromBaseToBase(possibleNext.get(i),10,3);
            for (int k = 0; k < num1.length(); k++) {
                if (num1.charAt(k) != num2.charAt(k)) {
                    numDiff++;
                }
            }
            if(numDiff>max){
                max=numDiff;
                index=possibleNext.get(i);
            }
        }
        return index;
    }


    public int[] check16(){
        System.out.println("Beginning check:");
        int numCov;
        int max=0;
        int a=-1,b=-1,c=-1;
        boolean[] temp= new boolean[numOutcomes];
        for(int i0=0; i0<numOutcomes; i0++){
            System.out.println("Working...");
            for(int i1=0; i1<numOutcomes; i1++){
                for(int i2=0; i2<numOutcomes; i2++){
                    System.arraycopy(visited, 0, temp, 0, visited.length);
                    numCov=0;
                    for(int i=0; i<numOutcomes;i++){
                        if((graph[i][i0]==1||graph[i][i1]==1||graph[i][i2]==1)&&!temp[i]){
                            numCov++;
                        }
                    }
                    if(numCov>max){
                        a=i0;
                        b=i1;
                        c=i2;
                        max=numCov;
                    }
                }
            }
        }
        System.out.println("Checking Complete!\nAdding bet: "+a+"\nAdding bet: "+b+"\nAdding bet: "+c);
        int [] bets= new int[]{a,b,c};
        return bets;

    }
    boolean checkSystem(int [] bettingsys){
        for(int i=0; i<numOutcomes;i++){
            visited[i]=false;
        }
        for(int i=0; i<bettingsys.length;i++){
            for(int j=0; j<numOutcomes; j++){
                if(graph[bettingsys[i]][j]==1&&!visited[j]){
                    visited[j]=true;
                }
            }
        }
        if(complete(visited)){
            return true;
        }
        return false;
    }

    private List<Integer> calcBestNext(boolean[] visited){
        possibleNext.clear();
        int max=0;
        int temp;
        for(int i=0; i<numOutcomes; i++){
            temp=calcNext(i, visited);
            if(max<temp){
                max=temp;
            }
        }
        for(int i=0; i<numOutcomes; i++){
            temp=calcNext(i, visited);
            if(temp==max){
                possibleNext.add(i);
            }
        }
        return possibleNext;
    }
    private int calcNext(int current, boolean[] visited) {
        int numCov=0;
        for(int i=0; i<numOutcomes; i++) {
            if(graph[current][i]==1&&!visited[i]) {
                numCov++;
            }
        }
        return numCov;
    }
    private int calcNumCovered(boolean[] visited){
        int numCov=0;
        for( int i=0; i<numOutcomes;i++){
            if(visited[i]){
                numCov++;
            }
        }
        return numCov;
    }
    private int erase(List<Integer> bettingSystem, int index, boolean[] visited){
        if(index==-1){
            return 0;
        }
        int temp=bettingSystem.remove(index);
        for(int i=0; i<numOutcomes; i++){
            visited[i]=false;
        }
        for(int i=0;i<bettingSystem.size(); i++){
            for(int j=0; j<numOutcomes;j++){
                if(graph[i][j]==1&&!visited[j]){
                    visited[j]=true;
                }
            }
        }
        return temp;
    }
    private void addNewBet(List<Integer> bettingSystem, int n, boolean[] visited){
        bettingSystem.add(n);
        for(int i=0; i<numOutcomes; i++) {
            if (graph[n][i] == 1 && !visited[i]) {
                visited[i]=true;
            }
        }
    }
}

