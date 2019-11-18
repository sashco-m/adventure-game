import hsa.Console;
import java.awt.*;
public class CPTSashcoCarson
{
    static Console c; // global console variable
    //HANGMAN SPECIFIC
    static int attempts, wordOneTotalAttempts, levelHangman = 1;
    //fonts
    static Font boggleTableFont = new Font ("Helvetica", Font.BOLD, 70); //font created for the table itself
    static Font boggleGuessFont = new Font ("Batang", Font.BOLD, 16); //font created for wordlist
    static Font fontAnswer = new Font ("Serif", Font.PLAIN, 50); //font to print answer for Hangman
    static Font fontIntro = new Font ("Serif", Font.PLAIN, 60); //font for beginning message
    static Font stickFont = new Font ("Arial", Font.PLAIN, 11); //font for the stick game
    static Font EndingFont = new Font ("Helvetica", Font.PLAIN, 30); //font for end of the game
    static Font subtitlesFont = new Font ("Helvetica", Font.PLAIN, 20); //font created for story
    static Font EndingFont2 = new Font ("Helvetica", Font.BOLD, 64); //font for end of the game

    public static void main (String[] args)  //main method
    {

	c = new Console (30, 150); //must be 150*30

	//VARIABLES/FONTS/COLOURS
	String[] correctWords = {"else", "espy", "hen", "hens", "hint", "hit", "hoe", "hot", "hype", "hyphen", "hyphens", "into", "neo", "nit", "ply", "see", "seen", "sent", "slept", "sly", "spy", "the", "thee", "then", "thin", "thy", "tin", "toe", "type", "yep", "yelp", "yelps"}; //correct boggle inputs
	String guessMain; //the guess entered
	String startMainGame = " "; //initializes variable for printing part of intro sequence
	int correctMain, totalCorrect = 0, totalWrong = 0, totalPoints = 0; //created to count errors and number of correct
	int playerA = 0, playerB = 0, stickCount = 21; //counts number of sticks and number chosen by each player
	char[] tableLetters = {'J', 'S', 'L', 'E', 'E', 'N', 'P', 'Y', 'E', 'H', 'T', 'H', 'E', 'O', 'I', 'N'}; //only used to construct the table

	//HANGMAN VARIABLES AND ANSWERS
	String[] CorrectAnswers = {"haiku", "dwarves", "sphinx", "rogue"}; //correct answers for Hangman

	char[] HaikuGuesses = {'_', '_', '_', '_', '_'}; //array for haiku inputs
	char[] HaikuGuessesReset = {'_', '_', '_', '_', '_'}; //array to clear HaikuGuesses error if user loses
	String HaikuHint = "A type of poem"; //haiku hint

	char[] DwarvesGuesses = {'_', '_', '_', '_', '_', '_', '_'}; //array for dwarves inputs
	char[] DwarvesGuessesReset = {'_', '_', '_', '_', '_', '_', '_'}; //array to clear DwarvesGuesses error if user loses
	String DwarvesHint = "A fantastical race of beings"; //dwarves hint

	char[] SphinxGuesses = {'_', '_', '_', '_', '_', '_'}; //array for sphinx inputs
	char[] SphinxGuessesReset = {'_', '_', '_', '_', '_', '_'}; //array to clear SphinxGuesses error if user loses
	String SphinxHint = "A mythological riddler"; //sphinx hint

	char[] RogueGuesses = {'_', '_', '_', '_', '_'}; //array for rogue inputs
	char[] RogueGuessesReset = {'_', '_', '_', '_', '_'}; //array to clear RogueGuesses error if user loses
	String RogueHint = "A non-conformist"; //rogue hint

	//INTRO
	backGround (); //draws the first green background
	delay (700); //delays to show the scene
	meteor (); // makes the meteor appear
	delay (700); //delay after the town explodes
	c.clear (); //clears the console to display text
	introTextOne (); //draws the first intro text
	delay (1000); //delays to read text and before drawing desert
	desert (); //draws desert
	desertDebris (); //draws desert debris
	delay (400); //delays to show desert
	while (!startMainGame.equalsIgnoreCase ("start"))
	{
	    startMainGame = introTextFinal (); //displays final text
	} //end of while loop
	clearAnimation ();

	//STORYONE
	desertAnimation ();
	subtitlesOne ();
	animationsOne ();
	enterWToContinue ();
	desertAnimation ();
	subtitlesTwo ();
	animationsTwo ();
	enterWToContinue ();

	//HANGMAN
	while (levelHangman == 1) //while loops are to ensure that the games progress sequentially
	{
	    GuessChecker (HaikuGuesses, HaikuHint, CorrectAnswers [0], HaikuGuessesReset);

	} //end of while loop

	while (levelHangman == 2)
	{
	    GuessChecker (DwarvesGuesses, DwarvesHint, CorrectAnswers [1], DwarvesGuessesReset);
	} //end of while loop

	while (levelHangman == 3)
	{
	    GuessChecker (SphinxGuesses, SphinxHint, CorrectAnswers [2], SphinxGuessesReset);
	} //end of while loop

	while (levelHangman == 4)
	{
	    GuessChecker (RogueGuesses, RogueHint, CorrectAnswers [3], RogueGuessesReset);
	} //end of while loop

	//STORYTWO
	desertAnimation ();
	subtitlesThree ();
	chairThrow ();
	trapDoor ();
	enterWToContinue ();
	subtitlesFour ();
	desertAnimation ();
	animationsThree ();
	enterWToContinue ();

	//BOGGLE
	boggleRules ();
	boggleSetUp ();
	c.setColor (Color.black);
	c.setFont (boggleTableFont);
	boggleTable (tableLetters); //creating the table
	guessMain = getWord (); //asking for the first word
	boggleTableRefill (tableLetters); //refreshing the table that gets written over
	while (true) //used to constantly ask for more words
	{
	    correctMain = comparison (guessMain, correctWords); //finding if the word is right, wrong or "I give up"
	    if (correctMain == 1)
	    {
		totalCorrect++; //keeping tally of correct to keep the list in order
		totalPoints += points (guessMain);
	    } //end of if statement
	    else if (correctMain == -1)
		totalWrong++; //keeping tally of the amount wrong
	    else
		break; //replace with finishing method

	    c.setFont (boggleGuessFont);
	    display (guessMain, totalCorrect, correctMain, totalWrong, totalPoints); //displaying tally or list whether right or wrong

	    guessMain = getWord (); //restarting the loop by asking for another word
	    c.setFont (boggleTableFont);
	    boggleTableRefill (tableLetters); //refilling boggle table
	} //end of while loop
	giveUp (totalPoints, totalWrong);
	enterWToContinue ();
	c.clear ();

	//STORYTHREE
	desertAnimation ();
	subtitlesFive ();
	chairThrowTwo ();
	enterWToContinue ();
	desertAnimation ();
	tuskenPalace ();
	subtitlesSix ();
	animationFour ();
	enterWToContinue ();
	subtitlesSeven ();
	throneRoom ();
	delay (1000);
	throneRoomAnimation ();
	enterWToContinue ();

	//21STICKS
	printRules (); //prints rules
	graphics (stickCount); //creates graphic of sticks

	while (stickCount >= 0) //keeps going until the stickcount is zero
	{
	    thoughtDelay (1000); //delay to simulate computer thought
	    playerA = playerATurn (playerB); //gives the amount taken by the player to the CPU
	    stickCount = calculateSticks (stickCount, playerA); //caclulates new count
	    printSticks (stickCount); //prints new count
	    graphics (stickCount); //updates graphics
	    if (stickCount < 1) //stops if less than one
		break;
	    playerB = getNumSticks (); //gets the number of sticks from the player
	    stickCount = calculateSticks (stickCount, playerB); //calc stick count
	    printSticks (stickCount); //updates the count
	    graphics (stickCount); //updates the graphics
	}
	gameOver (); //ends the game

	//STORYEND
	enterWToContinue ();
	throneRoom ();
	subtitlesEight ();
	throneRoomAnimationTwo ();
	enterWToContinue ();
	desertAnimation ();
	subtitlesNine ();
	kingDeathAnimation ();
	enterWToContinue ();

	//endscreen
	end ();

    } //end of main method


    public static void clearAnimation ()
    {
	for (int i = 0 ; i < 10 ; i++) //used to slowly clear the title screen
	{
	    c.setColor (Color.white);
	    c.fillRect (0 + (120 * i), 0, 120, 1000);
	    delay (150);

	} //end of for loop
	c.clear (); //clears whole screen again to start the cursor at the top

    } //end of clearAnimation


