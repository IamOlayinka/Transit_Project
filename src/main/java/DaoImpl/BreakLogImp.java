package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DTOs.BreakLog;
import Dao.BreakLogDao;
import dataaccessLayer.Datasource;

public class BreakLogImp implements BreakLogDao {

    @Override
    public boolean addBreakLog(BreakLog breakLog) {
        String sql = "INSERT INTO breaks (operator_id, vehicle_id, break_start_time, break_end_time, duration, break_type, notes) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, breakLog.getOperatorId());

            if (breakLog.getVehicleId() != null) {
                ps.setInt(2, breakLog.getVehicleId());
            } else {
                ps.setNull(2, Types.INTEGER);
            }

            ps.setTimestamp(3, Timestamp.valueOf(breakLog.getBreakStartTime()));

            if (breakLog.getBreakEndTime() != null) {
                ps.setTimestamp(4, Timestamp.valueOf(breakLog.getBreakEndTime()));
            } else {
                ps.setNull(4, Types.TIMESTAMP);
            }

            ps.setInt(5, breakLog.getDuration());

            ps.setString(6, breakLog.getBreakType());
            ps.setString(7, breakLog.getNotes());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        breakLog.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<BreakLog> getBreakLogsByOperator(int operatorId) {
        List<BreakLog> breakLogs = new ArrayList<>();
        String sql = "SELECT * FROM breaks WHERE operator_id = ? ORDER BY break_start_time DESC";

        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, operatorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BreakLog breakLog = new BreakLog();

                breakLog.setId(rs.getInt("id"));
                breakLog.setOperatorId(rs.getInt("operator_id"));

                int vehicleId = rs.getInt("vehicle_id");
                if (!rs.wasNull()) {
                    breakLog.setVehicleId(vehicleId);
                }

                Timestamp startTs = rs.getTimestamp("break_start_time");
                if (startTs != null) {
                    breakLog.setBreakStartTime(startTs.toLocalDateTime());
                }

                Timestamp endTs = rs.getTimestamp("break_end_time");
                if (endTs != null) {
                    breakLog.setBreakEndTime(endTs.toLocalDateTime());
                }

                breakLog.setBreakType(rs.getString("break_type"));
                breakLog.setNotes(rs.getString("notes"));

                Timestamp createdAt = rs.getTimestamp("created_at");
                if (createdAt != null) {
                    breakLog.setCreatedAt(createdAt.toLocalDateTime());
                }

                breakLog.setDuration(rs.getInt("duration"));

                breakLogs.add(breakLog);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return breakLogs;
    }

    @Override
    public List<BreakLog> getBreakLogsByOperatorAndDate(int operatorId, LocalDate date) {
        List<BreakLog> breakLogs = new ArrayList<>();
        String sql = "SELECT * FROM breaks WHERE operator_id = ? AND DATE(break_start_time) = ? ORDER BY break_start_time DESC";

        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, operatorId);
            ps.setDate(2, java.sql.Date.valueOf(date));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BreakLog breakLog = new BreakLog();

                breakLog.setId(rs.getInt("id"));
                breakLog.setOperatorId(rs.getInt("operator_id"));

                int vehicleId = rs.getInt("vehicle_id");
                if (!rs.wasNull()) {
                    breakLog.setVehicleId(vehicleId);
                }

                Timestamp startTs = rs.getTimestamp("break_start_time");
                if (startTs != null) {
                    breakLog.setBreakStartTime(startTs.toLocalDateTime());
                }

                Timestamp endTs = rs.getTimestamp("break_end_time");
                if (endTs != null) {
                    breakLog.setBreakEndTime(endTs.toLocalDateTime());
                }

                breakLog.setBreakType(rs.getString("break_type"));
                breakLog.setNotes(rs.getString("notes"));

                Timestamp createdAt = rs.getTimestamp("created_at");
                if (createdAt != null) {
                    breakLog.setCreatedAt(createdAt.toLocalDateTime());
                }

                breakLog.setDuration(rs.getInt("duration"));

                breakLogs.add(breakLog);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return breakLogs;
    }

