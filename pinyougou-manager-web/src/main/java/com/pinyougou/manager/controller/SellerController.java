package com.pinyougou.manager.controller;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;

import entity.PageResult;
import entity.ResultMsg;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

	@Reference
	private SellerService sellerService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbSeller> findAll(){			
		return sellerService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return sellerService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param seller
	 * @return
	 */
	@RequestMapping("/add")
	public ResultMsg add(@RequestBody TbSeller seller){
		try {
			sellerService.add(seller);
			return new ResultMsg(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param seller
	 * @return
	 */
	@RequestMapping("/update")
	public ResultMsg update(@RequestBody TbSeller seller){
		try {
			sellerService.update(seller);
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
	public TbSeller findOne(String id){
		return sellerService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public ResultMsg delete(String [] ids){
		try {
			sellerService.delete(ids);
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
	public PageResult search(@RequestBody TbSeller seller, int page, int rows  ){
		return sellerService.findPage(seller, page, rows);		
	}
	
	//更改商家状态
	@RequestMapping("/updateStatus")
	public ResultMsg updateStatus(String sellerId,String status) {
		try {
			sellerService.updateStatus(sellerId, status);
			return new ResultMsg(true, "成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(false, "失败");
		}
		
	}
	
}
