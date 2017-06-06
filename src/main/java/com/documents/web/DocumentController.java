package com.documents.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DocumentController {
	@Value("${appPath}")
	private String appPath;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
    public String read() throws Exception {
		return "read_files";
    }
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
    public String index() throws Exception {
		return "index";
    }
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/upload", method = RequestMethod.POST, consumes = { "application/json" })
	public @ResponseBody String upload(@RequestBody List<String> params) throws Exception {
		System.out.println("/upload");
		
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<String> fileNames = new ArrayList<String>();
		
		//get parameters from form
		for(String item : params) {
			String values[] = item.split(":");
			if(values[0].contains("file_secret")){
				fileNames.add(values[1]);
			} else {
				map.put(values[0],  values[1]);
			}
		}
		
		//replace key words in doc file with parameters from form
		for(String name : fileNames) {
			try(FileInputStream in = new FileInputStream(new File(appPath+"\\patterns\\"+name));
				HWPFDocument doc = new HWPFDocument(in);) {
				
				Range range = doc.getRange(); 

				for(Entry<String, String> entry: map.entrySet()) {
					String value = entry.getValue().equals("empty") ? " " : entry.getValue();
					System.out.println("replace in doc key: " + entry.getKey() + ", with value: " + value);
					range.replaceText(entry.getKey(), value);
				}
				String surname = map.get("{surname}");
				surname = surname.equals("empty") ? " " : surname
						;
				String fileName = appPath+"\\documents\\";
				if (surname != null && !surname.isEmpty()) {
					fileName = fileName + surname;
					System.out.println("filename: "  + fileName);
				}
				fileName = fileName + "_" + new Date().getDate()+"_"+new Date().getMonth()+"_"+new Date().getYear();
				fileName = fileName + "_" + name;
				
				System.out.println("output file: " + fileName);
				try (OutputStream out = new FileOutputStream(new File(fileName))) {
					doc.write(out);
					out.flush();
					out.close();
				}
				
				in.close();
				doc.close();
			}
		}
		
		System.gc();
		return "Success [" + new Date()+"]";
	}
	
	//загружаем файлы
	@RequestMapping(value="/read", method = RequestMethod.POST)
	public String read(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles, ModelMap model) throws Exception {
		System.out.println("/read");
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<String> list = new ArrayList<String>();
		
		for(MultipartFile uploadedFile : uploadingFiles) {
			String name = uploadedFile.getOriginalFilename();
			
			//put name of files 
			list.add(new String(name));
			
			int i = name.indexOf('.');
			String formName = name.substring(0, i);
			//считываем файл праметром для построения формы
			//System.out.println("appPath"+appPath);
			String path = appPath + "\\form\\" + formName + ".txt";
    		//System.out.println(path);

			try(BufferedReader b = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF8"));) {
				String readLine = "";
				int count = 0;
				while ((readLine = b.readLine()) != null) {
					//System.out.println(readLine);
					if( !readLine.isEmpty() && count > 0) {
						String arr[] = readLine.split(":");
						String key = arr[0];
						String value = arr[1];
						map.putIfAbsent(key, value);
					}
					++count;
				}
			}
		}
		
		model.addAttribute("params", map);
		model.addAttribute("files", list);
		return "index";
	}

	@ExceptionHandler(value = Exception.class)  
    public String exceptionHandler(Exception e){  
        return e.getMessage();  
    }
}