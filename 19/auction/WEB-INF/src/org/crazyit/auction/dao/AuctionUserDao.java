package org.crazyit.auction.dao;

import java.util.List;

import org.crazyit.common.dao.*;

import org.crazyit.auction.domain.*;
import org.crazyit.auction.business.*;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public interface AuctionUserDao extends BaseDao<AuctionUser>
{
	/**
	 * 根据用户名，密码查找用户
	 * @param username 查询所需的用户名
	 * @param pass 查询所需的密码
	 * @return 指定用户名、密码对应的用户
	 */
	AuctionUser findUserByNameAndPass(String username , String pass);
}