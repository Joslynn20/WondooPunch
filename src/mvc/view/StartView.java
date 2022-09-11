package mvc.view;

import java.sql.SQLException;

import mvc.exception.NotFoundException;
public class StartView {
	public static void main(String[] args) throws SQLException, NotFoundException {
		 NewMenuView.showDefaultMenu();
		 System.out.println("-----------");
	}
}

