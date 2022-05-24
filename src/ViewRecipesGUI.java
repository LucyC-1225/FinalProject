import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewRecipesGUI implements ActionListener {
    private JTextArea recipeInfo; //multiple lines of text
    private JTextField recipeEntryField; //one line of text
    private ArrayList<Recipe> allUserRecipes;
    private User user;

    public ViewRecipesGUI(User user) {
        this.user = user;
        recipeInfo = new JTextArea(20, 35); // change size if needed
        recipeEntryField = new JTextField();
        allUserRecipes = user.getThisUserRecipes();

        setupGui();
        loadAllRecipes();
    }

    public void setupGui(){
        JFrame frame = new JFrame("Recipe Manager");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


        //top
        JLabel welcomeLabel = new JLabel(user.getUserName() + "'s Recipes");
        welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.blue);

        JPanel welcomePanel = new JPanel();
        welcomePanel.add(welcomeLabel);

        //middle panel with recipe list
        JPanel recipeListPanel = new JPanel();
        recipeInfo.setFont(new Font("Helvetica", Font.PLAIN, 16));
        recipeInfo.setWrapStyleWord(true);
        recipeInfo.setLineWrap(true);
        recipeListPanel.add(recipeInfo);

        //bottom
        JPanel entryPanel = new JPanel();
        JLabel recipeLabel = new JLabel("Which recipe? Enter a number: ");
        recipeEntryField = new JTextField(10);
        JButton sendButton = new JButton("Send");
        JButton resetButton = new JButton("Reset");
        entryPanel.add(recipeLabel);
        entryPanel.add(recipeEntryField);
        entryPanel.add(sendButton);
        entryPanel.add(resetButton);

        frame.add(welcomePanel, BorderLayout.NORTH);
        frame.add(recipeListPanel, BorderLayout.CENTER);
        frame.add(entryPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(this);
        resetButton.addActionListener(this);

        frame.pack();
        frame.setVisible(true);
    }

    private void loadAllRecipes() {
        allUserRecipes = user.getThisUserRecipes();

        String labelStr = "";
        int count = 1;
        for (Recipe recipe : allUserRecipes)
        {
            labelStr += count + ". " + recipe.getRecipeName() + "\n";
            count++;
        }
        recipeInfo.setText(labelStr);
    }

    private void loadRecipeInfo(Recipe r) {
        Recipe recipe = r;
        String info = recipe.toString();
        recipeInfo.setText(info);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) (e.getSource());
        String text = button.getText();

        if (text.equals("Send")) {
            String selectedMovieNum = recipeEntryField.getText();
            int movieNumInt = Integer.parseInt(selectedMovieNum);

            int movieIdx = movieNumInt - 1;
            Recipe selectedRecipe = allUserRecipes.get(movieIdx);

            loadRecipeInfo(selectedRecipe);
        }

        else if (text.equals("Reset")) {
            recipeEntryField.setText("");
            loadAllRecipes();
        }
    }
}
