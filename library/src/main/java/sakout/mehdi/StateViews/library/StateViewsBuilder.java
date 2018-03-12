package sakout.mehdi.StateViews.library;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by medyo on 11/14/17.
 */

public class StateViewsBuilder {

    private final String TAG = "StateViewsBuilder";

    Context mContext;

    List<StateModel> mStates;

    Integer mTextColor;

    Integer mIconSize;

    Integer mIconColor;

    Integer mButtonBackgroundColor;

    Integer mButtonTextColor;

    Integer mTitleColor;

    Integer mDescriptionColor;

    Typeface mFont;

    private static StateViewsBuilder mInstance;

    private StateViewsBuilder(Context context) {
        mStates = new ArrayList<>();
        this.mContext = context;
    }

    public static StateViewsBuilder init (Context context){
        if (mInstance == null) {
            mInstance = new StateViewsBuilder(context);
        }

        return mInstance;
    }

    public static StateViewsBuilder getInstance (){
        return mInstance;
    }

    public Integer getTextColor (){
        return mTextColor;
    }

    public Context getContext (){
        return mContext;
    }

    public List<StateModel> getStates() {
        return mStates;
    }

    public StateViewsBuilder setTextColor (int color){
        this.mTextColor = color;
        return this;
    }

    public StateViewsBuilder setIconColor(int iconColor) {
        this.mIconColor = iconColor;
        return this;
    }

    public Integer getIconColor() {
        return this.mIconColor;
    }


    public StateViewsBuilder setIconSize(int iconSize) {
        this.mIconSize = iconSize;
        return this;
    }

    public Integer getIconSize() {
        return this.mIconSize;
    }

    /*
    public StateViewsBuilder addState (String tagName, View view){

        StateModel state = new StateModel();
        state.setView(view);
        state.setTag(tagName);
        state.setCustom(true);

        mStates.add(state);
        return this;
    }
*/
    public StateViewsBuilder addState (String tagName, int layout){

        StateModel state = new StateModel();
        state.setLayoutId(layout);
        state.setTag(tagName);
        state.setCustom(true);

        mStates.add(state);

        return this;
    }

    public StateViewsBuilder addState(String tag, String title, String description, Drawable icon, String buttonTitle, View.OnClickListener clickListener) {

        StateModel state = new StateModel();
        state.setTitle(title);
        state.setDescription(description);
        state.setButtonTitle(buttonTitle);
        state.setClickListener(clickListener);
        state.setIcon(icon);
        state.setTag(tag);
        state.setCustom(false);

        mStates.add(state);

        return this;
    }

    public StateViewsBuilder addState(String tag, String title, String description, Drawable icon, String buttonTitle) {
        addState(tag, title, description, icon, buttonTitle, null);
        return this;
    }

    public StateViewsBuilder setFontFace(Typeface font) {
        this.mFont = font;
        return this;
    }

    public Typeface getFont() {
        return mFont;
    }

    public StateViewsBuilder setButtonBackgroundColor(int buttonBackgroundColor) {
        this.mButtonBackgroundColor = buttonBackgroundColor;
        return this;
    }

    public StateViewsBuilder setButtonTextColor(int textColor) {
        this.mButtonTextColor = textColor;
        return this;
    }

    public Integer getButtonBackgroundColor() {
        return this.mButtonBackgroundColor;
    }

    public Integer getButtonTextColor() {
        return this.mButtonTextColor;
    }

}
