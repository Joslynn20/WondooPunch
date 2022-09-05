package mvc.view;


import java.sql.SQLException;
import java.util.Scanner;

import mvc.controller.CouponController;
import mvc.controller.CustomerController;
import mvc.controller.CustomerControllerImpl;
import mvc.controller.OptionController;
import mvc.controller.OrderController;
import mvc.controller.OrderControllerImpl;
import mvc.controller.ProductController;
import mvc.controller.SalesController;
import mvc.dao.CartDAOImpl;
import mvc.dto.Coupon;
import mvc.dto.Customer;
import mvc.dto.Option;
import mvc.dto.Orders;
import mvc.dto.Product;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;
import mvc.session.Session;
import mvc.session.SessionSet;


public class MenuView {
	private static Scanner sc = new Scanner(System.in);
	private static CustomerController customerController = new CustomerControllerImpl();
	private static ProductController productController = new ProductController();
	private static OrderController orderController = new OrderControllerImpl();
	private static CartDAOImpl cartDAO = new CartDAOImpl();
	private static CouponController couponController = new CouponController();
	private static OptionController optionController = new OptionController();
	
	public static void printMenu() {
		System.out.println("==== WondooPunch ====");
		System.out.println(" 1. 로그인    |  2. 회원가입    |  9. 종료");
	}
	
	
	public static void menu() throws SQLException, NotFoundException {
		while(true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet()); //[]
			
			MenuView.printMenu();
			System.out.print("입력 > ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				MenuView.printLoginType(); //로그인 타입 선택
				
				System.out.print("입력 > ");
				int type = Integer.parseInt(sc.nextLine());
				
				switch(type) {
					case 1 :
						//로그인
						MenuView.printUserMenu();
						break;
					case 2 :
						//관리자로그인
						MenuView.printAdminLogin();						
						break;
					case 9 : 
						MenuView.printMenu(); //되돌아가기
						System.exit(0);
				}
			case 2 :
				MenuView.register(); // 가입
				break;
			
			case 9 : 
				System.exit(0);
			}
		}

	}
		
	public static void printLoginType() {
		System.out.println("1. 개인회원 로그인  |  2. 관리자 로그인   |  9. 되돌아가기");
	}
	
	/**
	 * 	개인회원 로그인
	 * */	
	public static void printUserMenu() {
		while(true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet()); //Set객체 [session]
			//System.out.println("-----" +userId+ " 로그인 중 -----");
			System.out.println(" 1. ID/PW 입력하기  |  2. 아이디 찾기  |   3. 비밀번호 찾기  |   9. 되돌아가기 ");
			System.out.print("입력 > ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					MenuView.login();
					break;					
				case 2 :
					MenuView.findId();
					break;				
				case 3 :
					MenuView.findPassword();
					break;				
				case 9 :
					MenuView.printLoginType();
					//되돌아가기
				}
		}	
	}
	
	
	/**
	 * 로그인 메뉴
	 * */
	public static void login() {
		 System.out.print("아이디 > ");
		 String userId = sc.nextLine();
		 
		 System.out.print("비밀번호 > ");
		 String userPw = sc.nextLine();
		 
		 customerController.login(userId, userPw);
	}
	
	/**
	 * 	아이디 찾기
	 * */
	public static void findId() {
		System.out.print("회원가입 시 입력하신 성함 > ");
		String userName = sc.nextLine();
		System.out.print("회원가입 시 입력하신 휴대폰 번호 > ");
		String userPhoneNo = sc.nextLine();
		
		customerController.searchID(userName, userPhoneNo);
	}	
		
	
	/**
	 * 	비밀번호 찾기
	 * */
	public static void findPassword() {
		System.out.print("아이디 > ");
		String userId = sc.nextLine();		
		System.out.print("회원가입 시 입력하신 휴대폰 번호 > ");
		String userPhoneNo = sc.nextLine();

		customerController.searchPw(userId, userPhoneNo);	
	}
	
	
	/**
	 * 	회원가입 정보 입력하기
	 * */
	public static void register() {
		System.out.println("1. 회원가입 정보 입력하기  |   9. 되돌아가기");
		System.out.print("입력 > ");
		int no = Integer.parseInt(sc.nextLine());
		switch(no) {
		case 1 : 
			MenuView.userInput();
			break;		
		case 9 :
			MenuView.printLoginType();
			break;
		}	
	}
	
	public static void userInput() {
		System.out.print("가입자 이름 > ");
		String userName = sc.nextLine();
		System.out.print("가입자 생년월일 > ");
		String userBirth = sc.nextLine();
		System.out.print("가입자 휴대폰번호 > ");
		String userPhoneNo = sc.nextLine();
		System.out.print("가입 아이디 > ");
		String userId = sc.nextLine();
		System.out.print("가입 비밀번호 > ");
		String userPw = sc.nextLine();
		
		customerController.insertCustomer(new Customer(userName, userBirth, userPhoneNo, userId, userPw));
	}
			
	
	/**
	 * 	2. 로그인 이후 메뉴단(고객)
	 * @throws Throwable 
	 * @throws SQLException 
	 * */
	public static void afterLoginMenu(String userId, String userPw, String categoryName, String productName, String keyword) throws SQLException, Throwable {
		while(true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet()); //[]
			
			System.out.println("1.상품검색  |   2. 주문하기  |   3. 마이페이지  |   9. 로그아웃");
			System.out.print("입력 > ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				MenuView.printProductSearch(categoryName, productName, keyword, userId, userPw);
				break;
			case 2 :
				MenuView.printAllOrders(userId, userPw, categoryName, productName, keyword);
				break;				
			case 3 :
				MenuView.printMyPage(userId, userPw);
				break;
			case 9 :
				MenuView.logout(userId);
				break;
			}
		}
	}
	
	
	/**
	 * 로그아웃
	 * */
	public static void logout(String userId) {
		Session session = new Session(userId);		
		SessionSet ss = SessionSet.getInstance();
		ss.remove(session);	
	}
	

	
	/**
	 * 2. 상품검색
	 * @throws Throwable 
	 * @throws SQLException 
	 * */
	public static void printProductSearch(String categoryName, String productName, String keyword, String userId, String userPw) throws SQLException, Throwable {
		System.out.println("1.전체검색  |   2. 카테고리 검색  |   3. 상품명 검색  |   4. 키워드 검색  |   9. 되돌아가기");
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch(menu) {
			case 1 : 
				productController.productSelectAll();
				break;
			case 2 :
				String[] categoryNames = {"Beverage", "Dessert"};
				System.out.print("1."+categoryNames[0]+"2."+categoryNames[1]);
				int choice = Integer.parseInt(sc.nextLine());
				productController.productSelectBycategoryName(categoryName);				
				break;
			case 3 :
				productController.productSelectByproductName(productName);
				break;
			case 4 :
				productController.productSelectBykeyword(keyword);
				break;
			case 9 :
				MenuView.afterLoginMenu(userId, userPw, categoryName, productName, keyword);
				break;
		}
	}
	
	
	/**
	 * 	주문하기
	 * @throws Throwable 
	 * @throws SQLException 
	 * */
	public static void printAllOrders(String userId, String userPw, String categoryName, String productName, String keyword) throws SQLException, Throwable {
		System.out.println("1.주문하기  |   2. 퀵오더   |  3. 장바구니   |   9. 되돌아가기");
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch(menu) {
			case 1 :
				MenuView.order(userId, userPw, categoryName, productName, keyword ); //1.주문하기
				break;
			case 2 :
				MenuView.quickOrder(userId); //2.퀵오더
				break;
			case 3 :
				MenuView.printCart(userId, userPw, categoryName, productName, keyword); //3.장바구니로 이동
				break;
			case 9 :
				MenuView.afterLoginMenu(userId, userPw, categoryName, productName, keyword);
				break;				
		}
			
	}

