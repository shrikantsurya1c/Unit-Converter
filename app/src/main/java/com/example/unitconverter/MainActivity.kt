ackage com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}
// Types of TextFields(Input Text) are : TextField,BasicTextField and OutLinedTextField

@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputunit by remember { mutableStateOf("Meters") }
    var outputunit by remember { mutableStateOf("Meters") }
    var inputExpanded by remember { mutableStateOf(false)}
    var outputExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableDoubleStateOf(1.00) }
    val outputconversionFactor = remember { mutableDoubleStateOf(1.00) }
    var selectedFrom by remember { mutableStateOf("Convert from") }
    var selectedTo by remember { mutableStateOf("Convert to") }


    fun convertUnits(){


        // ?: - elvis operator
        // using this operator, we will either get the conversion value or 0.0
        // simply, if toDoubleorNull is null, it will return 0.0

        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        var result = 
            (inputValueDouble * conversionFactor.doubleValue * 100.0 / outputconversionFactor.value)
                .roundToInt() / 100.0
        outputValue = result.toString( )

    }
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {

        // Here all the UI elements will be stacked below each other
        Text("Unit Converter",style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.size(15.dp)) // used to add space between text and textfield
        OutlinedTextField(
            value = inputValue,
            onValueChange =
            {
                inputValue = it
                convertUnits()
        // Here ges what should happen when the value changes of our Outlinedtext Field changes
            },
            label = { Text("Enter the value") },

        )

        Spacer(modifier = Modifier.height(10.dp))

        Row() {


            /* I have commented the Toast thing because I don't want it in my app



            // To display the Toast we need the context of where the toast should be displayed
             val context = LocalContext.current // context mtlb in which area of the application should something happen
            // Here all the UI elements will be placed next to each other
            Button(onClick = { Toast
                .makeText(context, "Thanks for Clicking!", Toast.LENGTH_SHORT).show()}) {
                Text(text = "My Button")
            }
        }
        Text(text = "Result: ")
    */



/*
        In Jetpack Compose, the choice between padding modifiers and spacer elements for spacing is crucial.
        Padding modifiers offer simplicity, perfect for adding space around individual elements but lack
        reusability for consistent spacing across multiple components. Spacer elements, conversely,
        offer excel in reusability and clarity in design intent but add slight complexity and code verbosity.
        The decision hinges on specific needs: padding modifiers for fine control around single elements, and spacers for
        uniform spacing among multiple components.


        The choice of padding modifiers or spacer elements depends on the specific needs of your app.


        So simply, for spacing in components we can either use padding modifiers or spacer elements.
 */
            // Input Box
    Box{
        // Input Button

        // To expand the dropdown menu when the button is clicked, we used inputExpanded to true
        // then passed it on expanded = inputExpanded
        Button(onClick = { inputExpanded = true }) {
            Text(text = selectedFrom)
            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            Spacer(modifier = Modifier.width(5.dp))
        }
            DropdownMenu(expanded = inputExpanded, onDismissRequest = { inputExpanded = false }) {
                DropdownMenuItem(
                    text = { Text(text = "Centimetres") },
                    onClick = {
                        inputExpanded = false
                        selectedFrom = "Centimetres"
                        conversionFactor.value = 0.01
                        convertUnits()
                    }
                )

                DropdownMenuItem(
                    text = { Text(text = "Meters") },
                    onClick = {
                        inputExpanded = false
                        selectedFrom = "Meters"
                        conversionFactor.value = 1.00
                        convertUnits()
                    }
                )

                DropdownMenuItem(
                    text = { Text(text = "Feet") },
                    onClick = {
                        inputExpanded = false
                        selectedFrom = "Feet"
                        conversionFactor.value = 0.3048
                        convertUnits()
                    }
                )

                DropdownMenuItem(
                    text = { Text(text = "Milimeters") },
                    onClick = {
                        inputExpanded = false
                        selectedFrom = "Milimeters"
                        conversionFactor.value = 0.001
                        convertUnits()
                    }
                )
            }
    }

            Spacer(modifier = Modifier.padding(5.dp))

            // Output Box
    Box{
        // Output Button
        Button(onClick = { outputExpanded = true }) {
            Text(text = selectedTo)
            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            Spacer(modifier = Modifier.width(5.dp))
        }
            DropdownMenu(expanded = outputExpanded, onDismissRequest = {outputExpanded = false }) {
                DropdownMenuItem(
                    text = { Text(text = "Centimetres") },
                    onClick = {
                        outputExpanded = false
                        selectedTo = "Centimetres"
                        outputconversionFactor.value = 0.01
                        convertUnits()
                    }
                )

                DropdownMenuItem(
                    text = { Text(text = "Meters") },
                    onClick = {
                        outputExpanded = false
                        selectedTo = "Meters"
                        outputconversionFactor.value = 1.00
                        convertUnits()
                    }
                )

                DropdownMenuItem(
                    text = { Text(text = "Feet") },
                    onClick = {
                        outputExpanded = false
                        selectedTo = "Feet"
                        outputconversionFactor.value = 0.3048
                        convertUnits()
                    }
                )

                DropdownMenuItem(
                    text = { Text(text = "Milimeters") },
                    onClick =  {
                        outputExpanded = false
                        selectedTo = "Milimeters"
                        outputconversionFactor.value = 0.001
                        convertUnits()
                    }
                )


            }
        }
    }
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Result: $outputValue $selectedTo",
            style = MaterialTheme.typography.headlineSmall)
        }


    }



/*

In compose, the drop down menu component enables the creation of the option selector.
It's like a menu and can display a list of choice using its Child Element dropdown menu item.
And we have already provided a container and a trigger for it, although not functioning.

 */



@Preview("Light Theme", widthDp = 360, heightDp = 640, showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}