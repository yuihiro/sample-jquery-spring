package nagi.starter.SpringRest.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class SensorRowMapper implements RowMapper<Object> {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
		Map<String, Object> item = new HashMap<>();
		item.put("id", rs.getInt("sensor_id"));
		item.put("map_id", rs.getInt("map_id"));
		item.put("status", rs.getInt("status"));
		item.put("name", rs.getString("name"));
		item.put("ip", rs.getString("ipaddr"));
		item.put("port", rs.getInt("port"));
		item.put("fw_type", rs.getInt("fw_type"));
		item.put("fw_version", rs.getInt("fw_version"));
		item.put("mac", rs.getLong("macaddr"));
		item.put("mac_str", rs.getLong("macaddr"));
		item.put("mem_total", rs.getInt("mem_total"));
		item.put("mem_space", rs.getInt("mem_space"));
		item.put("flash_total", rs.getInt("flash_total"));
		item.put("flash_space", rs.getInt("flash_space"));
		item.put("sensor_type", rs.getInt("sensor_type"));
		item.put("model", rs.getString("model"));
		item.put("fw_version_str", rs.getString("fw_version_str"));

		item.put("status_str", getStatusStr(rs.getInt("status")));
		return item;
	}

	public String getStatusStr(int data) {
		return (data == 1) ? "ON" : "OFF";
	}
}
