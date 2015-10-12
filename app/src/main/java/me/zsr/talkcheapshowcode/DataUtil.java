package me.zsr.talkcheapshowcode;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @description:
 * @author: Zhangshaoru
 * @date: 9/25/15
 */
public class DataUtil {
    public static List<DemoCatogery> parseString2CatogeryList(String jsonStr) {
        List<DemoCatogery> tempList;
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(jsonStr);
        Type listType = new TypeToken<List<DemoCatogery>>() {}.getType();
        tempList = new Gson().fromJson(jsonObject.get("catogeries"), listType);
        return tempList;
    }
}
