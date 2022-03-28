import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class FrequencyProcessorTest {
    @Test
    public void testFindMinimumRemovalForUniqueFrequency() throws IOException {

        JSONObject obj = new JSONObject(IOUtils.toString(this.getClass().getResourceAsStream("test-frequency.json"), "UTF-8"));
        for(String key : obj.keySet()) {
            int removalCount = new FrequencyProcessor(key).findMinimumRemovalForUniqueFrequency();

            assertTrue("Expected: "+ obj.getInt(key) + " Found: " + removalCount + " for "+ key,removalCount==obj.getInt(key));

        }

    }
}
