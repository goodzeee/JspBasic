package com.jsp.repository;


import com.jsp.chap05.Person;
import com.jsp.entity.Dancer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.jsp.chap02.DancerSaveProcessServlet.dancerList;

// 역할: 실제 데이터베이스에 댄서들을 CRUD
// Model
public class DancerJdbcRepo {

    private static DancerJdbcRepo repo = new DancerJdbcRepo();

    // 싱글톤 구현
    private DancerJdbcRepo() {}

    // 싱글객체를 리턴하는 메서드
    public static DancerJdbcRepo getInstance() {
        return repo;
    }

    // DB 연결에 필요한 데이터
    private String username = "root"; // db 계정명
    private String password = "maria"; // db 비번
    private String url = "jdbc:mariadb://localhost:3306/spring5"; // db url 본인 아이피 : 데베 설치 위치
    private String driverClassName = "org.mariadb.jdbc.Driver"; // db벤더별 전용 커넥터 클래스

    // 댄서를 데이터베이스에 저장하는 기능
    public boolean save(Dancer dancer) {
        try (Connection conn
                     = DriverManager.getConnection(url, username, password)) {
            Class.forName(driverClassName);

            String sql = "INSERT INTO tbl_dancer " +
                    "(name, crew_name, dance_level) " +
                    "VALUES (?, ?, ?)";

            // 4. SQL 실행 객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 5. ? 값 채우기
            pstmt.setString(1, dancer.getName());
            pstmt.setString(2, dancer.getCrewName());
            pstmt.setString(3, dancer.getDanceLevel().toString());

            // 6. 실행 명령
            // INSERT, UPDATE, DELETE 같은 명령을 사용
            pstmt.executeUpdate();
            return true;

            // 7. 데이터베이스 연결 해제

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // 댄서 리스트를 반환하는 기능
    public List<Dancer> retrieve() {

        try (Connection conn = DriverManager.getConnection(url, username, password)){

            Class.forName(driverClassName);

            String sql = "SELECT * FROM tbl_dancer ";

            // SQL 실행 객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // ? 채우기

            // 실행 명령 - SELECT 는 다른 executeQuery() 메서드 사용
            // SELECT 반환 <-> 나머지는 영향
            // ResultSet : SELECT 의 결과 집합 표를 가져옴
            ResultSet rs = pstmt.executeQuery();// 자료구조로 반환

            // ResultSet 데이터 가져오기
            List<Dancer> people = new ArrayList<>();
            while (rs.next()) {   // next() -> boolean 리턴 따라서 true 인 동안 반복 실행
                // 커서가 가리키는 행의 데이터를 하나씩 추출
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String crewName = rs.getString("crew_name");
                String danceLevel = rs.getString("dance_level");

                Dancer dancer = new Dancer();
                dancer.setName(name);
                dancer.setCrewName(crewName);
                dancer.setDanceLevel(Dancer.DanceLevel.valueOf(danceLevel));

                dancerList.add(dancer);

            }
            return dancerList;
//            rs.next();  // 표의 행을 지목하는 커서 (한 번만 사용했으니 첫 행)
//            rs.next();
//            rs.next();  // 3번째 행 조회

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    }

