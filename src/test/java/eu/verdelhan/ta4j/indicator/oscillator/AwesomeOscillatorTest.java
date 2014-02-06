package eu.verdelhan.ta4j.indicator.oscillator;

import eu.verdelhan.ta4j.Tick;
import eu.verdelhan.ta4j.TimeSeries;
import eu.verdelhan.ta4j.indicator.simple.AverageHighLow;
import eu.verdelhan.ta4j.mocks.MockTick;
import eu.verdelhan.ta4j.mocks.MockTimeSeries;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;

public class AwesomeOscillatorTest {
	private TimeSeries series;

	@Before
	public void setUp() throws Exception {

		List<Tick> ticks = new ArrayList<Tick>();

		ticks.add(new MockTick(0, 0, 16, 8));//12
		ticks.add(new MockTick(0, 0, 12, 6));//9
		ticks.add(new MockTick(0, 0, 18, 14));//16
		ticks.add(new MockTick(0, 0, 10, 6));//8
		ticks.add(new MockTick(0, 0, 8, 4));//6

		this.series = new MockTimeSeries(ticks);
	}

	@Test
	public void testCalculateWithSma2AndSma3() throws Exception {
		AwesomeOscillator awesome = new AwesomeOscillator(new AverageHighLow(series), 2, 3);

		assertThat((double) awesome.getValue(0)).isEqualTo(0d);
		assertThat((double) awesome.getValue(1)).isEqualTo(0d);
		assertThat(awesome.getValue(2)).isEqualTo(0.1666666d);
		assertThat(awesome.getValue(3)).isEqualTo(1d);
		assertThat((double) awesome.getValue(4)).isEqualTo(-3d);
	}

	@Test
	public void testWithSma1AndSma2() throws Exception {
		AwesomeOscillator awesome = new AwesomeOscillator(new AverageHighLow(series), 1, 2);

		assertThat((double) awesome.getValue(0)).isEqualTo(0d);
		assertThat((double) awesome.getValue(1)).isEqualTo(-1.5d);
		assertThat((double) awesome.getValue(2)).isEqualTo(3.5d);
		assertThat((double) awesome.getValue(3)).isEqualTo(-4d);
		assertThat((double) awesome.getValue(4)).isEqualTo(-1d);
	}

	@Test
	public void testWithSmaDefault() throws Exception {
		AwesomeOscillator awesome = new AwesomeOscillator(new AverageHighLow(series));

		assertThat((double) awesome.getValue(0)).isEqualTo(0d);
		assertThat((double) awesome.getValue(1)).isEqualTo(0d);
		assertThat((double) awesome.getValue(2)).isEqualTo(0d);
		assertThat((double) awesome.getValue(3)).isEqualTo(0d);
		assertThat((double) awesome.getValue(4)).isEqualTo(0d);
	}

}
