package com.pinyougou.sellergoods.service;

import java.util.List;

import com.pinyougou.pojo.TbBrand;

import entity.PageResult;

/**
 * 品牌接口
 * @author Administrator
 *
 */
public interface BrandService {
	
	/**
	 * 查询所有品牌信息
	 * @return
	 */
	public List<TbBrand> findAll();
	
	/**
	 * 查询所有品牌信息-分页
	 * @return
	 */
	public PageResult findPage(Integer pageNum,Integer pageSize);
	
	/**
	 * 添加品牌信息
	 */
	public void add(TbBrand brand);
	
	/**
	 * 根据id查询品牌信息
	 * @return
	 */
	public TbBrand findOne(Long id);
	
	/**
	 * 更新品牌数据
	 * @param brand
	 */
	public void update(TbBrand brand);
	
	/**
	 * 根据id数组删除品牌信息
	 * @param ids
	 */
	public void delete(Long[] ids);
	
	/**
	 * 模糊查询-分页
	 * @param brand
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageResult findPage(TbBrand brand,Integer pageNum,Integer pageSize);
	

}
