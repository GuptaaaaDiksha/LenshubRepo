package web.helper.admin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import utils.GenericMethods;

public class JsonHelper {

	private static JSONObject getTestDataJsonObject(String foldername, String userType, String fileName) {
		String path;
		if (foldername.equals("testdata"))
			path = Paths
					.get(GenericMethods.getProjectRootDirectory(), "src", "test", "java", "web", "testdata",userType)
					.toString();
		else
			path = Paths.get(GenericMethods.getProjectRootDirectory(), "src", "test", "java", "web", "testdata",
					userType, foldername).toString();
		String jsonPath = path + "/" + fileName + ".json";
		Reader reader = null;
		try {
			reader = new FileReader(jsonPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(reader);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	public static void writeTestDataInJsonObject(String foldername, String userType, String fileName, String key, String value) {
		String path;
		ObjectMapper mapper = new ObjectMapper();
		if (foldername.equals("testdata"))
			path = Paths
					.get(GenericMethods.getProjectRootDirectory(), "src", "test", "java", "web", "testdata",userType)
					.toString();
		else
			path = Paths.get(GenericMethods.getProjectRootDirectory(), "src", "test", "java", "web", "testdata",
					userType, foldername).toString();
		String jsonPath = path + "/" + fileName + ".json";
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject obj = (JSONObject) jsonParser.parse(new FileReader(jsonPath));

			obj.put(key, value);
			ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

			writer.writeValue(Paths.get(jsonPath).toFile(), obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getTestDataString(String foldername, String userType, String fileName, String key) {
		JSONObject jsonObject = getTestDataJsonObject(foldername, userType, fileName);
		return (String) jsonObject.get(key);
	}

	public static String getTestDataString(String foldername, String userType, String fileName, String key1, String key2) {
		JSONObject jsonObject1 = getTestDataJsonObject(foldername, userType, fileName);
		JSONObject jsonObject2 = (JSONObject) jsonObject1.get(key1);
		return (String) jsonObject2.get(key2);
	}

	public static String getTestDataString(String foldername, String userType, String fileName, String key1, String key2, String key3) {
		JSONObject jsonObject1 = getTestDataJsonObject(foldername, userType, fileName);
		JSONObject jsonObject2 = (JSONObject) jsonObject1.get(key1);
		JSONObject jsonObject3 = (JSONObject) jsonObject2.get(key2);
		return (String) jsonObject3.get(key3);
	}

	public static boolean getTestDataBoolean(String foldername, String userType, String fileName, String key1, String key2,
			String key3) {
		JSONObject jsonObject1 = getTestDataJsonObject(foldername, userType, fileName);
		JSONObject jsonObject2 = (JSONObject) jsonObject1.get(key1);
		JSONObject jsonObject3 = (JSONObject) jsonObject2.get(key2);
		return (boolean) jsonObject3.get(key3);
	}

	@SuppressWarnings("unchecked")
	public static List<String> getTestDataArray(String foldername, String userType, String fileName, String key) {
		JSONObject jsonObject = getTestDataJsonObject(foldername, userType, fileName);
		return (List<String>) jsonObject.get(key);
	}

	@SuppressWarnings("unchecked")
	public static List<String> getTestDataArray(String foldername, String userType, String fileName, String key1, String key2) {
		JSONObject jsonObject1 = getTestDataJsonObject(foldername, userType, fileName);
		JSONObject jsonObject2 = (JSONObject) jsonObject1.get(key1);
		return (List<String>) jsonObject2.get(key2);
	}

	@SuppressWarnings("unchecked")
	public static List<String> getTestDataArray(String foldername, String userType, String fileName, String key1, String key2,
			String key3) {
		JSONObject jsonObject1 = getTestDataJsonObject(foldername, userType, fileName);
		JSONObject jsonObject2 = (JSONObject) jsonObject1.get(key1);
		JSONObject jsonObject3 = (JSONObject) jsonObject2.get(key2);
		return (List<String>) jsonObject3.get(key3);
	}
}
