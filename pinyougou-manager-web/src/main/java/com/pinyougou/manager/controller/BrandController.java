package com.pinyougou.manager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
/**
 * 品牌控制层
 * @author Administrator
 *
 */

import entity.PageResult;
import entity.ResultMsg;
@RestController
@RequestMapping("/brand")
public class BrandController {

	@Reference
	private BrandService brandService;
	
	/**
	 * 查询所有品牌信息
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbBrand> findAll(){
		return brandService.findAll();
	}
	
	/**
	 * 查询所有品牌信息-分页
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(Integer pageNum,Integer pageSize) {
		return brandService.findPage(pageNum, pageSize);
	}
	
	/**
	 * 添加品牌信息
	 * @param brand
	 */
	@RequestMapping("/add")
	public ResultMsg add(@RequestBody TbBrand brand) {
		try {
			brandService.add(brand);
			return new ResultMsg(true, "添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(false, "添加失败!");
		}
	}
	
	/**
	 * 根据id查询品牌信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public TbBrand findOne(Long id) {
		return brandService.findOne(id);
	}
	
	/**
	 * 根据id修改信息
	 * @param brand
	 */
	@RequestMapping("/update")
	public ResultMsg update(@RequestBody TbBrand brand) {
		try {
			brandService.update(brand);
			return new ResultMsg(true, "修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(false, "修改失败!");
		}
	}
	
	/**
	 * 根据id数组删除品牌信息
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public ResultMsg delete(Long[] ids) {
		try {
			brandService.delete(ids);
			return new ResultMsg(true, "删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(false, "删除失败!");
		}
	}
	
	/**
	 * 模糊查询-分页
	 * @param brand
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbBrand brand,Integer pageNum,Integer pageSize) {
		return brandService.findPage(brand, pageNum, pageSize);
	}
	
	
	
	
}
