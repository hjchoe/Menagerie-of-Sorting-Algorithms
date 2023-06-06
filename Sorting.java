
/******************************************************************************
 *  Compilation:  javac Sorting.java
 *  Execution:    java Sorting input.txt AlgorithmUsed
 *  Dependencies: StdOut.java In.java Stopwatch.java
 *  Data files:   http://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *
 *  A program to play with various sorting algorithms. 
 *
 *
 *  Example run:
 *  % java Sorting 2Kints.txt  2
 *
 ******************************************************************************/
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

public class Sorting {
  static Random rand = new Random();
  static final String format = "yyyyMMdd_HHmmss";

  static int[] randomSwap(int[] d) {
    if (d.length <= 1)
      return d;
    int pos1 = rand.nextInt(d.length);
    int pos2 = rand.nextInt(d.length);
    while (pos1 == pos2) {
      pos2 = rand.nextInt(d.length);
    }
    int temp = d[pos1];
    d[pos1] = d[pos2];
    d[pos2] = temp;
    return d;
  }

  static void swap(int[] arr, int n1, int n2) {
    int temp = arr[n1];
    arr[n1] = arr[n2];
    arr[n2] = temp;
  }

  public static void defaultSort(int[] arr) {
    Arrays.sort(arr);
  }

  public static void bubbleSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++)
      for (int j = 0; j < n - i - 1; j++)
        if (arr[j] > arr[j + 1]) {
          swap(arr, j, j + 1);
        }
  }

  public static void selectionSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
      int min_idx = i;
      for (int j = i + 1; j < n; j++) {
        if (arr[j] < arr[min_idx]) {
          min_idx = j;
        }
      }
      swap(arr, min_idx, i);
    }
  }

  public static void insertionSort(int[] arr) {
    int n = arr.length;
    for (int i = 1; i < n; ++i) {
      int key = arr[i];
      int j = i - 1;
      while (j >= 0 && arr[j] > key) {
        arr[j + 1] = arr[j];
        j = j - 1;
      }
      arr[j + 1] = key;
    }
  }

  public static void mergeSort(int[] arr) {
    msort(arr, 0, arr.length - 1);
  }

  static void msort(int[] arr, int l, int r) {
    if (l < r) {
      int m = l + (r - l) / 2;
      msort(arr, l, m);
      msort(arr, m + 1, r);

      merge(arr, l, m, r);
    }
  }

  static void merge(int[] arr, int l, int m, int r) {
    int n1 = m - l + 1;
    int n2 = r - m;

    int[] L = new int[n1];
    int[] R = new int[n2];

    for (int i = 0; i < n1; ++i)
      L[i] = arr[l + i];
    for (int j = 0; j < n2; ++j)
      R[j] = arr[m + 1 + j];

    int i = 0, j = 0;

    int k = l;
    while (i < n1 && j < n2) {
      if (L[i] <= R[j]) {
        arr[k] = L[i];
        i++;
      } else {
        arr[k] = R[j];
        j++;
      }
      k++;
    }

    while (i < n1) {
      arr[k] = L[i];
      i++;
      k++;
    }

    while (j < n2) {
      arr[k] = R[j];
      j++;
      k++;
    }
  }

  public static void quickSort(int[] arr) {
    qsort(arr, 0, arr.length - 1);
  }

  static void qsort(int[] arr, int low, int high) {
    if (low >= high) return;

    int pi = (low+high)/2;
    int left = low, right = high;
    int pivot = arr[pi];

    while (left <= right)
    {
      while (arr[left] < pivot) left++;
      while (arr[right] > pivot) right--;
      if (left > right) break;

      swap(arr, left, right);
      left++;
      right--;
    }

    qsort(arr, low, right);
    qsort(arr, left, high);
  }

  /**
   * 
   * Sorts the numbers present in the file based on the algorithm provided.
   * 0 = Arrays.sort() (Java Default)
   * 1 = Bubble Sort
   * 2 = Selection Sort
   * 3 = Insertion Sort
   * 4 = Mergesort
   * 5 = Quicksort
   *
   * @param args the command-line arguments
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    In in = new In(args[0]);

    // TODO: Generate 3 other arrays, b, c, d where
    // b contains sorted integers from a (You can use Java Arrays.sort() method)
    // c contains all integers stored in reverse order
    // (you can have your own O(n) solution to get c from b
    // d contains almost sorted array
    // (You can copy b to a and then perform (0.1 * d.length) many swaps to acheive
    // this.

    // Storing file input in an array
    int[] a = in.readAllInts();
    int[] b = a.clone();
    Arrays.sort(b);
    int[] c = b.clone();
    for (int i = 0; i < c.length / 2; i++) {
      int temp = c[i];
      c[i] = c[c.length - i - 1];
      c[c.length - i - 1] = temp;
    }
    int[] d = b.clone();
    for (int i = 0; i < 0.1 * d.length; i++) {
      d = randomSwap(d);
    }

    ArrayList<int[]> arrays = new ArrayList<int[]>();
    arrays.add(a);
    arrays.add(b);
    arrays.add(c);
    arrays.add(d);

    /*
     * for (int[] arr : arrays) {
     * System.out.println(Arrays.toString(arr));
     * }
     */

    // TODO:
    // Read the second argument and based on input select the sorting algorithm
    // * 0 = Arrays.sort() (Java Default)
    // * 1 = Bubble Sort
    // * 2 = Selection Sort
    // * 3 = Insertion Sort
    // * 4 = Mergesort
    // * 5 = Quicksort
    // Perform sorting on a,b,c,d. Pring runtime for each case along with timestamp
    // and record those.
    // For runtime and priting, you can use the same code from Lab 4 as follows:

    int selectionIndex = Integer.parseInt(args[1]);
    SortType selection = SortType.DEFAULT;
    switch (selectionIndex) {
      case 0: // Arrays.sort() (Java Default)
        selection = SortType.DEFAULT;
        break;
      case 1: // Bubble Sort
        selection = SortType.BUBBLE;
        break;
      case 2: // Selection Sort
        selection = SortType.SELECTION;
        break;
      case 3: // Insertion Sort
        selection = SortType.INSERTION;
        break;
      case 4: // Mergesort
        selection = SortType.MERGE;
        break;
      case 5: // Quicksort
        selection = SortType.QUICK;
        break;
      default:
        break;
    }

    char arrayUsed = 97;
    for (int i = 0; i < arrays.size(); i++) {
      // TODO: For each array, a, b, c, d:
      Stopwatch timer = new Stopwatch();
      // TODO: Perform Sorting and store the result in an array

      double time = timer.elapsedTimeMillis();

      String timeStamp = "ERROR";

      switch (selection) {
        case DEFAULT:
          defaultSort(arrays.get(i));
          time = timer.elapsedTimeMillis();
          timeStamp = new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
          break;
        case BUBBLE:
          bubbleSort(arrays.get(i));
          time = timer.elapsedTimeMillis();
          timeStamp = new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
          break;
        case SELECTION:
          selectionSort(arrays.get(i));
          time = timer.elapsedTimeMillis();
          timeStamp = new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
          break;
        case INSERTION:
          insertionSort(arrays.get(i));
          time = timer.elapsedTimeMillis();
          timeStamp = new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
          break;
        case MERGE:
          mergeSort(arrays.get(i));
          time = timer.elapsedTimeMillis();
          timeStamp = new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
          break;
        case QUICK:
          quickSort(arrays.get(i));
          time = timer.elapsedTimeMillis();
          timeStamp = new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
          break;
      }

      String netID = "hchoe4";
      String algorithmUsed = selection.name;
      StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time, timeStamp, netID, args[0]);

      FileWriter fw = new FileWriter(arrayUsed + ".txt");
      fw.write(Arrays.toString(arrays.get(i)));
      fw.close();
      arrayUsed++;
    }
  }
}

enum SortType {
  DEFAULT("Java Default"), BUBBLE("Bubble Sort"), SELECTION("Selection Sort"), INSERTION("Insertion Sort"),
  MERGE("Mergesort"), QUICK("Quicksort");

  String name;

  SortType(String name) {
    this.name = name;
  }
}