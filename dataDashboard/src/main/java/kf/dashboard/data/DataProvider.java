package kf.dashboard.data;

import java.util.List;

import kf.dashboard.model.DataRow;

public interface DataProvider {

	List<String> listFiles();
	List<DataRow> readFile(String defaultDataLocation);
}
