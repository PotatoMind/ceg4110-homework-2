import aaronhammer.ceg4110homework2.MainActivity
import aaronhammer.ceg4110homework2.R
import android.content.Context
import android.content.res.Resources
import android.support.v7.widget.ThemedSpinnerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.list_item.view.*

class ControllerAdapter(context: Context, objects: ArrayList<MainActivity.CustItem>) : ArrayAdapter<MainActivity.CustItem>(context, R.layout.list_item, objects), ThemedSpinnerAdapter {
    private val mDropDownHelper: ThemedSpinnerAdapter.Helper = ThemedSpinnerAdapter.Helper(context)

    override fun add(`object`: MainActivity.CustItem?) {
        super.add(`object`)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View

        if (convertView == null) {
            // Inflate the drop down using the helper's LayoutInflater
            val inflater = mDropDownHelper.dropDownViewInflater
            view = inflater.inflate(R.layout.list_item, parent, false)
        } else {
            view = convertView
        }

        val item = getItem(position)
        view.text1.text = item?.getName()

        return view
    }

    override fun getDropDownViewTheme(): Resources.Theme? {
        return mDropDownHelper.dropDownViewTheme
    }

    override fun setDropDownViewTheme(theme: Resources.Theme?) {
        mDropDownHelper.dropDownViewTheme = theme
    }
}