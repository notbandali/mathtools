/*
 * PlaceholderFragment.java
 * Copyright (C) 2014 Amin Bandali <me@aminbandali.com>
 *
 * MathTools is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MathTools is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.aminbandali.mathtools.app.fragment.vector;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.aminbandali.mathtools.app.R;
import com.aminbandali.mathtools.app.util.Line2D;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ProductsFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";

    private static final String STATE_EQ_FORM_TEXT = "eq_form_text";
    private static final String STATE_EQ_FORM_TAG = "eq_form_tag";

    private enum Space{
        space2D, space3D
    }
    private Space space = Space.space2D;

    @InjectView(R.id.rB2D)
    RadioButton rB2D;

    @InjectView(R.id.rB3D)
    RadioButton rB3D;

    @InjectView(R.id.sEqForm)
    TextView eqForm;

    @InjectView(R.id.productsll1) LinearLayout row1;
    @InjectView(R.id.productstextx1) EditText x1;
    @InjectView(R.id.productstexty1) EditText y1;
    @InjectView(R.id.productstextz1) EditText z1;
    @InjectView(R.id.productstextx2) EditText x2;
    @InjectView(R.id.productstexty2) EditText y2;
    @InjectView(R.id.productstextz2) EditText z2;

    @InjectView(R.id.productsll2) LinearLayout row2;
    @InjectView(R.id.productstextx3) EditText x3;
    @InjectView(R.id.productstexty3) EditText y3;
    @InjectView(R.id.productstextz3) EditText z3;
    @InjectView(R.id.productstextx4) EditText x4;
    @InjectView(R.id.productstexty4) EditText y4;
    @InjectView(R.id.productstextz4) EditText z4;

    public ProductsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vectors_products, container, false);
        ButterKnife.inject(this, rootView);
        doInit();
        return rootView;
    }

    private void doInit() {
        rB2D.setText(Html.fromHtml(getResources().getString(R.string.r2)));
        rB3D.setText(Html.fromHtml(getResources().getString(R.string.r3)));

        x1.setHint(Html.fromHtml(getResources().getString(R.string.x1)));
        y1.setHint(Html.fromHtml(getResources().getString(R.string.y1)));
        z1.setHint(Html.fromHtml(getResources().getString(R.string.z1)));

        x2.setHint(Html.fromHtml(getResources().getString(R.string.x2)));
        y2.setHint(Html.fromHtml(getResources().getString(R.string.y2)));
        z2.setHint(Html.fromHtml(getResources().getString(R.string.z2)));

        x3.setHint(Html.fromHtml(getResources().getString(R.string.x3)));
        y3.setHint(Html.fromHtml(getResources().getString(R.string.y3)));
        z3.setHint(Html.fromHtml(getResources().getString(R.string.z3)));

        x4.setHint(Html.fromHtml(getResources().getString(R.string.x4)));
        y4.setHint(Html.fromHtml(getResources().getString(R.string.y4)));
        z4.setHint(Html.fromHtml(getResources().getString(R.string.z4)));
    }

    @OnClick(R.id.sEqForm)
    void chooseEqForm(final TextView tV) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setItems(R.array.eq_forms_array, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // The 'which' argument contains the index position
                // of the selected item
                tV.setText(getResources().getStringArray(R.array.eq_forms_array)[which]);
                tV.setTag(which);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @OnClick(R.id.rB2D)
    void chooseR2Space(RadioButton rB) {
        z1.setVisibility(View.GONE);
        z2.setVisibility(View.GONE);
        z3.setVisibility(View.GONE);
        z4.setVisibility(View.GONE);
        row1.setWeightSum(4);
        row2.setWeightSum(4);
        space = Space.space2D;
    }
    @OnClick(R.id.rB3D)
    void chooseR3Space(RadioButton rB) {
        z1.setVisibility(View.VISIBLE);
        z2.setVisibility(View.VISIBLE);
        z3.setVisibility(View.VISIBLE);
        z4.setVisibility(View.VISIBLE);
        row1.setWeightSum(6);
        row2.setWeightSum(6);
        space = Space.space3D;
    }

    @OnClick(R.id.btnproductscalc)
    void calculate() {

        float x1 = Float.parseFloat(this.x1.getText().toString()),
              y1 = Float.parseFloat(this.y1.getText().toString()),
              x2 = Float.parseFloat(this.x2.getText().toString()),
              y2 = Float.parseFloat(this.y2.getText().toString()),
              x3 = Float.parseFloat(this.x3.getText().toString()),
              y3 = Float.parseFloat(this.y3.getText().toString()),
              x4 = Float.parseFloat(this.x4.getText().toString()),
              y4 = Float.parseFloat(this.y4.getText().toString());

        if (space == Space.space2D) {
            Line line1 = new Line(new Vector2D(x1, y1), new Vector2D(x2, y2));
            Line line2 = new Line(new Vector2D(x3, y3), new Vector2D(x4, y4));

            if (line1.isParallelTo(line2)) {
                String result = String.format("These lines are parallel.\nTheir distance: %f units", line1.distance(new Vector2D(x3, y3)));
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(result)
                        .setTitle("Result");
                builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else {

            }

        }
        else { // space == Space.space3D

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_EQ_FORM_TEXT, eqForm.getText().toString());
        outState.putString(STATE_EQ_FORM_TAG, eqForm.getTag().toString());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            eqForm.setText(savedInstanceState.getString(STATE_EQ_FORM_TEXT));
            eqForm.setTag(savedInstanceState.getString(STATE_EQ_FORM_TAG));
        }
    }
}