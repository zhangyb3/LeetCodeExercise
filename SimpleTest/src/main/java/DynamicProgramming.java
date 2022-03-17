public class DynamicProgramming {





    public static int distributeApples(int appleQuantity, int pileQuantity){
        int[][] distributions = new int[10][10];
        for(int count = 0; count < 10; count ++){
            distributions[0][count] = 1;
            distributions[1][count] = 1;
            distributions[count][1] = 1;
        }


        for(int m = 0; m <= appleQuantity; m++){
            for (int n = 1; n <= pileQuantity; n++){
//                distributions[m][n] = distributions[m][n-1] + distributions[][];
            }
        }



        return distributions[appleQuantity][pileQuantity];
    }

    public static void main(String[] args) {

    }
}
