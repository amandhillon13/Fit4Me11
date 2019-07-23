package com.example.fit4me11;


import java.util.HashMap;



public class UserData {
    public int height;
    public int weight;
    public HashMap<String, Integer> weightMap;
   // public HashMap<String, Integer> stepCountMap;
    public HashMap<String, Integer> calorieMap;

    public UserData() {
    }

    public UserData(int height, int weight) {
        this.height = height;
        this.weight = weight;
        this.weightMap = new HashMap<String, Integer>();
      //  this.stepCountMap = new HashMap<String, Integer>();
       this.calorieMap = new HashMap<String, Integer>();
        this.weightMap.put(new Day().toString(),weight);
       // this.stepCountMap.put(new Day().toString(),0);
        this.calorieMap.put(new Day().toString(),0);
    }

    public UserData(int height, int weight, HashMap<String, Integer> weightMap, HashMap<String, Integer> calorieMap) {
        this.height = height;
        this.weight = weight;
        this.weightMap = weightMap;
       // this.stepCountMap = stepCountMap;
        this.calorieMap = calorieMap;
    }

    public UserData(String str) {
        JsonObjectParser jsonObjectParser=new JsonObjectParser(str);
        this.height=jsonObjectParser.getInt("height");
        this.weight=jsonObjectParser.getInt("weight");
        JsonObjectParser jsonCalorieMap=new JsonObjectParser(jsonObjectParser.getString("calorieMap"));
        this.calorieMap=jsonCalorieMap.getIntMap();
        JsonObjectParser jsonWeightMap=new JsonObjectParser(jsonObjectParser.getString("weightMap"));
        this.weightMap=jsonWeightMap.getIntMap();

    }

    @Override
    public String toString() {
        return "UserData{" +
                "height=" + height +
                ", weight=" + weight +
                ", weightMap=" + weightMap +
                ", calorieMap=" + calorieMap +
                '}';
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public HashMap<String, Integer> getWeightMap() {
        return weightMap;
    }

    public void setWeightMap(HashMap<String, Integer> weightMap) {
        this.weightMap = weightMap;
    }

    public HashMap<String, Integer> getCalorieMap() {
        return calorieMap;
    }

    public void setCalorieMap(HashMap<String, Integer> calorieMap) {
        this.calorieMap = calorieMap;
    }


}
