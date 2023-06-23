package LA3Q1;

import java.util.ArrayList;
import java.util.Scanner;

public class LiyanDemoHashingWithLinearProbing {
    public static void main(String[] args) {

        myHeader(3,1);

//        making the user decided how many items they want to input
        System.out.print("Let's decide on the initial table capacity based on the load factor and dataset size\nHow many data items: ");
        items = input.nextInt();

//        calculating the load factor
        System.out.print("What is the load factor (Recommended: <= 0.5): ");
        lf=input.nextDouble();

//        Finding the table capacity from the above two values
//        Then  making sure that its to the nearest prime number by calling the checkPrime() method
        System.out.print("The minimum required table capacity would be: ");
        tableCapacity= checkPrime((int) (items/lf));
        System.out.print(checkPrime(tableCapacity));

//        Creating a LiyanValueEntry type array of size of the table-capacity
//        Then assigning that to hashTable field
        hashTable = new LiyanValueEntry [tableCapacity];

//      Instantiating the table contents with no-argument constructor
//        (Making all the table content = null)
        for (int i = 0; i < tableCapacity; i++) {
            hashTable[i] = new LiyanValueEntry();
        }

        System.out.println();
//     Entering each of the key items using the keyboard and passing that to addValueLinearProbe method
        for(int i=0; i<items; i++){
            System.out.print("Enter item " + (i+1)+": ");
            Integer key =input.nextInt();
            addValueLinearProbe(key);
        }

//         printing the hash table by calling the printHashTable() method
        System.out.print("The Current Hash-Table: " );
        printHashTable();

        System.out.print("\n\nLet’s remove two values from the table and then add one……");

//       removing two items from the hashTable
        for (int i=0; i<2; i++){
        System.out.print("\nEnter a value you want to remove: ");
        Integer removeKey = input.nextInt();
        removeValueLinearProbe(removeKey);
        printHashTable();
        }

        System.out.print("\nEnter a value to add to the table: ");
        Integer key = input.nextInt();
        addValueLinearProbe(key);
        System.out.print("The Current Hash-Table: ");
        printHashTable();

        System.out.print("\nRehashing the table...");
        reHashingWithLinearProbe();
        System.out.print("The Current Hash-Table: ");
        printHashTable();
        System.out.println();

        myFooter(3,1);


    }

//Fields
    public static int items;
    public static Scanner input = new Scanner(System.in);
    public static double lf;
    public static int tableCapacity;
    public static LiyanValueEntry [] hashTable;
    public static LiyanValueEntry [] workingHashTable;

//     will accept an integer key
//     then add this to the hash table based on linear probing technique
    public static void addValueLinearProbe(Integer key) {

//        finding the position of the key in the hash table
        int hashIndex = key %tableCapacity;

//        setting conditional statement if the hashindex value comes out -ve
        if (hashIndex<0){
            hashIndex+=tableCapacity;
        }

        while (true) {

//            condition if the spot is empty or available
            if ((hashTable[hashIndex].getKey().equals(-1)) || hashTable[hashIndex].getKey().equals(-111)) {
                hashTable[hashIndex].setKey(key);
                break;
            }
            else
            {
//                if the spot is not available, then the index will get incremented
                hashIndex++;

//                if the index is larger than the table capacity
                if (hashIndex>=tableCapacity){
                    hashIndex = 0;
                }
            }
        }
    }

    public static void reHashingWithLinearProbe() {
//        doubling the capacity of the table
        tableCapacity=checkPrime(tableCapacity*2);
//        assigning the hashtable to the working hash table
        workingHashTable = hashTable;
        System.out.println("\nThe rehashed table capacity is: " + tableCapacity);

        hashTable = new  LiyanValueEntry[tableCapacity];

//making sure that all the table is null and no available values print
        for (int i =0; i<workingHashTable.length; i++){
            if (workingHashTable[i].getKey().equals(-111)){
                workingHashTable[i].setKey(-1);
            }
        }

        for (int i =0; i<tableCapacity; i++){
            hashTable[i] = new LiyanValueEntry();
        }

//        adding the values to the working hashtable using the linear probing
        for(int i =0; i<workingHashTable.length; i++){
            addValueLinearProbe(workingHashTable[i].getKey());
        }

    }

//    prints the content of the hash table
    public static void printHashTable (){
        System.out.print("[");
        for (int i=0; i<tableCapacity; i++){
//            null if the key is eqal to -1
            if (hashTable[i].getKey().equals(-1)){
                System.out.print("null" + ", ");
            }
//            available if the key is equal to -111
            else if (hashTable[i].getKey().equals(-111)){
                System.out.print("available, ");
            }
            else
                System.out.print(hashTable[i].getKey() + ", ");
        }
        System.out.print("\b\b]");
    }

//    will accept the initial table capacity
//    then return the nearest prime number
    public static int checkPrime (int num){
        int m = num / 2;//we just need to check half of the n factors
        for (int i = 3; i <= m; i++) {
            if (num % i == 0) {
                i = 2;
                //System.out.printf("i = %d\n",i);
                num++;
                m = num / 2;
            }
        }
        return num;
    }

//    will accept an integer key
    public static void  removeValueLinearProbe(Integer key){

//      searching through the hash table using linear probing technique
        int hashIndex = key %tableCapacity;
        if (hashIndex<0){
            hashIndex+=tableCapacity;
        }
        while (true){

// if the hash index is equal to the kay value, then this is the value to be removed and be replaced with available
        if (hashTable[hashIndex].getKey().equals(key)){
            hashTable[hashIndex].setKey(-111);
           System.out.print(key + " is Found and removed!The Current Hash-Table: ");
           break;
        }
        else {
//            if the key is not found, then increment the position
            hashIndex++;
                if (hashIndex>=tableCapacity){
                    hashIndex = 0;
                }

//               if the whole table got itterated and the key is still not found then it will print not found
                else if (hashTable[hashIndex].getKey().equals(-1) || hashTable[hashIndex].getKey().equals(-111) ){
                    System.out.print(key + " not found! The Current Hash-Table: ");
                    break;
                }
        }
        }
     }

    public static void myHeader(int exerciseNum, int qNum) {
        System.out.printf("=======================================================\nLab Assignment %d, Q%d\nPrepared By: Liyan Al-Jallad\nStudent Number: 251246155\nGoal of this Exercise: Hashing With Linear Probing\n=======================================================\n", exerciseNum,qNum);
    }

    public static void myFooter(int exerciseNum,int qNum) {
        System.out.printf("=======================================================\nCompletion of Lab Exercise %d, Q%1d is successful!\nSigning off- Liyan\n=======================================================\n", exerciseNum, qNum);
    }

    }


