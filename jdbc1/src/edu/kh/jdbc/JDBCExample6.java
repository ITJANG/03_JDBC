package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCExample6 {

	public static void main(String[] args) {

		// 아이디, 비밀번호, 이름을 입력받아
		// 아이디, 비밀번호가 일치하는 사용자의
		// 이름을 수정(UPDATE)
		
		// 1. PreparedStatement 사용
		// 2. commit/rollnack 처리하기
		// 3. 성공 시 "수정 성공!" 출력 / 실패 "아이디 또는 비밀번호 불일치" 출력
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		Scanner sc = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String userName = "kh";
			String password = "kh1234";
			
			conn = DriverManager.getConnection(url, userName, password);
			
			sc = new Scanner(System.in);
			
			System.out.print("아이디 입력 : ");
			String id = sc.nextLine();
			
			System.out.print("비밀번호 입력 : ");
			String pw = sc.nextLine();
			
			System.out.print("이름 입력 : ");
			String name = sc.nextLine();
			
			String sql = """
					UPDATE TB_USER 
					SET USER_NAME = ? 
					WHERE USER_ID = ? AND USER_PW = ?
					""";
			
			// 4. PreparedStatement 객체 생성
			// -> 객체생성과 동시에 SQL이 담겨지게 됨
			// -> 미리 ? (위치홀더)에 값을 받을 준비를 해야되기 떄문
			
			pstmt = conn.prepareStatement(sql);
			
			// 5. ? 위치홀더 알맞은 값 대입
			// pstmt.set자료형(?순서, 대입할 값)
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			
			// + DML 수행전에 해줘야 할 것
			// AutoCommit 끄기
			// 끄는 이유 : 개발자가 트랜잭션을 마음대로 제어하기 위해서
			conn.setAutoCommit(false);
			
			// 6. SQL(INSERT) 수행 후 결과(int) 반환 받기
			// executeQuery() 	: SELECT 수행, ResultSet 반환
			// executeUpdate() 	: DML 수행, 결과 행 갯수(int) 반환
			// DML 실패 0, 성공 0 초과된 값 반환
			
			// pstmt에서 executeQuery(), executeUpdate()는 매개변수 X
			int result = pstmt.executeUpdate();
			
			// 7. result 값에 따른 결과 처리 + 트랜잭션 제어처리
			if(result > 0) { // INSERT 성공
				System.out.println(name + "님으로 수정 되었습니다");
				conn.commit(); // DB에 INSERT 영구 반영
			} else { // INSERT 실패
				System.out.println("아이디 또는 비번 불일치");
				conn.rollback(); // 실패 시 ROLLBACK
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
				if(sc != null) sc.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
