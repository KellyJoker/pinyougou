package com.pinyougou.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.pojo.TbBrandExample.Criteria;
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

	/**
	 * 模糊查询-分页
	 */
	@Override
	public PageResult findPage(TbBrand brand, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		TbBrandExample example = new TbBrandExample();
		Criteria criteria = example.createCriteria();
		
		if(brand!=null) {
			if(brand.getName()!=null && !"".equals(brand.getName())) {
				criteria.andNameLike("%"+brand.getName()+"%");
			}
			
			if(brand.getFirstChar()!=null && !"".equals(brand.getFirstChar())) {
				criteria.andFirstCharEqualTo(brand.getFirstChar());
			}
		}
		
		Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(example);
		
		
		return new PageResult(page.getTotal(),page.getResult());
	}

	@Override
	public List<Map> selectOptionList() {
		return brandMapper.selectOptionList();
	}

}
