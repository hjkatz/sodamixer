package fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hjkatz.sodamixer.R;

/** Created By: Harrison Katz on Date: 2/26/13 */
public class MixesFragment extends ListFragment
{
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        return inflater.inflate( R.layout.mixes_fragment, container, false );
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }
}