    public static void GuessChecker (char[] answer, String prompt, String actualWord, char[] revert)  //used to check Hangman user input
    {
	int winCheck = 0; //initializes variable
	wordOneTotalAttempts = answer.length + 5; //used for the bomb graphic

	c.println (prompt); //prompt that gives user a hint
	c.println ("Press 9 for the answer (testing purposes only)"); //input to print out the answer for the word, for teacher to skip
	bombGraphicsRefresh (attempts, wordOneTotalAttempts); //used for bomb graphics

	for (int i = 0 ; i < answer.length ; i++)
	{
	    c.print (answer [i] + " "); //
	    bombGraphicsRefresh (attempts, wordOneTotalAttempts);
	} //end of for loop
	c.println (" ");
	bombGraphicsRefresh (attempts, wordOneTotalAttempts);
	while (true)
	{
	    char input = InputLetter (answer, actualWord); //ERROR FOUND THIS LINE
	    IndexChecker (actualWord, answer, input); //call method


	    for (int i = 0 ; i < actualWord.length () ; i++)
	    {
		if (answer [i] == actualWord.charAt (i))
		{
		    winCheck = 2; //checks if the word is correct
		} //end of if statement

		else
		{
		    winCheck = 1; //continues on if the word isn't correct
		    break;
		} //end of else statement
	    } //end of for loop

	    if (winCheck == 2) //prints statement if the game has been won
	    {
		c.println ("Good job, you beat the game!");
		levelHangman++; //moves onto the next level
		attempts = 0;
		delay (800); //pauses before clearing the screen and restarting
		c.clear (); //clears whole screen
		break;
	    } //end of if statement

	    if (attempts == answer.length + 5) //condition if the player loses
	    {
		for (int i = 0 ; i < answer.length ; i++)
		{
		    answer [i] = revert [i]; //clears the whole array

		} //end of for loop

		attempts = 0; //clears the guess attempts
		for (int l = 0 ; l < 10 ; l++) //makes the screen different colours to simulate explosion
		{
		    c.setColor (Color.black);
		    c.fillRect (0, 0, 1200, 600);
		    for (int k = 0 ; k < 50000000 ; k++) //end of for loop
			c.setColor (Color.white);
		    c.fillRect (0, 0, 1200, 600);
		    for (int j = 0 ; j < 50000000 ; j++) //end of for loop
			c.setColor (Color.red);
		    c.fillRect (0, 0, 1200, 600);
		    for (int z = 0 ; z < 50000000 ; z++) //end of for loop
		    {
		    } //end of for loop
		} //end of for loop
		c.clear ();
		c.println ("Sorry, you didn't get the word.");
		c.print ("Restarting the game for you...\n\n"); //statement before game continues
		delay (800); //pause before restarting
		c.clear (); //clears the screen for the restart

		break;
	    } //end of if statement

	    c.println (prompt);
	} //end of while loop
    } //end of GuessChecker


    public static char InputLetter (char[] previouslyinput, String actualWord)  //method for user input
    {
	char letter = ' ';
	String lettertester;
	int condition = 1;
	c.print ("\nGuess a lowercase letter: "); //prompt for user input
	bombGraphicsRefresh (attempts, wordOneTotalAttempts);
	lettertester = c.readLine (); //reads user input
	bombGraphicsRefresh (attempts, wordOneTotalAttempts);

	//checks if user has input more than one word
	for (int j = 0 ; j < lettertester.length () ; j++)
	{
	    if (lettertester.charAt (j) == ' ')
	    {
		condition = 2;
	    } //end of if statement

	} //end of for loop

	while (condition == 2) //error trap to ensure the user inputs a single letter
	{
	    c.print ("\nEnter ONE lowercase letter: ");
	    bombGraphicsRefresh (attempts, wordOneTotalAttempts);
	    lettertester = c.readLine ();
	    bombGraphicsRefresh (attempts, wordOneTotalAttempts);
	    for (int j = 0 ; j < lettertester.length () ; j++)
	    {
		if (lettertester.charAt (j) == ' ')
		{
		    condition = 2;
		    break;
		} //end of if statement
		condition = 1;
	    } //end of for loop
	} //end of while loop
	while (lettertester.length () > 1) //error trap to ensure the user inputs a single letter
	{
	    c.print ("\nEnter ONE lowercase letter: ");
	    bombGraphicsRefresh (attempts, wordOneTotalAttempts);
	    lettertester = c.readLine ();
	    bombGraphicsRefresh (attempts, wordOneTotalAttempts);
	} //end of while loop

	while (Character.isUpperCase (lettertester.charAt (0))) //error trap to ensure the user inputs a single letter
	{
	    c.print ("\nEnter one LOWERCASE letter: ");
	    bombGraphicsRefresh (attempts, wordOneTotalAttempts);
	    lettertester = c.readLine ();
	    bombGraphicsRefresh (attempts, wordOneTotalAttempts);
	} //end of while loop


	for (int i = 0 ; i < previouslyinput.length ; i++)
	{
	    if (letter == previouslyinput [i])
	    {
		condition = 2;
		break;
	    } //end of if statement
	} //end of for loop

	while (condition != 1) //error trap to ensure the user inputs a single letter
	{
	    c.print ("\nGuess a letter you haven't guessed already: ");
	    bombGraphicsRefresh (attempts, wordOneTotalAttempts); //test
	    letter = c.readString ().charAt (0);
	    bombGraphicsRefresh (attempts, wordOneTotalAttempts); //test
	    for (int i = 0 ; i < previouslyinput.length ; i++)
	    {
		if (letter == previouslyinput [i])
		{
		    condition = 2;
		    break;
		} //end of if statement
		condition = 1;
	    } //end of for loop
	} //end of while loop

	c.clear (); //TESTING
	letter = lettertester.charAt (0);
	if (letter == '9') //prints answer
	{
	    c.setFont (fontAnswer);
	    c.setColor (Color.black);
	    c.drawString (actualWord, 500, 300);
	} //end of if statement

	return letter; //returns letter input
    } //end of InputLetter


    public static void IndexChecker (String word, char[] correctLetters, char letterInput)  //checks if the letter is in the actual word
    {
	int startIndex = 0;
	int letterIndex = 0;
	while (true) //ensures IndexChecker keeps on going
	{
	    letterIndex = word.indexOf (letterInput, startIndex);
	    if (letterIndex == -1)
	    {
		attempts++; //increases the attempt count
		break;
	    } //end of if statement
	    else
	    {
		startIndex = letterIndex + 1; //notes where the letter is in the word, then continues searching thereafter
		correctLetters [letterIndex] = word.charAt (letterIndex); //if the letter's right, it is filled into it's correct slot

	    } //end of else statement

	} //end of while loop
	for (int i = 0 ; i < correctLetters.length ; i++)
	{
	    c.print (correctLetters [i] + " "); //prints the array including all they've gotten right plus all the blanks

	} //end of for loop
	c.println ("Inputs remaining: " + (correctLetters.length + 5 - attempts) + "/" + (correctLetters.length + 5)); //prints attempts remaining and total attempts
	c.println (" ");
	bombGraphicsRefresh (attempts, wordOneTotalAttempts); //refreshes graphic

    } //end of IndexChecker


    public static void bombGraphicsRefresh (int attempts, int totalAttempts)
    {
	c.setColor (Color.white); //covers the location of the bomb first
	c.fillRect (900, 100, 400, 400);

	c.setColor (Color.orange); //ORANGE FUSE BAR

	c.fillRect (1005, 0, 30, 20 * totalAttempts);


	c.setColor (Color.black); //BLACK BOMB SPHERE
	c.fillOval (900, totalAttempts * 20, 240, 240);

	c.setColor (Color.white); //Fuse shortening
	c.fillRect (900, 0, 200, attempts * 20);

	if (attempts > 0 ) //trail of flame for the bomb
	{
	    c.setColor (Color.red);
	    c.fillOval (1000, (20 * attempts) - 20, 40, 40);
	    c.setColor (Color.yellow);
	    c.fillOval (1010, (20 * attempts) - 20, 20, 20);
	} //end of if statement


    } //end of bombGraphicsRefresh


