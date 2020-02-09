package com.nttdata.dbfw;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParamMapper {
	void mapParams(PreparedStatement pStmt) throws SQLException;
}
