package other;

import java.util.ArrayList;
import java.util.List;

public class FindBetterSys {
    public int numOutcomes=729;
    public int q=2;
    public int numSpaces=6;
    public int graph[][]= new int[numOutcomes][numOutcomes];
    public boolean visited[]= new boolean[numOutcomes];
    public List<Integer> bettingSystem= new ArrayList<Integer>();
    public boolean removed[];

    public static void main(String[] args){
        List<Integer> betts=new ArrayList<Integer>();
        betts.add(96);
        betts.add(208);
        betts.add(276);
        betts.add(254);
        betts.add(400);
        betts.add(688);
        betts.add(728);
        betts.add(567);
        betts.add(5);
        betts.add(63);
        betts.add(466);
        betts.add(453);
        betts.add(538);
        betts.add(110);
        betts.add(368);
        betts.add(543);
        betts.add(635);
        checkTightnesss checkTightnesss= new checkTightnesss();
        FindBetterSys fb= new FindBetterSys(betts);
        fb.generateCoverings();
        for(int i=0; i<betts.size(); i++){
            fb.addNewBet(betts.get(i));
        }
        for(int i=0; i<betts.size();i++) {
        //    other.checkTightnesss.findNewBet(betts, fb.HillClimbing.graph, fb.visited, betts.get(i));

        }

    }


    FindBetterSys(List<Integer> bettingSystem){
        this.bettingSystem=bettingSystem;
        removed=new boolean[bettingSystem.size()];
        for(int i=0; i<bettingSystem.size();i++){
            removed[i]=false;
        }
    }
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

    public boolean removeBetPair(int seedIndex){
        int temp= erase(seedIndex);
        int temp2, temp3;
        int[] pBet= new int[2];
        for(int i=0; i<bettingSystem.size(); i++){
            temp2= erase(i);
            for(int k=0; k<bettingSystem.size();k++) {
                temp3=erase(k);
                pBet = checkForBet();
                System.out.println("("+pBet[0]+","+pBet[1]+")");
                if (pBet[0] != -1 && pBet[1] != -1) {
                    System.out.println("Success!");
                    return true;
                }
                bettingSystem.add(k, temp3);
            }
            bettingSystem.add(i,temp2);
        }
        bettingSystem.add(seedIndex, temp);
        return false;
    }
    private void addNewBet(int n){
        bettingSystem.add(n);
        for(int i=0; i<numOutcomes; i++) {
            if (graph[n][i] == 1 && !visited[i]) {
                visited[i]=true;
            }
        }
    }
    public int[] checkForBet(){
        int ret[]=new int[]{-1, -1};
        for(int i=0; i<numOutcomes;i++){
            addNewBet(i);
            for(int j=0; j<numOutcomes; j++) {
                addNewBet(j);
                if (complete(visited)) {
                    ret[0]=i;
                    ret[1]=j;
                    System.out.println("Success!");
                    return ret;
                } else {
                    erase(bettingSystem.size() - 1);
                }
            }
            erase(bettingSystem.size()-1);
        }
        return ret;
    }
    private int erase(int index){
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
    private boolean complete(boolean temp[]) {
        for(int i=0;i<numOutcomes;i++) {
            if(!temp[i]) {
                return false;
            }
        }
        return true;
    }


}
