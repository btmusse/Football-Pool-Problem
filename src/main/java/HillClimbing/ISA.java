package HillClimbing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ISA {
    private int numOut=2187;
    private int q=3;
    private int R=3;
    private int n=7;
    public graph g;
    private ArrayList<Integer> possibleNext= new ArrayList<Integer>();
    public ArrayList<Integer> bettingSystem;
    private boolean[] visited= new boolean[numOut];

    public ISA(ArrayList<Integer> bettingSystem){
        g=new graph(numOut,R,n );
        setBettingSystem(bettingSystem);
        hillClimbing();
    }
    public void setBettingSystem(ArrayList<Integer> bettingSys){
        bettingSystem= new ArrayList<Integer>();
        for(int i=0; i<bettingSys.size(); i++){
            addNewBet(bettingSystem, bettingSys.get(i), visited);
        }
    }

    public void hillClimbing(){
        boolean keepGoing=true;
        int increment=0;
        int progress=0;
        //System.out.print("Algorithm start...");
        while(keepGoing){
           progress= climb(bettingSystem);
           if(progress==0){
               increment++;
//               System.out.println("incrementing");
           }
           else{
               increment=0;
           }
           if(increment>20){
               keepGoing=false;
           }
        }
    }

    private int climb(ArrayList<Integer> bettingSystem){
        int numberCovered=0;
        int initial;
        int maxCovered;
        int removedBet;
        int tempint=0;
        int temp= 0;
        initial=calcNumCovered(visited);
        for(int i=0; i< bettingSystem.size(); i++){
            maxCovered=calcNumCovered(visited);
            removedBet=erase(bettingSystem, i, visited);
            temp=removedBet;
            calcBestNext(visited);
            for(int j=0; j<numOut; j++){
                int bet=j;
                addNewBet(bettingSystem,bet, visited);
                tempint=calcNumCovered(visited);
                if(tempint>maxCovered){
                    temp=bet;
                    maxCovered=tempint;
                }
                erase(bettingSystem,bettingSystem.indexOf(bet), visited);
            }
           addNewBet(bettingSystem, temp, visited);
        }
        if(calcNumCovered(visited)>initial){
            numberCovered=calcNumCovered(visited)-initial;
        }
        if(numberCovered>0){
            return numberCovered;
        }
        else{
            return 0;
        }
    }

    private int calcNumCovered(boolean[] visited){
        int numCov=0;
        for( int i=0; i<numOut;i++){
            if(visited[i]){
                numCov++;
            }
        }
        return numCov;
    }
    private void addNewBet(ArrayList<Integer> bettingSystem, int n, boolean[] visited){
        bettingSystem.add(n);
        for(int i=0; i<numOut; i++) {
            if (g.graph[n][i] == 1 && !visited[i]) {
                visited[i]=true;
            }
        }
    }

    private int getRandomBet(){
        Random r= new Random();
        int betToAdd= possibleNext.get(r.nextInt(possibleNext.size()));
        return betToAdd;
    }
    private List<Integer> calcBestNext(boolean[] visited){
        possibleNext.clear();
        int max=0;
        int temp;
        for(int i=0; i<numOut; i++){
            temp=calcNext(i, visited);
            if(max<temp){
                max=temp;
            }
        }
        for(int i=0; i<numOut; i++){
            temp=calcNext(i, visited);
            if(temp==max){
                possibleNext.add(i);
            }
        }
        return possibleNext;
    }
    private int calcNext(int current, boolean[] visited) {
        int numCov=0;
        for(int i=0; i<numOut; i++) {
            if(g.graph[current][i]==1&&!visited[i]) {
                numCov++;
            }
        }
        return numCov;
    }
    private int erase(List<Integer> bettingSystem, int index, boolean[] visited){
        int temp=bettingSystem.remove(index);
        for(int i=0; i<numOut; i++){
            visited[i]=false;
        }
        for(int i=0;i<bettingSystem.size(); i++){
            for(int j=0; j<numOut;j++){
                if(g.graph[bettingSystem.get(i)][j]==1&&!visited[j]){
                    visited[j]=true;
                }
            }
        }
        return temp;
    }

    public static void main(String[] args){
        ArrayList<Integer> b=new ArrayList<Integer>();
//        b.add(0);
//        b.add(94);
//        b.add(274);
//        b.add(404);
//        b.add(15);
//        b.add(189);
//        b.add(362);
//        b.add(235);
//        b.add(309);
//        b.add(413);
//        b.add(141);
//        b.add(212);
//        b.add(342);
//        b.add(205);
//        b.add(262);
//        b.add(299);
        //b.add(728);
        Random r = new Random();
        IOwork io = new IOwork();
        IOwork badio= new IOwork();
        Checker check = new Checker();
        io.printerSetup("ISAOut.txt");
        badio.printerSetup("ISABadOut.txt");
        int inc=0;
        while(true) {
            b.clear();

            for (int i = 0; i < 73; i++) {
                b.add(r.nextInt(729));
            }
            ISA isa = new ISA(b);
            System.out.println(inc);
            inc++;
            check.setBettingSystem(isa.bettingSystem);
            check.checkSystem(null);
            if(check.numUn<14&&check.numUn>0){
                System.out.println("Less!");
                io.writeToFile(isa, check.numUn);
            }
            else if(check.numUn==0) {
                System.out.println("Solution!!!");
                io.writeToFile(isa, check.numUn);
                io.printerTeardown();
                break;
            }
            else{
                badio.writeToFile(isa, check.numUn);
            }
        }
    }
}
