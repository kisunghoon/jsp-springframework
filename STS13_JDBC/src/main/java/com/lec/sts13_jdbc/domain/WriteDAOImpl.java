package com.lec.sts13_jdbc.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import common.C;

@Component
public class WriteDAOImpl implements WriteDAO {

	JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		System.out.println("setTemplate() 호출");
		this.template = template;
	}
	
	public WriteDAOImpl() {
		// 서버 가동중에 빈(bean) 객체로 생성됨을 확인
		System.out.println("WriteDAOImpl() 생성");
	}
	
	@Override
	public List<WriteDTO> select() {
		return template.query(C.SQL_WRITE_SELECT, new BeanPropertyRowMapper<WriteDTO>(WriteDTO.class));
	}

	@Override
	public int insert(WriteDTO dto) {
		
		// 1.update() + PrepareStatementSetter() 사용
//		return
//		template.update(C.SQL_WRITE_INSERT, new PreparedStatementSetter() {
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setString(1, dto.getSubject());
//				ps.setString(2, dto.getContent());
//				ps.setString(3, dto.getName());			
//			}
//		});
		
		// 2. update() + PreparedStatementCreator() 사용
		
		return
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(C.SQL_WRITE_INSERT);
				ps.setString(1, dto.getSubject());
				ps.setString(2, dto.getContent());
				ps.setString(3, dto.getName());	
				return ps;
			}
		});
		
	} // end insert()

	@Override
	public List<WriteDTO> readByUid(final int uid) {
		
		// 조회수 증가
		this.template.update(C.SQL_WRITE_INC_VIEWCNT, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, uid);				
			}
		});
				
		// 특정 uid의 글 읽어오기 -> List<DTO>
		
		return
		this.template.query(C.SQL_WRITE_SELECT_BY_UID, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, uid);				
			}
		}, new BeanPropertyRowMapper<WriteDTO>(WriteDTO.class));
	}

	@Override
	public List<WriteDTO> selectByUid(int uid) {
		// 특정 uid의 글 읽어오기 -> List<DTO>		
		return
		this.template.query(C.SQL_WRITE_SELECT_BY_UID, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, uid);				
			}
		}, new BeanPropertyRowMapper<WriteDTO>(WriteDTO.class));
	}

	@Override
	public int update(WriteDTO dto) {
		
		return
		template.update(C.SQL_WRITE_UPDATE, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, dto.getSubject());
				ps.setString(2, dto.getContent());
				ps.setInt(3, dto.getUid());
			}
		});
	}

	@Override
	public int deleteByUid(int uid) {
		return
		this.template.update(C.SQL_WRITE_DELETE_BY_UID, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, uid);				
			}
		});
	}

}

















