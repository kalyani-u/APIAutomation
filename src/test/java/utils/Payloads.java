package utils;

public class Payloads {
	
	public static String updateGist(){
		return "{\r\n" + 
				"  \"description\": \"Hello\",\r\n" + 
				"  \"files\": {\r\n" + 
				"    \"delete_this_file.txt\": \"\",\r\n" + 
				"    \"file1.txt\": {\r\n" + 
				"      \"content\": \"\"\r\n" + 
				"    },\r\n" + 
				"    \"new_file.txt\": {\r\n" + 
				"      \"content\": \"\"\r\n" + 
				"    },\r\n" + 
				"    \"old_name.txt\": {\r\n" + 
				"      \"content\": \"\",\r\n" + 
				"      \"filename\": \"\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";
		
	}
	
}
