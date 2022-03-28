import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class FrequencyProcessor {
    private String text;
    int CHAR_LEN = 256;
    FrequencyProcessor(String text) {
        this.text = text;
    }

    public int findMinimumRemovalForUniqueFrequency() {
        int [] frequency = new int[CHAR_LEN];
        for(char ch: text.toCharArray()) {
            frequency[ch]++;
        }
        //create a max heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((ele1, ele2) -> ele2-ele1);

        //insert frequencies into the queue
        IntStream.range(0, CHAR_LEN).filter(idx-> frequency[idx] != 0).forEach(idx -> maxHeap.add(frequency[idx]));


        int removalCount = 0;

        while(!maxHeap.isEmpty()) {

            int maxFrequency = maxHeap.poll();

            if(maxHeap.isEmpty()) break;

            int secondMaxFrequency = maxHeap.peek();

            if(maxFrequency == secondMaxFrequency) {
                if(maxFrequency - 1 > 0) {
                    maxHeap.add(maxFrequency - 1);
                }
                removalCount++;
            }

        }
        return removalCount;
    }


}
