package other;

public class CheckerII {
    public int n=3;
    public int R=1;
    public int q=3;
    public String[] bettingSystem;
    public String[] outcomes;
    public String[] triples;
    public String[] possibilities;
    public String[] uncovered;
    public String[] unused;
    public String[] workingSystems;
    public Boolean[] checkingArray;
    public int numOut;
    public static void main(String args[]){
        CheckerII check= new CheckerII();
        check.generateOutcomes();
        // System.out.println(check.numOut);
        check.testAllBets();

    }
    public void testAllBets() {
        bettingSystem = new String[5];
        workingSystems = new String[60];
        for(int z=0; z<workingSystems.length;z++) workingSystems[z]="";
        int c = 0;
        workingSystems[0]="";
        for(int i = 0; i<23;i++) {
            bettingSystem[0]=outcomes[i];
            for(int j = i+1;j<24;j++) {
                bettingSystem[1]=outcomes[j];
                for(int k= j+1;k<25;k++) {
                    bettingSystem[2]=outcomes[k];
                    for (int l= k+1;l<26;l++) {
                        bettingSystem[3]=outcomes[l];
                        for (int m=l+1;m<27;m++) {
                            bettingSystem[4]=outcomes[m];
                            if (simplifiedCheck()) {
                                for(int n=0;n<5;n++) {
                                    workingSystems[c] += ""+ bettingSystem[n]+" ";
                                }
                                c++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(c);
        for(int o = c;o>=0;o--) {
            System.out.println(workingSystems[o] + "\n");
        }


    }
    public void generateSystem(){

        bettingSystem=new String[8];

        bettingSystem[0]="11100";
        bettingSystem[1]="22211";
        bettingSystem[2]="00022";
        bettingSystem[3]="11222"; //22211
        bettingSystem[4]="22000"; //22200
        bettingSystem[5]="00111"; //11122
        bettingSystem[6]="21012"; //11100
        bettingSystem[7]="10201"; //00011
//        bettingSystem[8]="33333"; //00022
//        bettingSystem[9]="02020"; //22110
//        bettingSystem[10]="20202"; //22001
//        bettingSystem[11]="12121"; //11002
//        bettingSystem[12]="21021";
//        bettingSystem[13]="10210";
//        bettingSystem[14]="01201";
//        bettingSystem[15]="02102";
//        bettingSystem[16]="12012";
    }
    public void generateOutcomes(){
        numOut=0;
        outcomes=new String[27];
        for(int i=0; i<q; i++){
            for(int j=0; j<q; j++){
                for(int k=0; k<q; k++){
                    outcomes[numOut] = "" + i + j + k;
                    numOut++;
                   /* for(int h=0; h<q; h++){
                        for(int l=0; l<q; l++) {
                            outcomes[numOut] = "" + i + j + k + h+l;
                            numOut++;
                        }
                    }*/
                }
            }
        }
    }
    public void uncoveredList() {

    }
    //generates all triples that exist in the set of outcomes
    public void generateTriples() {
        int counter =0;
        possibilities = new String[270];
        for(int i=0; i<q; i++){
            for(int j=0; j<q; j++){
                for(int k=0; k<q; k++){
                    possibilities[counter*10] = "xx" + i + j + k ;
                    possibilities[counter*10+1] = "x" + i + "x" + j + k ;
                    possibilities[counter*10+2] = "x" + i + j + "x" + k ;
                    possibilities[counter*10+3] = "x" + i + j + k + "x";
                    possibilities[counter*10+4] = ""+ i + "xx"+ j + k ;
                    possibilities[counter*10+5] = ""+i + "x"+ j + "x" + k  ;
                    possibilities[counter*10+6] = ""+i + "x" + j + k +"x";
                    possibilities[counter*10+7] = ""+i + j +"xx"+ k ;
                    possibilities[counter*10+8] = ""+i + j + "x" + k +"x";
                    possibilities[counter*10+9] = ""+i + j + k + "xx";
                    counter++;
                }
            }
        }
    }
    public void listAllTriples() {
        triples = new String[80];
        String[] doubled = new String[80];
        for(int i = 0; i< 8; i ++) {
            triples[i*10]=bettingSystem[i].substring(0,3)+"xx";
            triples[i*10+1]="x" + bettingSystem[i].substring(1,4)+"x";
            triples[i*10+2]="xx" + bettingSystem[i].substring(2,5);
            triples[i*10+3]=bettingSystem[i].substring(0,2)+"x"+ bettingSystem[i].substring(3,4)+"x";
            triples[i*10+4]=bettingSystem[i].substring(0,1)+"x"+ bettingSystem[i].substring(2,4)+"x";
            triples[i*10+5]=bettingSystem[i].substring(0,2)+"xx"+bettingSystem[i].substring(4,5);
            triples[i*10+6]=bettingSystem[i].substring(0,1)+"xx"+bettingSystem[i].substring(3,5);
            triples[i*10+7]=bettingSystem[i].substring(0,1)+"x"+bettingSystem[i].substring(2,3)+"x"+bettingSystem[i].substring(4,5);
            triples[i*10+8]="x"+bettingSystem[i].substring(1,2)+"x"+bettingSystem[i].substring(3,5);
            triples[i*10+9]="x"+bettingSystem[i].substring(1,3)+"x"+bettingSystem[i].substring(4,5);
        }
        int d=0;
        for(int j = 0;j<79;j++) {
            for(int k =j+1;k<80;k++) {
                if (triples[j].equals(triples[k])){
                    doubled[d] = triples[j];
                    d++;
                }
            }
        }
        System.out.println("Doubled triples");
        for(int l = 0;l<20;l++) {
            System.out.println(doubled[l]);
        }
        System.out.println("list of used and unused triples");
        boolean checked = false;
        int un =0;
        unused = new String[80];
        for(int m=0; m<270; m++) {
            for(int n = 0; n<80;n++) {
                if(possibilities[m].equals(triples[n])) {
                    checked = true;
                    unused[un]=possibilities[m];
                    un++;
                    //System.out.println(possibilities[m] +" used");
                }
            }
            if(checked == false) {
                System.out.println(possibilities[m]+ " UNUSED");
            }
            checked=false;
        }

    }
    public void checkSystem(){
        if(bettingSystem.length<q){
            return;
        }
        uncovered= new String[800];
        int un = 0;
        for(int i=0; i<bettingSystem.length; i++){
            for(int j=0; j<numOut;j++){
                int errors=0;
                for(int k=0; k<n; k++){
                    if(bettingSystem[i].charAt(k)!=outcomes[j].charAt(k)){
                        errors++;
                    }
                }
                if(errors<=R){
                    outcomes[j]+=" Covered";
                }
            }
        }
       /* for(int i=0; i<numOut; i++){
            System.out.println(outcomes[i]);
        }*/
        System.out.println("Uncovered bets");
        for(int j=0; j< numOut;j++) {
            if(outcomes[j].length()<6) {
                System.out.println(outcomes[j]);
                uncovered[un]=outcomes[j];
                un++;
            }
        }

    }
    public boolean simplifiedCheck() {
        if(bettingSystem.length<q){
            return false;
        }
        checkingArray = new Boolean[27];
        for(int n = 0;n<27;n++) {
            checkingArray[n] = false;
        }
        int counter = 0;
        for (int j=0;j<27;j++) {

            for(int k = 0; k<bettingSystem.length;k++) {
                counter = 0;
                for(int m = 0; m<3; m++) {
                    if(bettingSystem[k].charAt(m) == (outcomes[j].charAt(m))) {
                        counter+=1;
                    }
                }
                if(counter>1) {
                    checkingArray[j]=true;
                }
            }
        }
        for(int i=0; i<27; i++){
            if(checkingArray[i]!= true) return false;
        }

        return true;
    }
}