/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipesearch;
import java.util.List;
import javax.swing.*;
import se.chalmers.ait.dat215.lab2.Ingredient;
import se.chalmers.ait.dat215.lab2.Recipe;

public class RecipeSearchPresenter {
    
    final private JComboBox<String> cuisineComboBox;
    final private JComboBox<String> difficultyComboBox;
    final private JComboBox<String> ingredientComboBox;
    final private JSlider priceSlider;
    final private JList<String> recipeList;
    final private JSlider timeSlider;
    final private RecipeSearchModel model;
    final private JLabel recipePictureLabel;
    final private JLabel cuisineFieldLabel;
    final private JTextArea descriptionTextArea;
    final private JLabel difficultyFieldLabel;
    final private JTextArea instructionTextArea;
    final private JLabel servingsFieldLabel;
    final private JLabel priceFieldLabel;
    final private JLabel timeFieldLabel;
    final private JLabel titleFieldLabel;
    final private JTextArea ingredientsTextArea;
    final private JLabel mainIngredientFieldLabel;
    
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
        this.servingsFieldLabel = servingsFieldLabel;
        this.priceFieldLabel = priceFieldLabel;
        this.timeFieldLabel = timeFieldLabel;
        this.titleFieldLabel = titleFieldLabel;
        this.ingredientsTextArea = ingredientsTextArea;
        this.mainIngredientFieldLabel = mainIngredientFieldLabel;
        this.model = new RecipeSearchModel(null,0,null,0,null,50);
        showRecipe(this.model.getRandomRecipe());
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
        showRecipe(r);
    }
        public void showRecipe(Recipe r){
        // For showing recipe in detail view
        this.recipePictureLabel.setIcon(r.getImage(420, 300));
        this.cuisineFieldLabel.setText(r.getCuisine());
        this.descriptionTextArea.setText(r.getDescription());
        this.instructionTextArea.setText(r.getInstruction());
        this.difficultyFieldLabel.setText(r.getDifficulty());
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