    @Override
    public BreakLog getActiveBreakByOperator(int operatorId) {
        String sql = "SELECT * FROM breaks WHERE operator_id = ? AND break_end_time IS NULL ORDER BY break_start_time DESC LIMIT 1";

        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, operatorId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                BreakLog breakLog = new BreakLog();

                breakLog.setId(rs.getInt("id"));
                breakLog.setOperatorId(rs.getInt("operator_id"));

                int vehicleId = rs.getInt("vehicle_id");
                if (!rs.wasNull()) {
                    breakLog.setVehicleId(vehicleId);
                }

                Timestamp startTs = rs.getTimestamp("break_start_time");
                if (startTs != null) {
                    breakLog.setBreakStartTime(startTs.toLocalDateTime());
                }

                // break_end_time is null here, so no need to get it

                breakLog.setBreakType(rs.getString("break_type"));
                breakLog.setNotes(rs.getString("notes"));

                Timestamp createdAt = rs.getTimestamp("created_at");
                if (createdAt != null) {
                    breakLog.setCreatedAt(createdAt.toLocalDateTime());
                }

                breakLog.setDuration(rs.getInt("duration"));

                return breakLog;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateBreakLog(BreakLog breakLog) {
        String sql = "UPDATE breaks SET operator_id = ?, vehicle_id = ?, break_start_time = ?, break_end_time = ?, break_type = ?, notes = ?, duration = ? WHERE id = ?";

        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, breakLog.getOperatorId());

            if (breakLog.getVehicleId() != null) {
                ps.setInt(2, breakLog.getVehicleId());
            } else {
                ps.setNull(2, Types.INTEGER);
            }

            ps.setTimestamp(3, Timestamp.valueOf(breakLog.getBreakStartTime()));

            if (breakLog.getBreakEndTime() != null) {
                ps.setTimestamp(4, Timestamp.valueOf(breakLog.getBreakEndTime()));
            } else {
                ps.setNull(4, Types.TIMESTAMP);
            }

            ps.setString(5, breakLog.getBreakType());
            ps.setString(6, breakLog.getNotes());
            ps.setInt(7, breakLog.getDuration());
            ps.setInt(8, breakLog.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean endBreak(int breakId, LocalDateTime endTime) {
        String sqlGetStart = "SELECT break_start_time FROM breaks WHERE id = ?";
        String sqlUpdate = "UPDATE breaks SET break_end_time = ?, duration = ? WHERE id = ? AND break_end_time IS NULL";

        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement psGet = conn.prepareStatement(sqlGetStart);
             PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {

            psGet.setInt(1, breakId);
            ResultSet rs = psGet.executeQuery();

            if (!rs.next()) {
                return false;
            }

            Timestamp startTs = rs.getTimestamp("break_start_time");
            if (startTs == null) {
                return false;
            }

            long durationMinutes = java.time.Duration.between(startTs.toLocalDateTime(), endTime).toMinutes();
            if (durationMinutes > 30) {
                durationMinutes = 30;
            } else if (durationMinutes < 0) {
                durationMinutes = 0;
            }

            psUpdate.setTimestamp(1, Timestamp.valueOf(endTime));
            psUpdate.setInt(2, (int) durationMinutes);
            psUpdate.setInt(3, breakId);

            return psUpdate.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBreakLog(int breakId) {
        String sql = "DELETE FROM breaks WHERE id = ?";

        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, breakId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<BreakLog> getAllBreakLogs() {
        List<BreakLog> breakLogs = new ArrayList<>();
        String sql = "SELECT * FROM breaks ORDER BY break_start_time ASC";

        try (Connection conn = Datasource.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                BreakLog breakLog = new BreakLog();

                breakLog.setId(rs.getInt("id"));
                breakLog.setOperatorId(rs.getInt("operator_id"));

                int vehicleId = rs.getInt("vehicle_id");
                if (!rs.wasNull()) {
                    breakLog.setVehicleId(vehicleId);
                }

                Timestamp startTs = rs.getTimestamp("break_start_time");
                if (startTs != null) {
                    breakLog.setBreakStartTime(startTs.toLocalDateTime());
                }

                Timestamp endTs = rs.getTimestamp("break_end_time");
                if (endTs != null) {
                    breakLog.setBreakEndTime(endTs.toLocalDateTime());
                }

                breakLog.setBreakType(rs.getString("break_type"));
                breakLog.setNotes(rs.getString("notes"));

                Timestamp createdAt = rs.getTimestamp("created_at");
                if (createdAt != null) {
                    breakLog.setCreatedAt(createdAt.toLocalDateTime());
                }

                breakLog.setDuration(rs.getInt("duration"));

                breakLogs.add(breakLog);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Total Break Logs Found: " + breakLogs.size());
        return breakLogs;
    }

    @Override
    public BreakLog getBreakLogById(int breakId) {
        String sql = "SELECT * FROM breaks WHERE id = ?";

        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, breakId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                BreakLog breakLog = new BreakLog();

                breakLog.setId(rs.getInt("id"));
                breakLog.setOperatorId(rs.getInt("operator_id"));

                int vehicleId = rs.getInt("vehicle_id");
                if (!rs.wasNull()) {
                    breakLog.setVehicleId(vehicleId);
                }

                Timestamp startTs = rs.getTimestamp("break_start_time");
                if (startTs != null) {
                    breakLog.setBreakStartTime(startTs.toLocalDateTime());
                }

                Timestamp endTs = rs.getTimestamp("break_end_time");
                if (endTs != null) {
                    breakLog.setBreakEndTime(endTs.toLocalDateTime());
                }

                breakLog.setBreakType(rs.getString("break_type"));
                breakLog.setNotes(rs.getString("notes"));

                Timestamp createdAt = rs.getTimestamp("created_at");
                if (createdAt != null) {
                    breakLog.setCreatedAt(createdAt.toLocalDateTime());
                }

                breakLog.setDuration(rs.getInt("duration"));

                return breakLog;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public BreakLog getLatestBreakByOperator(int operatorId) {
        String sql = "SELECT * FROM breaks WHERE operator_id = ? ORDER BY break_start_time DESC LIMIT 1";

        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, operatorId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                BreakLog breakLog = new BreakLog();

                breakLog.setId(rs.getInt("id"));
                breakLog.setOperatorId(rs.getInt("operator_id"));

                int vehicleId = rs.getInt("vehicle_id");
                if (!rs.wasNull()) {
                    breakLog.setVehicleId(vehicleId);
                }

                Timestamp startTs = rs.getTimestamp("break_start_time");
                if (startTs != null) {
                    breakLog.setBreakStartTime(startTs.toLocalDateTime());
                }

                Timestamp endTs = rs.getTimestamp("break_end_time");
                if (endTs != null) {
                    breakLog.setBreakEndTime(endTs.toLocalDateTime());
                }

                breakLog.setBreakType(rs.getString("break_type"));
                breakLog.setNotes(rs.getString("notes"));

                Timestamp createdAt = rs.getTimestamp("created_at");
                if (createdAt != null) {
                    breakLog.setCreatedAt(createdAt.toLocalDateTime());
                }

                breakLog.setDuration(rs.getInt("duration"));

                return breakLog;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int countBreakLogs() {
        String sql = "SELECT COUNT(*) FROM breaks";

        try (Connection conn = Datasource.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Map<Integer, List<BreakLog>> getBreakLogsGroupedByOperator() {
        Map<Integer, List<BreakLog>> groupedLogs = new HashMap<>();
        String sql = "SELECT * FROM breaks ORDER BY operator_id, break_start_time DESC";

        try (Connection conn = Datasource.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                BreakLog breakLog = new BreakLog();

                breakLog.setId(rs.getInt("id"));
                breakLog.setOperatorId(rs.getInt("operator_id"));

                int vehicleId = rs.getInt("vehicle_id");
                if (!rs.wasNull()) {
                    breakLog.setVehicleId(vehicleId);
                }

                Timestamp startTs = rs.getTimestamp("break_start_time");
                if (startTs != null) {
                    breakLog.setBreakStartTime(startTs.toLocalDateTime());
                }

                Timestamp endTs = rs.getTimestamp("break_end_time");
                if (endTs != null) {
                    breakLog.setBreakEndTime(endTs.toLocalDateTime());
                }

                breakLog.setBreakType(rs.getString("break_type"));
                breakLog.setNotes(rs.getString("notes"));

                Timestamp createdAt = rs.getTimestamp("created_at");
                if (createdAt != null) {
                    breakLog.setCreatedAt(createdAt.toLocalDateTime());
                }

                breakLog.setDuration(rs.getInt("duration"));

                int operatorId = breakLog.getOperatorId();
                groupedLogs.computeIfAbsent(operatorId, k -> new ArrayList<>()).add(breakLog);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupedLogs;
    }
    
    
    @Override
    public boolean stopBreakLog(int breakId, LocalDateTime endTime) {
        String sqlGetStart = "SELECT break_start_time FROM breaks WHERE id = ?";
        String sqlUpdate = "UPDATE breaks SET break_end_time = ?, duration = ? WHERE id = ? AND break_end_time IS NULL";

        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement psGet = conn.prepareStatement(sqlGetStart);
             PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {

            psGet.setInt(1, breakId);
            ResultSet rs = psGet.executeQuery();

            if (!rs.next()) {
                return false;
            }

            Timestamp startTs = rs.getTimestamp("break_start_time");
            if (startTs == null) {
                return false;
            }

            long durationMinutes = java.time.Duration.between(startTs.toLocalDateTime(), endTime).toMinutes();
            if (durationMinutes < 0) {
                durationMinutes = 0;
            }

            psUpdate.setTimestamp(1, Timestamp.valueOf(endTime));
            psUpdate.setInt(2, (int) durationMinutes);
            psUpdate.setInt(3, breakId);

            return psUpdate.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
