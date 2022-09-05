package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.CoffeeOption;
import mvc.dto.DesertOption;
import mvc.dto.Option;
import mvc.exception.AddException;
import mvc.exception.ModifyException;
import mvc.exception.NotFoundException;

public interface OptionService {
	
	/**
	 * 옵션전체검색
	 * */
	List<Option> optionSelect() throws SQLException, NotFoundException;
	
	
	/**
	 * 옵션번호에 해당하는 옵션검색
	 * */
	Option optionSelectByOptionCode(String optionCode) throws NotFoundException, SQLException;
	
	/**
	 * 옵션추가
	 * */
	void optinInsert(Option option) throws SQLException, AddException;
	
	
	/**
	 * 옵션 수정
	 * */
	void optionUpdate(Option option) throws SQLException, ModifyException;
	
	
	/**
	 * 옵션삭제
	 * */
	void optionDelete(String optionCode) throws SQLException, NotFoundException;
	
	/**
	 * 고객 주문에서 커피옵션추가
	 * */
	void orderCoffeeOption(CoffeeOption coffeeoption) throws SQLException, AddException;
	
	/**
	 * 고객 주문에서 디저트옵션추가
	 * */
	void orderDesertOption(DesertOption desertoption) throws SQLException, AddException;
	
}// OptionService end
