package kf.dashboard.controller;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import kf.dashboard.data.DataProvider;
import kf.dashboard.model.DataRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController {

	private static Logger log = Logger.getLogger(DashboardController.class.toString());

	private final DataProvider dataProvider;

	@Autowired
	public DashboardController(DataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		log.info("dashboard page requested");
		final List<String> availableFiles = dataProvider.listFiles();
		model.addAttribute("availableFiles", availableFiles);
		return "dashboard";
	}

	@GetMapping("/table")
	public String table(
			@RequestParam(name = "fileName", required = false, defaultValue = "data.csv") String fileName,
			Model model) {
		log.info("table page requested");
		final List<DataRow> dataRows = dataProvider.readFile(fileName);
		model.addAttribute("fileName", fileName);
		model.addAttribute("dataRows", dataRows);
		return "table";
	}

	@GetMapping("/componentFlowGraph")
	public String componentFlowGraph(
			@RequestParam(name = "fileName", required = false, defaultValue = "data.csv") String fileName,
			Model model) {
		log.info("componentFlowGraph page requested");
		final List<DataRow> dataRows = dataProvider.readFile(fileName);
		model.addAttribute("fileName", fileName);
		model.addAttribute("dateValues", dataRows.stream().map(DataRow::getDate).collect(Collectors.toList()));
		model.addAttribute("componentAFlowKgH", dataRows.stream().map(DataRow::getComponentAFlowKgH).collect(Collectors.toList()));
		model.addAttribute("componentBFlowKgH", dataRows.stream().map(DataRow::getComponentBFlowKgH).collect(Collectors.toList()));
		return "componentFlowGraph";
	}

	@GetMapping("/temperatureGraph")
	public String temperatureGraph(
			@RequestParam(name = "fileName", required = false, defaultValue = "data.csv") String fileName,
			Model model) {
		log.info("temperatureGraph page requested");
		final List<DataRow> dataRows = dataProvider.readFile(fileName);
		model.addAttribute("fileName", fileName);
		model.addAttribute("dateValues", dataRows.stream().map(DataRow::getDate).collect(Collectors.toList()));
		model.addAttribute("coolantTemperatureC", dataRows.stream().map(DataRow::getCoolantTemperatureC).collect(Collectors.toList()));
		model.addAttribute("reactorOutletTemperatureC", dataRows.stream().map(DataRow::getReactorOutletTemperatureC).collect(Collectors.toList()));
		model.addAttribute("reactorHotspotTemperatureC", dataRows.stream().map(DataRow::getReactorHotspotTemperatureC).collect(Collectors.toList()));
		return "temperatureGraph";
	}

	@GetMapping("/yieldGraph")
	public String yieldGraph(
			@RequestParam(name = "fileName", required = false, defaultValue = "data.csv") String fileName,
			Model model) {
		log.info("yieldGraph page requested");
		final List<DataRow> dataRows = dataProvider.readFile(fileName);
		model.addAttribute("fileName", fileName);
		model.addAttribute("dateValues", dataRows.stream().map(DataRow::getDate).collect(Collectors.toList()));
		model.addAttribute("yieldPercent", dataRows.stream().map(DataRow::getYieldPercent).collect(Collectors.toList()));
		return "yieldGraph";
	}

	@GetMapping("/productFlowGraph")
	public String productFlowGraph(
			@RequestParam(name = "fileName", required = false, defaultValue = "data.csv") String fileName,
			Model model) {
		log.info("productFlowGraph page requested");
		final List<DataRow> dataRows = dataProvider.readFile(fileName);
		model.addAttribute("fileName", fileName);
		model.addAttribute("dateValues", dataRows.stream().map(DataRow::getDate).collect(Collectors.toList()));
		model.addAttribute("productFlowTonsPerDay", dataRows.stream().map(DataRow::getProductFlowTonsPerDay).collect(Collectors.toList()));
		return "productFlowGraph";
	}
}

