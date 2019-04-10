package other;

import java.util.ArrayList;
import java.util.List;
public class checkTightnesss {
    int NUMBETS=729;
    public boolean[] cannotPick= new boolean[NUMBETS];
    public boolean[] visited= new boolean[NUMBETS];
    public List<Integer> replacements= new ArrayList<Integer>();
    public int[][] graph= new int[NUMBETS][NUMBETS];
    public List<Integer> bettingSystem= new ArrayList<Integer>();
    public int q=2;
    public checkTightnesss(){
        for(int i=0; i<NUMBETS; i++){
            cannotPick[i]=false;
        }
    }
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
        checkTightnesss.generateCoverings();
        for(int i=0; i<betts.size(); i++){
            checkTightnesss.addNewBet(betts.get(i));
        }
       // for(int i=0; i<betts.size();i++) {
            checkTightnesss.findNewBet(16);

      //  }

    }
    public void generateCoverings(){
        String num1, num2;
        int numDiff;
        for(int i=0; i<NUMBETS; i++){
            visited[i]=false;
            num1=convertFromBaseToBase(i,10,3);
            for(int j=0;j<NUMBETS; j++){
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
    public void findNewBet(int betToRemove){
        if(complete(visited)){
            System.out.println("Betting System is good!");
        }
        int temp=erase(betToRemove);
        System.out.println("Searching for replacements for "+convertFromBaseToBase(temp, 10, 3)+ ": \n");
        cannotPick[temp]=true;

        for(int i=0; i<NUMBETS; i++){
            addNewBet(i);
            if(complete(visited)&&!cannotPick[i]){
                replacements.add(i);
            }

            erase(bettingSystem.size()-1);

        }
        if(!replacements.isEmpty()){
            System.out.println("Replacements for "+convertFromBaseToBase(temp, 10, 3)+ ": \n");
            for(int i=0; i<replacements.size(); i++){
                System.out.println(convertFromBaseToBase(replacements.get(i), 10, 3)+"\n");
            }
        }
        else{
            System.out.println("There are no replacements!");
        }
        cannotPick[temp]=false;
        addNewBet(betToRemove, temp);
    }
    private int erase(int index){
        int temp=bettingSystem.remove(index);
        for(int i=0; i<NUMBETS; i++){
            visited[i]=false;
        }
        for(int i=0;i<bettingSystem.size(); i++){
            for(int j=0; j<NUMBETS;j++){
                if(graph[i][j]==1&&!visited[j]){
                    visited[j]=true;
                }
            }
        }
        return temp;
    }
    private void addNewBet(int index, int n){
        bettingSystem.add(index, n);
        for(int i=0; i<NUMBETS; i++) {
            if (graph[n][i] == 1 && !visited[i]) {
                visited[i]=true;
            }
        }
    }
    private void addNewBet(int n){
        bettingSystem.add(n);
        for(int i=0; i<NUMBETS; i++) {
            if (graph[n][i] == 1 && !visited[i]) {
                visited[i]=true;
            }
        }
    }

    private boolean complete(boolean temp[]) {
        for(int i=0;i<NUMBETS;i++) {
            if(!temp[i]) {
                return false;
            }
        }
        return true;
    }
    public String convertFromBaseToBase(int num, int fromBase, int toBase) {
        String str= Integer.toString(num);
        String str1= Integer.toString(Integer.parseInt(str, fromBase), toBase);
        while(str1.length()<6){
            str1="0"+str1;
        }
        return str1;
    }
}
