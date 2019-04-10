package bruteForce;

public class main {
    public static void main(String args[]){
        recursiveBetting g= new recursiveBetting();
       // bruteForce.minTreeCoverSearch g= new bruteForce.minTreeCoverSearch();
        IOwork io = new IOwork();
        io.printerSetup("newStuff.txt");
        g.generateCoverings();
        g.partition();
        g.partition(g.order3);
        g.partition(g.order4);
        g.findBettingSystem(0);
        io.writeToFile(g);
        io.printerTeardown();

//        for(int i=0; i<g.numOutcomes; i++){
//            g.findBettingSystem(i);
//            System.out.println(g.bettingSystem.size());
//            io.writeToFile(g);
//        }
//        io.printerTeardown();
    }
}
