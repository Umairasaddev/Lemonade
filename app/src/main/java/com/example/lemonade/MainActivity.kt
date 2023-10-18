package com.example.lemonade

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    /**
     * DO NOT ALTER ANY VARIABLE OR VALUE NAMES OR THEIR INITIAL VALUES.
     *
     * Anything labeled var instead of val is expected to be changed in the functions but DO NOT
     * alter their initial values declared here, this could cause the app to not function properly.
     */
    private val LEMONADE_STATE = "LEMONADE_STATE"
    private val LEMON_SIZE = "LEMON_SIZE"
    private val SQUEEZE_COUNT = "SQUEEZE_COUNT"

    // SELECT represents the "pick lemon" state
    private val SELECT = "select"

    // SQUEEZE represents the "squeeze lemon" state
    private val SQUEEZE = "squeeze"

    // DRINK represents the "drink lemonade" state
    private val DRINK = "drink"

    // RESTART represents the state where the lemonade has been drunk and the glass is empty
    private val RESTART = "restart"

    // Default the state to select
    private var lemonadeState = "select"

    // Default lemonSize to -1
    private var lemonSize = -1

    // Default the squeezeCount to -1
    private var squeezeCount = -1

    private var lemonTree = LemonTree()

    //object of lemon tree
    private var lemonImage: ImageView? = null


    private var text_action: TextView? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // === DO NOT ALTER THE CODE IN THE FOLLOWING IF STATEMENT ===
        if (savedInstanceState != null) {
            lemonadeState = savedInstanceState.getString(LEMONADE_STATE, "select")
            lemonSize = savedInstanceState.getInt(LEMON_SIZE, -1)
            squeezeCount = savedInstanceState.getInt(SQUEEZE_COUNT, -1)
        }
        // === END IF STATEMENT ===


        lemonImage = findViewById(R.id.image_lemon_state)
        setViewElements()





        lemonImage!!.setOnClickListener {

            clickLemonImage()
//            when (lemonadeState) {      SELECT -> {
//                Log.d("Umair", "Entering in SELECT")
//                text_action!!.setText("Click to select a lemon!")
//                lemonImage!!.setImageResource(R.drawable.lemon_tree)
//                lemonSize = lemonTree.pick()
//                lemonadeState = SQUEEZE
//
//
//            }
//
//                SQUEEZE -> {
//                    Log.d("Umair", "Entering in SQUEEZE")
//                    lemonImage!!.setImageResource(R.drawable.lemon_squeeze)
//                    text_action!!.setText("Click to juice the lemon!")
//
//                    if (squeezeCount < lemonSize) {
//                        Log.d(
//                            "umair",
//                            "Lemon Size is ${lemonSize} and Squeeze Count is ${squeezeCount}"
//                        )
//                        squeezeCount++
//                        Log.d(
//                            "umair",
//                            "After Lemon Size is ${lemonSize} and Squeeze Count is ${squeezeCount}"
//                        )
//
//                        if (squeezeCount == lemonSize) {
//                            lemonadeState = DRINK
//                            Log.d("umair", "Going to Drink State")
//                        }
//                    }
//                }
//
//                DRINK -> {
//
//                    lemonImage!!.setImageResource(R.drawable.lemon_drink)
//                    text_action!!.setText("Click to drink your lemonade")
//
//                    Log.d("umair", "Going to Restart State")
//                    lemonadeState = RESTART
//
//                }
//
//                RESTART -> {
//                    lemonImage?.setImageResource(R.drawable.lemon_restart)
//                    text_action?.setText("Click to start again")
//                    Log.d("umair", "Going from Restart to  SELECT State")
//                    lemonadeState = SELECT
//                }
//            }

        }


        lemonImage!!.setOnLongClickListener {
            // TODO: replace 'false' with a call to the function that shows the squeeze count
            showSnackbar()
            true
        }
    }

    /**
     * === DO NOT ALTER THIS METHOD ===
     *
     * This method saves the state of the app if it is put in the background.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(LEMONADE_STATE, lemonadeState)
        outState.putInt(LEMON_SIZE, lemonSize)
        outState.putInt(SQUEEZE_COUNT, squeezeCount)
        super.onSaveInstanceState(outState)
    }

    /**
     * Clicking will elicit a different response depending on the state.
     * This method determines the state and proceeds with the correct action.
     */
    private fun clickLemonImage() {
        // TODO: use a conditional statement like 'if' or 'when' to track the lemonadeState


        when (lemonadeState) {

            SELECT -> {
                lemonadeState = SQUEEZE
                Log.d("Umair", "Entering in SELECT")
                lemonSize = lemonTree.pick()
                Log.d("Umair", "Lemonsize is ${lemonSize} and squeeze count is ${squeezeCount}")
                squeezeCount = 0

            }

            SQUEEZE -> {
                Log.d("Umair", "Entering in SQUEEZE")

                squeezeCount++
                lemonSize--
                Log.d("Umair", "Lemonsize is ${lemonSize} and squeeze count is ${squeezeCount}")
                if (lemonSize == 0) {
                    lemonadeState = DRINK
                    lemonSize = -1
                }

            }

            DRINK -> {
                Log.d("Umair", "Entering in DRINK")


                lemonadeState = RESTART
                lemonSize = -1
            }

            RESTART -> {
                Log.d("Umair", "Entering in RESTART")
                lemonadeState = SELECT
            }
        }
        setViewElements()
    }

    /**
     * Set up the view elements according to the state.
     */
    private fun setViewElements() {
        val textAction: TextView = findViewById(R.id.text_action)
        // TODO: set up a conditional that tracks the lemonadeState

        when (lemonadeState) {

            SELECT -> {
                Log.d("Umair", "on setViewElements Entering in SELECT")
                lemonImage?.setImageResource(R.drawable.lemon_tree)
                textAction.setText(R.string.lemon_select)
            }

            SQUEEZE -> {
                Log.d("Umair", "on setViewElements Entering in SQUEEZE")
                lemonImage?.setImageResource(R.drawable.lemon_squeeze)
                textAction.setText(R.string.lemon_squeeze)
            }

            DRINK -> {
                Log.d("Umair", "on setViewElements Entering in DRINK")
                lemonImage?.setImageResource(R.drawable.lemon_drink)
                textAction.setText(R.string.lemon_drink)
            }

            RESTART -> {
                Log.d("Umair", "on setViewElements Entering in RESTART")
                lemonImage?.setImageResource(R.drawable.lemon_restart)
                textAction.setText(R.string.lemon_empty_glass)

            }
        }

    }

    /**
     * === DO NOT ALTER THIS METHOD ===
     *
     * Long clicking the lemon image will show how many times the lemon has been squeezed.
     */
    private fun showSnackbar(): Boolean {
        if (lemonadeState != SQUEEZE) {
            return false
        }
        val squeezeText = getString(R.string.squeeze_count, squeezeCount)
        Snackbar.make(
            findViewById(R.id.constraint_Layout),
            squeezeText,
            Snackbar.LENGTH_SHORT
        ).show()
        return true
    }
}
//jkhjkhjkh

/**
 * A Lemon tree class with a method to "pick" a lemon. The "size" of the lemon is randomized
 * and determines how many times a lemon needs to be squeezed before you get lemonade.
 */
class LemonTree {
    fun pick(): Int {
        return (2..4).random()
    }
}
