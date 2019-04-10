package HillClimbing;

import HillClimbing.Checker;

import java.util.ArrayList;
import java.util.Random;

public class hillClimbingRunnable implements Runnable{
    public int tnum;
    public hillClimbingRunnable(int tnum){
        this.tnum=tnum;
        System.out.println("Thread "+tnum+" online!");
    }
    public void run(){
        Random r = new Random();
        IOwork io = new IOwork();
        IOwork badio= new IOwork();
        Checker check = new Checker();
        io.printerSetup("ISAOut"+tnum+".txt");
        badio.printerSetup("ISABadOut"+tnum+".txt");
        int inc=0;
        while(true) {
            ArrayList<Integer> b=new ArrayList<Integer>();
            b.clear();

            for (int i = 0; i < 16; i++) {
                b.add(r.nextInt(729));
            }
            ISA isa = new ISA(b);
           // System.out.println(tnum+" "+inc);
            inc++;
            check.setBettingSystem(isa.bettingSystem);
            check.checkSystem(null);
            if(check.numUn<14&&check.numUn>0){
                System.out.println(tnum+" Less!");
                io.writeToFile(isa, check.numUn);
            }
            else if(check.numUn==0) {
                System.out.println(tnum+" Solution!!!");
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