////////////////////////////////////////////////////기능 맞는지 ???
	/**
	 * 	1.주문하기
	 * @throws Throwable 
	 * @throws SQLException 
	 * */
	public static void order(String userId, String userPw, String categoryName, String productName, String keyword) throws SQLException, Throwable {
		System.out.println("주문하기");
		System.out.println("1. 음료    |  2. 디저트");
		System.out.print("입력 > ");		
		int select=Integer.parseInt(sc.nextLine());			
		while(select<=0 || select >=3) {			
			System.out.println("다시 입력해주세요.");		
			select = Integer.parseInt(sc.nextLine());
			System.out.print("입력 > ");	
		}	
		if(select == 1) {
			productController.productSelectBycategoryName("beverage");
		} else {
			productController.productSelectBycategoryName("dessert");
		}
		System.out.print("주문할 상품코드 > ");
		String productCode = sc.nextLine();
		
		System.out.print("수량 > ");
		int ProductQty = Integer.parseInt(sc.nextLine());
				
		System.out.print("추가 옵션 선택 > yes or no? ");
		String plusOption = sc.nextLine();
		
		while(true) {
			if(productCode.equals("F01") || productCode.equals("F02") || productCode.equals("F03") || productCode.equals("F04")) {
				System.err.println("1. 데우기  |  2. 크림치즈 추가");
				System.err.print("입력 > ");
				int dessertOptionNo = Integer.parseInt(sc.nextLine());
				System.out.print("추가할 수량 > ");
				int dessertOptionQty = Integer.parseInt(sc.nextLine());
				
			} else {
				System.out.println("1. 샷 추가  |  2. 휘핑 크림 추가  |  3. 시럽추가");
				System.out.print("입력 > ");
				int beverageOptionNo = Integer.parseInt(sc.nextLine());
				System.out.print("추가할 수량 > ");
				int beverageOptionQty = Integer.parseInt(sc.nextLine());
				}		
			break;
		}
////////////////////////orderController.insertOrder(order);
			
			System.out.println("1. 상품 추가하기  |  2. 결제하기  |  3. 장바구니  |  4. 주문취소");
			System.out.print("입력 > ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
				case 1 :
					MenuView.order(userId, userPw, categoryName, productName, keyword);	//기능이 맞는지?
					break;
				case 2 :
					MenuView.printPay(userId);
					break;
				case 3 :
					MenuView.printCart(userId, userPw, categoryName, productName, keyword);
					break;
				case 4 :
					MenuView.afterLoginMenu(userId, userPw, categoryName, productName, keyword);
					break;
			}
			
		}
	

	/**
	 * 	2. 퀵오더
	 * */
	public static void quickOrder(String userId) {
		System.out.println("**** 최근 주문 내역 ****");

		//최근주문내역 보여주는 기능
		orderController.QuickOrder(userId);
		
		System.out.print("주문선택 > ");
		MenuView.printPay(userId);
	}
	
	
	/**
	 * 	결제하기 : 주문전 총내역 보여주기 기능 못만들었음
	 * */
	public static void printPay(String userId) {
		while(true) {
		 System.out.print("카드사 > ");
		 String cardCompany = sc.nextLine();	 
		 System.out.print("결제하실 카드번호 > ");
		 String cardNo = sc.nextLine();	 
		 System.out.print("카드 비밀번호 4자리 > ");
		 int cardPw = Integer.parseInt(sc.nextLine());	 
		 System.out.print("사용하실 쿠폰이 있습니까? Yes or No > ");
		 String usingCoupons = sc.nextLine();
		 	do {
		 		usingCoupons.equals("Yes");
		 		System.out.print("쿠폰코드 입력 > ");
				String enterCouponCode = sc.nextLine();
				System.out.print("결제하시겠습니까? Yes or No");
				String answer = sc.nextLine();		
				do {
				 answer.equals("Yes");
				 System.out.println("결제가 완료되었습니다.");
				} while(answer.equals("No"));
				 System.out.println("결제가 취소되었습니다.");
		 	} while(usingCoupons.equals("No"));
		 		System.out.println("쿠폰없이 결제를 진행합니다.");
			 	System.out.print("결제하시겠습니까? Yes or No");
				String answer = sc.nextLine();
				if(answer.equals("Yes")) {
					System.out.println("결제가 완료되었습니다.");
				} else {
					System.out.println("결제가 취소되었습니다.");
				}
			 }
		}
	
	
	