    public static void backGround ()  // the green and blue background
    {
	c.setColor (Color.green);
	for (int i = 0 ; i < 8 ; i++)
	{
	    c.fillOval (100 + 100 * i, 300, 300, 300);
	} //end of for loop
	c.setColor (Color.blue);
	for (int i = 0 ; i < 8 ; i++)
	{
	    c.fillOval (100 + 100 * i, 50, 300, 300);
	} //end of for loop

	c.setColor (Color.gray); //rectangles for design
	c.fillRect (450, 250, 65, 240);
	c.fillRect (520, 300, 65, 190);
	c.fillRect (720, 270, 65, 220);
	c.fillRect (590, 450, 125, 40);

	c.setColor (Color.black); //rectangles for design
	c.drawRect (450, 250, 65, 240);
	c.drawRect (520, 300, 65, 190);
	c.drawRect (720, 270, 65, 220);
	c.drawRect (590, 450, 125, 40);

	for (int i = 0 ; i < 20 ; i++)
	{
	    c.fillRect (455, 255 + (12 * i), 10, 6);
	    c.fillRect (470, 255 + (12 * i), 10, 6);
	    c.fillRect (485, 255 + (12 * i), 10, 6);
	    c.fillRect (500, 255 + (12 * i), 10, 6);
	} //end of for loop
	for (int j = 0 ; j < 16 ; j++)
	{
	    c.fillRect (525, 305 + (12 * j), 10, 6);
	    c.fillRect (540, 305 + (12 * j), 10, 6);
	    c.fillRect (555, 305 + (12 * j), 10, 6);
	    c.fillRect (570, 305 + (12 * j), 10, 6);
	} //end of for loop
	for (int k = 0 ; k < 10 ; k++)
	{
	    c.fillRect (595 + (12 * k), 455, 10, 6);
	    c.fillRect (595 + (12 * k), 465, 10, 6);
	    c.fillRect (595 + (12 * k), 475, 10, 6);
	    c.fillRect (595 + (12 * k), 485, 10, 6);
	} //end of for loop
	for (int l = 0 ; l < 18 ; l++)
	{
	    c.fillRect (725, 275 + (12 * l), 10, 6);
	    c.fillRect (740, 275 + (12 * l), 10, 6);
	    c.fillRect (755, 275 + (12 * l), 10, 6);
	    c.fillRect (770, 275 + (12 * l), 10, 6);
	}
	c.setColor (Color.blue);
	int[] x = {200, 300, 350, 400, 370};
	int[] y = {560, 500, 200, 500, 560};
	c.fillPolygon (x, y, 5);

	c.setColor (Color.red);
	c.fillRect (220, 470, 200, 10);
	c.drawArc (220, 420, 200, 100, 0, 180);
	c.fillRect (220, 450, 10, 60);
	c.fillRect (420, 450, 10, 60);
	c.fillRect (320, 410, 10, 100);

    } //end of backGround


    public static void introTextOne ()  //change this to change the first message
    {

	c.setFont (fontIntro);
	c.drawString ("THE END HAS ARRIVED", 270, 300); //prints statement
    } //end of introTextOne


    public static String introTextFinal ()  // final game msg
    {
	String start;
	c.setColor (Color.red);
	c.drawString ("ENTER \"START\" TO", 340, 170);
	c.drawString ("BEGIN YOUR JOURNEY", 280, 230);
	start = c.readString ();
	return start;
    } //end of introTextFinal


    public static void desert ()  //important draws desert
    {
	c.setColor (Color.yellow);
	for (int i = 0 ; i < 8 ; i++)
	{
	    c.fillOval (100 + 100 * i, 300, 300, 300);
	} //end of for loop
	c.setColor (Color.cyan);
	for (int i = 0 ; i < 8 ; i++)
	{
	    c.fillOval (100 + 100 * i, 50, 300, 300);
	} //end of for loop
	Color crater1 = new Color (204, 112, 0);
	Color crater2 = new Color (153, 84, 0);
	Color crater3 = new Color (65, 23, 5);
	c.setColor (crater1);
	c.fillOval (550, 400, 120, 80);
	c.setColor (crater2);
	c.fillOval (590, 410, 70, 50);
	c.setColor (crater3);
	c.fillOval (620, 415, 20, 20);

	Color rust = new Color (183, 65, 14);
	c.setColor (rust);
	c.fillRect (220, 470, 200, 10);
	c.drawArc (220, 420, 200, 100, 0, 180);
	c.fillRect (220, 450, 10, 60);
	c.fillRect (420, 450, 10, 60);
	c.fillRect (320, 410, 10, 100);

    } //end of desert


    public static void desertDebris ()
    {
	for (int w = 0 ; w < 9 ; w++) //draws the random building debris
	{
	    int randHeight = (int) (200 * Math.random ()) + 50;
	    int randY = (int) (100 * Math.random ()) + 300;
	    c.setColor (Color.orange);
	    c.fillRect (450 + (38 * w), randY, 10, randHeight);

	} //end of for loop
	for (int w = 0 ; w < 9 ; w++) //draws random building debris
	{
	    int randHeight = (int) (30 * Math.random ()) + 20;
	    int randY = (int) (150 * Math.random ()) + 350;
	    int randWidth = (int) (30 * Math.random ()) + 10;
	    c.setColor (Color.gray);
	    c.fillRect (350 + (70 * w), randY, randWidth, randHeight);
	} //end of for loop

    } //end of desertDebris


    public static void meteor ()  //draws the meteor incoming
    {
	int stop = 0;
	int x = 0, y = 0;
	while (stop < 80)
	{
	    c.setColor (Color.red);
	    c.fillOval (x, y, 40, 40);
	    for (int i = 0 ; i < 20000000 ; i++) //end of for loop
		c.setColor (Color.orange);
	    c.fillOval (x, y, 40, 40);
	    x += 4;
	    y += 2;
	    for (int w = 0 ; w < 2000000 ; w++) //end of for loop
		c.setColor (Color.yellow);
	    c.fillOval (x, y, 40, 40);

	    x += 4;
	    y += 2;
	    stop++;
	} //end of end of while loop
	for (int l = 0 ; l < 10 ; l++) //makes the screen different colours to simulate explosion
	{
	    c.setColor (Color.black);
	    c.fillRect (0, 0, 1200, 600);
	    for (int k = 0 ; k < 100000000 ; k++) //end of for loop
		c.setColor (Color.white);
	    c.fillRect (0, 0, 1200, 600);
	    for (int j = 0 ; j < 100000000 ; j++) //end of for loop
		c.setColor (Color.red);
	    c.fillRect (0, 0, 1200, 600);
	    for (int z = 0 ; z < 100000000 ; z++) //end of for loop
	    {
	    }
	} //end of for loop



    } //end of meteor


    public static void delay (int time)  //allows for delays
    {
	for (int i = 0 ; i < 4 ; i++)
	{
	    try
	    {
		Thread.sleep (time);
	    }
	    catch (InterruptedException e)
	    {
	    }
	} //end of for loop
    } //end of delay


    public static String getWord ()  //used to get a new word
    {
	String enteredWord;
	enteredWord = c.readLine ();
	return enteredWord;
    } //end of getWord


    public static int comparison (String guess, String[] correctWords)  //used to compare the guess with the string[] of correct words
    {
	int correctGuess = -1; //-1 is false, 0 is "I give up" and 1 is true
	if (guess.equalsIgnoreCase ("I give up"))
	    correctGuess = 0;

	for (int i = 0 ; i < correctWords.length ; i++)
	{
	    if (correctWords [i].equalsIgnoreCase (guess))
	    {
		correctGuess = 1;
		correctWords [i] = ""; //removes the word so it is not used twice
		break; //exits the loop to save time

	    } //end of if statement
	} //end of for loop
	return correctGuess;
    } //end of comparison


    public static void display (String rightGuess, int numRightGuesses, int isCorrect, int totalWrong, int points)  //displays either a number or a list depending if the guess was right or wrong
    {
	if (isCorrect == 1) //if it is correct it will display points and the list
	{
	    c.drawString (rightGuess, 550, 20 + (20 * numRightGuesses));
	    c.setColor (Color.red);
	    c.fillRect (340, 0, 110, 30);
	    c.setColor (Color.black);
	    c.drawString (points + " points", 350, 20);
	} //end of if statement
	else //if wrong it will only display tally
	{
	    c.setColor (Color.white);
	    c.fillRect (1050, 25, 100, 100);
	    c.setColor (Color.black);
	    String wrongGuess = String.valueOf (totalWrong);
	    c.drawString (wrongGuess, 1100, 40);
	} //end of else statement
    } //end of display


