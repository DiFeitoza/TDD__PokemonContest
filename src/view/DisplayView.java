package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.PokedexPokemon;

public class DisplayView {
	private static DisplayView pokedexDisplay;
	
	public static DisplayView getInstance() {
		if (pokedexDisplay == null) {
			pokedexDisplay = new DisplayView();
		}
		return pokedexDisplay;
	}

    public void print(PokedexPokemon pokemonData, String pokemonAbilitiesAndEffects){
    	URL url;
    	ImageIcon imageIcon = new ImageIcon();
    	
		try {
			url = new URL(pokemonData.getImageUrl());
		    BufferedImage bufferedImage = ImageIO.read(url);
	        imageIcon = new ImageIcon(bufferedImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        JFrame jFrame = new JFrame();
        JLabel jLabel = new JLabel();
        
        //jLabel.setVerticalTextPosition( SwingConstants.CENTER);
        
        String html = "<html><body style='width: %1spx'>%1s%1s";
        String a = pokemonData.toHtml();
        String b = pokemonAbilitiesAndEffects;       
        
        jLabel.setIcon(imageIcon);
        jLabel.setText(String.format(html, 300, a, b));

        jLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        jFrame.add(jLabel);
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(950, 560);
        jFrame.setVisible(true);
    }
}