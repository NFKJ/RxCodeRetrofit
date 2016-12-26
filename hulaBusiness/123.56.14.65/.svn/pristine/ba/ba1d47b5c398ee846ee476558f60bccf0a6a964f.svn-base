package teststudy.com.joypupil.service.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import teststudy.com.joypupil.util.MD5Util;
import teststudy.com.joypupil.util.StringUtil;

@Service
public class DownloadServiceImpl implements DownloadService {
	
	private static Logger LOGGER = Logger.getLogger(DownloadServiceImpl.class);

	@Override
	public void download(String name, HttpServletRequest request, HttpServletResponse response) {
		String param;
		//1、判断文件是否存在，不存在直接404
		String path = "test" + name;
		File file = new File(path);
		if(!file.exists()){
			response.setStatus(404);
			return;
		}
		//2、判断是否有if-modified-since,If-None-Match，如果有，比对文件，文件相同时，返回304，不进行下载，不同时直接进行下载整个
		param = request.getHeader("If-None-Match");
		if(!StringUtil.isEmpty(param)){
			try(InputStream in = new FileInputStream(file)){
				if(param.equals(MD5Util.getMD5(in))){
					response.setStatus(304);
					return;
				}
			} catch (Exception e){
				LOGGER.error("比较文件是否改动失败", e);
			}
		}
		param = request.getHeader("If-Modified-Since");
		if(!StringUtil.isEmpty(param)){
			try{
				if(Math.abs(file.lastModified() - Long.valueOf(param)) < 1000){
					response.setStatus(304);
					return;
				}
			}catch (Exception e){
				LOGGER.error("比较文件是否改动失败", e);
			}
		}
		//3、判断是否有range，没有range直接整个文件下载，有range进行片段下载，状态标记位206
		param = request.getHeader("Range");
		
		//4、判断是否有if-range，如果有，比对文件是否改变，如果改变，将response进行reset，返回状态200并下载全文件，如果相同，返回状态用上一步产生的状态即可
		//5、用前面拿到的需要读取文件的字节来对文件进行读取操作，并写入response的输出流中
	}

}
