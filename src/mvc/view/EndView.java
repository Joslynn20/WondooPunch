package mvc.view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import mvc.dto.OrderLine;
import mvc.dto.Orders;
import mvc.dto.Product;

public class EndView {
  /**
   * 상품 전체 출력
   * */
	/*
	public static void printGoodsList(List<Goods> list) {
		System.out.println("-----상품 "+ list.size() +"개 -------------");
		for(Goods goods : list) {
			System.out.println(goods);
		}
		
		System.out.println();
	}
	*/	
	
	public static void printMessage(String message) {
		System.out.println(message);
	}
	
	/**
	 * 장바구니 보기
	 * */
	
	public static void printViewCart(String userId , Map<Product,Integer> cart) {
		System.out.println("장바구니내용....");
		
		for(Product product: cart.keySet()) {
			/*
			   A01 : 새우깡 : 1500	3
			   A03 : 콘칩	: 2000	2
			   A05 : 감자깡 : 1500	1 */
			
			String productCode = product.getProductCode();//상품번호
			String productName = product.getProductName();//상품이름
			int productPrice = product.getProductPrice();//상품가격
			
			int quantity = cart.get(product);//MAP에서 KEY에 해당하는 VALUE, 즉 수량
			System.out.println(productCode+" : "+productName+" : "+productPrice+" \t "+quantity);
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("1.결제하기  |  9.되돌아가기");
		switch(Integer.parseInt(sc.nextLine())) {
		case 1:
			MenuView.printPay();
			 //장바구니비우기
			 //SessionSet ss = SessionSet.getInstance();
			 //Session session = ss.get(userId);
			 //session.removeAttribute("cart");
			break;
			
		case 9:
			break;
		}
		
		//System.out.println(userId);
	
	}
	
	
	/**
	 * 주문 상세보기
	 * */
	/*
	//list get,set이 없는데 만들어주세요...
	public static void printOrderByUserId(List<OrderLine> orderList) {
	   for(Orders order : orderList) {
		   System.out.println(order.getOrderNo() +" | " + order.getOrderTotalPrice() +" | " + order.getOrderTotalQty()+ " | " + order.getOrderDate());
		   for(OrderLine orderLine : order.) {
			   System.out.println("  ▶ "+orderLine);
		   }
		   System.out.println();
	   }
		
	}
	*/
}












