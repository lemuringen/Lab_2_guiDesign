/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipesearch;
//import Libraries.

import java.util.ArrayList;
import java.util.List;
import se.chalmers.ait.dat215.lab2.*;




/**
 *
 * @author JesperU
 */
public class RecipeSearchModel {
private String difficulty = "Lätt";
private int maxTime = 50;
private String cuisine = "Sverige";
private int maxPrice = 100;
private String ingredient = "Vegetarisk";
private final RecipeDatabase db;
private List <Recipe> recipes;

public RecipeSearchModel(){
        this.db = RecipeDatabase.getSharedInstance();
}
public void setDifficulty(String difficulty){
    this.difficulty = difficulty;
}
public void setMaxTime(int maxTime){
    this.maxTime = maxTime;
}
public void setCuisine(String cuisine){
    this.cuisine = cuisine;
}
public void setMaxPrice(int maxPrice){
    this.maxPrice = maxPrice;
}
public void setIngredient(String ingredient){
    this.ingredient = ingredient;
}
public List<String> searchRecipe(){
   List<Recipe> tmpRecipes = db.search(new SearchFilter(this.difficulty, this.maxTime, this.cuisine, this.maxPrice, this.ingredient));
   List <String> recipeNames = new ArrayList();
   this.recipes = new ArrayList();
   
   for(Recipe r: tmpRecipes){
       if(r.getMatch()>= 40){
           recipeNames.add(r.getName());
           this.recipes.add(r);
       }
   }
   return recipeNames;
}    
public Recipe showRecipe(String recipeName){
    // should check if recies intaniziated or do it in the constructor
       for(Recipe r: this.recipes){
       if(r.getName().equals(recipeName)){
       return r;    
       }
   }
       return null; // FIX
}
}
