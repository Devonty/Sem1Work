package task_9_29;

import java.util.ArrayList;
import java.util.List;

public class Solve {
    public static void process(List<Integer> list1, List<Integer> list2) {
        int indexToDrown = 0;

        for (int i = 0; i < list1.size(); i++) {
            // if contains
            if (indexOf(list2, list1.get(i)) != -1) {
                drown(list1, i, indexToDrown);
                indexToDrown++;
            }
        }
    }

    public static boolean isIndexCorrect(int index, int listSize) {
        return (0 <= index) && (index < listSize);
    }

    public static void drown(List<Integer> list, int indexStart, int indexEnd) {
        if (!isIndexCorrect(indexStart, list.size()) || !isIndexCorrect(indexEnd, list.size()))
            throw new IndexOutOfBoundsException();

        for (int i = 0; i < indexStart - indexEnd; i++) {
            int index = indexStart - i;
            // Swap
            Integer tmp = list.get(index);
            list.set(index, list.get(index - 1));
            list.set(index - 1, tmp);
        }
    }

    public static int indexOf(List<Integer> list, int value) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == value) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(List.of(4, 5, 2, 1, 7, 5, 2, 9, 6));
        List<Integer> list2 = new ArrayList<>(List.of(5, 1, 8, 9, 1));

        process(list1, list2);

        // ({ 4, 5, 2, 1, 7, 5, 2, 9, 6 }, { 5, 1, 8, 9, 1 }) → ({ 5, 1, 5, 9, 4, 2, 7, 2, 6 })
        System.out.println(list1);
    }
}
