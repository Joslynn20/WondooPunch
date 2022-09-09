package mvc.view;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import mvc.controller.CartController;
import mvc.controller.CouponController;
import mvc.controller.CustomerController;
import mvc.controller.OptionController;
import mvc.controller.OrderController;
import mvc.controller.ProductController;
import mvc.controller.SalesController;
import mvc.dto.Admin;
import mvc.dto.Cart;
import mvc.dto.Coupon;
import mvc.dto.Customer;
import mvc.dto.Option;
import mvc.dto.Orders;
import mvc.dto.Product;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;
import mvc.session.SessionSet;
import mvc.session.SessionSetImpl;

public class NewMenuView {
	private static Scanner sc = new Scanner(System.in);
    private static  SessionSetImpl sessionSet = SessionSetImpl.getInstance();
    
   
    
    public static void enterMenu() {
    	System.out.println("==== WondooPunch ====");
		System.out.println(" 1. 개인회원 로그인   |  2. 관리자 로그인   |  3. 회원가입    |  9. 종료");	
		System.out.print("입력 > ");
    }

	public static boolean showDefaultMenu() {
		NewMenuView.enterMenu();
		int ch;
		while(true) {		
			do {	
				try {
				ch = Integer.parseInt(sc.nextLine());				
					switch(ch) {
						case 1:
							System.out.println(" 1. ID/PW 입력하기  |  2. 아이디 찾기  |   3. 비밀번호 찾기  |   9. 되돌아가기 ");
							System.out.print("입력 > ");				
							
								int menu =Integer.parseInt(sc.nextLine());
								switch(menu) {
								case 1 :
									NewMenuView.login();
									break;					
								case 2 ://아이디찾기
									NewMenuView.findId();
									break;				
								case 3 ://비번찾기
									NewMenuView.findPassword();
									break;				
								case 9 :
									NewMenuView.enterMenu();
									break;
								}
								break;
								 
						case 2://관리자로그인
							System.out.println("1. ID/PW 입력하기  |   9. 되돌아가기");	
							System.out.print("입력 > ");					
							int choice = Integer.parseInt(sc.nextLine());
								switch(choice) {
									case 1 : 
										NewMenuView.adminLogin();
										break;
									case 9 :	
										NewMenuView.enterMenu();
										break;
								}
								break;
						case 3://회원가입
								NewMenuView.register();
							break; 
						case 9://종료
								System.exit(0);
						} 
					} catch(Exception e) {
						FailView.errorMessage("입력양식을 확인하세요.");
						NewMenuView.showDefaultMenu();
						return false;
					}
	
				} while (ch != 1 || ch != 2 || ch != 9 );
			}	
		
	}	
    

	
/////////////////////////   관리자 메뉴단       ///////////////////////////////////////
	/**
	 * 	관리자 메뉴단
	 * @throws NotFoundException 
	 * @throws SQLException 
	 * @throws AddException 
	 * */
	
