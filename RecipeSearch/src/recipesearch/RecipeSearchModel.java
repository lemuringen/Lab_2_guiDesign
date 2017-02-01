package recipesearch;
//import Libraries.

import java.util.ArrayList;
import java.util.List;
import se.chalmers.ait.dat215.lab2.*;

public class RecipeSearchModel {
private String difficulty;
private int maxTime;
private String cuisine;
private int maxPrice;
private String ingredient;
private int matchTreshold;
private final RecipeDatabase db;
private List <Recipe> recipes;


public RecipeSearchModel(String difficulty, int maxTime, String cuisine, 
        int maxPrice, String ingredient, int matchTreshold){
    this.db = RecipeDatabase.getSharedInstance();
    this.difficulty = difficulty;
    this.maxTime = maxTime;
    this.cuisine = cuisine; 
    this.maxPrice = maxPrice;
    this.ingredient = ingredient;
    this.matchTreshold = matchTreshold;
}
public void setmatchTreshold(int matchTreshold){
    this.matchTreshold = matchTreshold;
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
   List <Recipe> tmpRecipes = db.search(new SearchFilter(this.difficulty, this.maxTime, this.cuisine, this.maxPrice, this.ingredient));
   List <String> recipeNames = new ArrayList();
   this.recipes = new ArrayList();
   
   for(Recipe r: tmpRecipes){
       if(r.getMatch()>= this.matchTreshold){
           recipeNames.add(r.getName());
           this.recipes.add(r);
       }
   }
   return recipeNames;
}    
public Recipe getRecipe(String recipeName){
    // should check if recies intaniziated or do it in the constructor
       for(Recipe r: this.recipes){
       if(r.getName().equals(recipeName)){
       return r;    
       }
   }
       return null; // FIX
}
}
