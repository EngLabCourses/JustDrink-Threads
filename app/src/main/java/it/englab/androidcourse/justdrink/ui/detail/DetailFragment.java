package it.englab.androidcourse.justdrink.ui.detail;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import it.englab.androidcourse.justdrink.R;
import it.englab.androidcourse.justdrink.model.Drink;
import it.englab.androidcourse.justdrink.utils.JsonUtil;

/**
 * Created by Peppe on 07/02/2017.
 */

public class DetailFragment extends Fragment {
    private static final String DRINK_ID = DetailFragment.class.getPackage() + ".DRINK_ID";

    private String drinkId;
    private TextView info, ingredients, instructions;
    private ImageView image;

    public static DetailFragment newInstance(String drinkId) {
        Bundle args = new Bundle();
        args.putString(DRINK_ID, drinkId);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drinkId = getArguments().getString(DRINK_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        info = (TextView) view.findViewById(R.id.detail_cocktail_info);
        ingredients = (TextView) view.findViewById(R.id.detail_cocktail_ingredients);
        instructions = (TextView) view.findViewById(R.id.detail_cocktail_instructions);
        image = (ImageView) view.findViewById(R.id.detail_cocktail_image);

        String json = JsonUtil.loadJSONFromAsset(getActivity(), drinkId);
        Drink mDrink = JsonUtil.fromJson(json, Drink.class);

        getActivity().setTitle(mDrink.getStrDrink());

        StringBuilder infoBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(mDrink.getStrCategory()))
            infoBuilder.append(mDrink.getStrCategory());
        if (!TextUtils.isEmpty(mDrink.getStrAlcoholic()))
            infoBuilder.append(", ").append(mDrink.getStrAlcoholic());

        info.setText(infoBuilder.toString());

        Picasso.with(image.getContext()).load(mDrink.getStrDrinkThumb()).into(image);

        instructions.setText(mDrink.getStrInstructions());

        ingredients.setText(getIngredientsString(mDrink));
    }

    private String getIngredientsString(Drink mDrink) {
        StringBuilder ingredientsBuilder = new StringBuilder();
        if (TextUtils.isEmpty(mDrink.getStrIngredient1()))
            return ingredientsBuilder.toString();
        ingredientsBuilder.append(mDrink.getStrIngredient1()).append(" (").append(mDrink.getStrMeasure1()).append(")");

        if (TextUtils.isEmpty(mDrink.getStrIngredient2()))
            return ingredientsBuilder.toString();
        ingredientsBuilder.append(", ").append(mDrink.getStrIngredient2()).append(" (").append(mDrink.getStrMeasure2()).append(")");

        if (TextUtils.isEmpty(mDrink.getStrIngredient3()))
            return ingredientsBuilder.toString();
        ingredientsBuilder.append(", ").append(mDrink.getStrIngredient3()).append(" (").append(mDrink.getStrMeasure3()).append(")");

        if (TextUtils.isEmpty(mDrink.getStrIngredient4()))
            return ingredientsBuilder.toString();
        ingredientsBuilder.append(", ").append(mDrink.getStrIngredient4()).append(" (").append(mDrink.getStrMeasure4()).append(")");

        if (TextUtils.isEmpty(mDrink.getStrIngredient5()))
            return ingredientsBuilder.toString();
        ingredientsBuilder.append(", ").append(mDrink.getStrIngredient5()).append(" (").append(mDrink.getStrMeasure5()).append(")");

        if (TextUtils.isEmpty(mDrink.getStrIngredient6()))
            return ingredientsBuilder.toString();
        ingredientsBuilder.append(", ").append(mDrink.getStrIngredient6()).append(" (").append(mDrink.getStrMeasure6()).append(")");

        if (TextUtils.isEmpty(mDrink.getStrIngredient7()))
            return ingredientsBuilder.toString();
        ingredientsBuilder.append(", ").append(mDrink.getStrIngredient7()).append(" (").append(mDrink.getStrMeasure7()).append(")");

        if (TextUtils.isEmpty(mDrink.getStrIngredient8()))
            return ingredientsBuilder.toString();
        ingredientsBuilder.append(", ").append(mDrink.getStrIngredient8()).append(" (").append(mDrink.getStrMeasure8()).append(")");

        if (TextUtils.isEmpty(mDrink.getStrIngredient9()))
            return ingredientsBuilder.toString();
        ingredientsBuilder.append(", ").append(mDrink.getStrIngredient9()).append(" (").append(mDrink.getStrMeasure9()).append(")");

        if (TextUtils.isEmpty(mDrink.getStrIngredient10()))
            return ingredientsBuilder.toString();
        ingredientsBuilder.append(", ").append(mDrink.getStrIngredient10()).append(" (").append(mDrink.getStrMeasure10()).append(")");

        if (TextUtils.isEmpty(mDrink.getStrIngredient11()))
            return ingredientsBuilder.toString();
        ingredientsBuilder.append(", ").append(mDrink.getStrIngredient11()).append(" (").append(mDrink.getStrMeasure11()).append(")");

        if (TextUtils.isEmpty(mDrink.getStrIngredient12()))
            return ingredientsBuilder.toString();
        ingredientsBuilder.append(", ").append(mDrink.getStrIngredient12()).append(" (").append(mDrink.getStrMeasure12()).append(")");

        if (TextUtils.isEmpty(mDrink.getStrIngredient13()))
            return ingredientsBuilder.toString();
        ingredientsBuilder.append(", ").append(mDrink.getStrIngredient13()).append(" (").append(mDrink.getStrMeasure13()).append(")");

        if (TextUtils.isEmpty(mDrink.getStrIngredient14()))
            return ingredientsBuilder.toString();
        ingredientsBuilder.append(", ").append(mDrink.getStrIngredient14()).append(" (").append(mDrink.getStrMeasure14()).append(")");

        if (TextUtils.isEmpty(mDrink.getStrIngredient15()))
            return ingredientsBuilder.toString();
        ingredientsBuilder.append(", ").append(mDrink.getStrIngredient15()).append(" (").append(mDrink.getStrMeasure15()).append(")");

        return ingredientsBuilder.toString();
    }
}