	public static boolean showAdminMenu() throws SQLException, NotFoundException, AddException {
		while(true) {
			try {
				int ch;
				System.out.println("\n===관리자 메뉴===");
				System.out.println("1. 상품 관리  |  2. 매출관리   |   3. 쿠폰관리   |   9. 로그아웃");
				
				System.out.print("입력 > ");
				ch = Integer.parseInt(sc.nextLine());
			
				switch (ch) {
				case 1:
					System.out.println("1. 상품 목록  |  2. 상품 등록  |   3. 상품 수정  |   4. 상품 삭제  |  5. 옵션 관리  |  9. 되돌아가기");	
					
					System.out.print("입력 > ");
					ch = Integer.parseInt(sc.nextLine());
					
					switch (ch) {
					case 1:// 상품목록조회
						ProductController.selectAllProduct();
						break;
					case 2:// 상품 등록
						System.out.print("등록할 상품정보 입력 > ");
						String regCode = sc.nextLine();
						System.out.print("상품코드 > ");
						String regProductCode = sc.nextLine();
						System.out.print("상품명 > ");
						String productName = sc.nextLine();
						System.out.print("가격 > ");
						int productPrice = Integer.parseInt(sc.nextLine());
						System.out.print("상품설명 > ");
						String productDetail = sc.nextLine();
						System.out.print("카테고리 > ");
						String categoryCode = sc.nextLine();			
						ProductController.insertProduct(new Product(regProductCode, productName, productPrice, productDetail, categoryCode ) );
						break;
					case 3:// 상품 수정
						System.out.print("수정할 코드 입력 > ");
						String modifyProductCode = sc.nextLine();						
						ProductController.selectProductByproductCode(modifyProductCode);
						System.out.print("수정하실 상품 정보가 맞습니까? (Yes or No) > ");
						try {
							String modifyProduct = sc.nextLine();
							if(modifyProduct.toUpperCase().equals("YES")) {			
								System.out.print("수정 가격 > ");
								productPrice = Integer.parseInt(sc.nextLine());
								System.out.print("수정 상품설명 > ");
								productDetail = sc.nextLine();				
								ProductController.updateProduct( new Product(modifyProductCode, productPrice, productDetail) );		
							} else {
								System.out.println("상품정보 수정을 취소합니다.");
							}
						} catch(Exception e) {
							FailView.errorMessage("응답하는 방식을 다시 확인해주세요.");
						}
						break;				
					case 4:// 상품 삭제
						System.out.print("삭제하실 상품 코드 입력 > ");
						String deleteCode = sc.nextLine();
						//해당 상품정보 띄워주기
						ProductController.selectProductByproductCode(deleteCode);
						
						System.out.print("삭제하실 상품 정보가 맞습니까? (Yes or No) > ");
						String delete = sc.nextLine();
						if(delete.toUpperCase().equals("YES")) {
							ProductController.deleteProduct(deleteCode);
						} else {
							System.out.println("삭제를 취소합니다.");
							break;
						}
						break;
					case 5:// 옵션 관리
						NewMenuView.printOption(); 
						break;
					case 9:// 되돌아가기
						break;
					}
					break;
				case 2:// 매출관리
					System.out.println("1. 매출 조회  |  2. 판매량 조회   |   3. 주문 조회   |   9. 되돌아가기");
					System.out.print("입력 > ");
					ch = Integer.parseInt(sc.nextLine());
					switch (ch) {
					case 1:// 매출 조회단 이동
						NewMenuView.printSalesCheck();
						break;
					case 2:// 판매량조회단 이동
						NewMenuView.printSalesVolume();
						break;
					case 3:// 주문조회 기능			
						SalesController.selectAllOrders();
						break;
					}
					break;		
				case 3:// 쿠폰관리
					System.out.println("1. 쿠폰 목록  |  2. 쿠폰 등록   |  3. 쿠폰 삭제  |  9. 되돌아가기");
					System.out.print("입력 > ");
					ch = Integer.parseInt(sc.nextLine());
					switch (ch) {
					case 1:// 쿠폰 목록 리스트 출력
						CouponController.selectAllCoupon();
						break;
					case 2 :
						System.out.println("등록할 쿠폰 정보 입력");
						System.out.print("쿠폰코드 > ");
						String couponCode = sc.nextLine();
						System.out.print("쿠폰명> ");
						String couponName = sc.nextLine();
						System.out.print("할인율 > ");
						int couponDC = Integer.parseInt(sc.nextLine());
						System.out.print("유효기간 > ");
						String couponExpDate = sc.nextLine();
						
						//쿠폰등록기능
						CouponController.insertCoupon( new Coupon(couponCode, couponName, couponDC, couponExpDate));
						break;
					case 3 :			
						System.out.print("삭제하실 쿠폰 코드 입력 > ");
						couponCode = sc.nextLine(); 
		
						//해당 쿠폰정보 띄워주기 기능
						CouponController.selectCouponByCouponCode(couponCode);
						
						System.out.print("삭제하실 쿠폰 정보가 맞습니까? (Yes or No) > ");
						String answer = sc.nextLine();
						if(answer.toUpperCase().equals("YES")) {
							//쿠폰삭제기능
							CouponController.deleteCoupon(couponCode);
						} else {
							System.out.println("쿠폰삭제취소");
						}	
						break;
					}			
				case 9:// 로그아웃
					//sessionSet.remove(userId);
					//return false;
				}	
			} catch(NumberFormatException e) {		
				FailView.errorMessage("숫자만 입력하세요.");
			} 
		}
	}

/////////////////////          관리자 기능 모음        //////////////////////////////////////////	
	/**
	 * 관리자 로그인 메뉴
	 * @throws AddException 
	 * @throws NotFoundException 
	 * @throws SQLException 
	 * */
	public static void adminLogin() throws SQLException, NotFoundException, AddException {
		
		 System.out.print("아이디 > ");
		 String inputId = sc.nextLine();
		 
		 System.out.print("비밀번호 > ");
		 String inputPw = sc.nextLine();
		 
		 String ADMINID = SessionSet.ADMINID;	 
		 String ADMINPW = SessionSet.ADMINPW;          
	
	     if(inputId.equals(ADMINID) && inputPw.equals(ADMINPW)) {
	    	 NewMenuView.showAdminMenu();
	     }  
	}
	
	

