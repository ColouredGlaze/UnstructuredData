package com.lmt.data.unstructured.api;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lmt.data.unstructured.entity.Resource;
import com.lmt.data.unstructured.entity.ResourceTemp;
import com.lmt.data.unstructured.service.ResourceEsService;
import com.lmt.data.unstructured.service.ResourceService;
import com.lmt.data.unstructured.service.ResourceTempService;
import com.lmt.data.unstructured.util.FileUtil;
import com.lmt.data.unstructured.util.UdConstant;

/**
 * @author MT-Lin
 * @date 2018/1/12 9:52
 */
@RestController
@RequestMapping("/FileApi")
public class FileApi {

	private Logger logger = LoggerFactory.getLogger(FileApi.class);

	@Autowired
	private FileUtil fileUtil;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private ResourceTempService resourceTempService;

	@Autowired
	private ResourceEsService resourceEsService;

	@RequestMapping("/download")
	public void download(@RequestParam String resourceId, @RequestParam String esId,
			@RequestParam String resourceTempId, HttpServletResponse response) {
		String resourceFileName, resourceName;
		if (!StringUtils.isEmpty(esId)) {
			// 下载审核通过的资源
			Resource resource = this.resourceService.findOneById(resourceId);
			if (null == resource) {
				logger.error("该资源[ID={}]信息不存在", resourceId);
				return;
			}
			this.resourceEsService.updateDownloadNum(esId);
			resourceFileName = resource.getResourceFileName();
			resourceName = resource.getDesignation();
		} else {
			// 下载待审核资源
			ResourceTemp resourceTemp = this.resourceTempService.findOneById(resourceTempId);
			if (null == resourceTemp) {
				logger.error("该待审核资源[ID={}]信息不存在", resourceTempId);
				return;
			}
			resourceName = resourceTemp.getDesignation();
			resourceFileName = resourceTempId + resourceName.substring(resourceName.lastIndexOf("."));
		}
		File downloadFile = new File(fileUtil.getFullFilePath(resourceFileName));
		if (!downloadFile.exists()) {
			logger.error("文件 [" + resourceFileName + "] 不存在");
			return;
		}
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			// 设置头部
			response.setContentType("application/octet-stream");
			response.setHeader("content-disposition",
					"attachment;filename=" + URLEncoder.encode(resourceName, "UTF-8"));
			os = response.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(downloadFile));
			int bytesReader;
			byte[] buffer = new byte[UdConstant.FILE_READ_BUFFER_SIZE];
			while ((bytesReader = bis.read(buffer)) != -1) {
				os.write(buffer, 0, bytesReader);
			}
		} catch (IOException e) {
			logger.error("文件下载出现异常");
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("文件输出流关闭出现异常（主机中的软件中止了一个已建立的连接。）");
				}
			}
			if (null != bis) {
				try {
					bis.close();
				} catch (IOException e) {
					logger.error("输入流关闭出现异常");
				}
			}
		}
	}
}
