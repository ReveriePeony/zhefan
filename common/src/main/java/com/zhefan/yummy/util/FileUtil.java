package com.zhefan.yummy.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author ReverirNight@Foxmail.com
 * @datetime 2018年11月14日
 *
 */
@Slf4j
@Component
public class FileUtil {

	public static boolean deleteFile(File dirFile) {
		if (!dirFile.exists()) {
			return false;
		}

		if (dirFile.isFile()) {
			return dirFile.delete();
		} else {

			for (File file : dirFile.listFiles()) {
				deleteFile(file);
			}
		}

		return dirFile.delete();
	}

	public static boolean saveFile(byte[] bytes, String filePath, String fileName) {
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		System.err.println(targetFile.exists());
		FileOutputStream out;
		try {
			out = new FileOutputStream(filePath + fileName);
			out.write(bytes);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error("文件不存在 保存失败", e);
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			log.error("文件写入失败 保存失败", e);
			return false;
		}
		return true;
	}

	public static boolean renameToFile(String startFilePath, String endFilePath) {
		File startFile = new File(startFilePath);
		File tmpFile = new File(endFilePath);
		if(!startFile.exists()) return false;
		return startFile.renameTo(tmpFile);
	}
	
	public static File multipartFileToFile(MultipartFile mfile) {
		String originalFilename = mfile.getOriginalFilename();
		String prefix = originalFilename.substring(0, originalFilename.lastIndexOf("."));
		String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
		File file = null;
		try {
			file = File.createTempFile(prefix, suffix);
			mfile.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			log.error("文件转换失败", e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("文件转换失败", e);
		}
		return file;
	}

}
