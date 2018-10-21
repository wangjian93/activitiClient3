package com.ivo.activiticlient.common.upload;

import com.ivo.activiticlient.biz.BizService;
import com.ivo.activiticlient.common.util.BizUtil;
import com.ivo.activiticlient.common.util.HttpRequestDeviceUtil;
import com.ivo.activiticlient.common.util.JsonUtil;
import com.ivo.activiticlient.common.util.StringUtil;
import com.ivo.activiticlient.domain.Employee;
import com.ivo.activiticlient.edoc.EdocService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UploadAndDownloadController {

	@Autowired
	private BizService bizService;
	
	private String savePath = "~/home/ivousr/ivo"+File.separator+"upload"+File.separator;

	@RequestMapping(value = "upload.do", method = RequestMethod.GET)
	public ModelAndView prepareUpload() {
		return new ModelAndView("common/upload");
	}

	@RequestMapping(value = "plUpload.do", method = RequestMethod.GET)
	public ModelAndView plUpload() {
		return new ModelAndView("common/plUpload");
	}

	
	@RequestMapping(value = "plUpload.do", method = RequestMethod.POST)
	public ModelAndView plUpload(
			@RequestParam("upload") List<MultipartFile> files,
			@RequestParam("order") String order,
			@RequestParam("material") String material,
			@RequestParam("factory") String factory,
			@RequestParam("type") String type,
			@RequestParam("specified") String specified,
			@RequestParam("row") String row,
			HttpSession session)
			throws Exception {
		ModelAndView mv = new ModelAndView("common/plUpload");
		String sid = BizUtil.getSource(order);
		String path = savePath
				+ "20"+ order.substring(order.length() - 9, order.length() - 7)
				+ File.separator + sid;
		File target = new File(path);
		if (!target.exists())
			target.mkdirs();
		for (MultipartFile file : files) {
			String filename = file.getOriginalFilename();
			String filetype = filename.substring(filename.lastIndexOf("."));
			File newFile = new File(path + File.separator + order + "_"+factory+"_"
					+type+"_"+specified+"_"+material+filetype);
			file.transferTo(newFile);
			mv.addObject("fileName", factory+"_"
					+type+"_"+specified+"_"+material+filetype);
			mv.addObject("row", row);
		}
		
		return mv;
	}
	
	
	@RequestMapping("plDownload.do")
	public void plDownload(@RequestParam("order") String order, @RequestParam("fileName") String fileName,
                           HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		File file = new File(savePath
				+ File.separator
				+ "20"
				+ order.substring(order.length() - 9, order.length() - 7)
				+ File.separator
				+ BizUtil.getSource(order)
				+ File.separator
				+ order
				+ "_"
				+ fileName
				);
		if (!file.exists()) {
			request.setAttribute("error", "此附件不存在！");
			request.getRequestDispatcher("pages/common/error.jsp").forward(
					request, response);
			return;
		}
		
	
		
		response.reset();
		response.setContentType("application/x-msdownload");
		response.setContentLength((int) file.length());
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0)
			fileName = "=?UTF-8?B?"
					+ (new String(Base64.encodeBase64(fileName
							.getBytes("UTF-8")))) + "?=";
		else
			fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ fileName);
		BufferedInputStream input = new BufferedInputStream(
				new FileInputStream(file));
		byte[] buf = new byte[4096];
		int len = 0;
		OutputStream output = response.getOutputStream();
		try {
			while ((len = input.read(buf)) > 0)
				output.write(buf, 0, len);
			output.flush();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			try {
				if (output != null)
					output.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	@RequestMapping(value = "upload.do", method = RequestMethod.POST)
	public ModelAndView upload(
            @RequestParam("upload") List<MultipartFile> files,
            @RequestParam("order") String order, HttpSession session)
			throws Exception {
		String sid = BizUtil.getSource(order);
		String uid = (String)session.getAttribute("user_ID");
		Employee user = bizService.getEmp(uid);
		String path = savePath
				+ "20"+ order.substring(order.length() - 9, order.length() - 7)
				+ File.separator + sid;
		File target = new File(path);
		if (!target.exists())
			target.mkdirs();
		for (MultipartFile file : files) {
			String filename = file.getOriginalFilename();
			/***********添加**********/
			String edocName = order + "_" + filename;
			File newFile = new File(path + File.separator + order + "_"
					+ filename);
			Long edition = null;
			if (newFile.exists()) {
				edition = System.currentTimeMillis();
				newFile = new File(path + File.separator + order + "_"
						+ filename + "_" + edition);
				edocName = order + "_" + edition + "_" + filename;
			}
			
			if("CHR".equals(sid)){
				String edocID = EdocService.createFile(sid, edocName, file);
				Attachment attachment = new Attachment(order, filename, file
						.getSize(), user, edition, edocID);
				file.transferTo(newFile);
				bizService.save(attachment);
			}else{
				Attachment attachment = new Attachment(order, filename, file
						.getSize(), user, edition);
				file.transferTo(newFile);
				bizService.save(attachment);
			}
		}
		return new ModelAndView("common/upload");
	}

	@RequestMapping(value = "delete.do", method = { RequestMethod.POST }, headers = { "x-requested-with" })
	@ResponseBody
	public String delete(@RequestParam("id") long id, HttpSession session) {
		Attachment attachment = bizService.getDao().getObject(Attachment.class, id);
		Employee user = bizService.getEmp((String)session.getAttribute("user_ID"));
		if (attachment != null) {
			if (!user.getEmployee_ID().equals(attachment.getUser().getEmployee_ID())) {
				return "您没有权限删除此附件！";
			}
			String order = attachment.getOrder();
			
			File file = new File(savePath
					+ "20"
					+ order.substring(order.length() - 9, order.length() - 7)
					+ File.separator
					+ BizUtil.getSource(order)
					+ File.separator
					+ order
					+ "_"
					+ attachment.getFileName()
					+ (attachment.getEdition() == null ? "" : ("_" + attachment
							.getEdition())));
			bizService.delete(attachment);
			file.delete();
		}
		return "success";
	}

	@RequestMapping("download.do")
	public void download(@RequestParam("id") long id,
                         HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Attachment attachment = bizService.getDao().getObject(Attachment.class, id);
		if (attachment == null) {
			request.setAttribute("error", "此附件不存在！");
			request.getRequestDispatcher("pages/common/close.jsp").forward(
					request, response);
			return;
		}
		String order = attachment.getOrder();
		File file = new File(savePath
				+ File.separator
				+ "20"
				+ order.substring(order.length() - 9, order.length() - 7)
				+ File.separator
				+ BizUtil.getSource(order)
				+ File.separator
				+ order
				+ "_"
				+ attachment.getFileName()
				+ (attachment.getEdition() == null ? "" : "_"
						+ attachment.getEdition()));
		if (!file.exists()) {
			request.setAttribute("error", "此附件不存在！");
			request.getRequestDispatcher("pages/common/error.jsp").forward(
					request, response);
			return;
		}
		response.reset();
		response.setContentType("application/x-msdownload");
		response.setContentLength((int) file.length());
		String fileName = attachment.getFileName();
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0)
			fileName = "=?UTF-8?B?"
					+ (new String(Base64.encodeBase64(fileName
							.getBytes("UTF-8")))) + "?=";
		else
			fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ fileName);
		BufferedInputStream input = new BufferedInputStream(
				new FileInputStream(file));
		byte[] buf = new byte[4096];
		int len = 0;
		OutputStream output = response.getOutputStream();
		try {
			while ((len = input.read(buf)) > 0)
				output.write(buf, 0, len);
			output.flush();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			try {
				if (output != null)
					output.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	@RequestMapping(value = "attach.do", method = { RequestMethod.POST }, headers = { "x-requested-with" })
	@ResponseBody
	public String getAttachments(
			@RequestParam(value = "order", required = false) String order,
			HttpServletRequest request) {
		if (order != null) {
			List<Attachment> list = getAttachments(order);
			List<AttachObject> attachments = new ArrayList<AttachObject>();
			for (Attachment attachment : list) {
				attachments.add(new AttachObject(attachment.getId(), attachment
						.getFileName(), convertSize(attachment.getSize()), "["
						+ attachment.getUser().getEmployee_ID() + "] "
						+ attachment.getUser().getEmployeeName()));
			}
			return JsonUtil.toJson(attachments);
		} else {
			Log logger = LogFactory.getLog("Attachment");
			logger.error("No order!" + request.getHeader("User-Agent") + "!"
					+ request.getRequestURL());
			return "";
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Attachment> getAttachments(String order) {
		if (StringUtil.isEmpty(order))
			return new ArrayList<Attachment>();
		return bizService.getDao().find("from Attachment a where a.order=? and a.validFlag = 1", order);
	}

	public String convertSize(long size) {
		if (size < 1024)
			return size + "byte";
		size = size / 1024;
		if (size < 1024)
			return size + "K";
		size = size / 1024;
		return size + "M";
	}
	
	@RequestMapping(value = "preview.do", method = { RequestMethod.POST }, headers = { "x-requested-with" })
	@ResponseBody
	public String getPreviewUrl(@RequestParam(value = "id", required = false) String id, HttpServletRequest request) {
		boolean ismobile = HttpRequestDeviceUtil.isMobileDevice(request);
		if(id != null){
			Attachment attachment = bizService.getDao().getObject(Attachment.class, Long.valueOf(id));
			return EdocService.tojson2(ismobile, attachment.getEdocId(), attachment.getFileName());
		}
		return EdocService.tojson(ismobile, "", "");
	}
}
