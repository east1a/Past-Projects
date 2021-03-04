//Junior Gurrola, Easton Anderson
//Ryan Parsons
//CS 240
//4/27/2018
//Three Heap - This program is an array implementation of a d-heap that has
//three children per node.

import java.util.*;
public class ThreeHeap implements PriorityQueue {
   
   private double[] heapDouble;
   private int heapSize;
 
   public ThreeHeap() {
      this.heapDouble = new double[10];
      this.heapSize = 0;
   }

   public boolean isEmpty() {//TESTED
      return this.heapSize == 0;
   }
 
   public int size() {//TESTED
      return this.heapSize;
   }
 
   public double findMin() {//TESTED
      //emptyHeapCheck();
      return heapDouble[1];
   }

   public double deleteMin() {
      emptyHeapCheck();
      double topMin = heapDouble[1];
      // Keep most recent value "in the air while being able .
      //double mostRecent = heapDouble[heapSize];
      heapDouble[1] = heapDouble[heapSize];
      heapSize--;
      percolateDown(1);
      //~int hole~// =// percolateDown(1);//, mostRecent);
      return topMin;
      
   }
   
   private void percolateDown(int hole){//, double mostRecent) {
      //sizeHeapCheck();
      
      if(this.size() == 1) {
         return;
      } else {
         int leftChild = (hole * 3) - 1;
         //int 3rdPlace = leftChild;
         
         // i = hole
         // For me it's just easier to differentiate between i = "index that will be stopped" and
         //    hole = "where we currently are"
         for(int i = hole; heapDouble[i] > heapDouble[minChild(hole)]; i = hole){
               //m = minChildBeforePercolatingDown
               int m = minChild(hole);
               double temp = heapDouble[hole];
               heapDouble[hole] = heapDouble[minChild(hole)];
               heapDouble[minChild(hole)] = temp;
               hole = m;//minChild(hole);
         }
         
      }
      
   }
   
   // Returns the minimum index of the children of a given node
   private int minChild(int hole) {
      int childLeft = (hole * 3) - 1;
      if(childLeft > heapSize){
         //If there are no children, return heapDouble[hole] + 1, since heapDouble[hole] is not more than heapDouble[hole] + 1;
         //This will stop the for loop (hopefully).
         return hole;
      }
      int childMid = (hole * 3);
      int childRight = (hole * 3) + 1;
      int minChild = childLeft;
      
      //if there is a childMid, the we take minimum of childMid and childLeft
      if(childMid <= heapSize) {
         if(heapDouble[childMid] < heapDouble[childLeft]){
            minChild = childMid;
         }
      }
      //minChild is now guarenteed to be the minimum of childLeft, and childMid
      if(childRight <= heapSize) {
         if(heapDouble[childRight] < heapDouble[minChild]) {
            minChild = childRight;
         }  
      }
      
      return minChild;
   }






   public void insert(double x){//TESTED
      sizeHeapCheck();
      // Increases heapSize by 1 and assigns said integer value to "hole"
      heapSize++;
      int hole = heapSize;
     
      //System.out.println("Hole: " + hole + ", parent: " + Math.round(hole * 1.0 / 3.0));
      //PERCOLATE UP! - Will only percolate up if x is a smaller value. Other wise, just place.
      for(heapDouble[0] = x; Double.compare(x, heapDouble[(int) Math.round((hole * 1.0 / 3.0))]) < 0;hole = (int) Math.round((hole * 1.0 / 3.0))) {
         heapDouble[hole] = heapDouble[(int) Math.round((hole * 1.0 / 3.0))];
      }
      //PLACE BUBBLE!  
      heapDouble[hole] = x;
   }
     // Checks if the heap has sufficient size for inserting an element.
   // Resizes the heap if there is not enough space.
   private void sizeHeapCheck(){//TESTED
      if(this.heapSize == heapDouble.length - 1){
                 double[] copy = Arrays.copyOf(this.heapDouble, (this.heapSize + 1) * 2);
         this.heapDouble = copy;
      }
   }
  
   private void emptyHeapCheck(){//TESTED
      if(isEmpty()){
         throw new EmptyHeapException();
      }
   }
 
   public void buildQueue(List<Double> list){
      heapSize = 0;
      for (int x = 1; x <= list.size() - 1; x++) {
         sizeHeapCheck();
         heapDouble[(int)x] = list.get(x);
         heapSize++;
      }
      for (int x = heapSize/2; x >0; x--) {
         percolateDown(x);
      }
   }
  
   public void makeEmpty(){//TESTED
      heapSize = 0;
   }
  
    public String toString(){//TESTED
      return Arrays.toString(Arrays.copyOfRange(heapDouble, 1, heapSize + 1));
   }
}