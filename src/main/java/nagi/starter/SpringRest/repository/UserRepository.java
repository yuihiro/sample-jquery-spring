package nagi.starter.SpringRest.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Map selectOneById(String id) {
		String sql = "select * from users where id = ?";
		Map result = null;
		try {
			result = jdbcTemplate.queryForMap(sql.toString(), id);
		} catch (EmptyResultDataAccessException e) {
		}
		return result;
	}

	public List<Map<String, Object>> selectAll() {
		return jdbcTemplate.queryForList("select * from users");
	}

	/*
	public ResultVo insertOne(UserVo vo) {
		String sql = "insert users set id = ?, password = ?";
		ResultVo result = new ResultVo();
		try {
			result.update_cnt = jdbcTemplate.update(sql.toString(), vo.id, vo.password);
		} catch (DuplicateKeyException e) {
			result.status = ResultStatus.DUPLICATE.toString();
		}
		return result;
	}
	*/
}