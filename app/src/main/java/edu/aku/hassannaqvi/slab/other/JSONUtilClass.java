package edu.aku.hassannaqvi.slab.other;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by ramsha.ahmed on 4/12/2018.
 */

public class JSONUtilClass {



        public static <T> T getModelFromJSON(String json, Class<T> type) {
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(json, type);
        }

    }
