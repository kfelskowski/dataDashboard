package kf.dashboard.data;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import kf.dashboard.model.DataRow;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import static org.junit.Assert.assertEquals;

public class FileDataProviderTest {
	private ResourceLoader resourceLoader = new DefaultResourceLoader();

	private static final int EXPECTED_NUMBER_OF_ROWS = 2;
	private DataRow expectedFirstRow;
	private DataRow expectedSecondRow;

	@Test
	public void shouldReadDataFromFile() {
		// given
		final String resourceName = "testData.csv";
		final FileDataProvider fileDataProvider = new FileDataProvider(new DefaultResourceLoader(), "data/");

		// when
		final List<DataRow> dataRows = fileDataProvider.readFile(resourceName);

		// then
		assertEquals(EXPECTED_NUMBER_OF_ROWS, dataRows.size());
		assertEquals(expectedFirstRow, dataRows.get(0));
		assertEquals(expectedSecondRow, dataRows.get(1));
	}

	@Test
	public void shouldListFilesInDataDirectory() {
		// given
		final String dataDirectory = "data/";
		final FileDataProvider fileDataProvider = new FileDataProvider(new DefaultResourceLoader(), dataDirectory);

		// when
		final List<String> fileNames = fileDataProvider.listFiles();

		// then
		assertEquals(1, fileNames.size());
		assertEquals("testData.csv", fileNames.get(0));
	}

	@Before
	public void setUp() {
		expectedFirstRow = DataRow.builder()
				.date(LocalDate.of(2018, 1, 1))
				.componentAFlowKgH(1000)
				.componentBFlowKgH(50)
				.coolantTemperatureC(265)
				.reactorOutletTemperatureC(269)
				.reactorHotspotTemperatureC(339)
				.yieldPercent(new BigDecimal("92.1"))
				.productFlowTonsPerDay(new BigDecimal("59.1"))
				.build();

		expectedSecondRow = DataRow.builder()
				.date(LocalDate.of(2018, 1, 2))
				.componentAFlowKgH(1001)
				.componentBFlowKgH(51)
				.coolantTemperatureC(266)
				.reactorOutletTemperatureC(268)
				.reactorHotspotTemperatureC(349)
				.yieldPercent(new BigDecimal("92.9"))
				.productFlowTonsPerDay(new BigDecimal("67.4"))
				.build();
	}
}

