import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Vector;


public class lab0x02 {
//    public static int randMax = 1000;
//    public static int randMin = 0;

    public static int minV = 1;
    public static int maxV = 255;

    public static int maxN = 1000000000;
    public static int timingSamples = 20;
    public static long maxWaitTime = 1000000000000L;

    enum CurrentSort {
        INSERTION_SORT,
        QUICK_SORT,
        MERGE_SORT,
        RADIX_SORT_D1,
        RADIX_SORT_D2,
        RADIX_SORT_D3,
    }

    public static void main(String[] args) {
//        char[][]arr = GenerateTestList(100,3,minV,maxV);
//        IsSorted(arr);
//        InsertionSort(arr);
//        IsSorted(arr);
//        arr = GenerateTestList(100,3,minV,maxV);
//        IsSorted(arr);
//        Quicksort(arr);
//        IsSorted(arr);
//        arr = GenerateTestList(100,3,minV,maxV);
//        IsSorted(arr);
//        Mergesort(arr);
//        IsSorted(arr);
//        arr = GenerateTestList(100,3,minV,maxV);
//        IsSorted(arr);
//        RadixSort(arr,3);
//        IsSorted(arr);

        SetupTest(CurrentSort.RADIX_SORT_D3);
//        int twoByte = (int)(Math.pow(2,16) -1);
//        int k = 3;
//        int[][] intArr = GenerateIntList(10, 3, 1, twoByte);
//        PrintDigits(intArr, k);
//        RadixSort(intArr, 3, 2);
//        PrintDigits(intArr, k);
//        int threeByte = (int)(Math.pow(2,24) - 1);
//        intArr = GenerateIntList(10, k, 1, threeByte );
//        PrintDigits(intArr,k);
//        RadixSort(intArr, 3, 3);
//        PrintDigits(intArr,k);


    }

    public static void IsSorted(char[][] arr){
        int n = arr.length;
        boolean isSorted = false;
        String result = new String();
        System.out.print("Verifying... ");
        for (int i = 0; i < n - 1; i++) {
            if (CompareStrings(arr[i + 1], arr[i]) < 0)  {
                isSorted = false;
                break;
            } else
                isSorted = true;
        }
        result = isSorted ? "Sorted!" : "Not Sorted!";
        System.out.println(result);
        return;
    }

    public static void PrintStrings (char[][] arr){
        for (int i = 0; i < arr.length; i++) {
            String str = new String(arr[i]);
            System.out.print(str + " - ");
        }
        System.out.println();
    }

