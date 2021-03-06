package com.lmt.data.unstructured.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lmt.data.unstructured.entity.ResourceTemp;
import com.lmt.data.unstructured.entity.search.ResourceTempSearch;
import com.lmt.data.unstructured.repository.ResourceTempRepository;
import com.lmt.data.unstructured.service.ResourceTempService;
import com.lmt.data.unstructured.util.EntityManagerQuery;
import com.lmt.data.unstructured.util.ResultData;

/**
 * @author MT-Lin
 * @date 2018/1/9 21:09
 */
@Service("ResourceTempServiceImpl")
@SuppressWarnings({ "rawtypes" })
public class ResourceTempServiceImpl implements ResourceTempService {

	@Autowired
	private ResourceTempRepository resourceTempRepository;

	@Autowired
	private EntityManagerQuery entityManagerQuery;

	@Override
	public Map save(ResourceTemp resourceTemp) {
		ResourceTemp exist = this.resourceTempRepository.findByMd5(resourceTemp.getMd5());
		if (null != exist) {
			return ResultData.newError("该文件已存在");
		}
		this.resourceTempRepository.save(resourceTemp);
		if (null == resourceTemp.getId()) {
			return ResultData.newError("资源信息保存失败");
		}
		return ResultData.newOK("资源上传成功，请等待审核", resourceTemp.getId());
	}

	@Override
	public Map search(ResourceTempSearch resourceTempSearch) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT rt.id, rt.designation, rt.description, ");
		sql.append("rt.resource_size AS resourceSize, ");
		sql.append("rt.upload_time AS uploadTime, ");
		sql.append("rt.resource_id AS resourceId, ");
		sql.append("ui.user_name AS author, ");
		sql.append("(SELECT c.designation FROM classify AS c WHERE c.id = rt.classify_id) ");
		sql.append("AS classify, ");
		sql.append("(SELECT dd.designation FROM digital_dictionary AS dd WHERE dd.code = rt.resource_type) ");
		sql.append("AS resourceType, ");
		sql.append("(SELECT dd.designation FROM digital_dictionary AS dd WHERE dd.code = a.operation) ");
		sql.append("AS operation, ");
		sql.append("(SELECT dd.code FROM digital_dictionary AS dd WHERE dd.code = a.operation) ");
		sql.append("AS operationCode ");
		sql.append("FROM resource_temp AS rt, audit AS a, user_info AS ui ");
		sql.append("WHERE rt.id = a.obj_id ");
		sql.append("AND a.status = '005003' ");
		sql.append("AND ui.id = rt.author_id ");
		if (!StringUtils.isEmpty(resourceTempSearch.getKeyword())) {
			sql.append("AND (ui.user_name LIKE ? OR rt.designation LIKE ? OR rt.description LIKE ? ) ");
			resourceTempSearch.setParamsCount(3);
		}
		Map result = this.entityManagerQuery.paginationSearch(sql, resourceTempSearch);
		return ResultData.newOK("查询待审核资源成功", result);
	}

	@Override
	public ResourceTemp findOneById(String resourceTempId) {
		return this.resourceTempRepository.findOne(resourceTempId);
	}

	@Override
	public int getUploadNum(String authorId) {
		return this.resourceTempRepository.countByAuthorId(authorId);
	}
}
