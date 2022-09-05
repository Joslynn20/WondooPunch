package mvc.view;

import java.sql.SQLException;
import java.util.Scanner;

import mvc.controller.CouponController;
import mvc.controller.OptionController;
import mvc.dto.Coupon;
import mvc.dto.Option;
import mvc.exception.AddException;
import mvc.exception.NotFoundException;

public class MenuView {
	private static Scanner sc = new Scanner(System.in);

	public static void menu() {
		while (true) {
			// SessionSet ss = SessionSet.getInstance();
			// System.out.println(ss.getSet()); //[]

			MenuView.printMenu();
			System.out.print("입력 > ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				MenuView.printLoginType(); // 로그인 타입 선택
				System.out.print("입력 > ");
				int type = Integer.parseInt(sc.nextLine());

				switch (type) {
				case 1:
					MenuView.login();// 로그인
					break;
				case 2:
					MenuView.findId();// 아이디찾기
					break;
				case 3:
					MenuView.findPassword();// 비밀번호찾기
					break;
				case 9:
					MenuView.printMenu();
					System.exit(0);
				}
			case 2:
				// MenuView.register(); // 가입
				break;

			case 9:
				System.exit(0);
			}
		}

	}

	public static void printMenu() {
		System.out.println("==== WondooPunch ====");
		System.out.println(" 1. 로그인    |  2. 가입    |  9. 종료");
	}

	public static void printLoginType() {
		System.out.println("1. 개인회원 로그인  |  2. 관리자 로그인   |  9. 되돌아가기");
	}

	public static void printUserMenu(String userId) {
		while (true) {
			// SessionSet ss = SessionSet.getInstance();
			// System.out.println(ss.getSet()); //Set객체 [session]
			// System.out.println("-----" +userId+ " 로그인 중 -----");
			System.out.println(" 1. ID/PW 입력하기  |  2. 아이디 찾기  |   3. 비밀번호 찾기  |   9. 되돌아가기 ");
			System.out.print("입력 > ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				// 상품검색 기능
				break;

			case 2:
				// 주문하기 기능
				break;

			case 3:
				// 마이페이지
				break;

			case 9:
				// 되돌아가기
			}
		}

	}

	public static void printSubMenu() {
		System.out.println("1. 회원가입 정보 입력하기  |   9. 되돌아가기");
		System.out.print("입력 > ");
		// 기능 입력
	}

	public static void printCustomerMenu() {
		System.out.println("1.상품검색  |   2. 주문하기  |   3. 마이페이지  |   9. 로그아웃");
		System.out.print("입력 > ");
		// 기능으로 이동
	}

	public static void printProductSearch() {
		System.out.println("1.전체검색  |   2. 카테고리 검색  |   3. 상품명 검색  |   4. 키워드 검색  |   9. 되돌아가기");
		System.out.print("입력 > ");
		// 기능으로 이동
	}

	public static void printAllOrders(String userId) {
		System.out.println("1.주문하기  |   2. 퀵오더   |  3. 장바구니   |   9. 되돌아가기");
		System.out.print("입력 > ");

		MenuView.order(); // 1.주문하기
		MenuView.quickOrder(); // 2.퀵오더
		MenuView.basket(); // 3.장바구니

	}

	public static void printCart(String userId) {
		System.out.println("1.장바구니 조회하기  |  2. 장바구니 수정하기   |   3. 장바구니 비우기  |  9. 되돌아가기");
		System.out.print("입력 > ");

		// 1번기능
		System.out.println("1. 결제하기    |    2. 되돌아가기");

		// 2번기능
		System.out.print("수정할 장바구니 상품코드 입력 > ");
		String productCode = sc.nextLine();

		// 해당정보 장바구니 띄워주기 기능

		System.out.print("수정하실 상품 정보가 맞습니까? (Yes or No)");
		String answer = sc.nextLine();

		System.out.println("1. 수량 수정하기    |   2. 삭제하기");
		System.out.print("입력 > ");
		int no = Integer.parseInt(sc.nextLine());
		System.out.print("수정 수량 > ");
		int cartQty = Integer.parseInt(sc.nextLine());

	}

