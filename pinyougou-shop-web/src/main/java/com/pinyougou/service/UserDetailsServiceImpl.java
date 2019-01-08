package com.pinyougou.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	private SellerService sellerService;
	
	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		
		//先根据用户名查找数据库中该用户名对应的角色信息
		TbSeller seller = sellerService.findOne(username);
		
		//为用户添加角色
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
		//如果有多个角色,则需要遍历角色集合,为该用户赋予所拥有的角色
		
		if(seller!=null) {
			//判断状态是否为1,如果不为1,登录失败
			if("1".equals(seller.getStatus())) {
				return new User(username, seller.getPassword(), authorities);
			}
			return null;
		}
		return null;
	}

}
