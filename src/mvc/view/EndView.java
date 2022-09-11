package mvc.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mvc.dto.Cart;
import mvc.dto.Coupon;
import mvc.dto.Customer;
import mvc.dto.DetailOption;
import mvc.dto.IssuedCoupon;
import mvc.dto.Option;
import mvc.dto.OrderLine;
import mvc.dto.Orders;
import mvc.dto.Product;

public class EndView {
	static Scanner sc = new Scanner(System.in);
	/**
	 * 성공메시지 출력
	 */

	public static void printMessage(String message) {
		System.out.println(message);
	}

	/**
	 * 고객 정보 조회
	 */
	public static void printCustomer(Customer customer) {
		System.out.println("\n------------" + customer.getUserName() + "님의 회원정보------------");
		System.out.println(customer);
	}


	/**
	 * 주문내역 조회
	 */
	public static void printOrder(List<Orders> orderList) {
		int count = 1;
		System.out.println("--------총 " + orderList.size() + "건의 주문 내역--------\n");
		for (Orders order : orderList) {
			System.out.print(count++ +".");
			System.out.println(order);
			for (OrderLine orderLine : order.getOrderLinelist()) {
				System.out.println("\n  ▶ " + orderLine);		
				for(DetailOption detailOption : orderLine.getList()){
					System.out.println("  ▶ " + detailOption);
					
				}
			}
			System.out.println("\n");
		}

	}
	
	/**
	 * 퀵오더
	 */
	public static void QuickOrder(List<Orders> orderList) {
		printOrder(orderList);
		System.out.print("주문선택 > ");
		int choice = Integer.parseInt(sc.nextLine());
		Orders order = orderList.get(choice-1);
		NewMenuView.printPay(order); // 결제하기 창으로 이동

	}
	
	/**
	 * 관리자메뉴 - 쿠폰 전체 목록 조회
	 * 
	 * @param list
	 */
	public static void printCouponList(List<Coupon> list) {
		System.out.println("---쿠폰목록 리스트---");
		for (Coupon coupon : list) {
			System.out.println(coupon);
		}
		System.out.println("----쿠폰 총: " + list.size() + "장----");

	}

	/**
	 * 관리자메뉴 - 쿠폰 코드에 대한 쿠폰 정보 검색
	 * 
	 * @param coupon
	 */
	public static void printCouponListByCode(Coupon coupon) {
		System.out.println("---쿠폰목록 리스트---");
		
			System.out.println(coupon);	
	}	

	/**
	 * 고객 - 쿠폰조회
	 * 
	 * @param list
	 */

	public static void printIssuedCouponList(List<IssuedCoupon> list) {
		System.out.println("---쿠폰목록 리스트---");
		for (IssuedCoupon issuedcoupon : list) {
			System.out.println(issuedcoupon);
		}
		System.out.println("----쿠폰 총: " + list.size() + "장----");

	}

	/**
	 * 관리자 - 옵션전체검색
	 * 
	 * @param list
	 */

	public static void  printOptionList(List<Option> list) {
		System.out.println("---옵션목록 리스트---");
		for (Option option : list) {
			System.out.println(option);
		}
	}

	/**
	 * 관리자 - 옵션코드에 대한 옵션정보검색
	 * 
	 * @param option2
	 */

	public static void printOptionListByCode(Option option) {
		System.out.println("---옵션목록 리스트---");
		
			System.out.println(option);
		}
	
	
	
	/**
	 * 고객-상품검색 관련 출력
	 * */
	public static void printProductList(List<Product> list ) {
		
		  System.out.println( "******************상품의 갯수:"+list.size()+"************************"); 
	      System.out.println("카테고리       상품코드       상품이름        상품가격      상품설명");
	        for(Product  p : list ) {
	         String categoryName = null; 	
	        if(p.getCategoryCode().equals("B")) {  	
	        	categoryName="음료";
	        }else {
	        	categoryName="디저트";
	        }	  
	          System.out.printf("%5s|%6s|%-11s\t|%-6d|%-15S",categoryName,p.getProductCode(),p.getProductName(),p.getProductPrice(),p.getProductDetail()); 
	    	  System.out.println(); 
	     }
		
	}
	
	public static void printProduct(Product product ) {
		
		
		System.out.println(product);
		
	}
	
	

	   public static void printSalesRate(List<String> list  ){
			System.out.println("*******************상품 :"+list.size()+"개에 대한 판매량 입니다*****************"); 
			for( String  d  :list  ) {
			   System.out.println(d); 
			  
			}
		
		}
		
		
		public static void printSalesRank(List<Product> list) {
			
			
			for( Product p  :list  ) {
				   System.out.println(p); 
				  
				}
			
			
		}
		
		
		public static void printAllOrders(List<Orders>  list ) {
			 for(  Orders  order   :   list ) {	 
		      System.out.println(order.getOrderNo()+". ID:"+order.getUserId()+" 주문 시간: "+order.getOrderDate()+"  구매 총액:"+order.getOrderTotalPrice()+"  사용 쿠폰코드:"+order.getCouponCode() ) ;  
		      System.out.println("*******주문번호:"+order.getOrderNo()+"에 대한 상세내역********");  
		       List<OrderLine> orderLineList  = order.getOrderLinelist();    		   
		       for( OrderLine ol  : orderLineList) {
		            
		    	   System.out.println("상품 코드:"+ol.getProductCode()+" 상품수량:"+ol.getOrderQty()+" 가격:"+ol.getOrderPrice()); 
		      
		    	   for( DetailOption  detailOption :ol.getList()) {
		    		   
		    	    System.out.print(" 옵션 코드:"+detailOption.getOptionCode());
		    	    System.out.print(" 옵션 수량:"+detailOption.getDetailOtionQty()); 
		    	    System.out.print(" 옵션 가격:"+detailOption.getDetailOptionPrice());
		            System.out.println();
		            	   
		    	   }
		    	  
		    	System.out.println();  
		    	       
		    	  
		    	  
		      }
			 }
		}
		 
		public static void printCartList(List <Cart> cartList) { 
			int count = 1;
			String userId = null;
			List<OrderLine> list = new ArrayList<OrderLine>();
			System.out.println("--------총 " + cartList.size() + "건의 장바구니 내역--------\n");

				for (Cart cart : cartList) {
					System.out.print(count++ +".");
					System.out.println(cart);		
					for(DetailOption detailOption : cart.getList()){
						System.out.println("  ▶ " + detailOption);
					}
					userId = cart.getUserId();
					OrderLine orderLine= new OrderLine(cart.getProductCode(), cart.getCartQty());
					orderLine.setList(cart.getList());
					list.add(orderLine);
				}
				System.out.println("\n");


		 System.out.println("1. 결제하기    |    9. 되돌아가기");
			try {
				System.out.print("입력 > ");
				int choice = Integer.parseInt(sc.nextLine());
				if (choice == 1) {
					Orders newOrder = new Orders(userId);
					newOrder.setOrderLinelist(list);
					NewMenuView.printPay(newOrder);
				} else if (choice == 9) {
				
				}
			} catch (NumberFormatException e) {
				FailView.errorMessage("1, 9 중에서 선택하세요.");
			}
			
		}
	
		
}
