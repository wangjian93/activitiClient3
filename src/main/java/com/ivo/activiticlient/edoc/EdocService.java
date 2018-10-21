package com.ivo.activiticlient.edoc;

import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.tempuri.*;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.*;

@Service
public class EdocService {
	
	/*---------------------------FileItem使用------------------------------------*/
	public static String createFile(String sid, String name, FileItem file) throws IOException{
		return createFile(createMyivoFolderByPath(sid), name, file);
	}
	
	public static String createFile(int folderId, FileItem file){
		return createFile(folderId, file.getName(), file.getSize(), file.get());
	}
	
	public static String createFile(String sid, FileItem file){
		int folderId = createMyivoFolderByPath(sid);
		return createFile(folderId, file);
	}
	
	public static String createFile(int folderId, String name, FileItem file){
		try {
			return createFile(folderId, name, file.getSize(), file.getInputStream());
		} catch (IOException e) {
			return "-1";
		}
	}
	/*---------------------------FileItem使用------------------------------------*/
	
	/*--------------------------File使用------------------------------------*/
	public static String createFile(String sid, String name, File file) throws IOException{
		FileInputStream inputStream = new FileInputStream(file);
		MultipartFile multifile = new MockMultipartFile(file.getName(), inputStream);
		return createFile(createMyivoFolderByPath(sid), name, multifile);
	}
	
	public static String createKmFile(String srcPath, String name, File file) throws IOException{
		FileInputStream inputStream = new FileInputStream(file);
		MultipartFile multifile = new MockMultipartFile(file.getName(), inputStream);
		return createFile(createKmFolderByPath(srcPath), name, multifile);
	}
	/*--------------------------File使用结束------------------------------------*/
	
	/*--------------------------MultipartFile使用------------------------------------*/
	public static String createFile(String sid, String name, MultipartFile file){
		return createFile(createMyivoFolderByPath(sid), name, file);
	}
	
	public static String createKmFile(String srcPath, String name, MultipartFile file){
		return createFile(createKmFolderByPath(srcPath), name, file);
	}
	
	public static String createFile(int folderId, String name, MultipartFile file){
		try {
//			return createFile(folderId, name, file.getSize(), file.getBytes());
			return createFile(folderId, name, file.getSize(), file.getInputStream());
		} catch (IOException e) {
			return "-1";
		}
	}
	public static String createFile(int folderId, MultipartFile file){
		try {
//			return createFile(folderId, file.getOriginalFilename(), file.getSize(), file.getBytes());
			return createFile(folderId, file.getOriginalFilename(), file.getSize(), file.getInputStream());
		} catch (IOException e) {
			return "-1";
		}
	}
	/*--------------------------MultipartFile使用 结束------------------------------------*/
	/**
	 * 获取token
	 * @return
	 */
	
