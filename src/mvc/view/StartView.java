package mvc.view;

import java.sql.SQLException;

import mvc.exception.NotFoundException;
import mvc.view.MenuView;

public class StartView {
	public static void main(String[] args) throws SQLException, NotFoundException {
		 MenuView.menu();
		 System.out.println("-----------");
	}
}

