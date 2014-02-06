package eu.verdelhan.ta4j.indicator.helper;

import eu.verdelhan.ta4j.Tick;
import eu.verdelhan.ta4j.mocks.MockTick;
import eu.verdelhan.ta4j.mocks.MockTimeSeries;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;


public class AverageTrueRangeTest {
	@Test
	public void testGetValue() {
		List<Tick> ticks = new ArrayList<Tick>();
		ticks.add(new MockTick(0, 12, 15, 8));
		ticks.add(new MockTick(0, 8, 11, 6));
		ticks.add(new MockTick(0, 15, 17, 14));
		ticks.add(new MockTick(0, 15, 17, 14));
		ticks.add(new MockTick(0, 0, 0, 2));
		AverageTrueRange atr = new AverageTrueRange(new MockTimeSeries(ticks), 3);
		
		assertThat((double) atr.getValue(0)).isEqualTo((double) 1d);
		assertThat((double) atr.getValue(1)).isEqualTo((double) 2d/3 + 6d/3);
		assertThat((double) atr.getValue(2)).isEqualTo((double) (2d/3 + 6d/3) * 2d/3 + 9d/3);
		assertThat((double) atr.getValue(3)).isEqualTo((double) ((2d/3 + 6d/3) * 2d/3 + 9d/3) * 2d/3 + 3d/3);
		assertThat((double) atr.getValue(4)).isEqualTo((double) (((2d/3 + 6d/3) * 2d/3 + 9d/3) * 2d/3 + 3d/3) * 2d/3 + 15d/3);
		
	}
}
