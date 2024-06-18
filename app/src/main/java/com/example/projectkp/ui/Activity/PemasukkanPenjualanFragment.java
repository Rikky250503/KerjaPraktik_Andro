//package com.example.projectkp.ui.Activity;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.appcompat.app.AppCompatActivity;
//import com.example.projectkp.databinding.FragmentPemasukkanPenjualanBinding;
////import com.ezatpanah.williamchart_youtube.databinding.ActivityBarChartBinding
//
//import com.example.projectkp.R;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link PemasukkanPenjualanFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class PemasukkanPenjualanFragment extends Fragment {
//
//    private FragmentPemasukkanPenjualanBinding _binding;
//    private FragmentPemasukkanPenjualanBinding binding;
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public PemasukkanPenjualanFragment() {
//        // Required empty public constructor
//
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment PemasukkanPenjualanFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static PemasukkanPenjualanFragment newInstance(String param1, String param2) {
//        PemasukkanPenjualanFragment fragment = new PemasukkanPenjualanFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState)
//    {
//        _binding = FragmentPemasukkanPenjualanBinding.inflate(getLayoutInflater());
//        //setContentView(_binding.getRoot());
//        binding = _binding;
//
//        binding.barChart.getAnimation().setDuration(animationDuration);
//        binding.barChart.animate(barSet);
//    }
//    return
//    {
//        _binding.getRoot();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        binding = null;
//    }
//
//    public static final List<Entry> barSet = new ArrayList<Entry>() {{
//        add(new BarEntry(0, 4F));
//        add(new BarEntry(1, 7F));
//        add(new BarEntry(2, 2F));
//        add(new BarEntry(3, 2.3F));
//        add(new BarEntry(4, 5F));
//        add(new BarEntry(5, 4F));
//    }};
//
//    public static final List<BarEntry> horizontalBarSet = new ArrayList<BarEntry>() {{
//        add(new BarEntry(0, 5F));
//        add(new BarEntry(1, 6.4F));
//        add(new BarEntry(2, 3F));
//    }};
//
//    public static final long animationDuration = 1000L;
//}
//
//}

package com.example.projectkp.ui.Activity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectkp.databinding.FragmentPemasukkanPenjualanBinding;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PemasukkanPenjualanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PemasukkanPenjualanFragment extends Fragment {

    private FragmentPemasukkanPenjualanBinding binding;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PemasukkanPenjualanFragment() {
        // Required empty public constructor
    }

    public static PemasukkanPenjualanFragment newInstance(String param1, String param2) {
        PemasukkanPenjualanFragment fragment = new PemasukkanPenjualanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPemasukkanPenjualanBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        //binding.barChart.getAnimation().setDuration(animationDuration);
        // Create a BarDataSet using your list of BarEntry objects (barSet)
        BarDataSet dataSet = new BarDataSet(barSet, "Label");
        // Create a BarData object and set the data set
        BarData barData = new BarData(dataSet);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(16f);

        // Set the BarData to the BarChart
        //binding.barChart.setData(barData);
//        binding.barChart.setData(barSet);
        //binding.barChart.animate();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

//    public static final List<Entry> barSet = new ArrayList<Entry>() {{
//        add(new BarEntry(0, 4F));
//        add(new BarEntry(1, 7F));
//        add(new BarEntry(2, 2F));
//        add(new BarEntry(3, 2.3F));
//        add(new BarEntry(4, 5F));
//        add(new BarEntry(5, 4F));
//    }};

    public static final List<BarEntry> barSet = new ArrayList<BarEntry>() {{
        add(new BarEntry(0, 4F));
        add(new BarEntry(1, 7F));
        add(new BarEntry(2, 2F));
        add(new BarEntry(3, 2.3F));
        add(new BarEntry(4, 5F));
        add(new BarEntry(5, 4F));
    }};

    public static final List<BarEntry> horizontalBarSet = new ArrayList<BarEntry>() {{
        add(new BarEntry(0, 5F));
        add(new BarEntry(1, 6.4F));
        add(new BarEntry(2, 3F));
    }};

    public static final long animationDuration = 1000L;
}
