package Dao;

import java.util.List;

import DTOs.MaintenanceSchedule;

public interface MaintenanceScheduleDAO {
    boolean saveSchedule(MaintenanceSchedule schedule);
    List<MaintenanceSchedule> getUpcomingSchedules();
	boolean updateStatus(int id, String newStatus);
	boolean deleteSchedule(int id);
	MaintenanceSchedule getById(int id);
	int countCompleted();
	List<MaintenanceSchedule> getUpcomingWithinDays(int days);
}
