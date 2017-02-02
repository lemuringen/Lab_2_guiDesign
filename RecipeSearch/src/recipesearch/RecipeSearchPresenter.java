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
    private JLabel recipePictureLabel;
    private JLabel cuisineFieldLabel;
    private JTextArea descriptionTextArea;
    private JLabel difficultyFieldLabel;
    private JTextArea instructionTextArea;
    private JLabel matchFieldLabel;
    private JLabel servingsFieldLabel;
    private JLabel priceFieldLabel;
    private JLabel timeFieldLabel;
    private JLabel titleFieldLabel;
    private JTextArea ingredientsTextArea;
    private JLabel mainIngredientFieldLabel;
    
    public RecipeSearchPresenter(
            JComboBox<String> difficultyComboBox,
            JSlider timeSlider, 
            JComboBox<String> cuisineComboBox, 
            JSlider priceSlider, 
            JComboBox<String> ingredientComboBox, 
            JList<String> recipeList, 
            JLabel recipePictureLabel,
            JLabel cuisineFieldLabel,
            JTextArea descriptionTextArea,
            JLabel difficultyFieldLabel,
            JTextArea instructionTextArea,
            JLabel servingsFieldLabel,
            JLabel priceFieldLabel,
            JLabel matchFieldLabel,
            JLabel timeFieldLabel,
            JLabel titleFieldLabel,
            JTextArea ingredientsTextArea,
            JLabel mainIngredientFieldLabel){
        this.difficultyComboBox = difficultyComboBox;
        this.timeSlider = timeSlider;
        this.cuisineComboBox = cuisineComboBox; 
        this.priceSlider = priceSlider;
        this.ingredientComboBox = ingredientComboBox;
        this.recipeList = recipeList;
        this.recipePictureLabel = recipePictureLabel;
        this.cuisineFieldLabel = cuisineFieldLabel;
        this.descriptionTextArea = descriptionTextArea;
        this.difficultyFieldLabel = difficultyFieldLabel;
        this.instructionTextArea = instructionTextArea;
        this.matchFieldLabel = matchFieldLabel;
        this.servingsFieldLabel = servingsFieldLabel;
        this.priceFieldLabel = priceFieldLabel;
        this.timeFieldLabel = timeFieldLabel;
        this.titleFieldLabel = titleFieldLabel;
        this.ingredientsTextArea = ingredientsTextArea;
        this.mainIngredientFieldLabel = mainIngredientFieldLabel;
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
        if(r==null){
            return;
        }
        this.recipePictureLabel.setIcon(r.getImage());
        this.cuisineFieldLabel.setText(r.getCuisine());
        this.descriptionTextArea.setText(r.getDescription());
        this.instructionTextArea.setText(r.getInstruction());
        this.difficultyFieldLabel.setText(r.getDifficulty());
        this.matchFieldLabel.setText(r.getMatch()+ "% sökträff");
        this.servingsFieldLabel.setText(r.getServings()+"");
        this.priceFieldLabel.setText(r.getPrice()+ " Kr");
        this.timeFieldLabel.setText(r.getTime()+ " minuter");
        this.titleFieldLabel.setText(r.getName());
        this.mainIngredientFieldLabel.setText(r.getMainIngredient());
        this.ingredientsTextArea.setText(null);
        for(Ingredient i: r.getIngredients()){
        this.ingredientsTextArea.append(i.toString() + "\n");
        }
    }
}