//////////////////////////////////////////////////////장바구니 controller가 없어요~~~~
	
	/**
	 * 	3. 장바구니
	 * @throws Throwable 
	 * @throws SQLException 
	 * */
	public static void printCart(String userId, String userPw, String categoryName, String productName, String keyword) throws SQLException, Throwable {		
		System.out.println("1.장바구니 조회하기  |  2. 장바구니 수정하기   |   3. 장바구니 비우기  |  9. 되돌아가기");
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch(menu) {
			case 1 :	
///////////////cartDAO.getCart(userId); //////////////////////////이렇게 가져오는게 맞는지...???
			
			System.out.println("1. 결제하기    |    2. 되돌아가기");
			System.out.print("입력 > ");
			int no = Integer.parseInt(sc.nextLine());
			switch(no) {
				case 1 :
					MenuView.printPay(userId);
					break;
				case 2 :
					MenuView.printAllOrders(userId, userPw, categoryName, productName, keyword);
					break;
			}
			break;		
			case 2 :			
			//2번기능
			System.out.print("수정할 장바구니 상품코드 입력 > ");
			String productCode = sc.nextLine();
			
/////////////////////////////해당정보 장바구니 띄워주기 기능 null로 채우는게 맞는지..?
///////////////cartDAO.updateCart(null, productCode, null, null, null, null, null, null);
			
			System.out.print("수정하실 상품 정보가 맞습니까? (Yes or No)");
			String answer = sc.nextLine();
			
			if(answer.equals("Yes")) {
				System.out.println("1. 수량 수정하기    |   2. 삭제하기");
				System.out.print("입력 > ");
				int choice = Integer.parseInt(sc.nextLine());
				
				if(choice == 1) {
					System.out.print("수정 수량 > ");
					int cartQty = Integer.parseInt(sc.nextLine());
				} else if(choice == 2) {
					//장바구니 삭제하기
///////////////////////////////////////////////////cartDAO.removeCart();
				}
			}
			break;
		}	
	}

	
	/**
	 * 	마이페이지
	 * @throws NotFoundException 
	 * @throws SQLException 
	 * */
	public static void printMyPage(String userId, String userPw) throws SQLException, Throwable {
		System.out.println("1. 개인정보조회   |   2. 개인정보 변경   |  3. 주문 내역   |  4. 쿠폰 조회   |  9. 되돌아가기");
		System.out.print("입력 > ");
		int menu = Integer.parseInt( sc.nextLine());
		switch(menu) {
			case 1 :
				System.out.print("비밀번호 입력 > ");
				String pass = sc.nextLine();
				
				customerController.searchCustomer(userId, userPw);
				
				break;
			case 2 :
				System.out.print("비밀번호 입력 > ");
				pass = sc.nextLine();
				
				System.out.println("변경하실 정보를 선택하세요.");
				System.out.println("1. 비밀번호  변경   |    2. 전화번호 변경    |    3. 회원 탈퇴");
				System.out.print("선택 > ");
				int choice = Integer.parseInt(sc.nextLine());			
				switch(choice) {
					case 1 : 
						//비밀번호 변경
						System.out.println("아이디를 입력하세요");
						String id = sc.nextLine();
						System.out.println("비밀번호를 입력하세요");
						String pw = sc.nextLine();
						System.out.println("변경할 비밀번호를 입력하세요");
						String newPw = sc.nextLine();

						customerController.updateCustomerPw(id, pw, newPw);
						break;
					case 2 : 
						//전화번호 변경
						System.out.println("아이디를 입력하세요");
						id = sc.nextLine();
						System.out.println("비밀번호를 입력하세요");
						pw = sc.nextLine();
						System.out.println("변경할 비밀번호를 입력하세요");
						String newPhone = sc.nextLine();
						
						customerController.updateCustomerPhoneNo(id, pw, newPhone);
						break;
					case 3 :
						System.out.println("회원탈퇴를 진행하시겠습니까? (입력: yes or no)");
						System.out.print("입력 > ");
						String answer = sc.nextLine();
						customerController.delete(userId, userPw);
				}
				break;
			case 3 :
				System.out.println("****** 총 주문내역 ******");				
				orderController.selectOrdersByUserId(userId);
				break;
			case 4 :
				System.out.println("****** 총 쿠폰내역 ******");
				couponController.couponSelect();
				break;
			case 9 :
				break;
		}
	}
	
	/**
	 * 	비밀번호 변경
	 * */
	public static void changePw(String userId, String userPw) {
		
	}
	
	
	
	/**
	 * 	관리자 로그인
	 * */
	public static void printAdminLogin() {
		System.out.println("-- 관리자 로그인 --");
		System.out.println("1. ID/PW 입력하기  |   9. 되돌아가기");	
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch(menu) {
			case 1 : 
				MenuView.login();
				break;
			case 9 :
				MenuView.printMenu();	
				break;
		}
	}
		
	
	/**
	 * 	로그인 이후 메뉴단(관리자)
	 * @throws AddException 
	 * */
	public static void adminMenu(String userId, Coupon coupon, Option option, String optionCode) throws SQLException, NotFoundException, AddException {		
		MenuView.printAdminLogin();
		
		System.out.println("1. 상품 관리  |  2. 매출관리   |   3. 쿠폰관리   |   9. 로그아웃");
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch(menu) {
			case 1:
				MenuView.printProduct(option, optionCode);
				break;
			case 2 :
				MenuView.printSales();
				break;
			case 3 :
				MenuView.printCoupon(coupon);
				break;
			case 9 :
				MenuView.logout(userId);//로그아웃
				return;
		}
	
	}
	
