import java.util.*;

public class ArrayTest {

    public static List<Map.Entry<Integer,Integer>> uniqueArray(TreeMap<Integer,Integer> treeMap){
        //这里将map.entrySet()转换成list
        List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(treeMap.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>() {
            //升序排序
            public int compare(Map.Entry<Integer,Integer> o1,
                               Map.Entry<Integer,Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }

        });

        return list;
    }

    public static void matrixFill(int N, int[][] arrays){
        int count = 1;
        int sum = 0;
        for(int line = 0; line < N; line++){


            for(int x = 0; x <= sum; x++){
                int y = sum - x;
                arrays[y][x] = count;
                System.out.println(x+","+y+" : " + count);
                count++;
            }
            sum++;
        }
    }

    public static void main(String[] args) {

//        while (true){
//            Scanner sc = new Scanner(System.in);
//            int lineQuantity = Integer.parseInt(sc.nextLine());
//            TreeMap<Integer,Integer> integerMap = new TreeMap<>();
//
//            for(int count = 0; count < lineQuantity; count++){
//                int number = Integer.parseInt(sc.nextLine());
//                integerMap.put(number,integerMap.getOrDefault(number,0) + 1);
//            }
//            List<Map.Entry<Integer,Integer>> sortedList = ArrayTest.uniqueArray(integerMap);
//
//            for(int count = 0 ; count < sortedList.size(); count++){
//                System.out.println(sortedList.get(count).getKey() + "," + sortedList.get(count).getValue());
//            }
//
//        }

        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[][] arrays = new int[N][N];
        matrixFill(N,arrays);
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if(arrays[i][j] > 0){
                    System.out.print(arrays[i][j]);
                }
                if(j != N-1){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }


}