	/**
	 * 	옵션관리
	 * @throws AddException 
	 * */
	public static void printOption() throws SQLException, NotFoundException, AddException {
		System.out.println("1. 옵션 목록  |  2. 옵션 등록   |   3. 옵션 수정  |   4. 옵션 삭제  |  9. 되돌아가기");
		try {
			System.out.print("입력 > ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
				case 1:
					//옵션 목록 리스트 출력 기능
					OptionController.selectAllOption();   //다 뜨는게 맞나요???
					break;
				case 2:
					System.out.println("등록할 옵션 정보 입력");
					System.out.print("옵션코드 > ");
					String optionCode = sc.nextLine();
					System.out.print("옵션명 > ");
					String optionName = sc.nextLine();
					System.out.print("옵션가격 > ");
					int optionPrice = Integer.parseInt(sc.nextLine());
					System.out.print("상품코드 > ");
					String productCode = sc.nextLine();		
					
					OptionController.insertOption( new Option(optionCode, optionName, optionPrice, productCode) );
					break;
				case 3://옵션수정
					//해당 옵션정보 띄워주기   어떤걸 띄워준다는 건지??? 전체 옵션이 나오나요????   -> 화면쪽 출력에 궁금함.....
					
					System.out.print("수정하실 옵션 정보가 맞습니까? (Yes or No) > ");
					String modifyOption = sc.nextLine();
					if(modifyOption.toUpperCase().equals("YES")) {
						System.out.print("수정할 옵션코드 입력 > ");
						String modifyCode = sc.nextLine();			
						System.out.print("수정 옵션명 > ");
						String modifyOptionName = sc.nextLine();
						System.out.print("수정 옵션가격 > ");
						int modifyOptionPrice = Integer.parseInt(sc.nextLine());
						System.out.print("수정 카테고리 > ");
						String modifyOptionCategory = sc.nextLine();
						
						OptionController.updateOption( new Option(modifyCode, modifyOptionName, modifyOptionPrice, modifyOptionCategory ));			
					} else {
						break;
					}
					break;
				case 4:
					System.out.print("삭제하실 옵션 코드 입력 > ");
					String delete = sc.nextLine();
					
					OptionController.optionSelectByOptionCode(delete);
					
					System.out.print("삭제하실 옵션 정보가 맞습니까? (Yes or No) > ");
					String deleteOption = sc.nextLine();
					if(deleteOption.toUpperCase().equals("YES")) {
						//삭제기능
					} else {
						break;
					}
					break;
				case 9:
					break;
			}
		} catch(Exception e) {
			FailView.errorMessage("번호를 선택 해 주세요.");
			
		}
	}

	
	
