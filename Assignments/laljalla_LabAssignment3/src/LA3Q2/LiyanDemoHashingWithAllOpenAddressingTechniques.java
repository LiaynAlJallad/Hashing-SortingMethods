package LA3Q2;

import LA3Q1.*;
import static LA3Q1.LiyanDemoHashingWithLinearProbing.*;

public class LiyanDemoHashingWithAllOpenAddressingTechniques {
    public static void main(String[] args) {

        myHeader(3,2);

        System.out.print("Let's demonstrate our understanding on all the open addressing techniques...\nHow many data items: ");
        items = input.nextInt();

//        allowing the user to input the load factor and the items
        System.out.print("What is the load factor (Recommended: <= 0.5): ");
        lf=input.nextDouble();

        System.out.print("The minimum required table capacity would be: ");
        tableCapacity= checkPrime((int) (items/lf));
        System.out.print(checkPrime(tableCapacity));

//        Creating a LiyanValueEntry type array of size of the table-capacity
//        Then assigning that to hashTable field
        hashTable = new LiyanValueEntry [tableCapacity];
        for (int i = 0; i < tableCapacity; i++) {
            hashTable[i] = new LiyanValueEntry();
        }

//       Integer [] arr = new Integer[] {-11,22,-33,-44,55};

        //output 2
       Integer [] arr = new Integer[] {7,14,-21,-28,35};

        System.out.printf("\nThe given dataset is: ");
        printArray(arr);

        System.out.printf("\nLet's enter each data item from the above to the hash table:\n");

        for (int i =0; i<arr.length; i++){
            addValueLinearProbe(arr[i]);
        }

//        printing the array after using the linear probing technique
        System.out.printf("\nAdding data - linear probing resolves collision");
        System.out.printf("\nThe Current Hash-Table: ");
        printHashTable();


        System.out.printf("\n\nAdding data - quadratic probing resolves collision");
//        emptying the array so that it's back to null
        emptyHashTable();

//    printing the array after using Quadratic Hashing
        for (int i =0; i<arr.length; i++){
            addValueQuadraticHashing(arr[i]);
        }
        System.out.printf("\nThe Current Hash-Table: ");
        printHashTable();

//        printing the array after using Double Hashing
        System.out.printf("\n\nAdding data - double-hashing resolves collision");
        int q = thePrimeNumberForSecondHashFunction(tableCapacity);
        System.out.printf("\nThe The q value for double hashing is: " + q);
       System.out.print( "\nThe Current Hash-Table: " );
        emptyHashTable();
        for (int i =0; i<arr.length; i++){
            addValueDoubleHashing(arr[i]);
        }
        printHashTable();
        System.out.println();

        myFooter(3,2);

    }

    public static void addValueQuadraticHashing (Integer key){
            int j=0;
            //hash index = key+j^2% table capacity
            int hashIndex = (int) (key+Math.pow(j,2))%tableCapacity;

            if (hashIndex<0){
                hashIndex+=tableCapacity;

            }
        while (true){

//           conditional statement for the result of the hash index
            if ((hashTable[hashIndex].getKey().equals(-1)) || hashTable[hashIndex].getKey().equals(-111)) {
                hashTable[hashIndex].setKey(key);
                break;
            }
            else {
// if the conditional statement is not satisfied then increment the j and reuse the equation
                j++;
                 hashIndex =(int) (key+Math.pow(j,2))%tableCapacity;
                if (hashIndex<0){
                    hashIndex+=tableCapacity;
                }
//                if the j keeps incrementing until it gets to the size of the table and there's still no spot found for the key then it will print this
                if (j>=tableCapacity){
                    System.out.print("\nProbing failed! Use a load factor of 0.5 or less. Goodbye!");
                    break;
                }

            }

        }
    }

    public static void addValueDoubleHashing(Integer key){

        int j=0;
        int q = thePrimeNumberForSecondHashFunction(tableCapacity);

        while (true){
//            the first hash function
            int hash1 = key%tableCapacity;
            if (hash1<0){
                hash1+=tableCapacity;
            }

//            the second hash function
            int hash2 = (j)*((q)- (key%q));
            if (hash2<0){
                hash2+=tableCapacity;
            }

            int hashIndex = (hash1+hash2)%tableCapacity;
            if (hashIndex<0){
                hashIndex+=tableCapacity;
            }

//            if the hashfunctions added satisfy the following condition then the index will be set and the loop will break
            if ((hashTable[hashIndex].getKey().equals(-1)) || hashTable[hashIndex].getKey().equals(-111)) {
                hashTable[hashIndex].setKey(key);
                break;
            }

            else{
//                if not then the j will get incremented
                    ++j;
                if (j >= tableCapacity) {
                    System.out.print("\nProbing failed! Use a load factor of 0.5 or less. Goodbye!");
                    break;

                }
            }
        }
    }

    public static void printArray (Integer[] arr ) {
        System.out.print("[");
        for (int i=0; i<arr.length;i++){
            System.out.printf(arr[i] + ", ");
        }
        System.out.printf("\b\b]");
    }

    public static void emptyHashTable (){
//        Creating a LiyanValueEntry type array of size of the table-capacity
//        Then assigning that to hashTable field

        hashTable = new LiyanValueEntry [tableCapacity];


        for (int i = 0; i < tableCapacity; i++) {
            hashTable[i] = new LiyanValueEntry();
        }
    }

    public static int  thePrimeNumberForSecondHashFunction(int num){

        if (num==2){
            return 2;
        }
//        decrementing the number so that it's no longer prime
        --num;
        int m = num / 2;//we just need to check half of the n factors
        for (int i = 3; i <= m; i++) {
            if (num % i == 0) {
                i = 2;
                //System.out.printf("i = %d\n",i);
                num--;
                m = num / 2;
            }
        }
        return num;
    }

    public static void myHeader(int exerciseNum, int qNum) {
        System.out.printf("=======================================================\nLab Assignment %d, Q%d\nPrepared By: Liyan Al-Jallad\nStudent Number: 251246155\nGoal of this Exercise: Hashing With All Open Addressing Techniques\n=======================================================\n", exerciseNum,qNum);
    }

    public static void myFooter(int exerciseNum,int qNum) {
        System.out.printf("=======================================================\nCompletion of Lab Exercise %d, Q%1d is successful!\nSigning off- Liyan\n=======================================================\n", exerciseNum,qNum);
    }




}
