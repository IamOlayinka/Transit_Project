package Dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import DTOs.BreakLog;

/**
 * Data Access Object interface for Break Log operations
 * Handles CRUD operations for operator breaks and out-of-service periods
 * 
 * @author [Your Name]
 */
public interface BreakLogDao {
    
    /**
     * Add a new break log entry
     * @param breakLog the break log to add
     * @return true if successful, false otherwise
     */
    boolean addBreakLog(BreakLog breakLog);
    
    /**
     * Get all break logs for a specific operator
     * @param operatorId the operator ID
     * @return list of break logs ordered by start time (most recent first)
     */
    List<BreakLog> getBreakLogsByOperator(int operatorId);
    
    /**
     * Get all break logs for a specific operator on a specific date
     * @param operatorId the operator ID
     * @param date the date to filter by
     * @return list of break logs for that date
     */
    List<BreakLog> getBreakLogsByOperatorAndDate(int operatorId, LocalDate date);
    
    /**
     * Get currently active break for an operator (break with no end time)
     * @param operatorId the operator ID
     * @return active break log or null if no active break
     */
    BreakLog getActiveBreakByOperator(int operatorId);
    
    /**
     * Update an existing break log
     * @param breakLog the break log to update
     * @return true if successful, false otherwise
     */
    boolean updateBreakLog(BreakLog breakLog);
    
    /**
     * End an active break by setting the end time
     * @param breakId the break ID to end
     * @param endTime the end time for the break
     * @return true if successful, false otherwise
     */
    boolean endBreak(int breakId, java.time.LocalDateTime endTime);
    
    /**
     * Delete a break log entry
     * @param breakId the break ID to delete
     * @return true if successful, false otherwise
     */
    boolean deleteBreakLog(int breakId);
    
    /**
     * Get all break logs
     * @return list of all break logs ordered by start time (most recent first)
     */
    List<BreakLog> getAllBreakLogs();
    
    /**
     * Get a specific break log by ID
     * @param breakId the break ID
     * @return the break log or null if not found
     */
    BreakLog getBreakLogById(int breakId);
    
    /**
     * Get the most recent break log for an operator
     * @param operatorId the operator ID
     * @return the most recent break log or null if none found
     */
    BreakLog getLatestBreakByOperator(int operatorId);
    
    /**
     * Count total number of break logs
     * @return total count of break logs
     */
    int countBreakLogs();
    
    /**
     * Get break logs grouped by operator
     * @return map where key is operator ID and value is list of their break logs
     */
    Map<Integer, List<BreakLog>> getBreakLogsGroupedByOperator();
    
    
    /**
     * Stop break log
     * @return boolean for if the stopping was successful or not
     */
    boolean stopBreakLog(int breakId, LocalDateTime endTime);
}