	/**
	 * 	매출조회단 이동 메소드
	 * */
	public static void printSalesCheck() {
		System.out.println("1. 일별 매출 조회  |  2. 누적 매출 조회   |   9. 되돌아가기");
		try {
			System.out.print("입력 > ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
				case 1: 
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
				case 2:
					//2: 누적 매출 총액, 누적 주문 완료 건수, 누적 주문 수량
					SalesController.selectSalesAll();
					break;
				case 9:
					break;
			}
		} catch(Exception e) {
			System.out.println("번호!!!!!");
		}
		
	}	
	
	
	/**
	 * 	판매량조회단 이동 메소드
	 * */
	public static void printSalesVolume() {
		System.out.println("1. 일별 판매량 조회    2. 누적 판매량 조회   9. 되돌아가기");
		try {
			System.out.print("입력 > ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
				case 1:
					//1: 일별 판매량:  제품별 일별 판매 수량, 제품별  일별 판매 순위 (5위)
					System.out.print("년도 입력 > ");
					int year = Integer.parseInt(sc.nextLine());
					System.out.print("월 입력 > ");
					int month = Integer.parseInt(sc.nextLine());
					System.out.print("일 입력 > ");
					int day = Integer.parseInt(sc.nextLine());
					 
					String date = year+"-"+month+"-"+day;
					
					System.out.print("일별 판매순위 5위까지만 보시겠습니까? (yes or no) > ");
					String answer = sc.nextLine();
					if(answer.toUpperCase().equals("YES")) {
						SalesController.selectSalesRankRateBydate(date);
					} else {
						SalesController.selectSalesRateBydate(date);
					}			
					break;
				case 2:
					//2: 누적 판매량: 제품별 누적 판매 수량, 제품별 누적 판매 순위(5위)
					System.out.println("누적 판매순위 5위까지만 보시겠습니까? (yes or no) ");
					System.out.print("입력 > ");
					answer = sc.nextLine();
					if(answer.toUpperCase().equals("YES")) {
						SalesController.selectSalesRankRateByallDate();
					} else {
						SalesController.selectSalesRateByallDate();
					}
					break;
				case 9:
					break;
			}
		} catch(Exception e) {
			System.out.println("숫자를 골라라");
		}
	}
	
	
///////////////////     고객 메뉴단       ////////////////////////////////////////////////////////	
	/**
	 * 	고객 메뉴단
	 * @throws NotFoundException 
	 * @throws SQLException 
	 * */
	public static boolean showUserMenu(String userId, String userPw) throws SQLException, NotFoundException {
		
		while(true) {
			System.out.println("\n===원하시는 메뉴를 고르시오===");
			int ch;
			System.out.println("\n===사용자 메뉴===");
			System.out.println("1.상품검색  |   2. 주문하기  |   3. 장바구니   |   4. 마이페이지  |   9. 로그아웃");
			try {
				System.out.print("입력 > ");
				ch = Integer.parseInt(sc.nextLine());
				switch (ch) {
				case 1:// 상품검색
					System.out.println("1.전체검색  |   2. 카테고리 검색  |   3. 상품명 검색  |   4. 키워드 검색  |   9. 되돌아가기");
					System.out.print("입력 > ");
					ch = Integer.parseInt(sc.nextLine());
					switch(ch) {
					case 1:// 전체검색
						ProductController.selectAllProduct();
						break;
					case 2:// 카테고리검색
						String[] categoryNames = {"빈칸","Beverage", "Dessert"};
						System.out.println("1."+categoryNames[1]+"2."+categoryNames[2]);
						System.out.print("입력 > ");
						int choice = Integer.parseInt(sc.nextLine());
						while( choice<=0 || choice>=3 ) {
							System.out.println("1과 2 중에 하나만 고르세요. ");
							System.out.print("입력 > ");
							choice = Integer.parseInt(sc.nextLine());  		
						}
						ProductController.selectProductByCategoryName(categoryNames[choice]);			
						break;
					case 3://상품명검색
						System.out.print("상품명을 입력해주세요 > ");
						String searchProductName = sc.nextLine();
						
						ProductController.selectProductByProductName(searchProductName); //상품명 안기다려주고 바로감
						break;
					case 4://키워드검색
						System.out.print("상품의 키워드를 입력하세요 > ");
					    String keyword=sc.nextLine();
						ProductController.selectProductByKeyword(keyword);
						break;
					case 9:// 되돌아가기
						break;
					}
					break;
				case 2:// 주문하기
					System.out.println("1.주문하기  |   2. 퀵오더   |   9. 되돌아가기");
					System.out.print("입력 > ");
					ch = Integer.parseInt(sc.nextLine());
					switch(ch) {
					case 1:// !!!기능구현이 잘 된건지 알수없음.....	
						while(true) {
							NewMenuView.selectOrder();
							System.out.println("1.  상품 추가하기  |  2. 결제하기  |  3. 주문취소");
							System.out.print("입력 > ");
							int choice = Integer.parseInt(sc.nextLine());
							if(choice == 1) {
								continue;
							} if(choice == 2) {
								NewMenuView.printPay();
							} if(choice == 3) {
								break;
							}	
						}
						break;
					case 2:// 퀵오더
						System.out.println("**** 최근 주문 내역 ****");
						//OrderController.QuickOrder();	
						//보경님과 상의.
						System.out.print("주문선택 > ");
						int choice = Integer.parseInt(sc.nextLine());
						NewMenuView.printPay(); //결제하기 창으로 이동
						break;
					case 9:// 되돌아가기
						break;
					}
					break;
				case 3:// 장바구니    ->재봉님과 상의
					System.out.println("1.장바구니 담기  |  2. 장바구니 조회하기  |  3. 장바구니 수정하기   |   4. 장바구니 비우기  |  9. 되돌아가기");
					System.out.print("입력 > ");
					ch = Integer.parseInt(sc.nextLine());
					switch(ch) {
						case 1://장바구니 담기
							while(true) {
								NewMenuView.selectOrder();
								System.out.println("1.  상품 추가하기  |  2. 결제하기  |  3. 장바구니 담기 취소");
								System.out.print("입력 > ");
								int choice = Integer.parseInt(sc.nextLine());
								if(choice == 1) {
									continue;
								} if(choice == 2) {
									NewMenuView.printPay();
								} if(choice == 3) {
									break;
								}	
							}
							break;
						case 2://장바구니 조회하기
							//장바구니 조회기능 출력
							System.out.println("1. 결제하기    |    2. 되돌아가기");
							System.out.print("입력 > ");
							int choice = Integer.parseInt(sc.nextLine());
							if(choice == 1) {
								NewMenuView.printPay();
							} else {
								break;
							}
							break;
						case 3://장바구니 수정하기
							//CartController.getCart();//장바구니 전체조회  //재봉님과 상의
							
							System.out.print("수정할 장바구니 번호 입력 > ");
							int cartNo = Integer.parseInt(sc.nextLine());
							
							System.out.println("1. 수량 수정하기    |   2. 삭제하기");
							System.out.print("입력 > ");
							int cartQty = Integer.parseInt(sc.nextLine());
							
							//CartController.updateCart(); //수정하기 기능
							break;
						case 4://장바구니 비우기
							//CartController.removeAllCart();
							break;
						case 9:// 되돌아가기
							break;
					}
					break;
					
				case 4:// 마이페이지
					System.out.println("1. 개인정보조회   |   2. 개인정보 변경   |  3. 주문 내역   |  4. 쿠폰 조회   |  9. 되돌아가기\n");
					System.out.print("입력 > ");
					ch = Integer.parseInt(sc.nextLine());
					switch (ch) {
					case 1:// 개인정보조회
						System.out.print("비밀번호 입력 > ");
						String pass = sc.nextLine();
						CustomerController.selectCustomer(userId, userPw);
						break;
					case 2:// 개인정보 변경
						System.out.print("비밀번호 입력 > ");
						pass = sc.nextLine();
					
						System.out.println("변경하실 정보를 선택하세요.");
						System.out.println("1. 비밀번호 변경   |    2. 전화번호 변경    |    3. 회원 탈퇴");
						System.out.print("선택 > ");
						ch = Integer.parseInt(sc.nextLine());	
						switch(ch) {
							case 1 ://비밀번호 변경
								System.out.print("아이디를 입력하세요 > ");
								String id = sc.nextLine();
								System.out.print("비밀번호를 입력하세요 > ");
								String pw = sc.nextLine();
								System.out.print("변경할 비밀번호를 입력하세요 > ");
								String newPw = sc.nextLine();
								CustomerController.updateCustomerPw(id, pw, newPw);
								break;
							case 2 ://전화번호 변경
								System.out.print("아이디를 입력하세요 > ");
								id = sc.nextLine();
								System.out.print("비밀번호를 입력하세요 > ");
								pw = sc.nextLine();
								System.out.print("변경할 전화번호를 입력하세요 > ");
								String newPhone = sc.nextLine();
								CustomerController.updateCustomerPhoneNo(id, pw, newPhone);
								break;
							case 3 :
								System.out.println("회원탈퇴를 진행하시겠습니까? (입력: yes or no)");
								System.out.print("입력 > ");
								String answer = sc.nextLine();
								if(answer.toUpperCase().equals("YES")) {
									//CustomerController.deleteCustomer(userId, userPw);
								} else {
									System.out.println("회원탈퇴를 취소합니다.");
								}
								break;
						}
						break;
					case 3:// 총주문내역
						System.out.println("****** 총 주문내역 ******");				
						//OrderController.selectOrdersByUserId();   //보경님과 상의
						break;
					case 4 ://쿠폰조회
						System.out.println("****** 총 쿠폰내역 ******");
						CouponController.selectAllCoupon();
						break;
					case 9://되돌아가기
						break;
					}
					break;
				case 9:// 로그아웃
					sessionSet.remove(userId);
					NewMenuView.showDefaultMenu();
					break;
				}
			} catch(NumberFormatException e) {
				System.out.println("제시된 형식에 맞추어 입력해주세요!");
			}
		}
			
	}
////////////////////////     고객  기능들           ///////////////////////////////////////////////////////////////////
	/**
	 * 로그인 기능
	 * @throws Exception 
	 * @throws SQLException 
	 * */
	public static void login() throws SQLException, Exception {
		 System.out.print("아이디 > ");
		 String userId = sc.nextLine();	 
		 System.out.print("비밀번호 > ");
		 String userPw = sc.nextLine();
		 CustomerController.login(userId, userPw);		 
		 
		 
		 /*if(sessionSet.add(userId)) {
			 NewMenuView.showUserMenu();
		 }*/
		
	}
	
