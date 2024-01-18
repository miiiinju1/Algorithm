import java.util.*;

class Solution {
    static final int MAX_DICE_FACE = 6;
    static final int MAX_DICE_SUM = 602; // 최대 주사위 면의 합

    static int[] A;
    static int[] B;
    static int[] A_SumCount;
    static int[] B_SumCount;
    static int[][] dices;
    static int diceCount;
    static boolean[] visited;
    static int maxCount = 0;
    static int[] maxPick;

    static void A_pick(int index, int size, int count, int sum) {
        if (count == size) {
            A_SumCount[sum]++;
            return;
        }
        for (int i = 0; i < MAX_DICE_FACE; i++) {
            A_pick(index + 1, size, count + 1, sum + dices[A[index]][i]);
        }
    }

    static void B_pick(int index, int size, int count, int sum) {
        if (count == size) {
            B_SumCount[sum]++;
            return;
        }
        for (int i = 0; i < MAX_DICE_FACE; i++) {
            B_pick(index + 1, size, count + 1, sum + dices[B[index]][i]);
        }
    }

    static void calculateDicePosibility(int size) {
        A_pick(0, size, 0, 0);
        B_pick(0, size, 0, 0);
    }

    static void compare(int size) {
        int count = 0;
        int[] cumulativeSum = new int[MAX_DICE_SUM * size + 1];
        int sum = 0;
        
        // 누적합 배열을 만든다
        for (int i = 0; i < cumulativeSum.length; i++) {
            sum += A_SumCount[i];
            cumulativeSum[i] = sum;
        }

        // B의 각 합에 대해 A의 누적합을 사용하여 계산
        for (int bSum = 0; bSum < B_SumCount.length; bSum++) {
            if (B_SumCount[bSum] > 0) {
                for (int aSum = bSum + 1; aSum < cumulativeSum.length; aSum++) {
                    if (A_SumCount[aSum] > 0) {
                        count += B_SumCount[bSum] * (cumulativeSum[cumulativeSum.length - 1] - cumulativeSum[aSum - 1]);
                        break;
                    }
                }
            }
        }

        // 최대 카운트와 maxPick 배열 업데이트
        if (maxCount < count) {
            maxCount = count;
            maxPick = new int[size];
            for (int i = 0; i < size; i++) {
                maxPick[i] = A[i] + 1;
            }
        }
    }

    static void combination(int index, int size) {
        if (index == size) {
            int b_i = 0;
            for (int i = 0; i < diceCount; i++) {
                if (!visited[i]) {
                    B[b_i++] = i;
                }
            }

            A_SumCount = new int[MAX_DICE_SUM * size + 1];
            B_SumCount = new int[MAX_DICE_SUM * size + 1];
            calculateDicePosibility(size);
            compare(size);

            return;
        }

        for (int i = 0; i < diceCount; i++) {
            if (!visited[i]) {
                A[index] = i;
                visited[i] = true;
                combination(index + 1, size);
                visited[i] = false;
            }
        }
    }

    public static int[] solution(int[][] d) {
        dices = d;
        diceCount = dices.length;
        maxPick = new int[diceCount / 2];

        A = new int[diceCount / 2];
        B = new int[diceCount / 2];
        visited = new boolean[diceCount];

        combination(0, diceCount / 2);
        
        return maxPick;
    }

    // 메인 함수 또는 테스트 함수는 생략됨
}

