package bruteForce;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class recursiveBetting {
    public int numOutcomes=729;
    public int recursions=1;
    public int q=2;
    public int numSpaces=6;
    public int graph[][]= new int[numOutcomes][numOutcomes];
    public ArrayList<Integer> order2= new ArrayList<Integer>();
    public ArrayList<Integer> order3= new ArrayList<Integer>();
    public ArrayList<Integer> order33= new ArrayList<Integer>();
    public ArrayList<Integer> order321= new ArrayList<Integer>();
    public ArrayList<Integer> order4= new ArrayList<Integer>();
    public ArrayList<Integer> order411= new ArrayList<Integer>();
    public ArrayList<Integer> order42= new ArrayList<Integer>();
    public ArrayList<Integer> order5= new ArrayList<Integer>();
    public ArrayList<Integer> order6= new ArrayList<Integer>();
    public boolean visited[]= new boolean[numOutcomes];
    public Stack<Integer> bettingSystem= new Stack<Integer>();

    public void partition(ArrayList<Integer> firstpart){
        int order=0;
        int ordernum2=0;
        for(int i=0; i<firstpart.size(); i++){
            order=calcOrder(i);
            ordernum2=calcOrder2(i);
            if(order==3) {
                switch (ordernum2) {
                    case 2:
                        order321.add(i);
                        break;
                    case 3:
                        order33.add(i);
                        break;
                }
            }
            else if(order==4) {
                switch (ordernum2) {
                    case 1:
                        order411.add(i);
                        break;
                    case 2:
                        order42.add(i);
                        break;
                }
            }
        }
    }
    public void partition(){
        int order=0;
        for(int i=0; i<numOutcomes; i++){
            order=calcOrder(i);
            switch(order){
                case 2:
                    order2.add(i);
                    break;
                case 3:
                    order3.add(i);
                    break;
                case 4:
                    order4.add(i);
                    break;
                case 5:
                    order5.add(i);
                    break;
                case 6:
                    order6.add(i);
                    break;
            }
        }
    }
    public String partitionToString(ArrayList<Integer> part){
        String retString="";
        int row=0;
        for(int i=0; i<part.size(); i++){
            retString= retString+""+toString(part.get(i))+", ";
            row++;
            if(row%5==0){
                retString=retString+"\n";
            }
        }
        return retString;
    }
    public int calcOrder(int i) {
        String bet = toString(i);
        int order = 0;
        int num0 = 0, num1 = 0, num2 = 0;
        for (int j = 0; j < numSpaces; j++) {
            if (bet.charAt(j) == '0') {
                num0++;
            } else if (bet.charAt(j) == '1') {
                num1++;
            } else {
                num2++;
            }
        }

        order = Integer.max(num0, num1);
        order = Integer.max(order, num2);

        return order;
    }

    public int calcOrder2(int i) {
        String bet = toString(i);
        int order = 0;
        int num0 = 0, num1 = 0, num2 = 0;
        for (int j = 0; j < numSpaces; j++) {
            if (bet.charAt(j) == '0') {
                num0++;
            } else if (bet.charAt(j) == '1') {
                num1++;
            } else {
                num2++;
            }
        }
        if((num0>num1&&num1>num2)||(num2>num1&&num1>num0))
            return num1;
        else if((num1>num0&&num0>num2)||(num2>num0&&num0>num1))
            return num0;
        else
            return num2;
    }
    public void findbetswithorder(){
        int[] betsToAdd;
        addNewBet(bettingSystem, 0, visited);
        removeCovered(order42);
        System.out.println("First Tripple: ");
        betsToAdd=findBestBettrippleinlist(order42, bettingSystem, visited);
        addNewBet(bettingSystem, betsToAdd[0], visited);
        System.out.println(toString(betsToAdd[0]));
        addNewBet(bettingSystem, betsToAdd[1], visited);
        System.out.println(toString(betsToAdd[1]));
        addNewBet(bettingSystem, betsToAdd[2], visited);
        System.out.println(toString(betsToAdd[2]));

        removeCovered(order411);
        System.out.println("Second Tripple: ");
        betsToAdd=findBestBettrippleinlist(order411,bettingSystem, visited);
        addNewBet(bettingSystem, betsToAdd[0], visited);
        System.out.println(toString(betsToAdd[0]));
        addNewBet(bettingSystem, betsToAdd[1], visited);
        System.out.println(toString(betsToAdd[1]));
        addNewBet(bettingSystem, betsToAdd[2], visited);
        System.out.println(toString(betsToAdd[2]));

        for(int i=0; i<3; i++){
            removeCovered(order321);
            System.out.println("Number of bets to choose from: "+order321.size());
            System.out.println("New Tripple: ");
            betsToAdd=findBestBettrippleinlist(order321,bettingSystem, visited);
            addNewBet(bettingSystem, betsToAdd[0], visited);
            System.out.println(toString(betsToAdd[0]));
            addNewBet(bettingSystem, betsToAdd[1], visited);
            System.out.println(toString(betsToAdd[1]));
            addNewBet(bettingSystem, betsToAdd[2], visited);
            System.out.println(toString(betsToAdd[2]));
        }
        for(int i=0; i<bettingSystem.size(); i++){
            System.out.println(toString(bettingSystem.get(i)));
        }
        if(complete(visited)){
            System.out.println("Complete!");
        }
    }
    private int[] findBestBettrippleinlist(ArrayList<Integer> list,Stack<Integer> bettingSystem, boolean[] covered){
        ArrayList<Integer> betting= new ArrayList<Integer>();
        int[] maxbets= new int[3];
        int numcov=0;
        int max=0;
        for(int i=0; i<list.size();i++){
            for(int j=0; j<list.size(); j++){
                for(int k=0; k<list.size(); k++){
                    addNewBet(bettingSystem, list.get(i), covered);
                    addNewBet(bettingSystem, list.get(j), covered);
                    addNewBet(bettingSystem, list.get(k), covered);
                    numcov=calcNumCovered(covered);
                    if(numcov>max){
                        max=numcov;
                        maxbets[0]=list.get(i);
                        maxbets[1]=list.get(j);
                        maxbets[2]=list.get(k);
                    }
                    erase(bettingSystem,  covered);
                    erase(bettingSystem, covered);
                    erase(bettingSystem, covered);

                }
            }
        }

        return maxbets;
    }
    public void removeCovered(ArrayList<Integer> betStore){
        for(int i=0; i<betStore.size(); i++){
            if(visited[betStore.get(i)]){
                betStore.remove(i);
            }
        }
    }
    public void findBettingSystem(int startNum) {
        bettingSystem.clear();
        for (int i = 0; i < numOutcomes; i++) {
            visited[i] = false;
        }
      //  addNewBet(bettingSystem, 0, visited);
      //  addNewBet(bettingSystem, startNum, visited);
        addNewBet(bettingSystem,94, visited);
        addNewBet(bettingSystem,274, visited);
        addNewBet(bettingSystem,404, visited);
        addNewBet(bettingSystem,15, visited);
        addNewBet(bettingSystem,189, visited);
        addNewBet(bettingSystem,362, visited);
        addNewBet(bettingSystem,235, visited);
        addNewBet(bettingSystem,309, visited);
        addNewBet(bettingSystem,413, visited);
        addNewBet(bettingSystem,141, visited);
        addNewBet(bettingSystem,212, visited);
        addNewBet(bettingSystem,342, visited);
        addNewBet(bettingSystem,205, visited);
        addNewBet(bettingSystem,262, visited);
        addNewBet(bettingSystem,299, visited);

        while (!complete(visited)) {
            System.out.println("Starting recursive Algorithm...");
            recAlg(bettingSystem, visited, recursions);
            for(int i=0; i<bettingSystem.size();i++) {
                System.out.println(bettingSystem.get(i));

            }
            System.out.println("Recursion finished!");
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

    private boolean complete(boolean temp[]) {
        for(int i=0;i<numOutcomes;i++) {
            if(!temp[i]) {
                return false;
            }
        }
        return true;
    }

    private int recAlg(List<Integer> bettingSystem, boolean[] visited, int numRecs){
        if(numRecs==0){
            return -3;
        }
        if(complete(visited)){
            return -1;
        }
        else{
            List<Integer> possibleNext=calcBestNext(visited);
            int max=0;
            ArrayList<Integer> maxindex=null;
            int compOut;
            int prevmaxindex=-1;

            for(int i=0; i<possibleNext.size();i++) {
                addNewBet(bettingSystem, possibleNext.get(i), visited);
                compOut=recAlg(bettingSystem, visited, numRecs-1);
                if(compOut==-1){
                    return -1;
                }
//                else if(compOut==-2) {
//                    compOut=calcNumCovered(visited);
//                     if (compOut > max) {
//                        max = compOut;
//                        if (prevmaxindex == -1) {
//                            prevmaxindex = i;
//                        } else {
//                            erase(bettingSystem, bettingSystem.indexOf(possibleNext.get(prevmaxindex)), visited);
//                            prevmaxindex = i;
//                        }
//
//                    }
//                    else {
//                        erase(bettingSystem, bettingSystem.indexOf(possibleNext.get(i)), visited);
//                    }
//                }
                else if(compOut==-3){
                    int output=calcNumCovered(visited);
                    if(output>max){
                        max=output;
                        if(maxindex==null){
                            maxindex=new ArrayList<Integer>();
                            for(int j=0; j<numRecs&&j<bettingSystem.size(); j++){
                                maxindex.add(erase(bettingSystem, bettingSystem.size()-1, visited));
                            }
                        }
                        else{
                            maxindex.clear();
                            for(int j=0; j<numRecs; j++){
                                maxindex.add(erase(bettingSystem, bettingSystem.size()-1, visited));
                            }
                        }
                    }
                    else {
                        for(int j=0; j<numRecs&&j<bettingSystem.size(); j++ ) {
                            erase(bettingSystem, bettingSystem.size()-1, visited);
                        }
                    }

                }
            }
            for(int i=0; i<maxindex.size(); i++){
                addNewBet(bettingSystem, maxindex.remove(maxindex.size()-1), visited);
            }
            return -3;
        }

    }
    private int recAlg(Stack<Integer> bettingSystem, boolean[] visited, int numRecs){
        int compOut;
        int max=0;
        ArrayList<Integer> maxindex=new ArrayList<Integer>();
        if(numRecs==0){
            return 0;
        }
        if(complete(visited)){
            return -1;
        }
        List<Integer> possibleNext=calcBestNext(visited);
        for(int i=0; i<possibleNext.size(); i++){
//            if(numRecs==3){
//                System.out.println("Working...");
//            }
            addNewBet(bettingSystem,possibleNext.get(i),visited);
            compOut=recAlg(bettingSystem, visited, numRecs-1);
            if(compOut==-1){
                return -1;
            }
            else if(compOut==0){
                int output=calcNumCovered(visited);
                if(output>max){
                    max=output;
                    maxindex.clear();
                    for(int j=0; j<numRecs; j++){
                        maxindex.add(erase(bettingSystem, visited));
                    }
                }
                else {
                    for(int j=0; j<numRecs; j++ ) {
                        erase(bettingSystem, visited);
                    }
                }
            }
        }
        for(int i=0; i<maxindex.size(); i++){
            addNewBet(bettingSystem, maxindex.get(i), visited);

        }
        if(numRecs==recursions) {
            System.out.print(maxindex.toString());
            return -3;
        }
        return 0;

    }
    private List<Integer> calcBestNext(boolean[] visited){
        List<Integer> possibleNext= new ArrayList<Integer>();
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
    private List<Integer> calcBestNextOrder(boolean[] visited){
        List<Integer> possibleNext= new ArrayList<Integer>();
        List<Integer> next= new ArrayList<Integer>();
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
        max=0;
        String number;
        for(int i=0; i<possibleNext.size();i++) {
            number = toString(possibleNext.get(i));
            int order = 0;
            int num0 = 0, num1 = 0, num2 = 0;
            for (int j = 0; j < numSpaces; j++) {
                if (number.charAt(j) == 0) {
                    num0++;
                } else if (number.charAt(j) == 1) {
                    num1++;
                } else {
                    num2++;
                }
            }

            order = Integer.max(num0, num1);
            order = Integer.max(order, num2);
            if(order>max){
                max=order;
            }
        }
        for( int i=0; i<possibleNext.size(); i++){
            number = toString(possibleNext.get(i));
            int order = 0;
            int num0 = 0, num1 = 0, num2 = 0;
            for (int j = 0; j < numSpaces; j++) {
                if (number.charAt(j) == 0) {
                    num0++;
                } else if (number.charAt(j) == 1) {
                    num1++;
                } else {
                    num2++;
                }
            }

            order = Integer.max(num0, num1);
            order = Integer.max(order, num2);
            if(order==max){
                next.add(possibleNext.get(i));
            }
        }
            return next;
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
    private int erase(Stack<Integer> bettingSystem,  boolean[] visited) {
        int temp = bettingSystem.pop();
        for (int i = 0; i < numOutcomes; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < bettingSystem.size(); i++) {
            for (int j = 0; j < numOutcomes; j++) {
                if (graph[bettingSystem.get(i)][j] == 1 && !visited[j]) {
                    visited[j] = true;
                }
            }
        }
        return temp;
    }
    private int erase(List<Integer> bettingSystem, int index, boolean[] visited){
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
    private void addNewBet(Stack<Integer> bettingSystem, int n, boolean[] visited){
        bettingSystem.push(n);
        for(int i=0; i<numOutcomes; i++) {
            if (graph[n][i] == 1 && !visited[i]) {
                visited[i]=true;
            }
        }
    }
    private void addNewBet(List<Integer> bettingSystem, int n, boolean[] visited){
        bettingSystem.add(n);
        for(int i=0; i<numOutcomes; i++) {
            if (graph[n][i] == 1 && !visited[i]) {
                visited[i]=true;
            }
        }
    }

    public String toString(int i){
       String num= convertFromBaseToBase(i, 10, 3);
        while(num.length()<numSpaces){
            num= "0"+num;
        }
        return num;
    }
}