    public static int points (String rightAnswer)  //used to determine the amount of points awarded based on length of word
    {
	int points = 0;
	if (rightAnswer.length () > 6)
	    points = 60;
	else if (rightAnswer.length () > 5)
	    points = 50;
	else if (rightAnswer.length () > 4)
	    points = 40;
	else if (rightAnswer.length () > 3)
	    points = 30;
	else if (rightAnswer.length () > 2)
	    points = 20;
	else
	    points = 10;
	return points;
    } //end of points


    public static void giveUp (int pointsEarned, int guessWrong)  //possibly change the ending based on the story/alter the outcome
    {

	c.clear ();

	if (pointsEarned > 200) //printed statement if they did very well in the game
	    c.println ("Incredible. You sir are a Boggle professional. The heroes are now able to progress with confidence visible in their strides.");
	else //prined statement if they didn't do very well
	    c.println ("To be honest, it could have been done a lot better.\nYou got " + guessWrong + " guesses wrong, so at least we know you tried.\nBecause of this, our heroes struggled greatly in their conflict\nand barely came out alive.");
    } //end of giveUp


    public static void boggleRules ()  //boggle instruction
    {
	c.println ("Please enter as many words as possible\nusing adjacent letters\nType \"I give up\" when finished\n7 letters = 60p\n6 letters = 50p\n5 letters = 40p\n4 letters = 30p\n3 letters = 20p"); //laying out the rules

    } //end of boggleRules


    public static void boggleSetUp ()  //part of setting up the game graphics
    {
	c.setColor (Color.white);
	c.fillRect (480, 0, 155, 20);
	c.fillRect (1040, 0, 155, 20);

	c.setFont (boggleGuessFont); //setting up the lists of right and wrong
	c.setColor (Color.red);
	c.drawString ("Correct Guesses", 485, 20);
	c.drawString ("Wrong Guesses", 1050, 20);
    } //end of boggleSetUp


    public static void boggleTable (char[] letters)  //graphics for the boggle board
    {
	c.setColor (Color.black);
	for (int i = 0 ; i < 4 ; i++) //each for loop draws the next 4 letters of the board(each row)
	{
	    char e = letters [i];
	    c.drawString (String.valueOf (e), 660 + 100 * i, 80);
	    for (int j = 0 ; j < 100000000 ; j++)
	    {
	    }
	}
	for (int j = 0 ; j < 4 ; j++)
	{
	    char e = letters [j + 4];
	    c.drawString (String.valueOf (e), 660 + 100 * j, 180);
	    for (int l = 0 ; l < 100000000 ; l++)
	    {
	    }
	}
	for (int k = 0 ; k < 4 ; k++)
	{
	    char e = letters [k + 8];
	    c.drawString (String.valueOf (e), 660 + 100 * k, 280);
	    for (int j = 0 ; j < 100000000 ; j++)
	    {
	    }
	}
	for (int z = 0 ; z < 4 ; z++)
	{
	    char e = letters [z + 12];
	    c.drawString (String.valueOf (e), 660 + 100 * z, 380);
	}
	for (int i = 0 ; i < 4 ; i++) //the dimensions of the boxes are 160x by 100y
	{
	    c.drawRect (640, 0 + (100 * i), 400, 100);
	    for (int j = 0 ; j < 200000000 ; j++)
	    {
	    }
	}
	for (int k = 0 ; k < 4 ; k++)
	{
	    c.drawRect (640 + (100 * k), 0, 100, 400);
	    for (int j = 0 ; j < 200000000 ; j++)
	    {
	    }
	}
    } //end of boggleTable


    public static void boggleTableRefill (char[] letters)  //used to display the basic board without animation, for refreshing the graphic
    {


	c.setColor (Color.white);
	c.fillRect (640, 0, 400, 400);
	boggleSetUp ();

	c.setFont (boggleTableFont);
	c.setColor (Color.black);



	for (int i = 0 ; i < 4 ; i++)
	{
	    char e = letters [i];
	    c.drawString (String.valueOf (e), 660 + 100 * i, 80);

	}
	for (int j = 0 ; j < 4 ; j++)
	{
	    char e = letters [j + 4];
	    c.drawString (String.valueOf (e), 660 + 100 * j, 180);

	}
	for (int k = 0 ; k < 4 ; k++)
	{
	    char e = letters [k + 8];
	    c.drawString (String.valueOf (e), 660 + 100 * k, 280);

	}
	for (int z = 0 ; z < 4 ; z++)
	{
	    char e = letters [z + 12];
	    c.drawString (String.valueOf (e), 660 + 100 * z, 380);
	}
	for (int i = 0 ; i < 4 ; i++) //the dimensions of the boxes are 160x by 100y
	{
	    c.drawRect (640, 0 + (100 * i), 400, 100);

	}
	for (int k = 0 ; k < 4 ; k++)
	    c.drawRect (640 + (100 * k), 0, 100, 400);

    } //end of boggleTableRefill


    public static int getNumSticks ()  //gets the number of sticks from the player
    {
	int inputSticks = 0;
	c.println ("How many Sticks are you going to pick? No less than one or more than four!");
	inputSticks = c.readInt ();
	//error trapping
	while (inputSticks < 1 || inputSticks > 4)
	{
	    c.println ("No less than one or more than four!");
	    inputSticks = c.readInt ();
	} //end of while loop
	return inputSticks;
    } //end of getNumSticks


    public static void printSticks (int numSticks)  //writes the amount of sticks
    {
	if (numSticks > 1)
	    c.println ("There are " + numSticks + " sticks remaining!");
	else if (numSticks == 1)
	    c.println ("There is only 1 stick remaining!");
	else
	    c.println ("There are no sticks remaining!");
    } //end of printSticks


    public static int playerATurn (int playerBInput)  //gets the input from the computer
    {
	int playerAInput = 1;
	if (playerBInput != 0)
	    playerAInput = (5 - playerBInput);
	c.println ("I choose " + playerAInput + "!");
	return playerAInput;
    } //end of playerATurn


    public static int calculateSticks (int stickTotal, int playerInput)  //calculates the remaining stickcount
    {
	int sticksLeft = stickTotal - playerInput;
	return sticksLeft;
    } //end of calculateSticks


    public static void printRules ()  //printed introduction
    {
	c.println ("I, the Tusken Raider king, challenge you\nto a game of 21 sticks\nThe last stick must be chosen in order to win!\nI go first!");
    } //end of printrules


    public static void gameOver ()  //printed when user loses
    {
	c.println ("Ha Ha Ha!! As far as is known by man, I am unbeatable!");
    } //end of gameOver


    public static void thoughtDelay (int time)  //simulates the computers thinking
    {
	c.print ("hmmmm");
	for (int i = 0 ; i < 4 ; i++)
	{
	    try
	    {
		Thread.sleep (time);
	    }
	    catch (InterruptedException e)
	    {
	    }
	    c.print (".");

	}
    } //end of thoughtDelay


    public static void graphics (int numSticks)  //displays the sticks
    {

	c.setFont (stickFont);
	c.setColor (Color.white);
	c.fillRect (550, 20, 800, 100);
	for (int i = 0 ; i < numSticks ; i++)
	{
	    c.setColor (Color.yellow);
	    c.fillRect (550 + (30 * i), 20, 20, 60);
	    c.setColor (Color.black);
	    c.drawRect (550 + (30 * i), 20, 20, 60);
	    c.drawString (String.valueOf (1 + i), 555 + (30 * i), 54);
	}
    } //end of graphics


    public static void subtitlesOne ()  //subtitles for the game
    {
	c.setFont (subtitlesFont);

	c.setColor (Color.black);
	c.drawRect (20, 420, 1170, 125);

	c.drawString ("We meet our heroes blindly walking through the desert. Sadly, these young to-be adventurers have no idea what great ", 25, 440);
	c.drawString ("dangers lie ahead for the two of them. The one wearing white robes, Grant, was a chair salesman before the end times.", 25, 460);
	c.drawString ("He came to know his partner Arty after offering him a job carrying his chairs, allowing Arty to leave his orphanage.", 25, 480);
	c.drawString ("Because of this, Arty feels as though he is in debt to Grant for giving him the opportunity. So, he has become more of an indentured", 25, 500);
	c.drawString ("servant than an employee. Anyway, these two are not sure how their bustling city has turned into a wasteland.", 25, 520);
	c.drawString ("All they heard was a bang...", 25, 540);

    } //end of subtitlesOne


