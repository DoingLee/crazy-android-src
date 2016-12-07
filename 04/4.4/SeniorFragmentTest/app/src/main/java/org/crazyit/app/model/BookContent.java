package org.crazyit.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookContent
{
	// 定义一个内部类，作为系统的业务对象
	public static class Book
	{
		public Integer id;
		public String title;
		public String desc;
		public Book(Integer id, String title, String desc)
		{
			this.id = id;
			this.title = title;
			this.desc = desc;
		}
		@Override
		public String toString()
		{
			return title;
		}
	}
	// 使用List集合记录系统所包含的Book对象
	public static List<Book> ITEMS = new ArrayList<Book>();
	// 使用Map集合记录系统所包含的Book对象
	public static Map<Integer, Book> ITEM_MAP
			= new HashMap<Integer, Book>();
	static
	{
		// 使用静态初始化代码，将Book对象添加到List集合、Map集合中
		addItem(new Book(1, "疯狂Java讲义"
				, "一本全面、深入的Java学习图书，已被多家高校选做教材。"));
		addItem(new Book(2, "疯狂Android讲义"
				, "Android学习者的首选图书，常年占据京东、当当、 "
				+ "亚马逊3大网站Android销量排行榜的榜首"));
		addItem(new Book(3, "轻量级Java EE企业应用实战"
				, "全面介绍Java EE开发的Struts 2、Spring 3、Hibernate 4框架"));
	}
	private static void addItem(Book book)
	{
		ITEMS.add(book);
		ITEM_MAP.put(book.id, book);
	}
}