	public static String getToken(){
		EDoc2OrganizationWebServiceSoapProxy soap = new EDoc2OrganizationWebServiceSoapProxy();
		String token="";
		try{
			String accessToken = soap.getAccessToken("EimEdoc");
			org.tempuri.ResultString intRes = soap.userLoginIntegrationByUserLoginName("admin", "10.20.2.156", accessToken);
			token = intRes.getResultValue();
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return token;
	}
	
	/**
	 * 删除
	 */
	public static void deleteFileList(String fileIds){
		if(fileIds == null || "".equals(fileIds)) return;
		EDoc2DocumentWebServiceSoapProxy docSoap = new EDoc2DocumentWebServiceSoapProxy();
		String token = getToken();
		if(StringUtils.isBlank(token)) return;
		try {
			docSoap.deleteFileListImpl(token, true, fileIds);
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 根据km的srcPath创建及更新文件加到KM路径下的相应位置,返回-1，表示创建文件夹失败
	 * @param srcPath
	 * @return
	 */
	public static int createKmFolderByPath(String srcPath){
		String token = getToken();
		if(StringUtils.isBlank(token)) return -1;
		EDoc2DocumentWebServiceSoapProxy docSoap = new EDoc2DocumentWebServiceSoapProxy();
		srcPath =  srcPath.replace("/", "\\");
		String path = "PublicRoot\\KM\\" + srcPath;
		try {
			ResultString resString = docSoap.createFolderByPath(token, path);
			if(resString.getResult() == 0){
				String resultValue = resString.getResultValue();
				String folderId = resultValue.substring((resultValue.lastIndexOf(",")+1), resultValue.length());
				return Integer.parseInt(folderId);
			}
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}
	/**
	 * 根据签核单的site创建及更新文件夹到MYIVO路径下相应位置,返回-1，表示创建文件夹失败
	 * @param sid
	 * @return
	 */
	public static int createMyivoFolderByPath(String sid){
		String token = getToken();
		if(StringUtils.isBlank(token)) return -1;
		EDoc2DocumentWebServiceSoapProxy docSoap = new EDoc2DocumentWebServiceSoapProxy();
		
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);//获取年份
		int month = ca.get(Calendar.MONTH)+1;//获取月份
		int day = ca.get(Calendar.DATE);//获取日期
		String filePath = "\\"+year+"\\"+month+"\\"+day;
		
		String path = "PublicRoot\\MYIVO\\"+sid+filePath;
		try {
			ResultString resString = docSoap.createFolderByPath(token, path);
			if(resString.getResult() == 0){
				String resultValue = resString.getResultValue();
				String folderId = resultValue.substring((resultValue.lastIndexOf(",")+1), resultValue.length());
				return Integer.parseInt(folderId);
			}
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}
	
	@SuppressWarnings({ "rawtypes", "hiding" })
	public static String createFile(int folderId, String fileName, long size, InputStream in) {
		String webServiceUrl = "http://10.20.2.156/WS47/EDoc2UploadFileWebService.asmx";
		int edocId = -1;
		int rs = -1;
		boolean IsUpdateFileVersion = false;
		String namespace = "http://tempuri.org/";
		String uploadId = java.util.UUID.randomUUID().toString().replace("-", "");
		String token = getToken();
		String fileCode = "";
		try {
			org.apache.axis.client.Service service = new org.apache.axis.client.Service();
			//创建文件信息
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(webServiceUrl));
			call.setUseSOAPAction(true);
			call.setSOAPActionURI(namespace + "StartCreateOrUpdateFile");
			call.setOperationName(new QName(namespace, "StartCreateOrUpdateFile"));
			call.addParameter(new QName(namespace, "uploadId"),XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(namespace, "token"),XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(namespace, "fileName"),	XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(namespace, "atFolderId"),	XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(namespace, "fileSize"),	XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(namespace, "fileCode"),	XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(namespace, "fileDescription"),	XMLType.XSD_STRING, ParameterMode.IN);
			
			call.setReturnType(new QName(namespace, "StartCreateOrUpdateFile"), Vector.class);
			Object[] params = new Object[] {uploadId,token,fileName,folderId+"",size+"",fileCode,""};
			Vector v = (Vector) call.invoke(params);
			rs = Integer.parseInt(v.get(0).toString());
			if(0 == rs){				
				edocId = Integer.parseInt(v.get(1).toString());
				IsUpdateFileVersion = Boolean.parseBoolean(v.get(1).toString());
				
				System.out.println("StartCreateOrUpdateFile Success fileId="+edocId);
				
				//上传文件流
				Call callUpload = (Call) service.createCall();
				callUpload.setTargetEndpointAddress(new java.net.URL(webServiceUrl));
				callUpload.setUseSOAPAction(true);
				callUpload.setSOAPActionURI(namespace + "UploadFileBlock");
				callUpload.setOperationName(new QName(namespace, "UploadFileBlock"));
				callUpload.addParameter(new QName(namespace, "uploadId"),XMLType.XSD_STRING, ParameterMode.IN);
				callUpload.addParameter(new QName(namespace, "token"),XMLType.XSD_STRING, ParameterMode.IN);
				callUpload.addParameter(new QName(namespace, "blockData"),	XMLType.XSD_BASE64, ParameterMode.IN);
				callUpload.addParameter(new QName(namespace, "upDataLength"),	XMLType.XSD_STRING, ParameterMode.IN);
				callUpload.addParameter(new QName(namespace, "filePos"),	XMLType.XSD_STRING, ParameterMode.IN);
				callUpload.addParameter(new QName(namespace, "isSecuritFile"),	XMLType.XSD_STRING, ParameterMode.IN);
				
				callUpload.setReturnType(new QName(namespace, "UploadFileBlock"), Vector.class);
				
				try{
					int maxLength=1024*1024*2;
					byte[] tempContent=new byte[maxLength];
					int byteread=0;
					int seek=0;
					while((byteread=in.read(tempContent,0,maxLength))!=-1){
						params = new Object[] {uploadId,token,tempContent,byteread+"",seek+"","false"};
						callUpload.invoke(params);
						seek+=byteread;
					}
					System.out.println("UploadFileBlock Success fileId="+edocId);
					
					//结束上传文件
					Call callComplete = (Call) service.createCall();
					callComplete.setTargetEndpointAddress(new java.net.URL(webServiceUrl));
					callComplete.setUseSOAPAction(true);
					callComplete.setSOAPActionURI(namespace + "ComplateUploadFile");
					callComplete.setOperationName(new QName(namespace, "ComplateUploadFile"));
					
					callComplete.addParameter(new QName(namespace, "uploadId"),XMLType.XSD_STRING, ParameterMode.IN);
					callComplete.addParameter(new QName(namespace, "token"),XMLType.XSD_STRING, ParameterMode.IN);
					callComplete.addParameter(new QName(namespace, "isUpdateFileVersion"),	XMLType.XSD_STRING, ParameterMode.IN);
					callComplete.addParameter(new QName(namespace, "isOverlayVersion"),	XMLType.XSD_STRING, ParameterMode.IN);
					callComplete.addParameter(new QName(namespace, "fileSize"),	XMLType.XSD_STRING, ParameterMode.IN);
					callComplete.setReturnType(new QName(namespace, "ComplateUploadFile"), Vector.class);
					
					params = new Object[] {uploadId,token,IsUpdateFileVersion+"","false",size+""};
					Object obj = (Object) callComplete.invoke(params);
				    System.out.println(obj);
					System.out.println("ComplateUploadFile Success fileId="+edocId);
				}catch(Exception iex){
					System.out.println(iex.toString());
				}finally{
					try{in.close();}catch(Exception fiex){}
				}
			}else {
				System.out.println("create file error");
			}			
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return edocId+"";
	}
	
	/**
	 * 根据文件夹编号、文件名、文件大小和文件块上传文件；返回为error时，代表上传失败
	 * @param folderId
	 * @param fileName
	 * @param size
	 * @param blockData
	 * @return
	 */
	public static String createFile(int folderId, String fileName, Long size, byte[] blockData){
		String token  = getToken();
		EDoc2UploadFileWebServiceSoapProxy uploadSoap = new EDoc2UploadFileWebServiceSoapProxy();
		try {
			if(StringUtils.isBlank(token) || folderId == -1 || "".equals(fileName) || fileName == null){
				return "-1";
			}else
			if(folderId == -1 || "".equals(fileName) || fileName == null){
				return "-1";
			}
			String name = fileName;
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			if(!checkType(suffix)) return "-1";
			long upid = new Date().getTime();
			ResultUploadFile resFile =  uploadSoap.startCreateOrUpdateFile(String.valueOf(upid), token, name, folderId, size, "", "");
			if(resFile.getResult() == 0){
				int resultValue = resFile.getResultValue();
				
				try{
					int uploadResult = uploadSoap.uploadFileBlock(String.valueOf(upid), token, blockData, (long)blockData.length,0, false);
					if(uploadResult == 0){
						uploadSoap.complateUploadFile(String.valueOf(upid), token, resFile.isIsUpdateFileVersion(), size);
						return resultValue+"";
					}					
				}catch(Exception e){
					System.out.println("finally input"+e);
				}
			}
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
		
		return "-1";
	}
	
	
	/**
	 * PC端在线预览；返回为空字符串时，检查fileId是否为空
	 * @param fileId
	 * @return
	 */
	public static String getPreview(String fileId){
		String token = getToken();
		if(StringUtils.isBlank(fileId) || StringUtils.isBlank(token) || fileId.equals("-1")){
			return "";
		}else{
			return "http://10.20.2.156/Preview.aspx?FileId="+fileId+"&calendarCode=1&token="+token+"&fromApp=123";
		}
	}
	
	/**
	 * 移动端在线预览；返回为空字符串时，检查fileId是否为空
	 * @param fileId
	 * @return
	 */
	public static String getMobilePreview(String fileId){
		String token = getToken();
		if(StringUtils.isBlank(fileId) || StringUtils.isBlank(token) || fileId.equals("-1")){
			return "";
		}else{
			return "http://edoc.ivo.com.cn/hfweb/MobilePreview?FileId="+fileId+"&calendarCode=1&token="+token;
		}
	}
	
	/**
	 * 根据文件名称，返回json格式字符串
	 */
	public static String tojson2(boolean ismobile, String fileId, String fileName){
		String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
		return tojson(ismobile, fileId, prefix);
	}
	
	/**
	 * 根据文件类型，返回json格式字符串
	 */
	public static String tojson(boolean ismobile, String fileId, String type){
		int result = 0;
		String preview = "";
		if(checkType(type)){
			if(ismobile){
				preview = getMobilePreview(fileId);
			}else{
				preview = getPreview(fileId);
			}
		}
		
		if(preview == null) preview = "";
		if(!"".equals(preview)) result = 1;
		
		StringBuilder json = new StringBuilder();
		StringBuilder datajson = new StringBuilder();
		
		datajson.append("{");
		datajson.append("\"ismobile\" : "+ismobile+", ");
		datajson.append("\"preview\" : \""+preview+"\" ");
		datajson.append("}");
		
		json.append("{");
		json.append("\"result\" : "+result+" ,");
		json.append("\"object\" : "+datajson+" ");
		json.append("}");
		
		return json.toString();
	}
	
	public static boolean checkType(String type){
		if(type == null || type.equals("")){
			return false;
		}
		type = type.toLowerCase();
		List<String> list = new ArrayList<String>();
		list.add("txt");
		list.add("ppt");
		list.add("pptx");
		list.add("doc");
		list.add("docx");
		list.add("xls");
		list.add("xlsx");
		list.add("pdf");
		list.add("jpg");
		list.add("jpeg");
		list.add("png");
		list.add("bmp");
		list.add("xml");
		list.add("log");
		list.add("rtf");
		for(String t : list){
			if(t.equals(type)){
				return true;
			}
		}
		return false;
	}
}