package aaronhammer.ceg4110homework2

import ControllerAdapter
import ModelClock
import UpdateClockCommand
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.view.*
import java.io.Serializable

class MainActivity : AppCompatActivity(), DigitalFragment.OnFragmentInteractionListener, AnalogFragment.OnFragmentInteractionListener, ButtonFragment.OnFragmentInteractionListener {
    var list = ArrayList<CustItem>()
    private var commands = CommandQ.create()
    var clock = ModelClock()


    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val test1 = CustItem("Main Screen", ButtonFragment())
        list.add(test1)
        var adapter = ControllerAdapter(toolbar.context, list)
        // Setup spinner
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // When the given dropdown item is selected, show its contents in the
                // container view.
                var selected = parent.getItemAtPosition(position) as CustItem
                if (selected.getFragmentType() is ButtonFragment) {
                    val inst = ButtonFragment()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.container, inst)
                            .commit()
                    // Changes the name of the spinner header text
                    spinner.text1?.text = selected.getName()
                } else if (selected.getFragmentType() is DigitalFragment ){
                    val inst = DigitalFragment()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.container, inst)
                            .commit()
                    // Changes the name of the spinner header text
                    spinner.text1?.text = selected.getName()
                } else {
                    val inst = AnalogFragment()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.container, inst)
                            .commit()
                    // Changes the name of the spinner header text
                    spinner.text1?.text = selected.getName()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        handler.post(runnable)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    class CustItem(name: String, type: Fragment) : Serializable {
        private val type = type
        private val name = name

        companion object {}

        fun getFragmentType() : Fragment {
            return type
        }

        fun getName() : String {
            return name
        }
    }

    fun addDigitalFragment() {
        list.add(CustItem("Digital Clock", DigitalFragment()))
        val adapter = ControllerAdapter(toolbar.context, list)
        spinner.adapter = adapter
        //(spinner.adapter as ControllerAdapter).add(CustItem("Digital Clock", DigitalFragment()))
    }

    fun addAnalogFragment() {
        list.add(CustItem("Analog Clock", AnalogFragment()))
        val adapter = ControllerAdapter(toolbar.context, list)
        spinner.adapter = adapter
    }

    fun addSecond() {
        val command = UpdateClockCommand(clock, "addSecond")
        command.doIt()
        commands.push(command)
        //Log.d("Time", clock.calender.time.toString())
    }

    fun subtractSecond() {
        val command = UpdateClockCommand(clock, "subSecond")
        command.doIt()
        commands.push(command)
        //Log.d("Time", clock.calender.time.toString())
    }

    fun addMinute() {
        val command = UpdateClockCommand(clock, "addMinute")
        command.doIt()
        commands.push(command)
        //Log.d("Time", clock.calender.time.toString())
    }

    fun subtractMinute() {
        val command = UpdateClockCommand(clock, "subMinute")
        command.doIt()
        commands.push(command)
        //Log.d("Time", clock.calender.time.toString())
    }

    fun addHour() {
        val command = UpdateClockCommand(clock, "addHour")
        command.doIt()
        commands.push(command)
        //Log.d("Time", clock.calender.time.toString())
    }

    fun subtractHour() {
        val command = UpdateClockCommand(clock, "subHour")
        command.doIt()
        commands.push(command)
        //Log.d("Time", clock.calender.time.toString())
    }

    fun addDay() {
        val command = UpdateClockCommand(clock, "addDay")
        command.doIt()
        commands.push(command)
        //Log.d("Time", clock.calender.time.toString())
    }

    fun subtractDay() {
        val command = UpdateClockCommand(clock, "subDay")
        command.doIt()
        commands.push(command)
        //Log.d("Time", clock.calender.time.toString())
    }

    fun addMonth() {
        val command = UpdateClockCommand(clock, "addMonth")
        command.doIt()
        commands.push(command)
        //Log.d("Time", clock.calender.time.toString())
    }

    fun subtractMonth() {
        val command = UpdateClockCommand(clock, "subMonth")
        command.doIt()
        commands.push(command)
        //Log.d("Time", clock.calender.time.toString())
    }

    fun addYear() {
        val command = UpdateClockCommand(clock, "addYear")
        command.doIt()
        commands.push(command)
        //Log.d("Time", clock.calender.time.toString())
    }

    fun subtractYear() {
        val command = UpdateClockCommand(clock, "subYear")
        command.doIt()
        commands.push(command)
        //Log.d("Time", clock.calender.time.toString())
    }

    fun undo() {
        commands.undo()
        //Log.d("Time", clock.calender.time.toString())
    }

    fun redo() {
        commands.redo()
        //Log.d("Time", clock.calender.time.toString())
    }

    var handler = Handler()
    private var runnable: Runnable = object : Runnable {
        override fun run() {
            Log.d("Time", clock.calender.time.toString())
            handler.postDelayed(this, 1000)
        }

    }
}