    public static void PrintDigits(int[][] arr, int k){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= k; j++) {
                int digit = arr[i][j];
                if (j == k)
                    System.out.print(digit + "\t");
                else
                    System.out.print(digit + ", ");
            }

        }
        System.out.println();
    }
    //compares 2 strings returns: (-) if str1 < str2, 0 if str1==str2, else (+)
    public static int CompareStrings (char[]str1, char[]str2){
        String string1 = new String(str1);
        String string2 = new String(str2);

        return string1.compareTo(string2);
    }

    public static char[][] GenerateTestList(int N, int k, int min, int max){
        char[][] arr = new char[N][k+1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < k + 1; j++) {
                if (j == k)
                    arr[i][j] = 0;
                else {
                    int c = (int) (Math.random() * (max - min)) + min;
                    arr[i][j] = (char)c;
                }
            }
        }
        return arr;
    }

    public static int[][] GenerateIntList(int N, int k, int min, int max){
        int[][] arr = new int[N][k + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < k + 1; j++) {
                if (j == k)
                    arr[i][j] = 0;
                else {
                    int rand = (int)(Math.random() * (max - min)) + min;
                    arr[i][j] = rand;
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

    public static void RadixSort(char[][] arr, int k, int d) {
        int exp = 8 * d;
        int min = 1;
        int max = (int)(Math.pow(2,exp) - 1);
        int n = arr.length;
        int range = max - min;
        char[][] temp = new char[n][k];
        int arrayPointer = 0; // if even: arr is sorted, odd: temp is sorted

        for (int digit = k-1; digit >= 0; digit--){

            //tally counts for current char
            int[] count = new int[range + 1];
            for (int i = 0; i < n; i++) {
                int currChar;
                if (arrayPointer % 2 == 0)
                    currChar = arr[i][digit];
                else
                    currChar = temp[i][digit];
                int countIndex = currChar - min;
                count[countIndex]++;
            }

            //prefix sum
            for (int i = 0; i < range; i++) {
                count[i + 1] += count[i];
            }

            //place values in temp array or back in source array
            for (int i = n - 1; i >= 0; i--) {
                int currDigit;
                int newIndex;
                if (arrayPointer % 2 == 0) {
                    currDigit = arr[i][digit];
                    newIndex = --count[currDigit - min];
                    temp[newIndex] = arr[i];
                } else{
                    currDigit = temp[i][digit];
                    newIndex = --count[currDigit - min];
                    arr[newIndex] = temp[i];
                }

            }
            arrayPointer++;
        }
        //copy back to original array if arrayPointer is odd
        if (arrayPointer % 2 == 1){
            for (int i = 0; i < n; i++) {
                arr[i] = temp[i];
            }
        }
    }

    public static void SetupTest(CurrentSort currSort){
        //initialize array and variables:
        //N loop (doubling each iteration)
        long k6TimeCurr = 0;
        long k6TimePrev = 0;
        long k12TimeCurr = 0;
        long k12TimePrev = 0;
        long k24TimeCurr = 0;
        long k24TimePrev = 0;
        long k48TimeCurr = 0;
        long k48TimePrev = 0;

            System.out.println(currSort);
            int currSortRound = 0;
            boolean continueTiming = true;

            System.out.format("%15s%15s%15s%15s%15s%15s%15s%15s%15s%15s\n","", "k=6","Doubling", "k=12", "Doubling", "k=24", "Doubling", "k=48", "Doubling", "Predicted");
            System.out.format("%15s%15s%15s%15s%15s%15s%15s%15s%15s%15s\n","N", "Time","Ratio", "Time", "Ratio", "Time", "Ratio", "Time", "Ratio", "DoublingRatio");

            for (int N = 2; N < maxN && continueTiming; N *= 2) {
                double doublingTime = 0;
                double expDoubling;
                if (currSort == CurrentSort.RADIX_SORT_D1 || currSort == CurrentSort.RADIX_SORT_D2 ||currSort == CurrentSort.RADIX_SORT_D3 )
                    expDoubling = 2;
                else if (currSort == CurrentSort.MERGE_SORT || currSort == CurrentSort.QUICK_SORT)
                    expDoubling = (N * (Math.log(N)/Math.log(2)) )/ (N/2 * (Math.log(N/2)/Math.log(2)));
                else
                    expDoubling = 4;
                System.out.format("%15d", N);
                for (int k = 6; k <= 48; k *= 2) {
                    long result = PerformTestTiming(currSort, N, k, minV, maxV);
                    if (result > maxWaitTime)
                        continueTiming = false;
                    switch (k){
                        case 6:
                            k6TimeCurr = result;
                            if (N > 2)
                                doublingTime = (double)k6TimeCurr / k6TimePrev;
                            break;
                        case 12:
                            k12TimeCurr = result;
                            if (N > 2)
                                doublingTime = (double)k12TimeCurr / k12TimePrev;
                            break;
                        case 24:
                            k24TimeCurr = result;
                            if (N > 2)
                                doublingTime = (double)k24TimeCurr / k24TimePrev;
                            break;
                        case 48:
                            k48TimeCurr = result;
                            if (N > 2)
                                doublingTime = (double)k48TimeCurr / k48TimePrev;
                            break;
                        default:
                            break;
                    }

                    System.out.format("%15d%15.5f", result, doublingTime);
                }
                System.out.format("%15.5f\n", expDoubling);
                k6TimePrev = k6TimeCurr;
                k12TimePrev = k12TimeCurr;
                k24TimePrev = k24TimeCurr;
                k48TimePrev = k48TimeCurr;
                currSortRound++;
            }

//        }
    }

    public static long PerformTestTiming(CurrentSort currentSort, int N, int k, int minV, int maxV){
        boolean continueTest = true;
//        String stringResult = new String();

        switch (currentSort){
            case INSERTION_SORT:
                if (N < 100000) {
                    int completedTests = 0;
                    long cumulativeTime = 0;
                    for (int i = 0; i < timingSamples && continueTest; i++) {
                        char[][] arr = GenerateTestList(N, k, minV, maxV);
                        long before = getCpuTime();
                        InsertionSort(arr);
                        long after = getCpuTime();
                        long total = after - before;
                        cumulativeTime += total;
                        continueTest = ContinueMultipleSamples(total);
                        completedTests++;
                    }
                    long average = cumulativeTime / completedTests;
                    return average;
                } else {
                    char[][] arr = GenerateTestList(N, k, minV, maxV);
                    long before = getCpuTime();
                    InsertionSort(arr);
                    long after = getCpuTime();
                    long total = after - before;
                    return total;
                }

            case QUICK_SORT:
                if (N < 100000) {
                    int completedTests = 0;
                    long cumulativeTime = 0;
                    for (int i = 0; i < timingSamples && continueTest; i++) {
                        char[][] arr = GenerateTestList(N, k, minV, maxV);
                        long before = getCpuTime();
                        Quicksort(arr);
                        long after = getCpuTime();
                        long total = after - before;
                        cumulativeTime += total;
                        continueTest = ContinueMultipleSamples(total);
                        completedTests++;
                    }
                    long average = cumulativeTime / completedTests;
                    return average;
                } else {
                    char[][] arr = GenerateTestList(N, k, minV, maxV);
                    long before = getCpuTime();
                    Quicksort(arr);
                    long after = getCpuTime();
                    long total = after - before;
                    return total;
                }

            case MERGE_SORT:
                if (N < 100000) {
                    int completedTests = 0;
                    long cumulativeTime = 0;
                    for (int i = 0; i < timingSamples && continueTest; i++) {
                        char[][] arr = GenerateTestList(N, k, minV, maxV);
                        long before = getCpuTime();
                        Mergesort(arr);
                        long after = getCpuTime();
                        long total = after - before;
                        cumulativeTime += total;
                        continueTest = ContinueMultipleSamples(total);
                        completedTests++;
                    }
                    long average = cumulativeTime / completedTests;
                    return average;
                } else {
                    char[][] arr = GenerateTestList(N, k, minV, maxV);
                    long before = getCpuTime();
                    Mergesort(arr);
                    long after = getCpuTime();
                    long total = after - before;
                    return total;
                }
            case RADIX_SORT_D1:
                int d = 1;
                if (N < 100000) {
                    int completedTests = 0;
                    long cumulativeTime = 0;
                    for (int i = 0; i < timingSamples && continueTest; i++) {
                        char[][] arr = GenerateTestList(N, k, minV, maxV);
                        long before = getCpuTime();
                        RadixSort(arr, k, d);
                        long after = getCpuTime();
                        long total = after - before;
                        cumulativeTime += total;
                        continueTest = ContinueMultipleSamples(total);
                        completedTests++;
                    }
                    long average = cumulativeTime / completedTests;
                    return average;
                } else {
                    char[][] arr = GenerateTestList(N, k, minV, maxV);
                    long before = getCpuTime();
                    RadixSort(arr, k, d);
                    long after = getCpuTime();
                    long total = after - before;
                    return total;
                }
            case RADIX_SORT_D2:
                int min = 1;
                d = 2;
                int exp = (int)(Math.pow(2,d));
                int max = (int)(Math.pow(2,exp) - 1);
                if (N < 100000) {
                    int completedTests = 0;
                    long cumulativeTime = 0;
                    for (int i = 0; i < timingSamples && continueTest; i++) {
                        char[][] arr = GenerateTestList(N, k, min, max);
                        long before = getCpuTime();
                        RadixSort(arr, k, d);
                        long after = getCpuTime();
                        long total = after - before;
                        cumulativeTime += total;
                        continueTest = ContinueMultipleSamples(total);
                        completedTests++;
                    }
                    long average = cumulativeTime / completedTests;
                    return average;
                } else {
                    char[][] arr = GenerateTestList(N, k, min, max);
                    long before = getCpuTime();
                    RadixSort(arr, k, d);
                    long after = getCpuTime();
                    long total = after - before;
                    return total;
                }
            case RADIX_SORT_D3:
                min = 1;
                d = 3;
                exp = (int)(Math.pow(2,d));
                max = (int)(Math.pow(2,exp) - 1);
                if (N < 100000) {
                    int completedTests = 0;
                    long cumulativeTime = 0;
                    for (int i = 0; i < timingSamples && continueTest; i++) {
                        char[][] arr = GenerateTestList(N, k, min, max);
                        long before = getCpuTime();
                        RadixSort(arr, k, d);
                        long after = getCpuTime();
                        long total = after - before;
                        cumulativeTime += total;
                        continueTest = ContinueMultipleSamples(total);
                        completedTests++;
                    }
                    long average = cumulativeTime / completedTests;
                    return average;
                } else {
                    char[][] arr = GenerateTestList(N, k, min, max);
                    long before = getCpuTime();
                    RadixSort(arr, k, d);
                    long after = getCpuTime();
                    long total = after - before;
                    return total;
                }


            default:
                return -1;

        }

    }


    public static boolean ContinueMultipleSamples(long calculatedRunTime){
        return calculatedRunTime > maxWaitTime ? false : true;
    }

    /** Get CPU time in nanoseconds since the program(thread) started. */
    /** from: http://nadeausoftware.com/articles/2008/03/java_tip_how_get_cpu_and_user_time_benchmarking#TimingasinglethreadedtaskusingCPUsystemandusertime **/
    public static long getCpuTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
        return bean.isCurrentThreadCpuTimeSupported( ) ?
                bean.getCurrentThreadCpuTime( ) : 0L;
    }


}
