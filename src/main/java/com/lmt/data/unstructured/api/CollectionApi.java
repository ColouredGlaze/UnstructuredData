package com.lmt.data.unstructured.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmt.data.unstructured.entity.Collection;
import com.lmt.data.unstructured.entity.search.CollectionSearch;
import com.lmt.data.unstructured.service.CollectionService;
import com.lmt.data.unstructured.service.ResourceEsService;
import com.lmt.data.unstructured.util.CheckResult;
import com.lmt.data.unstructured.util.RedisCache;
import com.lmt.data.unstructured.util.UdConstant;

/**
 * @author MT-Lin
 * @date 2018/1/25 9:42
 */
@RestController
@RequestMapping("/CollectionApi")
@SuppressWarnings("rawtypes")
public class CollectionApi {

	@Autowired
	private CollectionService collectionService;

	@Autowired
	private ResourceEsService resourceEsService;

	@Autowired
	private RedisCache redisCache;

	@RequestMapping("/delete")
	public Map delete(@RequestBody List<Collection> collections) {
		return this.collectionService.delete(collections);
	}

	@RequestMapping("/update")
	public Map update(@RequestBody Collection collection) {
		return this.collectionService.update(collection);
	}

	@RequestMapping("/search")
	public Map search(@RequestBody CollectionSearch collectionSearch) {
		collectionSearch.setCreator(redisCache.getUserId(collectionSearch));
		return this.collectionService.search(collectionSearch);
	}

	@RequestMapping("/isCollected")
	public Map isCollected(@RequestBody CollectionSearch collectionSearch) {
		collectionSearch.setCreator(redisCache.getUserId(collectionSearch));
		return this.collectionService.isCollected(collectionSearch);
	}

	@RequestMapping("/collectResource")
	public Map collectResource(@RequestBody Collection collection) {
		collection.setCreator(redisCache.getUserId(collection));
		Map result = this.collectionService.save(collection);
		if (CheckResult.isOK(result)) {
			this.resourceEsService.updateCollectionNum(collection.getObjId(), UdConstant.COLLECTION_OPERATION_ADD);
		}
		return result;
	}

	@RequestMapping("/cancelCollectResource")
	public Map cancelCollectResource(@RequestBody Collection collection) {
		collection.setCreator(redisCache.getUserId(collection));
		Map result = this.collectionService.delete(collection);
		if (CheckResult.isOK(result)) {
			this.resourceEsService.updateCollectionNum(collection.getObjId(), UdConstant.COLLECTION_OPERATION_CANCEL);
		}
		return result;
	}
}
