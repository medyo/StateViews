package sakout.mehdi.StateViews;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.Arrays;
import java.util.List;

import sakout.mehdi.StateViews.library.R;

/**
 * Created by medyo on 7/24/17.
 * This view plays as a wrapper for our different stats: Loading, Error and Normal
 */
public class StateView extends ViewFlipper {

    private final String TAG = "PageStatusView";
    /**
     * Data States
     */

    StateViewsBuilder mBuilder;
    LayoutInflater mInflater;
    OnClickListener mOnClickListener;

    public StateView(Context context) {
        super(context);
        setupView(null);
    }

    public StateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(attrs);
    }

    private void setupView(AttributeSet attrs) {

        // TODO: Add support of dynamic styles

        if (isInEditMode()) {
            return;
        }

        handleStates();
    }

    private void handleStates() {

        mInflater = LayoutInflater.from(getContext());

        /**
         * Append new state values
         */

        addView(getViewByLayoutId(R.layout.state_loading, STATES.LOADING.name()), 0);

        if (hasPrivateConfiguration()) {
            applyCustomStyle(mBuilder, mBuilder.getStates());
        } else if (StateViewsBuilder.getInstance() != null) {
            applyCustomStyle(StateViewsBuilder.getInstance(), StateViewsBuilder.getInstance().getStates());
        }

        hideStates();

    }

    public void addCustomState(String tag, String title, String description, Drawable icon, String buttonTitle) {

        StateViewsBuilder builder = mBuilder;
        if (builder == null) {
            builder = StateViewsBuilder.getInstance();
        }

        LinearLayout view = (LinearLayout) mInflater.inflate(R.layout.state_custom, null, false);

        setTitleValue(view, title, builder.getTextColor(), builder.getFont());
        setDescriptionValue(view, description, builder.getTextColor(), builder.getFont());
        setIcon(view, icon, builder.getIconColor(), builder.getIconSize());
        setButtonStyle(view, buttonTitle, builder.getFont(), builder.getButtonBackgroundColor(), builder.getButtonTextColor());

        view.setTag(tag);
        addView(view, getChildCount());
    }

    private void applyCustomStyle(StateViewsBuilder builder, List<StateModel> states) {

        /*
         * Load Views
         */

        for (int i = 0; i < states.size(); i++) {
            StateModel state = states.get(i);
            int position = i + 1;
            if (state.getCustom()) {

                if (state.getView() != null) {
                    View view = state.getView();
                    view.setTag(state.getTag());
                    addView(view, position);

                } else if (state.getLayoutId() != null) {
                    View view = mInflater.inflate(state.getLayoutId(), null, false);
                    view.setTag(state.getTag());
                    addView(view, position);
                }
            } else {
                LinearLayout view = (LinearLayout) mInflater.inflate(R.layout.state_custom, null, false);

                setTitleValue(view, state.getTitle(), builder.getTextColor(), builder.getFont());
                setDescriptionValue(view, state.getDescription(), builder.getTextColor(), builder.getFont());
                setIcon(view, state.getIcon(), builder.getIconColor(), builder.getIconSize());
                setButtonStyle(view, state.getButtonTitle(), builder.getFont(), builder.getButtonBackgroundColor(), builder.getButtonTextColor());

                view.setTag(state.getTag());
                addView(view, position);
            }
        }

    }

    public void applyGravity(Integer gravity) {

        StateViewsBuilder builder = mBuilder;

        if (StateViewsBuilder.getInstance() != null) {
            builder = StateViewsBuilder.getInstance();
        }

        if (builder == null) {
            return;
        }

        List<StateModel> states = builder.getStates();
        String[] inlineStates = new String[states.size()];

        for (int i = 0; i < states.size(); i++) {
            inlineStates[i] = states.get(i).getTag();
        }

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);

            if (view.getTag() != null && Arrays.asList(inlineStates).contains(view.getTag().toString())) {

                if (view instanceof LinearLayout) {
                    ((LinearLayout) view).setGravity(gravity);
                }

            }
        }


    }

    private Boolean hasPrivateConfiguration() {
        return mBuilder != null;
    }

    public void setPrivateConfiguration(StateViewsBuilder builder) {
        this.mBuilder = builder;
    }

    /**
     * Display the show progress View
     */
    @NonNull
    public void displayLoadingState() {
        setDisplayedChild(0);
    }

    @NonNull
    public void displayState(String tagName) {

        Boolean found = false;
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);

            if (view.getTag() != null && view.getTag().toString().equalsIgnoreCase(tagName)) {
                setDisplayedChild(i);
                found = true;
            }
        }

        if (!found) {
            Log.e(TAG, "Tag name Incorrect or not found");
        }
    }

    @NonNull
    public void hideStates() {

        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i).getTag() == null) {
                setDisplayedChild(i);
            }

        }
    }

    /**
     * Return a view based on layout id
     *
     * @param layout Layout Id
     * @param tag    Layout Tag
     * @return View
     */
    private View getViewByLayoutId(int layout, String tag) {

        View view = mInflater.inflate(layout, null, false);
        view.setTag(tag);
        view.setVisibility(View.GONE);

        return view;
    }

    private void setTitleValue(View view, String value, Integer color, Typeface typeface) {
        TextView mTextView = view.findViewById(R.id.state_title);
        mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        setTextColor(mTextView, color);
        setTextFont(mTextView, typeface);
        if (!TextUtils.isEmpty(value)) {
            mTextView.setText(value);
            mTextView.setVisibility(View.VISIBLE);
        } else {
            mTextView.setVisibility(View.GONE);
        }
    }

    private void setTextFont(TextView mTextView, Typeface typeface) {
        if (typeface != null) {
            mTextView.setTypeface(typeface);
        }
    }

    private void setTextColor(TextView textView, Integer color) {

        if (color != null) {
            textView.setTextColor(color);
        }
    }

    private void setDescriptionValue(View view, String value, Integer color, Typeface typeface) {
        TextView mTextView = view.findViewById(R.id.state_description);
        mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        setTextColor(mTextView, color);
        setTextFont(mTextView, typeface);
        if (!TextUtils.isEmpty(value)) {
            mTextView.setText(value);
            mTextView.setVisibility(View.VISIBLE);
        } else {
            mTextView.setVisibility(View.GONE);
        }
    }

    public void setOnStateButtonClicked(OnClickListener clickListener) {
        mOnClickListener = clickListener;

        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i).getTag() != null) {

                View view = getChildAt(i);

                if (view.findViewById(R.id.state_button) != null) {
                    Button button = view.findViewById(R.id.state_button);
                    button.setOnClickListener(mOnClickListener);
                }
            }


        }
    }

    private void setButtonStyle(View view, String buttonTitle, Typeface typeface, Integer buttonBackgroundColor, Integer buttonTextColor) {
        Button button = view.findViewById(R.id.state_button);
        setButtonFont(button, typeface);

        if (buttonBackgroundColor != null) {
            button.getBackground().setColorFilter(buttonBackgroundColor, PorterDuff.Mode.MULTIPLY);
        }

        if (buttonTextColor != null) {
            button.setTextColor(buttonTextColor);
        }

        if (!TextUtils.isEmpty(buttonTitle)) {
            button.setText(buttonTitle);
            button.setVisibility(VISIBLE);
            button.setOnClickListener(mOnClickListener);
        } else {
            button.setVisibility(GONE);
        }
    }

    private void setButtonFont(Button button, Typeface typeface) {
        if (typeface != null) {
            button.setTypeface(typeface);
        }
    }

    private void setIcon(View view, Drawable icon, Integer color, int iconSize) {
        ImageView mImageView = view.findViewById(R.id.state_icon);

        if (color != null) {
            mImageView.setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            TypedValue typedValue = new TypedValue();
            getContext().getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
            mImageView.setColorFilter(typedValue.data, android.graphics.PorterDuff.Mode.SRC_IN);
        }

        if (iconSize > 0) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(iconSize, iconSize);
            mImageView.setLayoutParams(params);
        }

        if (icon != null) {
            mImageView.setImageDrawable(icon);
            mImageView.setVisibility(View.VISIBLE);
        } else {
            mImageView.setVisibility(View.GONE);
        }
    }

    public enum STATES {
        LOADING
    }


}
