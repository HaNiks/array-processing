package by.han.arrays.domain.warehouse;

import by.han.arrays.domain.entity.IntArrayEntity;

public record ArrayStatistics(long sum, double avg, int max, int min) {

    public static ArrayStatistics of(IntArrayEntity entity) {
        int[] values = entity.getValues();
        long sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int v : values) {
            sum += v;
            if (v > max) max = v;
            if (v < min) min = v;
        }
        double avg = values.length == 0 ? 0.0 : (double) sum / values.length;
        return new ArrayStatistics(sum, avg, max, min);
    }
}