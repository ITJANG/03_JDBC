package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample4 {

	public static void main(String[] args) {
		
		// 부서명을 입력받아
		// 해당 부서에 근무하는 사원의
		// 사번, 이름, 부서명, 직급명을
		// 직급코드 오름차순으로 조회
		
		//[실행화면]
		// 부서명 입력 : 총무부
		// 200 / 선동일 / 총무부 / 대표
		// 202 / 노옹철 / 총무부 / 부사장
		// ...
		
		// 부서명 입력 : 개발팀
		// 일치하는 부서가 없습니다!
		
		// hint : SQL에서 문자열은 양쪽 ' ' 필요
		// ex) 총무부 입력 -> '총무부'
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Scanner sc = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			
			String userName = "kh";
			
			String password = "kh1234";
			
			conn = DriverManager.getConnection(url, userName, password);
			
			sc = new Scanner(System.in);
			
			System.out.print("부서명 입력 : ");
			String input = sc.nextLine();
			
			String sql = """
					SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME
					FROM EMPLOYEE e
					JOIN JOB j ON (e.JOB_CODE = j.JOB_CODE)
					LEFT JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)
					WHERE DEPT_TITLE = 
					'""" + input + "' ORDER BY e.JOB_CODE";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
//			flag 사용
//			boolean flag = true;
//			
//			while(rs.next()) {
//				flag = false;
//				String empID = rs.getString("EMP_ID");
//				String empName = rs.getString("EMP_NAME");
//				String deptTitle = rs.getString("DEPT_TITLE");
//				String jobName = rs.getString("JOB_NAME");
//				
//				System.out.printf("%s / %s / %s / %s \n", 
//						empID, empName, deptTitle, jobName);
//			}
//			if(flag)
//				System.out.println("일치하는 부서가 없습니다");
			
			// return 사용
			if(!rs.next()) {
				System.out.println("일치하는 부서가 없습니다");
				return;
			}
			do {
				String empID = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String deptTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				
				System.out.printf("%s / %s / %s / %s \n", 
						empID, empName, deptTitle, jobName);
			} while(rs.next());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (stmt != null) stmt.close();
				if (rs != null) rs.close();
				
				if (sc != null) sc.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		

	}

}
