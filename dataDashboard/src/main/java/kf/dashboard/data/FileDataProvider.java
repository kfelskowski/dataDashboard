package kf.dashboard.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import kf.dashboard.model.DataRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Service;

@Service
public class FileDataProvider implements DataProvider {
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
	public static final char ELEMENT_SEPARATOR = '\t';
	private String resourcesLocation;

	private final ResourceLoader resourceLoader;

	@Autowired
	public FileDataProvider(ResourceLoader resourceLoader, String resourcesLocation) {
		this.resourceLoader = resourceLoader;
		this.resourcesLocation = resourcesLocation;
	}

	@Override
	public List<String> listFiles() {
		try {
			final Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
					.getResources("classpath:" + resourcesLocation + "*");
			return Arrays.stream(resources)
					.map(Resource::getFilename)
					.collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public List<DataRow> readFile(String resourceName) {
		final ClassPathResource classPathResource = new ClassPathResource(resourcesLocation + resourceName);
		try (CSVReader csvReader = new CSVReader(new BufferedReader(
				new InputStreamReader(classPathResource.getInputStream())), ELEMENT_SEPARATOR)) {
			// read and ignore headers
			csvReader.readNext();
			final List<String[]> data = csvReader.readAll();
			return data.stream()
					.map(this::mapToDataRow)
					.collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private DataRow mapToDataRow(String[] rawRow) {
		return DataRow.builder()
				.date(LocalDate.parse(rawRow[0], dateFormatter))
				.componentAFlowKgH(Long.parseLong(rawRow[1]))
				.componentBFlowKgH(Long.parseLong(rawRow[2]))
				.coolantTemperatureC(Long.parseLong(rawRow[3]))
				.reactorOutletTemperatureC(Long.parseLong(rawRow[4]))
				.reactorHotspotTemperatureC(Long.parseLong(rawRow[5]))
				.yieldPercent(new BigDecimal(rawRow[6]))
				.productFlowTonsPerDay(new BigDecimal(rawRow[7]))
				.build();
	}
}

