package com.btamilmani.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TobacoReportRowMapper implements RowMapper<TobacoReportDto>{

	@Override
	public TobacoReportDto mapRow(ResultSet rs, int arg1) throws SQLException {
		TobacoReportDto tobacoReportDto = new TobacoReportDto();
		tobacoReportDto.setYear(rs.getString("year"));
		tobacoReportDto.setLoc_abbr(rs.getString("loc_abbr"));
		tobacoReportDto.setLoc_desc(rs.getString("Loc_desc"));
		return tobacoReportDto;
	}

}
