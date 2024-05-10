package com.jsp.chap05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// 데이터 저장하는 SQL = MariaDB 연결 및 CRUD !
public class JdbcBasic {

    // DB 연결에 필요한 데이터
    private String username = "root"; // db 계정명
    private String password = "maria"; // db 비번
    private String url = "jdbc:mariadb://localhost:3306/spring5"; // db url 본인 아이피 : 데베 설치 위치
    private String driverClassName = "org.mariadb.jdbc.Driver"; // db 벤더별 전용 커넥터 클래스

    // INSERT 기능
    public void insert(Person p) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // 1. 연결 드라이버 로딩
            Class.forName(driverClassName);

            // 2. 데이터베이스 접속

            // 3. 실행할 SQL 생성
            String sql = "INSERT INTO tbl_person " +
                    "(id, person_name, person_age) " +
                    "VALUES (?, ?, ?)";

            // 4. SQL 실행 객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 5. ? 값 채우기
            pstmt.setInt(1, p.getId());
            pstmt.setString(2, p.getPersonName());
            pstmt.setInt(3, p.getPersonAge());

            // 6. 실행 명령
            // INSERT, UPDATE, DELETE 같은 명령을 사용
            pstmt.executeUpdate();

            // 7. 데이터베이스 연결 해제

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE 기능
    public void delete(int id) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // 1. 연결 드라이버 로딩
            Class.forName(driverClassName);

            // 2. 데이터베이스 접속

            // 3. 실행할 SQL 생성
            String sql = "DELETE FROM tbl_person " +
                    "WHERE id = ?";

            // 4. SQL 실행 객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 5. ? 값 채우기
            pstmt.setInt(1, id);  // 삭제할 조건절에 id

            // 6. 실행 명령
            // INSERT, UPDATE, DELETE 같은 명령을 사용
            pstmt.executeUpdate();

            // 7. 데이터베이스 연결 해제

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE 기능
    public void update(int id, String newName, int newAge) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // 1. 연결 드라이버 로딩
            Class.forName(driverClassName);

            // 2. 데이터베이스 접속

            // 3. 실행할 SQL 생성
            String sql = "UPDATE tbl_person " +
                    "SET person_name = ?, person_age = ? " +
                    "WHERE id = ?";

            // 4. SQL 실행 객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 5. ? 값 채우기
            pstmt.setString(1, newName);  // 삭제할 조건절에 id
            pstmt.setInt(2, newAge);  // 삭제할 조건절에 id
            pstmt.setInt(3, id);  // 삭제할 조건절에 id

            // 6. 실행 명령
            // INSERT, UPDATE, DELETE 같은 명령을 사용
            pstmt.executeUpdate();

            // 7. 데이터베이스 연결 해제

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT
    public List<Person> findAll() {

        try (Connection conn = DriverManager.getConnection(url, username, password)){

            Class.forName(driverClassName);

            String sql = "SELECT * FROM tbl_person " +
                    "ORDER BY id DESC";

            // SQL 실행 객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // ? 채우기

            // 실행 명령 - SELECT 는 다른 executeQuery() 메서드 사용
            // SELECT 반환 <-> 나머지는 영향
            // ResultSet : SELECT 의 결과 집합 표를 가져옴
            ResultSet rs = pstmt.executeQuery();// 자료구조로 반환

            // ResultSet 데이터 가져오기
            List<Person> people = new ArrayList<>();
            while (rs.next()) {   // next() -> boolean 리턴 따라서 true 인 동안 반복 실행
                // 커서가 가리키는 행의 데이터를 하나씩 추출
                int id = rs.getInt("id");
                String personName = rs.getString("person_name");
                int personAge = rs.getInt("person_age");

                Person person = new Person(id, personName, personAge);
                people.add(person);  // 사람 정보 리스트로 가져와서 추가하기

            }
            return people;
//            rs.next();  // 표의 행을 지목하는 커서 (한 번만 사용했으니 첫 행)
//            rs.next();
//            rs.next();  // 3번째 행 조회

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
