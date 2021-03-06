package com.lmt.data.unstructured.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmt.data.unstructured.entity.search.ResourceUploadSearch;
import com.lmt.data.unstructured.service.ResourceUploadService;
import com.lmt.data.unstructured.util.RedisCache;

/**
 * @author MT-Lin
 * @date 2018/2/1 13:59
 */
@RestController
@RequestMapping("/ResourceUploadApi")
@SuppressWarnings("rawtypes")
public class ResourceUploadApi {

	@Autowired
	private ResourceUploadService resourceUploadService;

	@Autowired
	private RedisCache redisCache;

	@RequestMapping("/search")
	public Map search(@RequestBody ResourceUploadSearch resourceUploadSearch) {
		String userId = redisCache.getUserId(resourceUploadSearch);
		resourceUploadSearch.setUserId(userId);
		return this.resourceUploadService.search(resourceUploadSearch);
	}
}
