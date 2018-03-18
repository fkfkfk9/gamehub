package com.gamehub.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {

  private static final Logger logger = 
      LoggerFactory.getLogger(UploadFileUtils.class);

  //매개변수로 파일을 업로드할 폴더경로, 파일의 이름, 파일의 데이터를 받는 업로드 메소드이다.
  public static String uploadFile(String uploadPath, 
                              String originalName, 
                              byte[] fileData)throws Exception{
	System.out.println("파일 업로드 진입");
    //고유값을 가지고 있는 아이디를 생성해준다. 중복데이터 생성을 방지한다.
    UUID uid = UUID.randomUUID();
    //고유값을 기존의 파일명에 붙여서 고유한 파일명을 만든다.
    String savedName = uid.toString() +"_"+originalName;
    //calcPath메소드를 이용하여 오늘날짜의 폴더를 생성시키고 그 경로를 리턴받아온다.
    String savedPath = calcPath(uploadPath);
    System.out.println("저장경로 : " + savedPath);
    //업로드 경로에 오늘날짜로 생성시킨 폴더로 고유값을 가진 파일명으로 파일을 생성시킨다.
    File target = new File(uploadPath +savedPath,savedName);
    //생성시킨 파일에 매개변수로 넘어온 byte배열 타입의 파일데이터를 복사시킨다.
    FileCopyUtils.copy(fileData, target);
    //원본파일명의 .이후의 문자열을 잘라옴으로써 데이터 타입명을 받아온다.
    String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
    //리턴값 초기화
    String uploadedFileName = null;
    //데이터 타입을 전송하여 그림파일인지 아닌지를 구별한다.
    if(MediaUtils.getMediaType(formatName) != null){//그림파일일 경우
      //그림파일의 썸네일용으로 축소시킨 파일을 만들어 그 경로를 리턴받는다.
      uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
    }else{//그림파일이 아닐 경우
      //업로드된 파일의 경로를 만들어 리턴해준다.
      uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
    }
    logger.info(uploadedFileName);
    //그림일 경우 썸네일경로를 아닐경우 파일의 경로를 리턴해준다.
    return uploadedFileName;
    
  }
  
  private static  String makeIcon(String uploadPath,//그림파일이 아닌경우 불러오는 메소드
    String path, //매개변수는 썸네일과 같이 업도르 경로, 날짜경로, 파일이름이다.
    String fileName)throws Exception{
	logger.info("업로드 경로 : " + uploadPath);
	logger.info("폴더명 : " + path);
	logger.info("파일명 : " + fileName);
    String iconName = 
        uploadPath + path + File.separator+ fileName;
    logger.info("result : " + iconName);
    //썸네일 파일을 만들지 않고 그냥 현재 파일의 경로를 완성시켜 \를 /로 변환시켜 리턴 즉 현재 파일의 경로 리턴
    return iconName.substring(
        uploadPath.length()).replace(File.separatorChar, '/');
  }
  
  public static String addScalr(String pd_img)throws Exception {
	  String scalr_img = "";
	  scalr_img = pd_img.replace("일/", "일/s_");
	  return scalr_img;
  }
  
  private static  String makeThumbnail(//파일포멧이 그림일 경우 불러오는 메소드
              String uploadPath, //업로드 경로
              String path, 		 //오늘날짜의 날짜경로
              String fileName)throws Exception{//생성시킨 파일이름
    //메소드 명대로 썸네일을 만드는 메소드로 기존 이미지 파일을 축소시켜 s_를 붙인 파일을 또 하나 만든다.
	
	//기존의 이미지 파일을 읽어온다.
    BufferedImage sourceImg = 
        ImageIO.read(new File(uploadPath + path, fileName));
    //읽어온 기존 이미지를 축소시킨다.
    BufferedImage destImg = 
        Scalr.resize(sourceImg, 
            Scalr.Method.AUTOMATIC, 
            Scalr.Mode.FIT_TO_HEIGHT,100);
    //파일경로에 s_를 붙인 새로운 파일경로를 만든다.
    String thumbnailName = 
        uploadPath + path + File.separator +"s_"+ fileName;
    //새 파일경로를 생성시킨다.
    File newFile = new File(thumbnailName);
    //기존 파일의 이미지 포멧 타입을 가져온다.
    String formatName = 
        fileName.substring(fileName.lastIndexOf(".")+1);
    
    //기존 포멧타입으로 축소시킨 이미지를 새로만든 생성경로에 만들어준다.
    ImageIO.write(destImg, formatName.toUpperCase(), newFile);
    //파일경로의 \를 /로 바꿔 리턴해준다.
    return thumbnailName.substring(
        uploadPath.length()).replace(File.separatorChar, '/');
  } 
  
  
  //파일 업로드 경로 만드는 메소드 현재 날짜를 받아와서 매개변수로 온 업로드 경로에 오늘날짜로 폴더를 생성시킨다.
  private static String calcPath(String uploadPath){
	System.out.println("날짜로 폴더 생성 진입");
    //날짜에 대한 정보를 받아온다.
    Calendar cal = Calendar.getInstance();
    //현재 년도를 가져온다 File.separator는 \를 의미한다.
    String yearPath = File.separator+cal.get(Calendar.YEAR);
    //현재 년도에 달을 추가한다. 즉 \2018\02라는 경로를 만들어 저장시킨것이다.
    String monthPath = yearPath + 
        File.separator + 
        new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
    //위의 경로에 일까지 추가한다. \2018\02월18일 이라는 경로를 만들었다.
    String datePath = monthPath + "월" +        
        new DecimalFormat("00").format(cal.get(Calendar.DATE))+"일";
    //위에 만들어놓은 경로에 폴더를 생성시킨다.
    System.out.println("년경로 : " + yearPath + " 월경로 : " + datePath);
    makeDir(uploadPath, yearPath,datePath);
    
    logger.info(datePath);
    //리턴값은 업로드 경로를 제외한 파일이 업로드될 최종 경로이다.
    return datePath;
  }
  
  
  private static void makeDir(String uploadPath, String... paths){
    //마지막으로 입력한 경로가 이미 폴더가 있다면 더이상 진행하지 않고 리턴
	System.out.println("폴더생성 진입");
    if(new File(paths[paths.length-1]).exists()){
      return;
    }
    //paths배열에 들어온 경로수 만큼 폴더를 만들어 준다.
    for (String path : paths) {
      
      File dirPath = new File(uploadPath + path);
      
      if(! dirPath.exists() ){
        dirPath.mkdir();
        System.out.println("폴더 생성시킴");
      } 
    }
  }
  
  
}
