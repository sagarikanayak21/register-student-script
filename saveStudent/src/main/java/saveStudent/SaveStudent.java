package saveStudent;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.vo.Student;

public class SaveStudent {
	public static void main(String[] args) {
		List<Student> stList = readExcel("D:\\workspace\\xlSheet\\StudentData.xlsx");
		for(Student std: stList) {
			System.out.println(std);
		}
//		saveStudent(stList);
		
	}
	private static List<Student> readExcel(String file) {
		List<Student> stList = null;
		try {
			XSSFWorkbook work = new XSSFWorkbook(new FileInputStream(file));
			
			XSSFSheet sheet = work.getSheet("Sheet1");
			stList = new ArrayList<>();
			XSSFRow row = null;
			int i=1;
			
			while((row = sheet.getRow(i)) != null) {
				Student std = new Student();
				std.setfName(row.getCell(0).getStringCellValue());
//				System.out.println("First Name: " + row.getCell(0));
				
				std.setlName(row.getCell(1).getStringCellValue());
//				System.out.println("Last Name: " +row.getCell(1));
				
				std.setCourse(row.getCell(2).getStringCellValue());
//				System.out.println("Stream: " +row.getCell(2));
				
				stList.add(std);
				i++;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return stList;
	}
	
	// Dummy code not completed
	private static void saveStudent(List<Student> list) {
		for(Student std:list) {
			System.out.println(std);
			HttpURLConnection connection = null;
			try {
				URL url=new URL("http://localhost:8080/student/register");
				connection=(HttpURLConnection)url.openConnection(); 
				connection.setRequestMethod("POST");
//				set the body as STUDENT object
				
				int code = connection.getResponseCode();
				
				  if (code !=  200) {
		                throw new IOException("Invalid response from server: " + code);
		            }

		            BufferedReader rd = new BufferedReader(new InputStreamReader(
		                    connection.getInputStream()));
		            String data = rd.readLine();
		            // return null;
			} catch (Exception e) {
				//Logger.error("Error on getting the student details");
				e.printStackTrace();
				System.out.println("Error on getting the student details");
				// return null;
				
			} finally {
				if(null != connection)
					connection.disconnect();
			}
		}
	}
}


