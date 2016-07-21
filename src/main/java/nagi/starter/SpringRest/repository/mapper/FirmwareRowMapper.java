package nagi.starter.SpringRest.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class FirmwareRowMapper implements RowMapper<Object> {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
		Map<String, Object> item = new HashMap<>();
		item.put("fw_type", rs.getInt("fw_type"));
		item.put("fw_type_str", rs.getString("fw_type_str"));
		item.put("fw_version", rs.getInt("fw_version"));
		item.put("fw_version_str", rs.getString("fw_version_str"));
		item.put("file_name", rs.getString("file_name"));
		item.put("checksum", rs.getString("checksum"));
		item.put("server_check", rs.getInt("server_check"));

		item.put("server_check_str", getServerCheckStr(rs.getInt("server_check")));
		item.put("reg_time", rs.getLong("reg_time"));
		return item;
	}

	public String getServerCheckStr(int data) {
		return (data == 0) ? "대기" : (data == 1) ? "성공" : "실패";
	}
}
