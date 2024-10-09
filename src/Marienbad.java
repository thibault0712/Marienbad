/**
* Code who allow to play a famous game called Marienbad
* @author T.FALEZAN
*/


class Marienbad{

	void principal(){
		String player1 = SimpleInput.getString("Entrer le nom du premier joueur : ");
		String player2 = SimpleInput.getString("Entrer le nom du second joueur : ");
		int[] linesContent = initializeGameLines();
		launchGame(player1, player2, linesContent);
	}
	
	/**
	* Create lines for the game, line number is set by players
	* @return a table with the stick number per lines
	*/
	int[] initializeGameLines(){
		int lineNumber = SimpleInput.getInt("Entrer le nombre de ligne à utiliser pour jouer : ");
		while(lineNumber < 2 || lineNumber > 15){
			System.out.println("ERREUR : nombre de ligne incorrect, le nombre de ligne doit être compris entre 2 et 15");
			lineNumber = SimpleInput.getInt("Entrer le nombre de ligne à utiliser pour jouer : ");
		}
		
		int lineContent = 1;
		int[] linesContent = new int[lineNumber];
		for (int i = 0; i < lineNumber; i++){
			linesContent[i] = lineContent;
			lineContent += 2;
		}
		
		return linesContent;
	}
	
	/**
	* Show sticks per lines
	* @param linesContent is a table with the stick number of all lines
	*/
	void displayLines(int[] linesContent){
		for (int i = 0; i < linesContent.length; i++){
			System.out.print(i + " : ");
			final int stickNumber = linesContent[i];
			for(int j = 0; j < stickNumber; j++){
				System.out.print(" |");
			}
			System.out.print("\n");
		}
	}
	
	/**
	* Verify if a player has won or not
	* @param linesContent is a table with the stick number of all lines
	* @return true -> player won | false -> any player won
	*/
	boolean playerWon(int[] linesContent){
		boolean win = false;
		int totalStickAvailable = 0;
		int i = 0;
		
		while(i < linesContent.length && totalStickAvailable <= 0){
			totalStickAvailable += linesContent[i];
			i += 1; 
		}
		
		if (totalStickAvailable == 0){
			win = true;
		}
		
		return win;
	}
	
	
	/**
	* Start the game and manage who should play 
	* @param player1 is the name of the first player
	* @param player2 is the name of the second player
	* @param linesContent is a table with the stick number of all lines
	*/
	void launchGame(String player1, String player2, int[] linesContent){
		int playerWhoPlay = 1; //1 -> First Player || 2 -> Second Player
		
		displayLines(linesContent);

		while(!playerWon(linesContent)){
			removeStick(linesContent);
			displayLines(linesContent);
		}
		
		System.out.println("Bien joué à celui qui a gagné");
	}
	
	/**
	* Remove sticks in a line and verify if it's possible
	* @param linesContent is a table with the stick number of all lines
	*/
	void removeStick(int[] linesContent){
		boolean removeDone = false;
		int line;
		int stickNumberToRemove;
		
		do{
			line = SimpleInput.getInt("Entrer la ligne à éditer : ");
			stickNumberToRemove = SimpleInput.getInt("Entrer le nombre de baton à retirer : ");
			
			if(line > linesContent.length - 1){
				System.out.println("ERREUR : numéro de ligne mauvais");
			}else if(stickNumberToRemove > linesContent[line]){
				System.out.println("ERREUR : impossible de retirer " + stickNumberToRemove + " le nombre de baton à retirer est trop élevé pour cette ligne");
			}else if(stickNumberToRemove <= 0){
				System.out.println("ERREUR : impossible de retirer " + stickNumberToRemove + " le nombre de baton à retirer est trop élevé pour cette ligne");
			}else{
				linesContent[line] -= stickNumberToRemove;
				removeDone = true; 
			}
		}while(!removeDone);
	}
}
