/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipesearch;
import java.util.List;
import javax.swing.*;

public class RecipeSearchPresenter {
    private JComboBox<String> cuisineComboBox;
    private JComboBox<String> difficultyComboBox;
    private JComboBox<String> ingredientComboBox;
    private JSlider priceSlider;
    private JList<String> recipeList;
    private JSlider timeSlider;
    private RecipeSearchModel model;
    
    public RecipeSearchPresenter(JComboBox<String> difficultyComboBox,
            JSlider timeSlider, JComboBox<String> cuisineComboBox, 
            JSlider priceSlider, JComboBox<String> ingredientComboBox){
        this.difficultyComboBox = difficultyComboBox;
        this.timeSlider = timeSlider;
        this.cuisineComboBox = cuisineComboBox; 
        this.priceSlider = priceSlider;
        this.ingredientComboBox = ingredientComboBox;
        
        this.model = new RecipeSearchModel();
        
        this.difficultyComboBox = new JComboBox(new String[]{"Lätt", "Mellan", "Svår"});
        this.timeSlider = new JSlider(10, 150, 45);
        this.cuisineComboBox = new JComboBox(new String[] {"Sverige", "Grekland", "Indien", "Asien", "Afrika", "Frankrike"}); 
        this.priceSlider = new JSlider(10, 300, 50);
        this.ingredientComboBox = new JComboBox(new String[]{"Kött", "Fisk", "Kyckling", "Vegetarisk"});
    }
    public void searchRecipe(){
        // dåligt?
        String[] fulHack = new String[100];
        List<String> tmp = model.searchRecipe();
        int i = 0;
        for(String s: tmp){
            fulHack[i] = s;
            i++;
        }
        recipeList = new JList(fulHack);
        //recipeList = new JList(new String[]{""});
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
}
