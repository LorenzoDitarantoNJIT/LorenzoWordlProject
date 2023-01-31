package com.example.lorenzowordlproject

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val showButton = findViewById<Button>(R.id.showInput)
        val textView = findViewById<TextView>(R.id.textView)
        val guess1 = findViewById<TextView>(R.id.guess1)
        val guess2 = findViewById<TextView>(R.id.guess2)
        val guess3 = findViewById<TextView>(R.id.guess3)
        // finding the edit text
        val editText = findViewById<EditText>(R.id.editText)

        showButton.setOnClickListener {

            val thisGuess = editText.text
            //textView.text = thisGuess
            //Toast.makeText(this, thisGuess, Toast.LENGTH_SHORT).show()

            when (counter) {
                0 -> {
                    var guessOne = compareResult(thisGuess.toString().uppercase(), 0)
                    guess1.text = guessOne
                }
                1 -> {
                    var guessTwo = compareResult(thisGuess.toString().uppercase(), 1)
                    guess2.text = guessTwo
                }
                2 -> {
                    var guessThree =compareResult(thisGuess.toString().uppercase(), 2)
                    guess3.text = guessThree
                }
                else -> { // Note the block
                    val finalMessage = "$wordToGuess was the correct answer"
                    textView.text = finalMessage
                }
            }

            counter++
        }
    }
    val wordToGuess = getRandomFourLetterWord();
    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */

    val fourLetterWords =
        "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care,Case,Cash,City,Club,Cost,Date,Deal,Door,Duty,East,Edge,Face,Fact,Farm,Fear,File,Film,Fire,Firm,Fish,Food,Foot,Form,Fund,Game,Girl,Goal,Gold,Hair,Half,Hall,Hand,Head,Help,Hill,Home,Hope,Hour,Idea,Jack,John,Kind,King,Lack,Lady,Land,Life,Line,List,Look,Lord,Loss,Love,Mark,Mary,Mind,Miss,Move,Name,Need,News,Note,Page,Pain,Pair,Park,Part,Past,Path,Paul,Plan,Play,Post,Race,Rain,Rate,Rest,Rise,Risk,Road,Rock,Role,Room,Rule,Sale,Seat,Shop,Show,Side,Sign,Site,Size,Skin,Sort,Star,Step,Task,Team,Term,Test,Text,Time,Tour,Town,Tree,Turn,Type,Unit,User,View,Wall,Week,West,Wife,Will,Wind,Wine,Wood,Word,Work,Year,Bear,Beat,Blow,Burn,Call,Care,Cast,Come,Cook,Cope,Cost,Dare,Deal,Deny,Draw,Drop,Earn,Face,Fail,Fall,Fear,Feel,Fill,Find,Form,Gain,Give,Grow,Hang,Hate,Have,Head,Hear,Help,Hide,Hold,Hope,Hurt,Join,Jump,Keep,Kill,Know,Land,Last,Lead,Lend,Lift,Like,Link,Live,Look,Lose,Love,Make,Mark,Meet,Mind,Miss,Move,Must,Name,Need,Note,Open,Pass,Pick,Plan,Play,Pray,Pull,Push,Read,Rely,Rest,Ride,Ring,Rise,Risk,Roll,Rule,Save,Seek,Seem,Sell,Send,Shed,Show,Shut,Sign,Sing,Slip,Sort,Stay,Step,Stop,Suit,Take,Talk,Tell,Tend,Test,Turn,Vary,View,Vote,Wait,Wake,Walk,Want,Warn,Wash,Wear,Will,Wish,Work,Able,Back,Bare,Bass,Blue,Bold,Busy,Calm,Cold,Cool,Damp,Dark,Dead,Deaf,Dear,Deep,Dual,Dull,Dumb,Easy,Evil,Fair,Fast,Fine,Firm,Flat,Fond,Foul,Free,Full,Glad,Good,Grey,Grim,Half,Hard,Head,High,Holy,Huge,Just,Keen,Kind,Last,Late,Lazy,Like,Live,Lone,Long,Loud,Main,Male,Mass,Mean,Mere,Mild,Nazi,Near,Neat,Next,Nice,Okay,Only,Open,Oral,Pale,Past,Pink,Poor,Pure,Rare,Real,Rear,Rich,Rude,Safe,Same,Sick,Slim,Slow,Soft,Sole,Sore,Sure,Tall,Then,Thin,Tidy,Tiny,Tory,Ugly,Vain,Vast,Very,Vice,Warm,Wary,Weak,Wide,Wild,Wise,Zero,Ably,Afar,Anew,Away,Back,Dead,Deep,Down,Duly,Easy,Else,Even,Ever,Fair,Fast,Flat,Full,Good,Half,Hard,Here,High,Home,Idly,Just,Late,Like,Live,Long,Loud,Much,Near,Nice,Okay,Once,Only,Over,Part,Past,Real,Slow,Solo,Soon,Sure,That,Then,This,Thus,Very,When,Wide"

    // Returns a list of four letter words as a list
    fun getAllFourLetterWords(): List<String> {
        return FourLetterWordList.FourLetterWordList.fourLetterWords.split(",")
    }

    // Returns a random four letter word from the list in all caps
    fun getRandomFourLetterWord(): String {
        val allWords = getAllFourLetterWords()
        val randomNumber = (0..allWords.size).shuffled().last()
        return allWords[randomNumber].uppercase()
    }

    private fun checkGuess(guess: String) : String {
        var result = ""
        //I added wordToGuess, change later
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

    private fun compareResult(guess: String, viewcount: Int) : String {
        //I added wordToGuess, change later
        val textView1 = findViewById<TextView>(R.id.textView2)
        val textView2 = findViewById<TextView>(R.id.textView3)
        val textView3 = findViewById<TextView>(R.id.textView4)
        val wordToGuess = "bake";
        val spannedGuess = SpannableString(guess)
        val colorRed = ForegroundColorSpan(Color.RED)
        val colorGreen = ForegroundColorSpan(Color.GREEN)
        val colorBlack = ForegroundColorSpan(Color.MAGENTA)
        val result = checkGuess(guess)
        var ogNum = 0
        var flag = 0
        for (i in 0..3) {
            ogNum = i
            if (result[i] == 'O') {
                spannedGuess.setSpan(colorGreen, ogNum, ogNum + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
                }
            if (result[i] == '+') {
                spannedGuess.setSpan(colorRed, ogNum, ogNum + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            }
            if (result[i] == 'X') {
                spannedGuess.setSpan(colorBlack, ogNum, ogNum + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            }
            continue
        }
        when (viewcount) {
            0 -> textView1.text = spannedGuess
            1 -> textView2.text = spannedGuess
            2 -> textView3.text = spannedGuess
            else -> { // Note the block
                return "no"
            }
        }
        return result
    }
}