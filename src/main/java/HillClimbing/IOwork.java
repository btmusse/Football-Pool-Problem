package HillClimbing;

import bruteForce.bruteForceFinder;
import bruteForce.minTreeCoverSearch;
import bruteForce.recursiveBetting;
import other.FindBetterSys;

import java.io.PrintWriter;
import java.io.*;

public class IOwork {
    public PrintWriter pw;

    public void printerSetup(String filename){
        try{
            pw= new PrintWriter( new File("/Users/benjaminmussell/IdeaProjects/MAT268work/threadOut/"+filename));
        }
        catch(Exception e){
            e.printStackTrace();
            System.exit(2);
        }
    }

    public void writeToFile(bruteForceFinder g){
        pw.print(g.bettingSystem.size()+" Betting system: {");
        for(int i=0; i<g.bettingSystem.size();i++) {
            if(i%5==0){
                pw.println();
            }
            pw.print("\""+g.convertFromBaseToBase(g.bettingSystem.get(i), 10, 3)+"\", ");

        }
        pw.println("}\n\n\n");

    }
    public void writeToFile(ISA gg, int numUn){
        pw.print(gg.bettingSystem.size()+" Betting system: {");
        for(int i=0; i<gg.bettingSystem.size();i++) {
            if(i%5==0){
                pw.println();
            }
            pw.print("\""+gg.g.convertFromBaseToBase(gg.bettingSystem.get(i), 10, 3)+"\", ");

        }
        pw.println("} "+numUn+" uncovered\n\n\n");
        pw.flush();

    }
    public void writePartitionsToFile(recursiveBetting g){
        pw.println("-------- Bets of Order 2-2-2 -------");
        pw.println(g.partitionToString(g.order2));
        pw.println("-------- Bets of Order 3-2-1 -------");
        pw.println(g.partitionToString(g.order321));
        pw.println("-------- Bets of Order 3-3-0 -------");
        pw.println(g.partitionToString(g.order33));
        pw.println("-------- Bets of Order 4-1-1 -------");
        pw.println(g.partitionToString(g.order411));
        pw.println("-------- Bets of Order 4-2-0 -------");
        pw.println(g.partitionToString(g.order42));
        pw.println("-------- Bets of Order 5-1-0 -------");
        pw.println(g.partitionToString(g.order5));
        pw.println("-------- Bets of Order 6-0-0 -------");
        pw.println(g.partitionToString(g.order6));
    }

    public void writeToFile(FindBetterSys g){
        pw.print(g.bettingSystem.size()+" Betting system: {");
        for(int i=0; i<g.bettingSystem.size();i++) {
            if(i%5==0){
                pw.println();
            }
            pw.print("\""+g.convertFromBaseToBase(g.bettingSystem.get(i), 10, 3)+"\", ");

        }
        pw.println("}\n\n\n");

    }

    public void writeToFile(minTreeCoverSearch g){
        pw.print(g.bettingSystem.size()+" Betting system: {");
        for(int i=0; i<g.bettingSystem.size();i++) {
            if(i%5==0){
                pw.println();
            }
            pw.print("\""+g.convertFromBaseToBase(g.bettingSystem.get(i), 10, 3)+"\", ");

        }
        pw.println("}\n\n\n");

    }
    public void writeToFile(recursiveBetting g){
        pw.print(g.bettingSystem.size()+" Betting system: {");
        for(int i=0; i<g.bettingSystem.size();i++) {
            if(i%5==0){
                pw.println();
            }
            pw.print("\""+g.convertFromBaseToBase(g.bettingSystem.get(i), 10, 3)+"\", ");

        }
        pw.println("}\n\n\n");

        pw.flush();
    }

    public void printerTeardown(){
        pw.close();
    }
}
