package nagi.starter.SpringRest.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MainRepository {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	public Map loadStatus() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("ap_status", loadApStatus());
		result.put("sensor_status", loadSensorStatus());
		result.put("current_time", System.currentTimeMillis());

		return result;
	}

	public Map loadApStatus() {
		Map<String, Integer> result = new HashMap<String, Integer>();

		String sql = "select count(bssid) from (select bssid from (select * from `ap_cur_list` where active = :active and ap_mode = :mode) as woo group by bssid) as bin";
		Map param = new HashMap<>();
		param.put("active", 1);
		param.put("mode", 0);
		int manage_cnt = jdbcTemplate.queryForObject(sql, param, Integer.class);
		param.put("mode", 1);
		int not_cnt = jdbcTemplate.queryForObject(sql, param, Integer.class);
		param.put("mode", 2);
		int external_cnt = jdbcTemplate.queryForObject(sql, param, Integer.class);
		param.put("mode", 3);
		int rap_cnt = jdbcTemplate.queryForObject(sql, param, Integer.class);

		result.put("manage_cnt", manage_cnt);
		result.put("not_cnt", not_cnt);
		result.put("external_cnt", external_cnt);
		result.put("rap_cnt", rap_cnt);
		result.put("total_cnt", manage_cnt + not_cnt + external_cnt + rap_cnt);

		return result;
	}

	public Map loadSensorStatus() {
		Map<String, Integer> result = new HashMap<String, Integer>();

		String sql = "select count(*) from `sensor_info_tbl` where status = :status group by status";
		Map param = new HashMap<>();
		param.put("status", 1);
		int on_cnt = 0;
		try {
			on_cnt = jdbcTemplate.queryForObject(sql, param, Integer.class);
		} catch (EmptyResultDataAccessException except) {

		}
		param.put("status", 0);
		int off_cnt = 0;
		try {
			off_cnt = jdbcTemplate.queryForObject(sql, param, Integer.class);
		} catch (EmptyResultDataAccessException except) {

		}

		result.put("on_cnt", on_cnt);
		result.put("off_cnt", on_cnt);
		result.put("total_cnt", on_cnt + off_cnt);
		return result;
	}

}