	/**
	 * 	아이디 찾기
	 * */
	public static void findId() {
		System.out.print("회원가입 시 입력하신 성함 > ");
		String userName = sc.nextLine();
		System.out.print("회원가입 시 입력하신 휴대폰 번호 > ");
		String userPhoneNo = sc.nextLine();		
		CustomerController.selectID(userName, userPhoneNo);
	}	
		
	
	/**
	 * 	비밀번호 찾기
	 * */
	public static void findPassword() {
		System.out.print("아이디 > ");
		String userId = sc.nextLine();		
		System.out.print("회원가입 시 입력하신 휴대폰 번호 > ");
		String userPhoneNo = sc.nextLine();	
		CustomerController.searchPw(userId, userPhoneNo);
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
			CustomerController.insertCustomer(new Customer(userName, userBirth, userPhoneNo, userId, userPw));
			break;		
		case 9 :
			NewMenuView.enterMenu();
			break;
		}
		if(no !=1 || no !=9) {
			FailView.errorMessage("1과 9만 입력을 선택하세요.");
			NewMenuView.register();
		}
	}
	
	
	/**
	 * 	결제하기 : 주문전 총내역 보여주기 기능 못만들었음
	 * */
	public static void printPay() {	

		String usingCoupons = "";
		try {
			System.out.print("카드사 > ");
			String cardCompany = sc.nextLine();	 
			 
			System.out.print("결제하실 카드번호 > ");
			String cardNo = sc.nextLine();	 
			 
			System.out.print("카드 비밀번호 4자리 > ");
			int cardPw = Integer.parseInt(sc.nextLine());	
			 
			System.out.print("사용하실 쿠폰이 있습니까? Yes or No > ");
			usingCoupons = sc.nextLine();
		 
			if(usingCoupons.toUpperCase().equals("YES")) {
			 	System.out.print("쿠폰코드 입력 > ");
				String enterCouponCode = sc.nextLine();
				System.out.print("결제하시겠습니까? Yes or No > ");
				String answer = sc.nextLine();		
				if(answer.toUpperCase().equals("YES")) {
					System.out.println("결제가 완료되었습니다.");
				} else if(answer.toUpperCase().equals("NO")) {
					System.out.println("결제가 취소되었습니다.");	
				} 
			}
			
			if(usingCoupons.toUpperCase().equals("NO")) {
				System.out.println("쿠폰 없이 결제를 진행합니다.");
				System.out.print("결제하시겠습니까? Yes or No > ");
				String pay = sc.nextLine();
				if(pay.toUpperCase().equals("YES")) {
					System.out.println("쿠폰없이 결제가 완료되었습니다.");
				} 
			}
		
					
		} catch(Exception e) {
			FailView.errorMessage("입력 양식을 확인하세요");
		}
	
	 }
	 
	
			//OrderController.insertOrder( new()); 두영님과 상의

		
	
	/**
	 * 	주문하기/장바구니 중복관련 기능     ////////////////////////////기능수정 필요함!!!!!
	 * @throws NotFoundException 
	 * @throws SQLException 
	 * */
	public static void selectOrder() throws SQLException, NotFoundException {
		while(true) {
			//카테고리에 따른 상품출력
			String[] categoryNames = {"빈칸","Beverage", "Dessert"};
			System.out.println("1."+categoryNames[1]+"2."+categoryNames[2]);
			System.out.print("선택 > ");
			int choice = Integer.parseInt(sc.nextLine());
			while( choice<=0 || choice>=3 ) {
				System.out.println("1과 2 중에 하나만 고르세요. ");
				System.out.print("선택 > ");
				choice = Integer.parseInt(sc.nextLine());  		
			}
			ProductController.selectProductByCategoryName(categoryNames[choice]);			
					
			System.out.print("상품코드 > ");
			String productCode = sc.nextLine();
			System.out.print("수량 > ");
			int qty = Integer.parseInt(sc.nextLine());
			
			//상품코드에 따른 옵션 띄워주기
			OptionController.selectOptionByProductCode(productCode); //두영님과 상의
			
			System.out.print("추가 옵션 선택 > yes or no?");
			try {
				String plus = sc.nextLine();
				if(plus.toUpperCase().equals("YES")) {
					while(true) {
						System.out.print("옵션코드 > ");
						String optionCode = sc.nextLine();		
							//추가해주는 기능		
							//OptionController.insertOption();//이거 맞나요..?
							
						System.out.print("옵션 추가 yes or no?");
						String plusOption = sc.nextLine();
						if(plusOption.toUpperCase().equals("YES")) {
							continue;
						} if(plusOption.toUpperCase().equals("NO")) {
							
							//추가해주는 기능
							break;
						}
					}
				}	
			} catch(Exception e) {
				System.out.println("입력을 확인하세요!");
			}
		}
	}	

}
