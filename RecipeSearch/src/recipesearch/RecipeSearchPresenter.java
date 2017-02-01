/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipesearch;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import se.chalmers.ait.dat215.lab2.Ingredient;
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
    private JLabel detailCuisine;
    private JTextArea detailDescription;
    private JLabel detailDifficulty;
    private JTextArea detailInstruction;
    private JLabel detailMatch;
    private JLabel detailPortions;
    private JLabel detailPrice;
    private JLabel detailTime;
    private JLabel detailTitle;
    private JTextArea detailIngredient;
    public RecipeSearchPresenter(
            JComboBox<String> difficultyComboBox,
            JSlider timeSlider, 
            JComboBox<String> cuisineComboBox, 
            JSlider priceSlider, 
            JComboBox<String> ingredientComboBox, 
            JList<String> recipeList, 
            JLabel recipePictureJLabel,
            JLabel detailCuisine,
            JTextArea detailDescription,
            JLabel detailDifficulty,
            JTextArea detailInstruction,
            JLabel detailMatch,
            JLabel detailPortions,
            JLabel detailPrice,
            JLabel detailTime,
            JLabel detailTitle,
            JTextArea detailIngredient){
        this.difficultyComboBox = difficultyComboBox;
        this.timeSlider = timeSlider;
        this.cuisineComboBox = cuisineComboBox; 
        this.priceSlider = priceSlider;
        this.ingredientComboBox = ingredientComboBox;
        this.recipeList = recipeList;
        this.recipePictureJLabel = recipePictureJLabel;
        this.detailCuisine = detailCuisine;
        this.detailDescription = detailDescription;
        this.detailDifficulty = detailDifficulty;
        this.detailInstruction = detailInstruction;
        this.detailMatch = detailMatch;
        this.detailPortions = detailPortions;
        this.detailPrice = detailPrice;
        this.detailTime = detailTime;
        this.detailTitle = detailTitle;
        this.detailIngredient = detailIngredient;
        this.model = new RecipeSearchModel(null,0,null,0,null,50);
  
    }
    private void searchRecipe(){
        // Search  recipes and set JList (recipeList) with data
        List<String> tmp = model.searchRecipe();
        String[] hitList = new String[tmp.size()];
        
        for(int i = 0; i < tmp.size(); i++ ){
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
        this.detailCuisine.setText(r.getCuisine());
        this.detailDescription.setText(r.getDescription());
        this.detailInstruction.setText(r.getInstruction());
        this.detailDifficulty.setText(r.getDifficulty());
       //detailInstruction;
        this.detailMatch.setText(r.getMatch()+ "%");
        this.detailPortions.setText(r.getServings()+"");
        this.detailPrice.setText(r.getPrice()+ " Kr");
        this.detailTime.setText(r.getTime()+ " minuter");
        this.detailTitle.setText(r.getName());
        this.detailIngredient.setText(null);
        for(Ingredient i: r.getIngredients()){
     this.detailIngredient.append(i.toString() + "\n");
        }
    }
}
