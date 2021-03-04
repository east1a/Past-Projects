import java.util.*;

public class MyClient {

   public static void main(String[] args) {
      ArrayList al = new ArrayList();
      
      // double[] test = {1,2,3,4,5,6,7,8,9,10};
      ThreeHeap test = new ThreeHeap();
      al.add(5.0);
      al.add(6.0);
      al.add(-21.0);
      al.add(30.0);
      al.add(1.0);
      al.add(4.0);
      al.add(3.0);
      al.add(7.0);
      al.add(9.0);
      al.add(11.0);
      al.add(-30.0);
      al.add(0.0);
//       System.out.println(test.toString());
//       System.out.println(test.deleteMin());
//       System.out.println(test.toString());
//       System.out.println(test.deleteMin());
//       System.out.println(test.toString());
      test.buildQueue(al);
      System.out.print(test.toString());
   }

}

//experimental code
//       for ( ; (hole * 3)- 1 <= heapSize; hole = child) {
//          if (child != heapSize && (heapDouble[child - 1] < heapDouble[child])) {
//       	   child--;
//             if (child + 2 != heapSize && (heapDouble[child] > heapDouble[child + 2])) {
//                child += 2;
//             }   
//          }
//          else if (child + 1 != heapsize && (heapdouble[child] > heapDouble[child + 1])){
//     	      child+=2;
//          }
//     		if (heapDouble[child] < (temp)) {
//            	heapDouble[hole] = heapDouble[child];
//     	   }
//       }
//       //Move hole down
//       heapDouble[hole] = temp;
//    } 