	public static void printPay(String userId) {
		while (true) {
			System.out.print("카드사 > ");
			String cardCompany = sc.nextLine();

			System.out.print("결제하실 카드번호 > ");
			String cardNo = sc.nextLine();

			System.out.print("카드 비밀번호 4자리 > ");
			int cardPw = Integer.parseInt(sc.nextLine());

			System.out.print("사용하실 쿠폰이 있습니까? Yes or No > ");
			String usingCoupons = sc.nextLine();
			if (usingCoupons.equals("Yes")) {
				System.out.print("쿠폰코드 입력 > ");
				String enterCouponCode = sc.nextLine();
				break;
			} else {
				// 최종 결제 내역 띄워주기
				System.out.print("결제하시겠습니까? Yes or No");
				String answer = sc.nextLine();
				if (answer.equals("Yes")) {
					System.out.println("결제가 완료되었습니다.");
				} else {
					System.out.println("결제가 취소되었습니다.");
				}
			}
			// 최종 결제 내역 띄워주기
			System.out.print("결제하시겠습니까? Yes or No");
			String answer = sc.nextLine();
			if (answer.equals("Yes")) {
				System.out.println("결제가 완료되었습니다.");
			} else {
				System.out.println("결제가 취소되었습니다.");
			}
		}

	}

