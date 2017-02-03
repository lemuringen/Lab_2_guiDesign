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
   return searchRecipe(this.difficulty, this.maxTime, this.cuisine, this.maxPrice, this.ingredient);
}    
public List<String> searchRecipe(String difficulty, int maxTime, String cuisine, int maxPrice, String ingredient){
   List <Recipe> tmpRecipes = db.search(new SearchFilter(difficulty, maxTime, cuisine, maxPrice, ingredient));
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
   public Recipe getRandomRecipe(){
       String difficulty;
       int maxTime;
       String cuisine;
       int maxPrice;
       String mainIngredient;
       maxTime = 150*(int)Math.random();
       maxPrice = 200 * (int)Math.random();
       int r = (int)(3*Math.random());
       if(r == 0){
        difficulty = "Lätt";   
       }else if(r == 1){
        difficulty = "Mellan";
       }else{
        difficulty = "Svår";
       }
       
       r = (int)(6*Math.random());
       if(r == 0){
        cuisine = "Sverige";   
       }else if(r == 1){
           cuisine = "Frankrike";
       }else if (r == 2){
           cuisine = "Asien";
       }else if (r == 3){
           cuisine = "Afrika";
       }else if (r == 4){
           cuisine = "Indien";
       }else{
           cuisine = "Grekland";
       }
       
       r = (int)(4*Math.random());
       if(r == 0){
           mainIngredient = "Kött";
       }else if(r == 1){
           mainIngredient = "Fisk";
       }else if (r == 2){
           mainIngredient = "Kyckling";
       }else{
           mainIngredient = "Vegetariskt";
       }
   List <String> li = searchRecipe(difficulty, maxTime, cuisine, maxPrice, mainIngredient);
   return getRecipe(li.get(0));
   }
}