    public static void enterWToContinue ()  //continues storyline
    {
	char storyProgress;
	c.println ("Enter 'w' to continue");
	storyProgress = c.readChar ();
	//error trapping
	while (storyProgress != 'w')
	{
	    storyProgress = c.readChar ();
	}
	c.clear ();
    } //end of enterWToContinue


    public static void subtitlesTwo ()  //subtitles for the game
    {
	c.setFont (subtitlesFont);

	c.setColor (Color.black);
	c.drawRect (20, 420, 1170, 125);

	c.drawString ("Then the Tusken Raiders appeared! They were covered in rags and other scraps of clothing, and wore huge goggles.", 25, 440);
	c.drawString ("Our heroes were absolutely terrified of these gross creatures, so they ran and hid behind a sand dune. The Raiders assumed they ", 25, 460);
	c.drawString ("won, so they started celebrating their victory. After some encouraging and lots of tears from Arty, they decided to go back and fight.", 25, 480);
	c.drawString ("Of course, this was not without Grant mocking Arty for sounding like a goat.", 25, 500);
	c.drawString ("Grant needs Arty because he carries Grant's primary weapon: a practically infinite supply of chairs.", 25, 520);
	c.drawString ("Will our heroes be successful in their battle? The answer will be up to you and your skills in HANGMAN!", 25, 540);
    } //end of subtitlesTwo


    public static void animationsOne ()  //Characters moving
    {
	int stop = 0;
	int x = 0;
	while (stop < 90)
	{

	    for (int i = 0 ; i < 30000000 ; i++)
	    {
	    }
	    c.setColor (Color.white);
	    c.fillRect (50, 50, 1200, 200);

	    desertAnimation ();

	    characterGrant (false, x + 150, 100);
	    characterArty (false, x + 100, 150);
	    for (int i = 0 ; i < 30000000 ; i++)
	    {
	    }

	    x += 10;
	    stop++;
	} //end of while loop

    } //end of animationsOne


    public static void animationsTwo ()  //Characters moving
    {
	int stop = 0;
	int x = 900;
	while (stop < 30)
	{
	    characterGrant (false, x + 150, 100);
	    characterArty (false, x + 100, 150);

	    for (int i = 0 ; i < 30000000 ; i++)
	    {
	    }


	    c.setColor (Color.white);
	    c.fillRect (0, 50, 1400, 200);
	    desertAnimation ();

	    for (int i = 0 ; i < 30000000 ; i++)
	    {
	    }

	    characterTuskenRaider (true, x + 250, 100);
	    characterTuskenRaider (true, x + 200, 150);

	    x -= 30;
	    stop++;
	} //end of while loop
    } //end of animationsTwo


    public static void desertAnimation ()  //draws simple yellow background
    {
	c.setColor (Color.yellow);
	for (int i = 0 ; i < 8 ; i++)
	{
	    c.fillOval (100 + 100 * i, 100, 300, 300);
	} //end of for loop
    } //end of desertAnimation


    public static void characterGrant (boolean openMouth, int x, int y)  //Grant character design
    {
	Color peach = new Color (255, 218, 185);
	Color mouth = new Color (255, 173, 96);
	Color hair = new Color (160, 82, 45);
	c.setColor (peach);
	c.fillOval (x, y, 35, 35);

	c.setColor (Color.white);
	c.fillRoundRect (x + 3, y + 34, 30, 60, 10, 10);
	//arm 1
	c.fillRoundRect (x - 7, y + 34, 10, 30, 10, 10);
	//arm 2
	c.fillRoundRect (x + 33, y + 34, 10, 30, 10, 10);

	//facial details
	//eyes
	c.setColor (Color.blue);
	c.fillOval (x + 10, y + 10, 5, 5);
	c.fillOval (x + 22, y + 10, 5, 5);
	//nose
	c.setColor (Color.black);
	c.drawOval (x + 16, y + 15, 5, 5);
	//mouth
	if (openMouth == true)
	{
	    c.setColor (mouth);
	    c.fillOval (x + 8, y + 20, 20, 10);
	    c.setColor (Color.black);
	    c.drawOval (x + 8, y + 20, 20, 10);
	} //end of if statement

	else
	{
	    c.setColor (mouth);
	    c.fillRect (x + 11, y + 23, 15, 3);
	} //end of else statement

	c.setColor (Color.black);
	//outlines
	//legs
	c.drawRoundRect (x + 3, y + 62, 15, 30, 10, 10);
	c.drawRoundRect (x + 18, y + 62, 15, 30, 10, 10);
	//arm 1
	c.drawRoundRect (x - 7, y + 34, 10, 30, 10, 10);
	//arm 2
	c.drawRoundRect (x + 33, y + 34, 10, 30, 10, 10);
	//head
	c.drawOval (x, y, 35, 35);
	//body
	c.drawRoundRect (x + 3, y + 34, 30, 30, 10, 10);

	//hair
	c.setColor (hair);
	c.fillOval (x, y, 10, 10);
	c.fillOval (x + 5, y - 5, 10, 10);
	c.fillOval (x + 10, y - 7, 10, 10);
	c.fillOval (x + 15, y - 7, 10, 10);
	c.fillOval (x + 20, y - 7, 10, 10);
	c.fillOval (x + 25, y - 5, 10, 10);
    } //end of characterGrant


    public static void characterArty (boolean openMouth, int x, int y)  //Arty character design
    {
	Color peach = new Color (255, 218, 185);
	Color mouth = new Color (255, 173, 96);
	Color hair = new Color (160, 82, 45);
	Color dirt = new Color (210, 180, 140);

	//backpack
	c.setColor (dirt);
	c.fillRoundRect (x - 6, y + 10, 48, 60, 10, 10);

	c.setColor (peach);
	c.fillOval (x, y, 35, 35);

	c.setColor (Color.lightGray);
	c.fillRoundRect (x + 3, y + 34, 30, 60, 10, 10);
	//arm 1
	c.fillRoundRect (x - 7, y + 34, 10, 30, 10, 10);
	//arm 2
	c.fillRoundRect (x + 33, y + 34, 10, 30, 10, 10);

	//dirt
	c.setColor (dirt);
	c.fillOval (x + 10, y + 70, 8, 8);
	c.fillOval (x + 10, y + 40, 8, 8);
	c.fillOval (x + 20, y + 50, 8, 8);

	//eyes
	c.setColor (Color.blue);
	c.fillOval (x + 10, y + 10, 5, 5);
	c.fillOval (x + 22, y + 10, 5, 5);
	//tears
	c.fillOval (x + 8, y + 15, 3, 3);
	c.fillOval (x + 7, y + 18, 3, 3);
	c.fillOval (x + 24, y + 15, 3, 3);
	c.fillOval (x + 26, y + 18, 3, 3);

	//nose
	c.setColor (Color.black);
	c.drawOval (x + 16, y + 15, 5, 5);
	//mouth
	if (openMouth == true)
	{
	    c.setColor (mouth);
	    c.fillOval (x + 8, y + 20, 20, 10);
	    c.setColor (Color.black);
	    c.drawOval (x + 8, y + 20, 20, 10);
	}


	else
	{
	    c.setColor (mouth);
	    c.fillRect (x + 11, y + 23, 15, 3);
	}


	c.setColor (Color.black);
	//outlines
	//legs
	c.drawRoundRect (x + 3, y + 62, 15, 30, 10, 10);
	c.drawRoundRect (x + 18, y + 62, 15, 30, 10, 10);
	//arm 1
	c.drawRoundRect (x - 7, y + 34, 10, 30, 10, 10);
	//arm 2
	c.drawRoundRect (x + 33, y + 34, 10, 30, 10, 10);
	//head
	c.drawOval (x, y, 35, 35);
	//body
	c.drawRoundRect (x + 3, y + 34, 30, 30, 10, 10);

	//hair
	c.setColor (hair);

	c.fillOval (x + 5, y - 3, 10, 10);

	c.fillOval (x + 15, y - 5, 10, 10);

	c.fillOval (x + 25, y - 3, 10, 10);


    } //end of characterArty


