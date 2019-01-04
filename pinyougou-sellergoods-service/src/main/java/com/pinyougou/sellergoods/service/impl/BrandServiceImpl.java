package com.pinyougou.sellergoods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;

import entity.PageResult;
@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	private TbBrandMapper brandMapper;

	/**
	 * 查询所有品牌信息
	 */
	@Override
	public List<TbBrand> findAll() {
		return brandMapper.selectByExample(null);
	}

	/**
	 * 查询所有品牌信息-分页
	 */
	@Override
	public PageResult findPage(Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(null);
		
		return new PageResult(page.getTotal(),page.getResult());
	}

	/**
	 * 添加品牌信息
	 */
	@Override
	public void add(TbBrand brand) {
		brandMapper.insert(brand);
	}

	/**
	 * 根据id查询品牌信息
	 */
	@Override
	public TbBrand findOne(Long id) {
		return brandMapper.selectByPrimaryKey(id);
	}

	/**
	 * 更新信息
	 */
	@Override
	public void update(TbBrand brand) {
		brandMapper.updateByPrimaryKey(brand);
	}

	/**
	 * 根据id数组删除品牌信息
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids) {
			brandMapper.deleteByPrimaryKey(id);
		}
	}

}
