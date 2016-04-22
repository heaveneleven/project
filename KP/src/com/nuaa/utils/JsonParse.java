package com.nuaa.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONArray;
import org.json.JSONException;

public class JsonParse {
	/**
	 * 转换成json数据
	 * 
	 * @param obj
	 * @return
	 */
	public static String getJson(Object obj) {
		if (obj == null)
			return null;
		ObjectMapper om = new ObjectMapper();
		StringWriter sw = new StringWriter();
		try {
			JsonGenerator jg = new JsonFactory().createJsonGenerator(sw);
			om.writeValue(jg, obj);
			jg.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}

	/**
	 * 将json数据转换成List<Object>
	 * 
	 * @param json
	 * @param entityClass
	 * @return
	 */
	public static <T> List<T> getList(String json, Class<T> entityClass) {
		if (StringUtils.isBlank(json))
			return null;
		List<T> list = new ArrayList<T>();
		ObjectMapper om = new ObjectMapper();
		om.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			JSONArray array = new JSONArray(json);
			for (int i = 0; i < array.length(); i++) {
				T t = om.readValue(array.getString(i), entityClass);
				list.add(t);
			}
			return list;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json数据转换成List<LinkedHashMap<String, Object>>
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<LinkedHashMap<String, Object>> getListMap(String json) {
		if (StringUtils.isBlank(json))
			return null;
		ObjectMapper om = new ObjectMapper();
		om.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			List<LinkedHashMap<String, Object>> list = om.readValue(json, List.class);
			return list;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 将json数据转换成List<Map<String, T>>
	 * 
	 * @param json
	 * @return
	 */
	public static <T> List<Map<String, T>> getListMap(String json, Class<T> entityClass) {
		if (StringUtils.isBlank(json))
			return null;
		ObjectMapper om = new ObjectMapper();
		om.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			List<Map<String, T>> list = om.readValue(json, new TypeReference<List<Map<String, T>>>() {
			});
			return list;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json数据转换成Map<String, Object>
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getMap(String json) {
		if (StringUtils.isBlank(json))
			return null;
		ObjectMapper om = new ObjectMapper();
		om.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			Map<String, Object> map = om.readValue(json, Map.class);
			return map;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json数据转换成Map<String, T>
	 * 
	 * @param json
	 * @return
	 */
	public static <T> Map<String, T> getMap(String json, Class<T> entityClass) {
		if (StringUtils.isBlank(json))
			return null;
		ObjectMapper om = new ObjectMapper();
		om.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			Map<String, T> list = om.readValue(json, new TypeReference<Map<String, T>>() {
			});
			return list;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json数据转换成指定的类型
	 * 
	 * @param json
	 * @param valueType
	 * @return
	 */
	public static <T> T getObject(String json, Class<T> valueType) {
		if (StringUtils.isBlank(json))
			return null;
		ObjectMapper om = new ObjectMapper();
		om.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return om.readValue(json, valueType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