    public static void characterTuskenRaider (boolean alive, int x, int y)  //Tusken Raider character design
    {
	Color bags = new Color (205, 133, 63);
	Color body = new Color (222, 184, 135);
	if (alive == true)
	{

	    c.setColor (body);
	    //body
	    c.fillRoundRect (x + 3, y + 62, 15, 30, 10, 10);
	    c.fillRoundRect (x + 18, y + 62, 15, 30, 10, 10);
	    //arm 1
	    c.fillRoundRect (x - 7, y + 34, 10, 30, 10, 10);
	    //arm 2
	    c.fillRoundRect (x + 33, y + 34, 10, 30, 10, 10);
	    //head
	    c.fillOval (x, y, 35, 35);
	    //body
	    c.fillRoundRect (x + 3, y + 34, 30, 30, 10, 10);

	    //bags
	    c.setColor (bags);
	    c.fillRect (x + 3, y + 55, 30, 5);
	    c.fillRect (x + 22, y + 35, 5, 25);
	    c.fillRect (x + 15, y, 5, 25);
	    c.fillRect (x + 15, y + 25, 17, 5);

	    //mouth
	    c.setColor (Color.white);
	    c.fillOval (x + 8, y + 20, 10, 12);
	    c.setColor (Color.black);
	    c.drawOval (x + 8, y + 20, 10, 12);

	    //weapon
	    c.setColor (Color.gray);
	    c.fillRect (x - 10, y + 5, 5, 90);
	    c.fillRect (x - 15, y + 15, 15, 5);

	    //eyes
	    c.setColor (Color.blue);
	    c.fillOval (x + 5, y + 10, 10, 10);
	    c.fillOval (x + 17, y + 10, 10, 10);

	    c.setColor (Color.black);
	    //outlines
	    //legs
	    c.drawRoundRect (x + 3, y + 62, 15, 30, 10, 10);
	    c.drawRoundRect (x + 18, y + 62, 15, 30, 10, 10);
	    //arm 1
	    c.drawRoundRect (x - 7, y + 34, 10, 30, 10, 10);
	    //arm 2
	    c.drawRoundRect (x + 33, y + 34, 10, 30, 10, 10);
	    //head
	    c.drawOval (x, y, 35, 35);
	    //body
	    c.drawRoundRect (x + 3, y + 34, 30, 30, 10, 10);
	    //eyes
	    c.drawOval (x + 5, y + 10, 10, 10);
	    c.drawOval (x + 17, y + 10, 10, 10);
	}


	else //rotates the character 90 degrees (dead)
	{
	    c.setColor (body);
	    //outlines
	    //legs
	    c.fillRoundRect (x + 62, y + 3, 30, 15, 10, 10);
	    c.fillRoundRect (x + 62, y + 18, 30, 15, 10, 10);
	    //arm 1
	    c.fillRoundRect (x + 34, y - 7, 10, 30, 10, 10);
	    //arm 2
	    c.fillRoundRect (x + 34, y + 33, 10, 30, 10, 10);
	    //head
	    c.fillOval (x, y, 35, 35);
	    //body
	    c.fillRoundRect (x + 34, y + 3, 30, 30, 10, 10);

	    c.setColor (bags);
	    //bags
	    c.setColor (bags);
	    c.fillRect (x + 55, y + 3, 5, 30);
	    c.fillRect (x + 35, y + 22, 25, 5);
	    c.fillRect (x, y + 15, 25, 5);
	    c.fillRect (x + 25, y + 15, 5, 17);

	    //eyes
	    c.setColor (Color.red);
	    c.fillOval (x + 10, y + 5, 10, 10);
	    c.fillOval (x + 10, y + 17, 10, 10);

	    //mouth
	    c.setColor (Color.white);
	    c.fillOval (x + 20, y + 8, 10, 12);
	    c.setColor (Color.black);
	    c.drawOval (x + 20, y + 8, 10, 12);

	    c.setColor (Color.black);
	    //outlines
	    //legs
	    c.drawRoundRect (x + 62, y + 3, 30, 15, 10, 10);
	    c.drawRoundRect (x + 62, y + 18, 30, 15, 10, 10);
	    //arm 1
	    c.drawRoundRect (x + 34, y - 7, 10, 30, 10, 10);
	    //arm 2
	    c.drawRoundRect (x + 34, y + 33, 10, 30, 10, 10);
	    //head
	    c.drawOval (x, y, 35, 35);
	    //body
	    c.drawRoundRect (x + 34, y + 3, 30, 30, 10, 10);
	    //eyes
	    c.drawOval (x + 10, y + 5, 10, 10);
	    c.drawOval (x + 10, y + 17, 10, 10);

	} //end of else statement
    } //end of characterTuskenRaider


    public static void subtitlesThree ()  //subtitles for the game
    {
	c.setFont (subtitlesFont);

	c.setColor (Color.black);
	c.drawRect (20, 420, 1170, 125);

	c.drawString ("With the Raiders celebrating, it was the perfect time for a sneak attack. With a single chair throw, they were all ", 25, 440);
	c.drawString ("knocked out cold. After some time had passed wandering the open desert, they reached a small house that had been concealed by", 25, 460);
	c.drawString ("the sand. After knocking, they were immediately pulled inside, where they saw a group of people of all ages making shelter.", 25, 480);
	c.drawString ("They explained to Grant and Arty how the world had been taken over in weeks by a surprise invasion, and how it was only a ", 25, 500);
	c.drawString ("matter of time before they were found. They knew their mission: to stop the Raiders at any cost. Arty was hesitant, and at this", 25, 520);
	c.drawString ("point the stress had made him communicate entirely through cries and sobs. But no, it was their duty to save the world.", 25, 540);
    } //end of subtitlesThree


    public static void subtitlesFour ()  //subtitles for the game
    {
	c.setFont (subtitlesFont);

	c.setColor (Color.black);
	c.drawRect (20, 420, 1170, 125);

	c.drawString ("For the next six months, Grant and Arty would embark on this quest. Every day, they got a little bit closer to the Tusken", 25, 440);
	c.drawString ("Raider King. Once they dealt with the leader, the Tusken Raiders would not be able to keep their tight grasp on Earth.", 25, 460);
	c.drawString ("Occasionally, our heroes would run into packs of them. They are extremely deceptive, by moving in lines to conceal their", 25, 480);
	c.drawString ("numbers. They would be sent kilometers in the wrong direction, running for their lives. It's honestly incredible that Arty", 25, 500);
	c.drawString ("was still alive, since Grant kept pushing him so hard. This is just one of the worse encounters they have faced...", 25, 520);
	c.drawString ("Their success depends on your BOGGLE skills! Will they come out with confidence or with imprisonment?!", 25, 540);
    } //end of subtitlesFour


    public static void trapDoor ()  //drawing the trapdoor
    {

	for (int i = 0 ; i < 8 ; i++)
	{
	    c.setColor (Color.black);
	    c.drawRect (599, 199, 51, 101);
	    c.setColor (Color.orange);
	    c.fillRect (600, 200, 50, 100);
	    c.setColor (Color.black);
	    c.fillRect (620, 250, 10, 10);

	    c.setColor (Color.yellow);
	    c.fillOval (580 - (15 * i), 180, 100, 150);
	    delay (200);

	} //end of for loop
    } //end of trapDoor


    public static void animationsThree ()  //Tusken Raider locations
    {
	characterTuskenRaider (true, 850, 100);
	characterTuskenRaider (true, 650, 200);
	characterTuskenRaider (true, 550, 150);
    } //end of animationsThree


    public static void chairThrow ()  //chair throwing
    {
	int stop = 0;
	int x = 0;

	characterTuskenRaider (true, 850, 100);
	characterTuskenRaider (true, 800, 150);

	while (stop < 25)
	{

	    characterTuskenRaider (true, 850, 100);
	    characterTuskenRaider (true, 800, 150);

	    for (int i = 0 ; i < 30000000 ; i++)
	    {
	    }
	    c.setColor (Color.white);
	    c.fillRect (50, 50, 1200, 200);
	    desertAnimation ();
	    chair (x + 150, 100);
	    for (int i = 0 ; i < 30000000 ; i++)
	    {
	    }

	    x += 30;
	    stop++;
	}
	characterTuskenRaider (false, 850, 200);
	characterTuskenRaider (false, 800, 250);
    } //end of chairThrow


    public static void chair (int x, int y)  //chair drawing
    {
	c.setColor (Color.gray);
	c.fillRect (x + 50, y, 10, 50);
	c.fillRect (x, y + 50, 60, 10);
	c.fillRect (x, y + 60, 10, 50);
	c.fillRect (x + 50, y + 60, 10, 50);
    } //end of chair


