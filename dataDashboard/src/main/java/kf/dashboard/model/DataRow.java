package kf.dashboard.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class DataRow {
	private LocalDate date;
	private long componentAFlowKgH;
	private long componentBFlowKgH;
	private long coolantTemperatureC;
	private long reactorOutletTemperatureC;
	private long reactorHotspotTemperatureC;
	private BigDecimal yieldPercent;
	private BigDecimal productFlowTonsPerDay;
}

