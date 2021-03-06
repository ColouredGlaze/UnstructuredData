package com.lmt.data.unstructured.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmt.data.unstructured.entity.DigitalDictionary;
import com.lmt.data.unstructured.entity.search.DigitalDictionarySearch;
import com.lmt.data.unstructured.service.DigitalDictionaryService;
import com.lmt.data.unstructured.util.ResultData;

/**
 * @author MT-Lin
 * @date 2018/1/3 9:11
 */
@RestController
@RequestMapping("/DigitalDictionaryApi")
@SuppressWarnings("rawtypes")
public class DigitalDictionaryApi {

	@Autowired
	private DigitalDictionaryService digitalDictionaryService;

	/**
	 *
	 * @apiNote 保存数据字典
	 * @param digitalDictionary
	 *            保存的数据字典
	 * @return Map
	 */
	@RequestMapping("/save")
	public Map save(@RequestBody DigitalDictionary digitalDictionary) {
		return this.digitalDictionaryService.save(digitalDictionary);
	}

	@RequestMapping("/findOneById")
	public Map findOneById(@RequestBody DigitalDictionarySearch digitalDictionarySearch) {
		String id = digitalDictionarySearch.getId();
		if (null == id) {
			return ResultData.newError("传入的数据字典ID为空");
		}
		return this.digitalDictionaryService.findOneById(id);
	}

	@RequestMapping("/update")
	public Map update(@RequestBody DigitalDictionary digitalDictionary) {
		String id = digitalDictionary.getId();
		if (null == id) {
			return ResultData.newError("传入的数据字典ID为空");
		}
		return this.digitalDictionaryService.update(digitalDictionary);
	}

	@RequestMapping("/search")
	public Map search(@RequestBody DigitalDictionarySearch digitalDictionarySearch) {
		return this.digitalDictionaryService.search(digitalDictionarySearch);
	}

	@RequestMapping("/getChildrenForSelect")
	public Map getChildrenForSelect(@RequestBody DigitalDictionarySearch digitalDictionarySearch) {
		return this.digitalDictionaryService.getChildrenForSelect(digitalDictionarySearch.getParentCode());
	}

	@RequestMapping("/getParentCodeTree")
	public Map findChildrenForTree() {
		return this.digitalDictionaryService.getParentCodeTree();
	}

	@RequestMapping("/delete")
	public Map delete(@RequestBody List<DigitalDictionary> digitalDictionaries) {
		return this.digitalDictionaryService.delete(digitalDictionaries);
	}

}
