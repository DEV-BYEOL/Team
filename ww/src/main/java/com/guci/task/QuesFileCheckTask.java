package com.guci.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.guci.domain.QuesAttachVO;
import com.guci.mapper.QuesAttachMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class QuesFileCheckTask {

	/*
	  데이터베이스 내에서 사용된 파일 목록을 얻어와서 해당 폴더의 파일 목록에서
	  데이터베이스에서 없는 파일을 잡아 내서 없는 파일들을 삭제하는 순서로 구성되어있다.
	  (601~3) cron 설정과 삭제 처리
	 */
	@Setter(onMethod_ = @Autowired)
	private QuesAttachMapper attachMapper;
	
	private String getFolderYesterDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String str = sdf.format(cal.getTime());
		return str.replace("-", File.separator);
	}
	
	@Scheduled(cron = "0 0 2 * * *")
	public void checkFiles() {
		log.warn("File Check Task Run.....................");
		log.warn(new Date());
		// 데이터베이스에 담겨있는 파일 리스트
		List<QuesAttachVO> fileList = attachMapper.getOldFiles();
		
		// 해당 폴더의 파일 목록에서 데이터베이스에서 없는 파일을 잡아낼 준비
		List<Path> fileListPaths = fileList.stream()
				.map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName()))
				.collect(Collectors.toList());
		// 이미지 파일이 섬네일 파일인 경우
		fileList.stream().filter(vo -> vo.isFileType() == true)
		.map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), "s_" + vo.getUuid() + "_" + vo.getFileName()))
		.forEach(p -> fileListPaths.add(p));
		log.warn("========================================");
		fileListPaths.forEach(p -> log.warn(p));
		
		// 어제 파일의 디렉토리
		File targetDir = Paths.get("C:\\upload", getFolderYesterDay()).toFile();
		
		File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);
		
		log.warn("-----------------------------------------");
		for(File file : removeFiles) {
			log.warn(file.getAbsolutePath());
			file.delete();
		}
	}
}
