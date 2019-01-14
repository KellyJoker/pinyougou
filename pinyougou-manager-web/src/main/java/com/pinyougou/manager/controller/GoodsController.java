package com.pinyougou.manager.controller;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojogroup.Goods;
import com.pinyougou.sellergoods.service.GoodsService;

import entity.PageResult;
import entity.ResultMsg;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

	@Reference
	private GoodsService goodsService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbGoods> findAll(){			
		return goodsService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return goodsService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param goods
	 * @return
	 */
	@RequestMapping("/add")
	public ResultMsg add(@RequestBody Goods goods){
		//获取商家id
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		goods.getGoods().setSellerId(name); 
		try {
			goodsService.add(goods);
			return new ResultMsg(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param goods
	 * @return
	 */
	@RequestMapping("/update")
	public ResultMsg update(@RequestBody Goods goods){
		//校验是否是当前商家的 id
		Goods goods2 = goodsService.findOne(goods.getGoods().getId());
		//获取当前登录的商家 ID
		String seller = SecurityContextHolder.getContext().getAuthentication().getName();
		if (!goods2.getGoods().getSellerId().equals(seller) || !goods.getGoods().getSellerId().equals(seller)) {
			return new ResultMsg(false, "非法操作");
		}
		
		try {
			goodsService.update(goods);
			return new ResultMsg(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public Goods findOne(Long id){
		return goodsService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public ResultMsg delete(Long [] ids){
		try {
			goodsService.delete(ids);
			return new ResultMsg(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param brand
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbGoods goods, int page, int rows  ){
		return goodsService.findPage(goods, page, rows);		
	}
	
	@RequestMapping("/updateStatus")
	public ResultMsg updateStatus(Long[] ids, String status){
		try {
			goodsService.updateStatus(ids, status);
			return new ResultMsg(true, "成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(false, "失败");
		}
	}
	
}
