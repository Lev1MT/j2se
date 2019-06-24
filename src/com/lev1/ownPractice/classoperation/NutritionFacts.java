package com.lev1.ownPractice.classoperation;

public class NutritionFacts {
    private final int servingSize;

    /**
     * 建造者模式
     */
    public static class Builder{
        private final int servingSize;
        public Builder(int servingSize){
            this.servingSize = servingSize;
        }
        public NutritionFacts build(){
            return new NutritionFacts(this);
        }
    }
    private NutritionFacts(Builder builder){
        this.servingSize = builder.servingSize;
    }
}