    public static void subtitlesFive ()  //subtitles for the game
    {
	c.setFont (subtitlesFont);

	c.setColor (Color.black);
	c.drawRect (20, 420, 1170, 125);

	c.drawString ("Nevertheless, our heroes were victorious. All it took was one chair from Grant, who had become very good ", 25, 440);
	c.drawString ("at throwing chairs. Arty, however, had not become any better at coping with his crippling anxiety. ", 25, 460);
	c.drawString ("After many grueling days in the sun, they finally reached the Tusken Palace. On every balcony you could see a", 25, 480);
	c.drawString ("Tusken Raider standing guard. It seems like the King was expecting them. However, what happens next changed the course of", 25, 500);
	c.drawString ("their entire story...", 25, 520);
	c.drawString ("", 25, 540);
    } //end of subtitlesFive


    public static void subtitlesSix ()  //subtitles for the game
    {
	c.setFont (subtitlesFont);

	c.setColor (Color.black);
	c.drawRect (20, 420, 1170, 125);

	c.drawString ("While they were both in awe of the palace, a Tusken raider snuck up and kidnapped Arty!!!", 25, 440);
	c.drawString ("Grant was bewildered, but he still could have saved Arty if he chased the Raider. However, he thought that was too much", 25, 460);
	c.drawString ("work. Not long after, Grant realizes how poorly he had been treating Arty so he decided to go rescue him. Since Arty had", 25, 480);
	c.drawString ("his chairs, he would have to go in sneaky-beaky. He managed to fit himself through a palace window, and was greeted", 25, 500);
	c.drawString ("by a fabulously decorated room. The palace felt like a maze, with room after room of plush velvet carpet. Eventually, Grant", 25, 520);
	c.drawString ("reached two doors, with one of them being 12 feet tall. He opened the largest door, only to find... ", 25, 540);

    } //end of subtitlesSix


    public static void subtitlesSeven ()  //subtitles for the game
    {
	c.setFont (subtitlesFont);

	c.setColor (Color.black);
	c.drawRect (20, 420, 1170, 125);

	c.drawString ("...a room full of enemies. They were all waiting for him, sitting on metal folding chairs. At the back of the room was the", 25, 440);
	c.drawString ("Tusken Raider King. Suspended from the ceiling in a cage was his pal, Arty. He cried tears of joy, but both of them knew", 25, 460);
	c.drawString ("Grant was toast. But then Grant had an idea!! He ran right at the mob of enemies. Arty thought he had gone insane. ", 25, 480);
	c.drawString ("Grant picked up one of the folding chairs, then went to town on the Tusken Raiders. ", 25, 500);
	c.drawString ("Soon, all that was left was Grant, Arty and the King. Grant was desperate, so he came up with a plan to deal with", 25, 520);
	c.drawString ("the King...", 25, 540);

    } //end of subtitlesSeven


    public static void chairThrowTwo ()  //chair throwing
    {
	int stop = 0;
	int x = 0;

	while (stop < 20)
	{

	    characterTuskenRaider (true, 850, 100);
	    characterTuskenRaider (true, 650, 200);
	    characterTuskenRaider (true, 550, 150);

	    for (int i = 0 ; i < 30000000 ; i++)
	    {
	    }
	    c.setColor (Color.white);
	    c.fillRect (50, 50, 1200, 200);
	    desertAnimation ();
	    chair (x + 150, 100);
	    for (int i = 0 ; i < 30000000 ; i++)
	    {
	    }

	    x += 30;
	    stop++;
	}
	characterTuskenRaider (false, 850, 150);
	characterTuskenRaider (false, 650, 250);
	characterTuskenRaider (false, 550, 200);
    } //end of chairThrowTwo


    public static void tuskenPalace ()  //throne room design
    {
	Color details = new Color (160, 82, 45);
	int[] xRoof = {760, 895, 828};
	int[] yRoof = {150, 150, 100};

	int[] xDoor = {790, 790, 828, 865, 865};
	int[] yDoor = {270, 180, 160, 180, 270};
	//body
	c.setColor (Color.white);
	//base
	c.fillRect (680, 270, 300, 20);
	//pillars
	c.fillRect (700, 150, 20, 120);
	c.fillRect (740, 200, 15, 70);
	c.fillRect (900, 200, 15, 70);
	c.fillRect (940, 150, 20, 120);
	//middle
	c.fillRect (770, 150, 115, 120);

	//outlines
	c.setColor (Color.black);
	//base
	c.drawRect (680, 270, 300, 20);
	//pillars
	c.drawRect (700, 150, 20, 120);
	c.drawRect (740, 200, 15, 70);
	c.drawRect (900, 200, 15, 70);
	c.drawRect (940, 150, 20, 120);
	//middle
	c.drawRect (770, 150, 115, 120);
	//roof
	c.drawPolygon (xRoof, yRoof, 3);
	//door
	c.drawPolygon (xDoor, yDoor, 5);

	//details
	//roof
	c.setColor (details);
	c.fillPolygon (xRoof, yRoof, 3);
	//door
	c.fillPolygon (xDoor, yDoor, 5);
    } //end of tuskenPalace


    public static void throneRoom ()  //throne room design
    {
	Color walls = new Color (238, 233, 233);
	Color floor = new Color (139, 137, 137);
	Color throne = new Color (205, 175, 149);

	int[] xFloor = {100, 250, 700, 900};
	int[] yFloor = {400, 300, 300, 400};
	int[] xGlassDoor = {720, 720, 880, 880};
	int[] yGlassDoor = {320, 100, 160, 390};

	c.setColor (walls);
	c.fillRect (100, 0, 800, 400);

	c.setColor (floor);
	c.fillPolygon (xFloor, yFloor, 4);

	//windows
	c.setColor (Color.cyan);
	c.fillRect (300, 100, 100, 100);
	c.fillRect (420, 100, 100, 100);
	c.fillRect (540, 100, 100, 100);
	//slidingdoor
	c.fillPolygon (xGlassDoor, yGlassDoor, 4);

	//ArtyCage
	characterArty (true, 495, 0);

	c.setColor (Color.black);
	c.fillRect (465, 85, 90, 10);

	c.fillRect (465, 0, 10, 85);
	c.fillRect (465 + (10 * 2), 0, 10, 85);
	c.fillRect (465 + (10 * 4), 0, 10, 85);
	c.fillRect (465 + (10 * 6), 0, 10, 85);
	c.fillRect (465 + (10 * 8), 0, 10, 85);

	//outline
	c.setColor (Color.black);
	//door
	c.drawPolygon (xGlassDoor, yGlassDoor, 4);
	//room
	c.drawRect (100, 0, 800, 400);
	c.drawLine (250, 0, 250, 300);
	c.drawLine (700, 0, 700, 300);
	//windows
	c.drawRect (300, 100, 100, 100);
	c.drawRect (420, 100, 100, 100);
	c.drawRect (540, 100, 100, 100);

	//throneoutline
	c.drawRect (649, 269, 101, 81);
	c.drawRect (749, 149, 51, 151);

	//throne
	c.setColor (throne);
	c.fillRect (650, 270, 100, 80);
	c.fillRect (700, 300, 100, 80);
	c.fillRect (750, 150, 50, 150);

	//throneoutline2
	c.setColor (Color.black);
	c.drawRect (699, 299, 101, 81);
    } //end of throneRoom


    public static void throneRoomAnimation ()  //throne room scene
    {
	//enemies
	characterTuskenRaider (true, 450, 270);
	characterTuskenRaider (true, 400, 260);
	characterTuskenRaider (true, 480, 300);
	tuskenRaiderKing (true, 600, 200);
	characterGrant (false, 150, 300);

	int stop = 0;
	int x = 0;

	while (stop < 10)
	{

	    characterTuskenRaider (true, 450, 270);
	    characterTuskenRaider (true, 400, 260);
	    characterTuskenRaider (true, 480, 300);
	    tuskenRaiderKing (true, 600, 200);
	    characterGrant (false, 150, 300);

	    for (int i = 0 ; i < 30000000 ; i++)
	    {
	    }
	    c.setColor (Color.white);
	    c.fillRect (50, 50, 1200, 200);
	    throneRoom ();
	    chair (x + 100, 200);
	    for (int i = 0 ; i < 30000000 ; i++)
	    {
	    }

	    x += 30;
	    stop++;

	}
	characterTuskenRaider (false, 450, 300);
	characterTuskenRaider (false, 400, 290);
	characterTuskenRaider (false, 480, 330);
	tuskenRaiderKing (true, 600, 200);
	characterGrant (false, 150, 300);
    } //end of throneRoomAnimation


