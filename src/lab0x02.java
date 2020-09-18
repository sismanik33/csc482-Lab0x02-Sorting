import java.lang.reflect.Array;
import java.util.Arrays;

public class lab0x02 {
//    public static int randMax = 1000;
//    public static int randMin = 0;
    public static void main(String[] args) {

        int N = 200;
        int k = 2;
        int minV = 97;
        int maxV = 122;

        char[][] arr = GenerateTestList(N, k, minV, maxV);
//        int result = CompareStrings(arr[0], arr[1]);
        PrintStrings(arr);

//        System.out.println("String 1: " + new String(arr[0]) + " String 2: " + new String(arr[1]) + " compare function returns: " + result);
//        Quicksort(arr);
//        Mergesort(arr);
        InsertionSort(arr);
        PrintStrings(arr);
    }
    public static void PrintStrings (char[][] arr){
        for (int i = 0; i < arr.length; i++) {
            String str = new String(arr[i]);
            System.out.print(str + " ");
        }
        System.out.println();
    }
    public static int CompareStrings (char[]str1, char[]str2){
        String string1 = new String(str1);
        String string2 = new String(str2);

        return string1.compareTo(string2);
    }

    public static char[][] GenerateTestList(int N, int k, int minV, int maxV){
        char[][] arr = new char[N][k+1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < k + 1; j++) {
                if (j == k)
                    arr[i][j] = 0;
                else {
                    int ascii = (int) (Math.random() * (maxV - minV)) + minV;
                    arr[i][j] = (char)ascii;
                }
            }
        }
        return arr;
    }

    public static void Quicksort(char[][] array) {
        Quicksort(array, 0, array.length - 1);
    }

    public static void Quicksort(char[][] array, int left, int right){
        if (left >= right)
            return;
        int mid = left + ((right - left) / 2);
        char[] pivot = array[mid];

        int pivIndex = Partition(array, left, right, pivot);
        Quicksort(array, left, pivIndex - 1);
        Quicksort(array, pivIndex, right);

    }

    public static int Partition(char[][] array, int left, int right, char[] pivot){
        while (left <= right){

            while (CompareStrings(array[left], pivot) < 0){
                left++;
            }

            while (CompareStrings(array[right], pivot) > 0){
                right--;
            }

            if (left <= right) {
                char[] temp = null;
                temp = array[right];
                array[right]= array[left];
                array[left]= temp;

                left++;
                right--;
            }

        }
        return left;
    }

    public static void Mergesort(char[][] array){
        Mergesort(array, new char[array.length][], 0, array.length - 1);
    }

    public static void Mergesort(char[][] array, char[][] temp, int left, int right){
        if (left >= right)
            return;

        int mid = left + ((right - left) / 2);
        Mergesort(array, temp, left, mid);
        Mergesort(array, temp, mid + 1, right);
        merge(array, temp, left, right);
    }

    public static void merge (char[][] array, char[][] temp, int leftStart, int rightEnd){
        int leftEnd = leftStart + ((rightEnd - leftStart) / 2);
        int rightStart = leftEnd + 1;
        int size = rightEnd - leftStart + 1;

        int leftWalker = leftStart;
        int rightWalker = rightStart;
        int index = leftStart;

        while (leftWalker <= leftEnd && rightWalker <= rightEnd){
            if (CompareStrings( array[leftWalker], array[rightWalker] ) <= 0){
                temp[index] = array[leftWalker];
                leftWalker++;
            } else {
                temp[index] = array[rightWalker];
                rightWalker++;
            }
            index++;
        }

        System.arraycopy(array, leftWalker, temp, index, leftEnd - leftWalker + 1);
        System.arraycopy(array, rightWalker, temp, index, rightEnd - rightWalker + 1);
        System.arraycopy(temp, leftStart, array, leftStart, size);
    }

    public static void InsertionSort(char[][] array){
        int i = 1;
        while (i < array.length){
            char[] x = array[i];
            int j = i - 1;
            while (j >= 0 && CompareStrings(array[j],x) > 0){
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = x;
            i++;
        }
    }

    //*** Radix Sort Functions:  ************//
//    public static void CountSort(char[][] arr, int currChar){
//        char[][] output = new char [arr.length][];
//        int i;
//        int[] count = new int[arr.length];
//        Arrays.fill(count, 0);
//
//    }
}
