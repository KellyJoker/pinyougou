package com.pinyougou.shop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import entity.ResultMsg;
import util.FastDFSClient;

@RestController
@RequestMapping("/uploadController")
public class UploadController {
	
	@Value("${FILE_SERVER_URL}")
	private String FILE_SERVER_URL;

	@RequestMapping("/upload")
	public ResultMsg upload(MultipartFile files) {
		//获取文件的扩展名
		String filename = files.getOriginalFilename();
		//获取文件的后缀名
		String lastFilename = filename.substring(filename.lastIndexOf(".")+1);
		
		try {
			//创建一个FastDFS客户端
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
			
			//执行上传处理
			String path = fastDFSClient.uploadFile(files.getBytes(),lastFilename);
			
			//4、拼接返回的 url 和 ip 地址，拼装成完整的 url
			String url = FILE_SERVER_URL+path;
			return new ResultMsg(true,url);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(false,"上传失败!");
		}
	}
	
}