    public static void tuskenRaiderKing (boolean notLaughing, int x, int y)  //Tusken Raider King character
    {
	Color bags = new Color (205, 133, 63);
	Color body = new Color (222, 184, 135);
	int[] xCrown = {x + 10, x + 10, x + 35, x + 60, x + 60};
	int[] yCrown = {y + 10, y - 10, y - 35, y - 10, y + 10};

	c.setColor (body);
	//body
	c.fillRoundRect (x + 6, y + 124, 30, 60, 20, 20);
	c.fillRoundRect (x + 36, y + 124, 30, 60, 20, 20);
	//arm 1
	c.fillRoundRect (x - 14, y + 68, 20, 60, 20, 20);
	//arm 2
	c.fillRoundRect (x + 66, y + 68, 20, 60, 20, 20);
	//head
	c.fillOval (x, y, 70, 70);
	//body
	c.fillRoundRect (x + 6, y + 68, 60, 60, 20, 20);

	//bags
	c.setColor (bags);
	c.fillRect (x + 6, y + 110, 60, 10);
	c.fillRect (x + 44, y + 70, 10, 50);
	c.fillRect (x + 30, y, 10, 50);
	c.fillRect (x + 30, y + 50, 34, 10);

	if (notLaughing == true)
	{

	    //mouth
	    c.setColor (Color.white);
	    c.fillOval (x + 16, y + 40, 20, 24);
	    c.setColor (Color.black);
	    c.drawOval (x + 16, y + 40, 20, 24);
	}
	else
	{
	    //mouth
	    c.setColor (Color.red);
	    c.fillOval (x + 16, y + 40, 30, 20);
	    c.setColor (Color.black);
	    c.drawOval (x + 16, y + 40, 30, 20);
	}

	//weapon
	c.setColor (Color.gray);
	c.fillRect (x - 20, y + 10, 10, 180);
	c.fillRect (x - 30, y + 30, 30, 10);

	//eyes
	c.setColor (Color.blue);
	c.fillOval (x + 10, y + 20, 20, 20);
	c.fillOval (x + 34, y + 20, 20, 20);

	c.setColor (Color.black);
	//outlines
	//legs
	c.drawRoundRect (x + 6, y + 124, 30, 60, 20, 20);
	c.drawRoundRect (x + 36, y + 124, 30, 60, 20, 20);
	//arm 1
	c.drawRoundRect (x - 14, y + 68, 20, 60, 20, 20);
	//arm 2
	c.drawRoundRect (x + 66, y + 68, 20, 60, 20, 20);
	//head
	c.drawOval (x, y, 70, 70);
	//body
	c.drawRoundRect (x + 6, y + 68, 60, 60, 20, 20);
	//eyes
	c.drawOval (x + 10, y + 20, 20, 20);
	c.drawOval (x + 34, y + 20, 20, 20);
	c.drawPolygon (xCrown, yCrown, 5);

	//crown
	c.setColor (Color.yellow);
	c.fillPolygon (xCrown, yCrown, 5);
    } //end of tuskenRaiderKing


    public static void animationFour ()  //characters moving
    {
	int stop = 0;
	int x = 0;

	while (stop < 65)
	{
	    characterGrant (true, 600, 150);
	    characterArty (true, 400, 200);
	    tuskenPalace ();

	    for (int i = 0 ; i < 30000000 ; i++)
	    {
	    }

	    c.setColor (Color.white);
	    c.fillRect (0, 200, 450, 200);

	    desertAnimation ();

	    characterTuskenRaider (true, x + 150, 200);
	    characterGrant (true, 600, 150);
	    characterArty (true, 400, 200);
	    tuskenPalace ();
	    for (int i = 0 ; i < 30000000 ; i++)
	    {
	    }

	    x += 3;
	    stop++;
	}

	x = 345;
	stop = 0;
	while (stop < 65)
	{

	    for (int i = 0 ; i < 30000000 ; i++)
	    {
	    }

	    c.setColor (Color.white);
	    c.fillRect (0, 190, 450, 200);

	    desertAnimation ();

	    characterTuskenRaider (true, x + 100, 200);
	    characterGrant (true, 600, 150);
	    characterArty (true, x, 200);
	    tuskenPalace ();
	    for (int i = 0 ; i < 30000000 ; i++)
	    {
	    }

	    x -= 10;
	    stop++;
	}
    } //end of animationFour


    public static void end ()  //final scene
    {


	c.setFont (EndingFont);
	c.setColor (Color.black);
	c.drawString ("Grant celebrated - he won! He freed Arty and started to party.", 200, 30);
	c.drawString ("Grant partied way too hard, and he ended up getting a heart attack.", 160, 90);
	c.drawString ("As he died, he made his final decree. Arty would be the next King", 155, 150);
	c.drawString ("of Europe for working for Grant so hard. Kind of an unethical", 205, 210);
	c.drawString ("monarchical move, but it was cool. Arty became a great, wise", 215, 270);
	c.drawString ("ruler, and the first to issue all decrees by crying.", 275, 330);
	c.drawString ("Grant was forever known as the savior of Europe, and a great", 210, 390);
	c.drawString ("poster boy for blood cholesterol warning PSAs.", 275, 450);

	c.setFont (EndingFont2);
	c.drawString ("THE END", 450, 550);
    } //end of end


    public static void subtitlesEight ()  //subtitles for the game
    {
	c.setFont (subtitlesFont);

	c.setColor (Color.black);
	c.drawRect (20, 420, 1170, 125);

	c.drawString ("Grant had failed his mission. The Tusken Raider King beat him, fair and square. ", 25, 440);
	c.drawString ("Viewing such a pathetic character like Grant, the King could not help himself but laugh. For a random kid to think", 25, 460);
	c.drawString ("he could take on the strongest leader the world has even seen made his chuckles spiral out uncontrollably.", 25, 480);
	c.drawString ("Slowly, one step at a time, the King started moving backwards in his fit of laughter. Soon after, he fell right out", 25, 500);
	c.drawString ("of the sliding glass door that he had installed on the second floor. Why was it even there? Simply to provide a better", 25, 520);
	c.drawString ("view of his kingdom, which now hurtled towards his face as he rushed towards the ground.", 25, 540);
    } //end of subtitlesEight


    public static void subtitlesNine ()  //subtitles for the game
    {
	c.setFont (subtitlesFont);

	c.setColor (Color.black);
	c.drawRect (20, 420, 1170, 125);

	c.drawString ("Through unusual means, the Tusken Raider King was defeated. ", 25, 440);
	c.drawString ("Grant, despite how his success was based on pure luck, still felt proud of his defeat-turned-victory.", 25, 460);
	c.drawString ("Now immobilized in the sand, the King could do harm no longer.", 25, 480);

    } //end of subtitlesNine


    public static void throneRoomAnimationTwo ()
    {
	int stop = 0;
	int x = 0;

	characterGrant (true, 300, 280);
	tuskenRaiderKing (false, 450 + x, 200);

	while (stop < 35)
	{


	    for (int i = 0 ; i < 30000000 ; i++)
	    {
	    }
	    c.setColor (Color.white);
	    c.fillRect (550, 50, 1200, 350);

	    throneRoom ();
	    characterGrant (true, 300, 280);
	    tuskenRaiderKing (false, 450 + x, 200);
	    for (int i = 0 ; i < 30000000 ; i++)
	    {
	    }


	    x += 10;
	    stop++;
	}
    } //end of throneRoomAnimationTwo


    public static void kingDeathAnimation ()  //animation for the king dying
    {
	int stop = 0;
	int x = 0;

	tuskenRaiderKing (false, 450, x - 50);

	while (stop < 20)
	{

	    for (int i = 0 ; i < 30000000 ; i++)
	    {
	    }
	    c.setColor (Color.white);
	    c.fillRect (350, 0, 400, 400);

	    desertAnimation (); //brings back the desert

	    c.setColor (Color.yellow);
	    c.fillOval (350, 200, 200, 200);

	    tuskenRaiderKing (false, 450, x - 50);
	    for (int i = 0 ; i < 30000000 ; i++)
	    {
	    }

	    x += 10;
	    stop++;
	    c.setColor (Color.yellow);
	    c.fillOval (350, 200, 200, 200);
	}
	x -= 10;
	tuskenRaiderKing (true, 450, x - 50);
	c.setColor (Color.yellow);
	c.fillOval (350, 200, 200, 200);

    }
} //end of kingDeathAnimation




