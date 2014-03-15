package app.service.cmblocationunit.impl;

import java.util.List;

import app.dao.location.ReadLocationDao;
import app.dao.unitofmeasurement.ReadUnitOfMeasurementDao;
import app.dao.unitofmeasurement.impl.ReadUnitOfMeasurementDaoImpl;
import app.location.dao.impl.ReadLocationDaoImpl;
import app.model.Item;
import app.service.cmblocationunit.CmbLocationUnitService;

public class CmbLocationUnitServiceImpl implements CmbLocationUnitService{
	public String[] addDataToCmbUnitOfMeasurement(){
		int containMe = 0;
		String[] dataForCmb = new String[countUnits()];
		ReadUnitOfMeasurementDao myMeasurementDao = new ReadUnitOfMeasurementDaoImpl();
		List<Item> unitList = myMeasurementDao.getFromDbUnitsOfMeasurement();
		for (Item myUnits : unitList) {
			dataForCmb[containMe]=myUnits.getUnitOfMeasurement();
			containMe++;
		}
		return dataForCmb;
	}
	public int countUnits(){
		int myCount =0;
		ReadUnitOfMeasurementDao myMeasurementDao = new ReadUnitOfMeasurementDaoImpl();
		List<Item> unitList = myMeasurementDao.getFromDbUnitsOfMeasurement();
		for (Item myUnits : unitList) {
			myUnits.getUnitOfMeasurement();
			myCount++;
		}
		return myCount;
	}
	public String[] addDataToCmbLocation(){
		int containNumberOfLocation = 0;
		String[] dataForCmb = new String[countLocation()];
		ReadLocationDao myLocationDao = new ReadLocationDaoImpl();
		List<Item> locationList = myLocationDao.getFromDbLocation();
		for (Item myLocation : locationList) {
			dataForCmb[containNumberOfLocation]=myLocation.getLocation();
			containNumberOfLocation++;
		}
		return dataForCmb;
	}
	public int countLocation(){
		int myCount =0;
		ReadLocationDao myLocationDao = new ReadLocationDaoImpl();
		List<Item> locationList = myLocationDao.getFromDbLocation();
		for (Item myLocation : locationList) {
			myLocation.getLocation();
			myCount++;
		}
		return myCount;
	}
}
