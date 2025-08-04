package Dao;

import Builder.GpsLog;
import java.util.List;

public interface GpsLogDao {
	boolean saveGpsLog(GpsLog log);
	public List<GpsLog> getAllLogs();
}