/////////////////////////////////////////////////////////홍제님과 상의!
	/**
	 * 	상품관리  
	 * @throws AddException 
	 * */	
	public static void printProduct(Option option, String optionCode) throws SQLException, NotFoundException, AddException {
		System.out.println("1. 상품 목록  |  2. 상품 등록   |   3. 상품 수정  |   4. 상품 삭제  |  5. 옵션 관리  |  9. 되돌아가기");
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch(menu) {
			case 1 :
				productController.productSelectAll();
				break;
			case 2 :		/////////////////////////////////////////어디???
				System.out.print("등록할 상품정보 입력 > ");
				String regCode = sc.nextLine();
				System.out.print("상품코드 > ");
				String productCode = sc.nextLine();
				System.out.print("상품명 > ");
				String productName = sc.nextLine();
				System.out.print("가격 > ");
				int productPrice = Integer.parseInt(sc.nextLine());
				System.out.print("상품설명 > ");
				String productDetail = sc.nextLine();
				System.out.print("카테고리 > ");
				String categoryCode = sc.nextLine();
				
				productController.productInsert( new Product(productCode, productName, productPrice, productDetail, categoryCode ) );
				break;
			case 3 :
				System.out.print("수정할 상품이름 입력 > ");
				productName = sc.nextLine();
				
				//해당정보 띄워주기
				productController.productSelectByproductName(productName);
				
				System.out.print("수정하실 상품 정보가 맞습니까? (Yes or No)");
				String modifyProduct = sc.nextLine();
				if(modifyProduct.toUpperCase().equals("YES")) {
					
					System.out.print("수정 가격 > ");
					productPrice = Integer.parseInt(sc.nextLine());
					System.out.print("수정 상품설명 > ");
					productDetail = sc.nextLine();
				
					
					
					System.out.println("상품정보를 수정했습니다.");
				} else {
					System.out.println("상품정보 수정을 취소합니다.");
				}
				break;
				
			case 4 :
				System.out.print("삭제하실 상품 코드 입력 > ");
				//해당 상품정보 띄워주기
				
				
				System.out.print("삭제하실 상품 정보가 맞습니까? (Yes or No)");
				String delete = sc.nextLine();
				if(delete.toUpperCase().equals("YES")) {
					//삭제기능
					System.out.println("삭제되었습니다.");
				} else {
					System.out.println("삭제를 취소합니다.");
					//되돌아가기 기능
				}
				break;
				
			case 5 :
				MenuView.printOption(option, optionCode );
				break;
		}			
	}
	

	/**
	 * 	매출관리
	 * */
	public static void printSales() {
		System.out.println("1. 매출 조회  |  2. 판매량 조회   |   3. 주문 조회   |   9. 되돌아가기");
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch(menu) {
			case 1 : 
				//매출 조회단 이동
				MenuView.printSalesCheck();
				break;
			case 2 :
				//판매량조회단 이동
				MenuView.printSalesVolume();
				break;
			case 3 :				
				//주문조회 기능			
				SalesController.selectAllOrders();
		}
	}
	
	
	
	/**
	 * 	옵션관리
	 * @throws AddException 
	 * */
	public static void printOption(Option option, String optionCode) throws SQLException, NotFoundException, AddException {
		System.out.println("1. 옵션 목록  |  2. 옵션 등록   |   3. 옵션 수정  |   4. 옵션 삭제  |  9. 되돌아가기");
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch(menu) {
			case 1 :
				//옵션 목록 리스트 출력 기능
				optionController.optionSelect();
				break;
			case 2 :
				//옵션정보 등록기능
				optionController.optionInsert(option);
				break;
			case 3 :
			System.out.print("수정할 옵션코드 입력 > ");
			String modify = sc.nextLine();
			
			//해당 상품정보 띄워주기 
			optionController.optionSelectByOptionCode(optionCode);
			
			System.out.print("수정하실 옵션 정보가 맞습니까? (Yes or No)");
			String modifyOption = sc.nextLine();
			if(modifyOption.toUpperCase().equals("YES")) {
				System.out.print("수정 옵션명 > ");
				String optionName = sc.nextLine();
				System.out.print("수정 옵션가격 > ");
				int optionPrice = Integer.parseInt(sc.nextLine());
				System.out.print("수정 카테고리 > ");
				String optionCategory = sc.nextLine();
				System.out.println("옵션정보를 수정했습니다.");
				
				optionController.optionUpdate(option);
				
			} else {
				System.out.println("옵션정보 수정을 취소합니다.");
			}
			break;
		case 4 :
			System.out.print("삭제하실 옵션 코드 입력 > ");
			String delete = sc.nextLine();
			
			//해당 상품정보 띄워주기 
			optionController.optionSelectByOptionCode(optionCode);
			
			System.out.print("삭제하실 옵션 정보가 맞습니까? (Yes or No)");
			String deleteOption = sc.nextLine();
			if(deleteOption.toUpperCase().equals("YES")) {
				//삭제기능
				optionController.optionDelete(optionCode);
			} else {
				//되돌아가기 기능
				MenuView.printOption(option, optionCode);
			}
			break;
		}
	}

	
	/**
	 * 	매출조회단 이동 메소드
	 * */
	public static void printSalesCheck() {
		System.out.println("1. 일별 매출 조회  |  2. 누적 매출 조회   |   9. 되돌아가기");
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch(menu) {
			case 1 : 
				//1: 일 매출 총액, 전체 주문 완료 건수, 전체 주문 수량
				System.out.print("년도 입력 > ");
				int year = Integer.parseInt(sc.nextLine());
				System.out.print("월 입력 > ");
				int month = Integer.parseInt(sc.nextLine());
				System.out.print("일 입력 > ");
				int day = Integer.parseInt(sc.nextLine());
				 
				String date = year+"-"+month+"-"+day;			
				
				SalesController.selectSalesBydate(date);
				break;
			case 2 :
				//2: 누적 매출 총액, 누적 주문 완료 건수, 누적 주문 수량
				SalesController.selectSalesAll();
				break;
		}
	}
	
	
	/**
	 * 	판매량조회단 이동 메소드
	 * */
	public static void printSalesVolume() {
		System.out.println("1. 일별 판매량 조회    2. 누적 판매량 조회   9. 되돌아가기");
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch(menu) {
			case 1 :
				//1: 일별 판매량:  제품별 일별 판매 수량, 제품별  일별 판매 순위 (5위)
				System.out.print("년도 입력 > ");
				int year = Integer.parseInt(sc.nextLine());
				System.out.print("월 입력 > ");
				int month = Integer.parseInt(sc.nextLine());
				System.out.print("일 입력 > ");
				int day = Integer.parseInt(sc.nextLine());
				 
				String date = year+"-"+month+"-"+day;
				
				System.out.print("일별 판매순위 5위까지만 보시겠습니까? yes or no");
				String answer = sc.nextLine();
				if(answer.toUpperCase().equals("YES")) {
					SalesController.selectSalesRankRateBydate(date);
				} else {
					SalesController.selectSalesRateBydate(date);
				}			
				break;
			case 2 :
				//2: 누적 판매량: 제품별 누적 판매 수량, 제품별 누적 판매 순위(5위)
				System.out.print("누적 판매순위 5위까지만 보시겠습니까? yes or no");
				answer = sc.nextLine();
				if(answer.toUpperCase().equals("YES")) {
					SalesController.selectSalesRankRateByallDate();
				} else {
					SalesController.selectSalesRateByallDate();
				}
				break;
		}
	}
	
	
	/**
	 * 	쿠폰관리
	 * @throws SQLException 
	 * @throws NotFoundException 
	 * @throws AddException 
	 * */
	public static void printCoupon(Coupon coupon) throws SQLException, NotFoundException, AddException {
		System.out.println("1. 쿠폰 목록  |  2. 쿠폰 등록   |  3. 쿠폰 삭제  |  9. 되돌아가기");
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch(menu) {
			case 1 :
				//쿠폰 목록 리스트 출력
				couponController.couponSelect();
				break;
			case 2 :
				//쿠폰등록기능
				couponController.couponInsert(coupon);
				break;
			case 3 :
				
				System.out.print("삭제하실 쿠폰 코드 입력 > ");
				String couponCode = sc.nextLine(); 
				
				//해당 쿠폰정보 띄워주기 기능
				
				
				System.out.print("삭제하실 쿠폰 정보가 맞습니까? (Yes or No)");
				String answer = sc.nextLine();
				if(answer.toUpperCase().equals("YES")) {
					//쿠폰삭제기능
					couponController.couponDelete(couponCode);
					System.out.println("쿠폰삭제완료");
				} else {
					System.out.println("쿠폰삭제취소");
				}
		}
	
	}
	
	
	
	
	
//////////////////////////////////////////////////////////////////////////////////////
	
		
	
	

    /**
     * 장바구니 담기
     * */
    public static void putCart(String userId) {
		System.out.println("--장바구니 담기 작업 --");
		System.out.print("상품번호 : ");
		String goodsId = sc.nextLine();
		System.out.print("수량 : ");
		int qty = Integer.parseInt(sc.nextLine());
		
		//CartController.putCart(id,goodsId,qty);
	
		
	}
	
    /**
     * 장바구니 내역조회
     * */
	public static void viewCart(String userId) {
		//CartController.viewCart(id);
		
		System.out.println("1. 결제하기    |    2. 되돌아가기");
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		if(menu == 1) {
			MenuView.printPay(userId);
		} else {
			//고객 로그인 이후 메뉴단으로 되돌아감
		}
		
	}
}



