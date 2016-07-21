package nagi.starter.SpringRest.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import nagi.starter.SpringRest.repository.mapper.FirmwareRowMapper;
import nagi.starter.SpringRest.repository.mapper.SensorRowMapper;
import nagi.starter.SpringRest.repository.vo.GameVo;
import nagi.starter.SpringRest.repository.vo.ResultVo;

@Repository
public class SensorRepository {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	public List selectSensorList(int $limit) {
		String sql = "SELECT * FROM sensor_info_tbl ORDER BY name DESC";
		if ($limit != -1) {
			sql += " LIMIT " + $limit;
		}
		List result = jdbcTemplate.query(sql, new SensorRowMapper());
		return result;
	}

	public List selectFirmwareList(int $limit) {
		String sql = "SELECT * FROM sensor_firmware_info ORDER BY fw_type desc, fw_version desc, reg_time DESC";
		if ($limit != -1) {
			sql += " LIMIT " + $limit;
		}
		List result = jdbcTemplate.query(sql, new FirmwareRowMapper());
		return result;
	}

	public Map selectOneById(int id) {
		String sql = "SELECT * FROM sensor_info_tbl WHERE id = :id";
		Map result = null;

		try {
			result = (Map) jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", id), new SensorRowMapper());
		} catch (EmptyResultDataAccessException e) {
		}
		return result;
	}

	public ResultVo insertOne(GameVo vo) {
		System.out.println(vo);
		String sql = "insert games set priority = :priority, extra = :extra, comment = :comment";
		ResultVo result = new ResultVo();

		SqlParameterSource param = new BeanPropertySqlParameterSource(vo);
		KeyHolder key = new GeneratedKeyHolder();
		try {
			result.update_cnt = jdbcTemplate.update(sql, param, key);
		} catch (DuplicateKeyException e) {
			result.status = "DUPLICATE";
		}

		int idx = key.getKey().intValue();

		sql = "insert game_players set game_idx = :game_idx, team_name = :team_name, player_idx = :player_idx, team_idx = :team_idx, score = :score, player_id = :player_id, result = :result, lose_point = :lose_point";

		MapSqlParameterSource map_param = new MapSqlParameterSource();
		map_param.addValue("game_idx", idx);
		map_param.addValue("team_name", vo.player_1_team_name);
		map_param.addValue("player_idx", vo.player_1_idx);
		map_param.addValue("team_idx", vo.player_1_team);
		map_param.addValue("score", vo.player_1_score);
		map_param.addValue("player_id", vo.player_1_id);
		map_param.addValue("result", (vo.result == 1) ? 1 : (vo.result == 2) ? 2 : 3);
		map_param.addValue("lose_point", vo.player_2_score);
		result.update_cnt = jdbcTemplate.update(sql, map_param);

		map_param = new MapSqlParameterSource();
		map_param.addValue("game_idx", idx);
		map_param.addValue("team_name", vo.player_2_team_name);
		map_param.addValue("player_idx", vo.player_2_idx);
		map_param.addValue("team_idx", vo.player_2_team);

		map_param.addValue("score", vo.player_2_score);
		map_param.addValue("player_id", vo.player_2_id);
		map_param.addValue("result", (vo.result == 1) ? 2 : (vo.result == 2) ? 1 : 3);
		map_param.addValue("lose_point", vo.player_1_score);
		result.update_cnt = jdbcTemplate.update(sql, map_param);

		return result;
	}

	public ResultVo updateOne(GameVo vo) {
		ResultVo result = new ResultVo();
		String sql = "update games set idx = ?, name = ?, favorite_team = ?, comment = ? where idx = ?";
		try {
			//result.update_cnt = jdbcTemplate.update(sql, vo.idx, vo.idx);
		} catch (DuplicateKeyException e) {
			result.status = "DUPLICATE";
		}
		return result;
	}

	public ResultVo deleteOne(String idxs) {
		ResultVo result = new ResultVo();
		String sql = "delete from games where idx in (" + idxs + ")";
		//result.update_cnt = jdbcTemplate.update(sql);
		sql = "delete from game_players where game_idx in (" + idxs + ")";
		//result.update_cnt = jdbcTemplate.update(sql);

		return result;
	}
}