package com.documents.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DocumentController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
    public String read() throws Exception {
		System.out.println("/");
		return "read_files";
    }
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
    public String index() throws Exception {
		return "index";
    }
	
	@RequestMapping(value="/upload", method = RequestMethod.POST, consumes = { "application/json" })
	public String upload(@RequestBody String params) throws Exception {
		System.out.println("/upload");
		System.out.println(params);
		String json = params.substring(1, params.length()-1);
		String arr[] = json.split(",");
		Map<String, String> map = new HashMap<String, String>();
		for(int i = 0; i < arr.length; i++) {
			String item = arr[i].substring(1, params.length()-1);
			String values[] = item.split(":");
			map.put(values[0], values[1]);
		}  
		
		/*
		map.get("")

		for(MultipartFile uploadedFile : uploadingFiles) {
			HWPFDocument doc = new HWPFDocument(uploadedFile.getInputStream());
			Range range = doc.getRange();
			
			for(Entry<String, String> entry: formParams.entrySet()) {
				range.replaceText(entry.getKey(), entry.getValue());
			}
			
			Random r = new Random();
			int num = r.nextInt();
			//TODO: surname name year
			String fileName = "C:\\Users\\Family\\Documents\\Сахаров\\Documents\\blank-mesto-zhitelstva" + "" + num +".doc";
			doc.write(new FileOutputStream(new File(fileName)));
		}
	  	*/
		
		//save all new files to output
		return "index";
	}
	
	//загружаем файлы
	@RequestMapping(value="/read", method = RequestMethod.POST)
	public String read(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles, ModelMap model) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		
		for(MultipartFile uploadedFile : uploadingFiles) {
			String name = uploadedFile.getOriginalFilename();
			int i = name.indexOf('.');
			String formName = name.substring(0, i);
			//считываем файл праметром для построения формы
			String path = "C:\\Users\\Family\\Documents\\Saharov\\form\\" + formName + ".txt";
			File f = new File(path);
			
			BufferedReader b = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF8"));
            String readLine = "";
            
            while ((readLine = b.readLine()) != null) {
                String arr[] = readLine.split(":");
                String key = arr[0];
                String value = arr[1];
                map.putIfAbsent(key, value);
            }
		}
		
		model.addAttribute("params", map);
		return "index";
	}

	@ExceptionHandler(value = Exception.class)  
    public String exceptionHandler(Exception e){  
        return e.getMessage();  
    }
}

/*
FileInputStream in = new FileInputStream(new File("C:\\Users\\Family\\Documents\\Сахаров\\patterns\\blank-mesto-zhitelstva.doc"));
HWPFDocument doc = new HWPFDocument(in);

Range range = doc.getRange(); 
range.replaceText("$фамилия", "Сахаров");
range.replaceText("$имя", "Дмитрий"); 
range.replaceText("$отчество", "Иванович"); 
Random r = new Random();
int num = r.nextInt();
String fileName = "C:\\Users\\Family\\Documents\\Сахаров\\patterns\\blank-mesto-zhitelstva" + "" + num +".doc";
doc.write(new FileOutputStream(new File(fileName))); 
 */


//stub
		/*
		FileInputStream in = new FileInputStream(new File("C:\\Users\\Family\\Documents\\Сахаров\\patterns\\blank-mesto-zhitelstva.doc"));
		HWPFDocument doc = new HWPFDocument(in);
		String regexp = "^{.+}$";
		//TODO: test от  <фамилия> <имя> <отчество> <р-дд> . <р-мм> . <р-гггг> <ader>
		//List<String> params = new ArrayList<String>();
		//for(MultipartFile uploadedFile : uploadingFiles) {
          //File file = new File(uploadingdir + uploadedFile.getOriginalFilename());
          //uploadedFile.transferTo(file);
			//InputStream input = uploadedFile.getInputStream();
			//HWPFDocument doc = new HWPFDocument(input);
			//parse reg exp http://stackoverflow.com/questions/12305071/using-apache-poi-with-regex-to-extract-uppercase-words
			WordExtractor we = new WordExtractor(doc);
			if (we.getParagraphText() != null) {
		        String[] dataArray = we.getParagraphText();
		        for (int i = 0; i < dataArray.length; i++) {
		            String data = dataArray[i].toString();
		            System.out.println(data);
		            Pattern p = Pattern.compile(regexp);
		            Matcher m = p.matcher(data);
		            List<String> sequences = new ArrayList<String>();
		            while (m.find()) {
		                sequences.add(data.substring(m.start(), m.end()));
		               // System.out.println(data.substring(m.start(), m.end()));
		            }
		            System.out.println("size: " + sequences.size());
		        }
		    }
			
			//OutputStream output  = new FileOutputStream(new File("C:\\file.doc"));
			//IOUtils.copy(input, output);
      //}
		*/