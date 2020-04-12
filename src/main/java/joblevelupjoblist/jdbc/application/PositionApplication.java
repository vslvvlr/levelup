package joblevelupjoblist.jdbc.application;

import joblevelupjoblist.jdbc.service.PositionJdbcService;

import java.sql.SQLException;

public class PositionApplication {

    public static void main(String[] args) throws SQLException {

        PositionJdbcService service = new PositionJdbcService();
        service.findPositionByName("QA");


    }


}