	public void printMyPage(String userId) {
		System.out.println("1. 개인정보조회   |   2. 개인정보 변경   |  3. 주문 내역   |  4. 쿠폰 조회   |  9. 되돌아가기");
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			// 기능
			break;
		case 2:
			System.out.print("비밀번호 입력 > ");
			String userPw = sc.nextLine();

			System.out.println("변경하실 정보를 선택하세요.");
			System.out.println("1. 비밀번호  변경   |    2. 전화번호 변경    |    3. 회원 탈퇴");
			System.out.print("선택 > ");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1:
				// 비밀번호 변경
				break;
			case 2:
				// 전화번호 변경
				break;
			case 3:
				System.out.println("회원탈퇴를 진행하시겠습니까? (입력: yes or no)");
				System.out.print("입력 > ");
				String out = sc.nextLine();
				// 회원탈퇴기능
			}
		case 3:
			System.out.println("****** 총 ?? 건의 주문내역******");
			// 기능
			break;
		case 4:
			System.out.println("****** 총 ?? 건의 쿠폰내역******");
			// 기능
			break;
		}
	}

	public static void printAdminLogin() {
		System.out.println("-- 관리자 로그인 --");
		System.out.println("1. ID/PW 입력하기  |   9. 되돌아가기");
		System.out.print("입력 > ");
		// 기능으로 이동 (login 메소드를 사용할 수 있으면 그대로 활용)
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String userPw = sc.nextLine();
	}

	public static void printAdminMenu() {
		System.out.println("1. 상품 관리  |  2. 매출관리   |   3. 쿠폰관리   |   9. 로그아웃");
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			// 상품 관리단으로 이동
			break;
		case 2:
			// 매출 관리단으로 이동
			break;
		case 3:
			// 쿠폰 관리단으로 이동
			break;
		case 9:
			// MenuView.logout(String userId);//로그아웃
			return;
		}
	}

	public static void printProduct() throws SQLException, NotFoundException, AddException {
		System.out.println("1. 상품 목록  |  2. 상품 등록   |   3. 상품 수정  |   4. 상품 삭제  |  5. 옵션 관리  |  9. 되돌아가기");
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			// 상품 목록 리스트 출력
			break;
		case 2:
			// 상품정보기능
		case 3:
			System.out.print("수정할 상품코드 입력 > ");
			// 해당정보 띄워주기

			System.out.print("수정하실 상품 정보가 맞습니까? (Yes or No)");
			String modifyProduct = sc.nextLine();
			if (modifyProduct.equals("Yes")) {
				System.out.print("수정 상품명 > ");
				String productName = sc.nextLine();
				System.out.print("수정 가격 > ");
				int productPrice = Integer.parseInt(sc.nextLine());
				System.out.print("수정 상품설명 > ");
				String productDetail = sc.nextLine();
				System.out.print("수정 카테고리 > ");
				String category = sc.nextLine();
				System.out.println("상품정보를 수정했습니다.");
			} else {
				System.out.println("상품정보 수정을 취소합니다.");
			}
			break;

		case 4:
			System.out.print("삭제하실 상품 코드 입력 > ");
			// 해당 상품정보 띄워주기

			System.out.print("삭제하실 상품 정보가 맞습니까? (Yes or No)");
			String delete = sc.nextLine();
			if (delete.equals("Yes")) {
				// 삭제기능
				System.out.println("삭제되었습니다.");
			} else {
				System.out.println("삭제를 취소합니다.");
				// 되돌아가기 기능
			}
			break;

		case 5:
			MenuView.printOption();
			break;
		}

	}

	public static void printOption() throws SQLException, NotFoundException, AddException {
		System.out.println("1. 옵션 목록  |  2. 옵션 등록   |   3. 옵션 수정  |   4. 옵션 삭제  |  9. 되돌아가기");
		System.out.print("입력 > ");
		int option = Integer.parseInt(sc.nextLine());
		switch (option) {
		case 1:
			OptionController.optionSelect();
			break;

		case 2:
			// 옵션정보 등록기능
			System.out.println("옵션코드는?");
			String code = sc.nextLine();
			System.out.println("옵션이름은?");
			String name = sc.nextLine();
			System.out.println("옵션가격은?");
			int price = Integer.parseInt(sc.nextLine());
			System.out.println("카테고리는?");
			String ct = sc.nextLine();

			Option optionA = new Option(code, name, price, ct);
			OptionController.optionInsert(optionA);
			break;

		case 3:
			System.out.print("수정할 옵션코드 입력 > ");
			// 해당옵션정보 띄워주기
			String optionCode = sc.nextLine();
			OptionController.optionSelectByOptionCode(optionCode);

			System.out.print("수정하실 옵션 정보가 맞습니까? (Yes or No)");
			String modifyOption = sc.nextLine();
			if (modifyOption.equals("Yes")) {
				System.out.print("수정 옵션명 > ");
				String optionName = sc.nextLine();
				System.out.print("수정 옵션가격 > ");
				int optionPrice = Integer.parseInt(sc.nextLine());
				System.out.print("수정 카테고리 > ");
				String optionCategory = sc.nextLine();
				System.out.println("옵션정보를 수정했습니다.");
			} else {
				System.out.println("옵션정보 수정을 취소합니다.");
			}
			break;

		case 4:
			System.out.print("삭제하실 옵션 코드 입력 > ");
			// 해당 상품정보 띄워주기
			String optionCodeD = sc.nextLine();
			OptionController.optionSelectByOptionCode(optionCodeD);

			System.out.print("삭제하실 옵션 정보가 맞습니까? (Yes or No)");
			String deleteOption = sc.nextLine();
			if (deleteOption.equals("Yes")) {
				// 삭제기능
				OptionController.optionDelete(optionCodeD);

				System.out.println("삭제되었습니다.");
			} else {
				System.out.println("삭제를 취소합니다.");
				// 되돌아가기 기능
			}
			break;
		}
	}

	public static void printSales() {
		System.out.println("1. 매출 조회  |  2. 판매량 조회   |   3. 주문 조회   |   9. 되돌아가기");
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			// 매출 조회단 이동
			MenuView.printSalesCheck();
			break;
		case 2:
			// 판매량조회단 이동
			MenuView.printSalesVolume();
			break;
		case 3:
			System.out.println("*** 주문 ??건에 대한 내역***");
			// 주문조회 기능
		}
	}

	/**
	 * 매출조회단 이동 메소드
	 */
	public static void printSalesCheck() {
		System.out.println("1. 일별 매출 조회  |  2. 누적 매출 조회   |   9. 되돌아가기");
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			// 1: 일 매출 총액, 전체 주문 완료 건수, 전체 주문 수량
			break;
		case 2:
			// 2: 누적 매출 총액, 누적 주문 완료 건수, 누적 주문 수량
			break;
		}
	}

	/**
	 * 판매량조회단 이동 메소드
	 */
	public static void printSalesVolume() {
		System.out.println("1. 일별 판매량 조회    2. 누적 판매량 조회   9. 되돌아가기");
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			// 1: 일별 판매량: 제품별 일별 판매 수량, 제품별 일별 판매 순위 (5위)
			break;
		case 2:
			// 2: 누적 판매량: 제품별 누적 판매 수량, 제품별 누적 판매 순위(5위)
			break;
		}
	}

	public static void printCoupon() throws SQLException, NotFoundException, AddException {
		System.out.println("1. 쿠폰 목록  |  2. 쿠폰 등록   |  3. 쿠폰 삭제  |  9. 되돌아가기");
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			// 쿠폰 목록 리스트 출력
			CouponController.couponSelect();

			break;
		case 2:
			// 쿠폰등록기능
			System.out.println("쿠폰번호는?");
			String code = sc.nextLine();
			System.out.print("쿠폰명");
			String name = sc.nextLine();
			System.out.print("할인율");
			int dc = Integer.parseInt(sc.nextLine());

			Coupon coupon = new Coupon(code, name, dc, null);
			CouponController.couponInsert(coupon);
			break;
		case 3: // 쿠폰삭제
			System.out.print("삭제하실 쿠폰 코드 입력 > ");
			String couponCode = sc.nextLine();
			// 해당 쿠폰정보 띄워주기 기능
			// CouponController에서 출력

			System.out.print("삭제하실 쿠폰 정보가 맞습니까? (Yes or No)");
			String answer = sc.nextLine();
			if (answer.equals("Yes")) {
				// 쿠폰삭제기능
				CouponController.couponDelete(couponCode);
				System.out.println("쿠폰삭제완료");
			} else {
				System.out.println("쿠폰삭제취소");
			}
		}

	}

	/**
	 * 로그인 메뉴
	 */
	public static void login() {
		System.out.print("아이디 : ");
		String userId = sc.nextLine();

		System.out.print("비밀번호 : ");
		String userPw = sc.nextLine();

		// CustomerController.login(userId, userPw);
	}

	/**
	 * 아이디 찾기
	 */
	public static void findId() {
		System.out.print("회원가입 시 입력하신 성함 > ");
		String userName = sc.nextLine();
		System.out.print("회원가입 시 입력하신 휴대폰 번호 > ");
		String userPhoneNo = sc.nextLine();
	}

	/**
	 * 비밀번호 찾기
	 */
	public static void findPassword() {
		System.out.print("아이디 > ");
		String userId = sc.nextLine();
		System.out.print("회원가입 시 입력하신 휴대폰 번호 > ");
		String userPhoneNo = sc.nextLine();
	}

	/**
	 * 회원가입 정보 입력하기
	 */
	public static void userInput() {
		System.out.print("입력 > ");
		int no = Integer.parseInt(sc.nextLine());
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
	}

	/**
	 * 1.주문하기 2. 퀵오더 3. 장바구니 9. 되돌아가기 기능 중 주문하기
	 */
	public static void order() {
		System.out.print("상품명 or 코드 > ");// 코드를 작성해보긴했음(참고용 아래에 있음)
		System.out.print("수량 > ");

		System.out.print("추가 옵션 선택 > yes or no? ");
		String menu = sc.nextLine();
		System.out.println("1. 샷 추가  |  2. 휘핑 크림 추가  |  3. 시럽추가");
		System.out.print("입력 > ");
		int option = Integer.parseInt(sc.nextLine());

		System.out.println("1. 상품 추가하기  |  2. 결제하기  |  3. 장바구니  |  4. 주문취소");
		System.out.print("입력 > ");
		int finish = Integer.parseInt(sc.nextLine());
	}

	/**
	 * 1.주문하기 2. 퀵오더 3. 장바구니 9. 되돌아가기 기능 중 퀵오더
	 */
	public static void quickOrder() {
		System.out.println("**** 최근 주문 내역 ****");
		// 최근주문내역 보여주는 기능
		System.out.print("주문선택 > ");
		MenuView.printPay();
	}

	/**
	 * 1.주문하기 2. 퀵오더 3. 장바구니 9. 되돌아가기 기능 중 장바구니
	 */
	public static void basket() {
		// 장바구니로 이동
	}

	/**
	 * 로그아웃
	 */
	public static void logout(String userId) {
		// Session session = new Session(userId);

		// SessionSet ss = SessionSet.getInstance();
		// ss.remove(session);
	}

	// 아이디찾기, 비밀번호찾기 기능!!!!
	// String word = sc.nextLine();
	// select * from customer where userId="%"+word+"%";

	/**
	 * 장바구니 담기
	 */
	public static void putCart(String userId) {
		System.out.println("--장바구니 담기 작업 --");
		System.out.print("상품번호 : ");
		String goodsId = sc.nextLine();
		System.out.print("수량 : ");
		int qty = Integer.parseInt(sc.nextLine());

		// CartController.putCart(id,goodsId,qty);

	}

	/**
	 * 장바구니 내역조회
	 */
	public static void viewCart(String userId) {
		// CartController.viewCart(id);

		System.out.println("1. 결제하기    |    2. 되돌아가기");
		System.out.print("입력 > ");
		int menu = Integer.parseInt(sc.nextLine());
		if (menu == 1) {
			MenuView.printPay(userId);
		} else {
			MenuView.printCustomerMenu();// 고객 로그인 이후 메뉴단으로 되돌아감
		}

	}
}
