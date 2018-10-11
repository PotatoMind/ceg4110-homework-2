package aaronhammer.ceg4110homework2

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_button.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ButtonFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ButtonFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ButtonFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragmentval view = inflater.inflate(R.layout.fragment_button, container, false)
        var view = inflater.inflate(R.layout.fragment_button, container, false)
        view.findViewById<Button>(R.id.digitalButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.analogButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.addSecondButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.subSecondButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.addMinuteButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.subMinuteButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.addHourButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.subHourButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.addDayButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.subDayButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.addMonthButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.subMonthButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.addYearButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.subYearButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.undoButton).setOnClickListener(this)
        view.findViewById<Button>(R.id.redoButton).setOnClickListener(this)
        //view.findViewById<Button>(R.id.testButton).setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.analogButton -> (activity as MainActivity).addAnalogFragment()
            R.id.digitalButton -> (activity as MainActivity).addDigitalFragment()
            R.id.addSecondButton -> (activity as MainActivity).addSecond()
            R.id.subSecondButton -> (activity as MainActivity).subtractSecond()
            R.id.addMinuteButton -> (activity as MainActivity).addMinute()
            R.id.subMinuteButton -> (activity as MainActivity).subtractMinute()
            R.id.addHourButton -> (activity as MainActivity).addHour()
            R.id.subHourButton -> (activity as MainActivity).subtractHour()
            R.id.addDayButton -> (activity as MainActivity).addDay()
            R.id.subDayButton -> (activity as MainActivity).subtractDay()
            R.id.addMonthButton -> (activity as MainActivity).addMonth()
            R.id.subMonthButton -> (activity as MainActivity).subtractMonth()
            R.id.addYearButton -> (activity as MainActivity).addYear()
            R.id.subYearButton -> (activity as MainActivity).subtractYear()
            R.id.undoButton -> (activity as MainActivity).undo()
            R.id.redoButton -> (activity as MainActivity).redo()
            //R.id.testButton -> Log.d("Test", (activity as MainActivity).clock.calender.time.toString())
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ButtonFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ButtonFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
