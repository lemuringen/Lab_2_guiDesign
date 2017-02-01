/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipesearch;
import java.util.List;
import javax.swing.*;
import se.chalmers.ait.dat215.lab2.Recipe;

public class RecipeSearchPresenter {
    private JComboBox<String> cuisineComboBox;
    private JComboBox<String> difficultyComboBox;
    private JComboBox<String> ingredientComboBox;
    private JSlider priceSlider;
    private JList<String> recipeList;
    private JSlider timeSlider;
    private RecipeSearchModel model;
    private JLabel recipePictureJLabel;
    
    public RecipeSearchPresenter(JComboBox<String> difficultyComboBox,
            JSlider timeSlider, JComboBox<String> cuisineComboBox, 
            JSlider priceSlider, JComboBox<String> ingredientComboBox, JList<String> recipeList, JLabel recipePictureJLabel){
        this.difficultyComboBox = difficultyComboBox;
        this.timeSlider = timeSlider;
        this.cuisineComboBox = cuisineComboBox; 
        this.priceSlider = priceSlider;
        this.ingredientComboBox = ingredientComboBox;
        this.recipeList = recipeList;
        this.recipePictureJLabel = recipePictureJLabel;
        
        this.model = new RecipeSearchModel(null,0,null,0,null,50);
  
    }
    private void searchRecipe(){
        // Search  recipes and set JList (recipeList) with data
        List<String> tmp = model.searchRecipe();
        String[] hitList = new String[tmp.size()];
        
        for(int i = 0; i < tmp.size(); i++ ){
            System.out.println(tmp.get(i));
            hitList[i] = tmp.get(i);
        }
        recipeList.setListData(hitList);
        }
    public void updateMaxPrice(){
        model.setMaxPrice(this.priceSlider.getValue());
        searchRecipe();
    }
    public void updateMaxTime(){
        model.setMaxTime(this.timeSlider.getValue());
        searchRecipe();        
    }
    public void updateCuisine(){
        model.setCuisine((String)this.cuisineComboBox.getSelectedItem());
        searchRecipe();
    }
    public void updateDifficulty(){
        model.setDifficulty((String)this.difficultyComboBox.getSelectedItem());
        searchRecipe();
    }
    public void updateIngredient(){
        model.setIngredient((String)this.ingredientComboBox.getSelectedItem());
        searchRecipe();        
    }
    public void showRecipe(){
        // For showing recipe in detail view
        Recipe r = model.getRecipe((String)this.recipeList.getSelectedValue());
        this.recipePictureJLabel.setIcon(r.getImage());
    }
}
