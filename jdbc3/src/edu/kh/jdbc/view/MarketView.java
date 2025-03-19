package edu.kh.jdbc.view;

import java.util.Scanner;

public class MarketView {
	// 메인메뉴
	public void menu() {
		
		int input = 0;
		
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("\n 마켓");
			System.out.println("1. 구매자");
			System.out.println("2. 판매자");
			System.out.println("0. 종료");			
			input = sc.nextInt();
			sc.nextLine();
			
			switch(input) {
				case 1: menuBuyer(); break;
				case 2: menuSeller(); break;
			}
		} while (input != 0);
		
	}
	
	// 구매자 메뉴
	public void menuBuyer() {
		
		int input = 0;
		
		Scanner sc = new Scanner(System.in);
		
		do {
			
			System.out.println("\n 마켓");
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 구매");
			System.out.println("4. 구매내역");
			input = sc.nextInt();
			sc.nextLine();
			
			switch(input) {
			case 1: userSignIn(); break;
			case 2: userCreatAccount(); break;
			case 3: userBuy(); break;
			case 4: userBuyList(); break;
			}
		} while (input != 0);
	}

	// 판매자 메뉴
	public void menuSeller() {
		
		int input = 0;
		
		Scanner sc = new Scanner(System.in);
		
		do {
			
			System.out.println("\n 마켓");
			System.out.println("1. 상품 조회");
			System.out.println("2. 상품 등록");
			System.out.println("3. 상품 수정");
			System.out.println("4. 상품 삭제");
			input = sc.nextInt();
			sc.nextLine();
			
			switch(input) {
				case 1: productSelect(); break;
				case 2: productInsert(); break;
				case 3: productUpdate(); break;
				case 4: productDelete(); break;
			}
		} while (input != 0);
	}
	


////////////////////구매자///////////////////////////	
	
	private void userSignIn() {
		// TODO Auto-generated method stub
		
	}

	private void userCreatAccount() {
		// TODO Auto-generated method stub
		
	}

	private void userBuy() {
		// TODO Auto-generated method stub
		
	}

	private void userBuyList() {
		// TODO Auto-generated method stub
		
	}

////////////////////판매자///////////////////////////

	private void productSelect() {
		// TODO Auto-generated method stub
		
	}

	private void productInsert() {
		// TODO Auto-generated method stub
		
	}

	private void productUpdate() {
		// TODO Auto-generated method stub
		
	}

	private void productDelete() {
		// TODO Auto-generated method stub
		
	}

}
	
